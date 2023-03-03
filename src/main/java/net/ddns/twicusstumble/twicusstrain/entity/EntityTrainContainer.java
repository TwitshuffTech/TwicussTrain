package net.ddns.twicusstumble.twicusstrain.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.ILootContainer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class EntityTrainContainer extends EntityTrainBase implements ILockableContainer, ILootContainer {
    private NonNullList<ItemStack> containerItems = NonNullList.<ItemStack>withSize(36, ItemStack.EMPTY);
    public boolean dropContentsWhenDead = true;
    private ResourceLocation lootTable;
    private long lootTableSeed;

    public EntityTrainContainer(World worldIn)
    {
        super(worldIn);
    }

    public EntityTrainContainer(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @Override
    public void killMinecart(DamageSource source) {
        super.killMinecart(source);

        if (this.world.getGameRules().getBoolean("doEntityDrops")) {
            InventoryHelper.dropInventoryItems(this.world, this, this);
        }
    }

    public boolean isEmpty() {
        for (ItemStack itemstack : this.containerItems) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public ItemStack getStackInSlot(int index) {
        this.addLoot((EntityPlayer)null);
        return this.containerItems.get(index);
    }

    public ItemStack decrStackSize(int index, int count) {
        this.addLoot((EntityPlayer)null);
        return ItemStackHelper.getAndSplit(this.containerItems, index, count);
    }

    public ItemStack removeStackFromSlot(int index) {
        this.addLoot((EntityPlayer)null);
        ItemStack itemstack = this.containerItems.get(index);

        if (itemstack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            this.containerItems.set(index, ItemStack.EMPTY);
            return itemstack;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack) {
        this.addLoot((EntityPlayer)null);
        this.containerItems.set(index, stack);

        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    public void markDirty() {}

    public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.isDead) {
            return false;
        } else {
            return player.getDistanceSq(this) <= 64.0D;
        }
    }

    public void openInventory(EntityPlayer player) {}

    public void closeInventory(EntityPlayer player) {}

    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Nullable
    @Override
    public Entity changeDimension(int dimensionIn, net.minecraftforge.common.util.ITeleporter teleporter) {
        this.dropContentsWhenDead = false;
        return super.changeDimension(dimensionIn, teleporter);
    }

    @Override
    public void setDead() {
        if (this.dropContentsWhenDead) {
            InventoryHelper.dropInventoryItems(this.world, this, this);
        }

        super.setDead();
    }

    @Override
    public void setDropItemsWhenDead(boolean dropWhenDead)
    {
        this.dropContentsWhenDead = dropWhenDead;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        if (this.lootTable != null) {
            compound.setString("LootTable", this.lootTable.toString());

            if (this.lootTableSeed != 0L) {
                compound.setLong("LootTableSeed", this.lootTableSeed);
            }
        } else {
            ItemStackHelper.saveAllItems(compound, this.containerItems);
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.containerItems = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        if (compound.hasKey("LootTable", 8)) {
            this.lootTable = new ResourceLocation(compound.getString("LootTable"));
            this.lootTableSeed = compound.getLong("LootTableSeed");
        } else {
            ItemStackHelper.loadAllItems(compound, this.containerItems);
        }
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (super.processInitialInteract(player, hand)) return true;

        if (!this.world.isRemote) {
            if (!player.isSneaking()) {
                player.displayGUIChest(this);

                return true;
            }
        }

        return false;
    }

    public int getField(int id) {
        return 0;
    }

    public void setField(int id, int value) {}

    public int getFieldCount() {
        return 0;
    }

    public boolean isLocked() {
        return false;
    }

    public void setLockCode(LockCode code) {}

    public LockCode getLockCode() {
        return LockCode.EMPTY_CODE;
    }

    public void addLoot(@Nullable EntityPlayer player) {
        if (this.lootTable != null) {
            LootTable loottable = this.world.getLootTableManager().getLootTableFromLocation(this.lootTable);
            this.lootTable = null;
            Random random;

            if (this.lootTableSeed == 0L) {
                random = new Random();
            } else {
                random = new Random(this.lootTableSeed);
            }

            LootContext.Builder lootcontext$builder = new LootContext.Builder((WorldServer)this.world).withLootedEntity(this); // Forge: add looted entity to LootContext

            if (player != null) {
                lootcontext$builder.withLuck(player.getLuck()).withPlayer(player); // Forge: add player to LootContext
            }

            loottable.fillInventory(this, random, lootcontext$builder.build());
        }
    }

    public net.minecraftforge.items.IItemHandler itemHandler = new net.minecraftforge.items.wrapper.InvWrapper(this);

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable net.minecraft.util.EnumFacing facing) {
        if (capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) itemHandler;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, @Nullable net.minecraft.util.EnumFacing facing) {
        return capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    public void clear() {
        this.addLoot((EntityPlayer)null);
        this.containerItems.clear();
    }

    public void setLootTable(ResourceLocation lootTableIn, long lootTableSeedIn) {
        this.lootTable = lootTableIn;
        this.lootTableSeed = lootTableSeedIn;
    }

    public ResourceLocation getLootTable() {
        return this.lootTable;
    }
}
