package net.ddns.twicusstumble.twicusstrain.entity;

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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityTrain extends EntityMinecart {
    public double throttle = 0;
    public double throttleSpeed = 0.1D;
    public double maxSpeed = 0.6D;
    public boolean isSelected = false;
    public List<EntityPlayer> players = new ArrayList<>();
    protected List<EntityTrain> connectedTrains = new ArrayList<EntityTrain>();
    protected double connectionDistance = 1.5D;
    protected boolean isPowerCar = false;
    private NBTTagList trainNBTTags = null;

    protected boolean canBePushed = false;

    private static final int[][][] MATRIX = new int[][][] {{{0, 0, -1}, {0, 0, 1}}, {{ -1, 0, 0}, {1, 0, 0}}, {{ -1, -1, 0}, {1, 0, 0}}, {{ -1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, { -1, 0, 0}}, {{0, 0, -1}, { -1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};

    public EntityTrain(World worldIn) {
        super(worldIn);
    }

    public EntityTrain(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (super.processInitialInteract(player, hand)) {
            return true;
        }

        if (player.isSneaking()) {
            if (!this.world.isRemote) {
                ItemStack itemStack = player.getHeldItem(hand);

                if (itemStack.getItem() instanceof ItemWrench) {
                    this.connectTrains(player);

                    return true;
                }
            }
            return false;
        } else {
            if (!this.world.isRemote) {
                player.startRiding(this);
            }

            return true;
        }
    }

    public boolean connectTrains(EntityPlayer player) {
        if (this.connectedTrains.size() >= 2) {
            return false;
        }

        double x = this.posX;
        double y = this.posY;
        double z = this.posZ;
        boolean flag = false;

        for (EntityTrain entityTrain : this.world.getEntitiesWithinAABB(EntityTrain.class, new AxisAlignedBB(x - 7.0D, y - 7.0D, z - 7.0D, x + 7.0D, y + 7.0D, z + 7.0D))) {
            if (entityTrain.isSelected && entityTrain.players.contains(player) && entityTrain.connectedTrains.size() < 2 && entityTrain != this) {
                this.connectedTrains.add(entityTrain);
                entityTrain.connectedTrains.add(this);

                entityTrain.isSelected = false;
                entityTrain.players.remove(player);

                if (this.connectedTrains.size() >= 2) {
                    return true;
                }

                flag = true;
            }
        }

        if (!flag) {
            this.isSelected = true;
            this.players.add(player);
        }

        return flag;
    }

    @Override
    public boolean canBePushed() {
        return this.canBePushed;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.world.isRemote) {
            this.reconnect();

            Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
            if (entity instanceof EntityLivingBase) {
                double input = (double) ((EntityLivingBase) entity).moveForward;
                this.changeThrottle(input);
            }

            this.updateConnection();
        }
    }

    public void reconnect() {
        if (this.trainNBTTags != null) {
            double x = this.posX;
            double y = this.posY;
            double z = this.posZ;

            for (EntityTrain entityTrain : this.world.getEntitiesWithinAABB(EntityTrain.class, new AxisAlignedBB(x - 2.0D, y - 2.0D, z - 2.0D, x + 2.0D, y + 2.0D, z + 2.0D))) {
                for (NBTBase uuid : trainNBTTags) {
                    if (entityTrain.getPersistentID().toString().equals(uuid.toString().replace("\"", ""))) {
                        this.connectedTrains.add(entityTrain);
                    }
                }
            }

            this.trainNBTTags = null;
        }
    }

    public void changeThrottle(double input) {
        if (input > 0) {
            this.throttle += this.throttleSpeed;
        } else if (input < 0) {
            this.throttle -= this.throttleSpeed;
        }

        this.throttle = MathHelper.clamp(this.throttle, -0.1, 1);
    }

    public void applyThrottle() {
        double squaredSpeed = this.motionX * this.motionX + this.motionZ * this.motionZ;
        double targetSpeed = this.maxSpeed * this.throttle;

        Entity entity = this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);

        if (squaredSpeed < 0.0001D) {
            if (this.isPowerCar) {
                this.isPowerCar = false;
            }
        }
        if (entity instanceof EntityLivingBase) {
            if (targetSpeed > 0 && squaredSpeed < 0.0001D && (double)((EntityLivingBase)entity).moveForward > 0.0D) {
                double x = -Math.sin((double)(entity.rotationYaw * 0.017453292F));
                double z = Math.cos((double)(entity.rotationYaw * 0.017453292F));

                this.motionX += x * 0.1D;
                this.motionZ += z * 0.1D;

                this.isPowerCar = true;
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

    public void updateConnection() {
        List<EntityTrain> removingCache = new ArrayList<EntityTrain>();
        double distanceCache = 0;

        for (int i = 0; i < this.connectedTrains.size(); i++) {
            EntityTrain train = this.connectedTrains.get(i);

            if (train.isDead) {
                removingCache.add(train);
                train.connectedTrains.remove(this);
            } else if (!this.isPowerCar) {
                double distance = this.getDistance(train);
                if (Math.abs(distance - this.connectionDistance) > distanceCache) {
                    distanceCache = distance;

                    double x = (this.posX - train.posX) / distance;
                    double y = (this.posY - train.posY) / distance;
                    double z = (this.posZ - train.posZ) / distance;

                    this.posX = train.posX + x * connectionDistance;
                    this.posY = train.posY + y * connectionDistance;
                    this.posZ = train.posZ + z * connectionDistance;
                }
            }
        }

        this.connectedTrains.removeAll(removingCache);
    }

    @Override
    public void moveMinecartOnRail(BlockPos pos)
    {
        double mX = this.motionX;
        double mZ = this.motionZ;

        if (this.isBeingRidden())
        {
            mX *= 0.75D;
            mZ *= 0.75D;
        }

        double max = this.maxSpeed;
        mX = MathHelper.clamp(mX, -max, max);
        mZ = MathHelper.clamp(mZ, -max, max);
        this.move(MoverType.SELF, mX, 0.0D, mZ);
    }

    @Override
    protected void moveAlongTrack(BlockPos pos, IBlockState state)
    {
        this.fallDistance = 0.0F;
        Vec3d vec3d = this.getPos(this.posX, this.posY, this.posZ);
        this.posY = (double)pos.getY();

        BlockRailBase blockrailbase = (BlockRailBase)state.getBlock();

        double slopeAdjustment = getSlopeAdjustment();
        BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = blockrailbase.getRailDirection(world, pos, state, this);

        switch (blockrailbase$enumraildirection)
        {
            case ASCENDING_EAST:
                this.motionX -= slopeAdjustment;
                ++this.posY;
                break;
            case ASCENDING_WEST:
                this.motionX += slopeAdjustment;
                ++this.posY;
                break;
            case ASCENDING_NORTH:
                this.motionZ += slopeAdjustment;
                ++this.posY;
                break;
            case ASCENDING_SOUTH:
                this.motionZ -= slopeAdjustment;
                ++this.posY;
        }

        int[][] aint = MATRIX[blockrailbase$enumraildirection.getMetadata()];
        double d1 = (double)(aint[1][0] - aint[0][0]);
        double d2 = (double)(aint[1][2] - aint[0][2]);
        double d3 = Math.sqrt(d1 * d1 + d2 * d2);
        double d4 = this.motionX * d1 + this.motionZ * d2;

        if (d4 < 0.0D)
        {
            d1 = -d1;
            d2 = -d2;
        }

        double d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

        if (d5 > 2.0D)
        {
            d5 = 2.0D;
        }

        this.motionX = d5 * d1 / d3;
        this.motionZ = d5 * d2 / d3;

        this.applyThrottle();

        double d18 = (double)pos.getX() + 0.5D + (double)aint[0][0] * 0.5D;
        double d19 = (double)pos.getZ() + 0.5D + (double)aint[0][2] * 0.5D;
        double d20 = (double)pos.getX() + 0.5D + (double)aint[1][0] * 0.5D;
        double d21 = (double)pos.getZ() + 0.5D + (double)aint[1][2] * 0.5D;
        d1 = d20 - d18;
        d2 = d21 - d19;
        double d10;

        if (d1 == 0.0D)
        {
            this.posX = (double)pos.getX() + 0.5D;
            d10 = this.posZ - (double)pos.getZ();
        }
        else if (d2 == 0.0D)
        {
            this.posZ = (double)pos.getZ() + 0.5D;
            d10 = this.posX - (double)pos.getX();
        }
        else
        {
            double d11 = this.posX - d18;
            double d12 = this.posZ - d19;
            d10 = (d11 * d1 + d12 * d2) * 2.0D;
        }

        this.posX = d18 + d1 * d10;
        this.posZ = d19 + d2 * d10;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.moveMinecartOnRail(pos);

        if (aint[0][1] != 0 && MathHelper.floor(this.posX) - pos.getX() == aint[0][0] && MathHelper.floor(this.posZ) - pos.getZ() == aint[0][2])
        {
            this.setPosition(this.posX, this.posY + (double)aint[0][1], this.posZ);
        }
        else if (aint[1][1] != 0 && MathHelper.floor(this.posX) - pos.getX() == aint[1][0] && MathHelper.floor(this.posZ) - pos.getZ() == aint[1][2])
        {
            this.setPosition(this.posX, this.posY + (double)aint[1][1], this.posZ);
        }

        this.applyDrag();
        Vec3d vec3d1 = this.getPos(this.posX, this.posY, this.posZ);

        if (vec3d1 != null && vec3d != null)
        {
            double d14 = (vec3d.y - vec3d1.y) * 0.05D;
            d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

            if (d5 > 0.0D)
            {
                this.motionX = this.motionX / d5 * (d5 + d14);
                this.motionZ = this.motionZ / d5 * (d5 + d14);
            }

            this.setPosition(this.posX, vec3d1.y, this.posZ);
        }

        int j = MathHelper.floor(this.posX);
        int i = MathHelper.floor(this.posZ);

        if (j != pos.getX() || i != pos.getZ())
        {
            d5 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.motionX = d5 * (double)(j - pos.getX());
            this.motionZ = d5 * (double)(i - pos.getZ());
        }


        if(shouldDoRailFunctions())
        {
            ((BlockRailBase)state.getBlock()).onMinecartPass(world, this, pos);
        }
    }

    @Override
    public Type getType() {
        return Type.RIDEABLE;
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

    @Override
    public void killMinecart(DamageSource source)
    {
        this.setDead();

        if (this.world.getGameRules().getBoolean("doEntityDrops"))
        {
            ItemStack itemstack = new ItemStack(ItemInit.ITEM_TRAIN, 1);

            if (this.hasCustomName())
            {
                itemstack.setStackDisplayName(this.getCustomNameTag());
            }

            this.entityDropItem(itemstack, 0.0F);
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.getBoolean("Connected")) {
            this.trainNBTTags = (NBTTagList) compound.getTag("ConnectedTrains");
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        if (this.connectedTrains.size() > 0) {
            compound.setBoolean("Connected", true);

            NBTTagList trains = new NBTTagList();
            for (EntityTrain train : this.connectedTrains) {
                trains.appendTag(new NBTTagString(train.getPersistentID().toString()));
            }
            compound.setTag("ConnectedTrains", trains);
        } else {
            compound.setBoolean("Connected", false);
        }
    }
}
