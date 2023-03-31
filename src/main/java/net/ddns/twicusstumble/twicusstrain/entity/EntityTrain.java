package net.ddns.twicusstumble.twicusstrain.entity;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.ddns.twicusstumble.twicusstrain.item.ItemWrench;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import scala.tools.nsc.doc.model.Trait;

import java.util.ArrayList;
import java.util.List;

public class EntityTrain extends EntityTrainBase {
    private static final DataParameter<Float> throttle = EntityDataManager.createKey(EntityTrain.class, DataSerializers.FLOAT);

    protected float throttleSpeed = 0.1F;

    private float maxThrottle = 1.0F;
    private float minThrottle = -0.2F;

    public EntityTrain(World worldIn) {
        super(worldIn);
    }

    public EntityTrain(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataManager.register(throttle, 0.0F);
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (super.processInitialInteract(player, hand)) {
            return true;
        };

        if (!player.isSneaking()) {
            if (!this.world.isRemote) {
                player.startRiding(this);

                return true;
            }
        }
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.world.isRemote) {
            Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
            if (entity instanceof EntityLivingBase) {
                double input = (double) ((EntityLivingBase) entity).moveForward;

                this.changeThrottle(input);
            }

            this.applyThrottle();
        }
    }

    public float getThrottle() {
        return this.dataManager.get(throttle);
    }

    public float getMaxThrottle() {
        return maxThrottle;
    }

    public float getMinThrottle() {
        return minThrottle;
    }

    public void changeThrottle(double input) {
        float throttleValue = this.dataManager.get(throttle);
        if (input > 0) {
            throttleValue += this.throttleSpeed;
        } else if (input < 0) {
            throttleValue -= this.throttleSpeed;
        }

        throttleValue = MathHelper.clamp(throttleValue, minThrottle, maxThrottle);
        this.dataManager.set(throttle, throttleValue);
    }

    public void applyThrottle() {
        double squaredSpeed = this.motionX * this.motionX + this.motionZ * this.motionZ;
        double targetSpeed = this.maxSpeed * this.dataManager.get(throttle);

        Entity entity = this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);

        if (entity instanceof EntityLivingBase) {
            if (targetSpeed > 0 && squaredSpeed < 0.0001D && (double)((EntityLivingBase)entity).moveForward > 0.0D) {
                double x = -Math.sin((double)(entity.rotationYaw * 0.017453292F));
                double z = Math.cos((double)(entity.rotationYaw * 0.017453292F));

                this.motionX += x * 0.1D;
                this.motionZ += z * 0.1D;

                resetPowerCar();
                this.isPowerCar(true);
            } else {
                if (targetSpeed * Math.abs(targetSpeed) > squaredSpeed) {
                    this.motionX *= 1.1D;
                    this.motionZ *= 1.1D;
                } else if (targetSpeed * Math.abs(targetSpeed) < squaredSpeed) {
                    this.motionX *= 0.9D;
                    this.motionZ *= 0.9D;
                }
            }
        }
    }

    @Override
    public double getMountedYOffset()
    {
        return (double)this.height * 0.75;
    }

    @Override
    public ItemStack getCartItem() {
        return new ItemStack(ItemInit.ITEM_TRAIN);
    }
}
