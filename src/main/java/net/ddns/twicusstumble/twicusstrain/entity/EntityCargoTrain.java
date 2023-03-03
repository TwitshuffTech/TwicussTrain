package net.ddns.twicusstumble.twicusstrain.entity;

import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityCargoTrain extends EntityTrainContainer {

    public EntityCargoTrain(World worldIn) {
        super(worldIn);
    }

    public EntityCargoTrain(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public void killMinecart(DamageSource source) {
        super.killMinecart(source);

        if (this.world.getGameRules().getBoolean("doEntityDrops")) {
            this.dropItemWithOffset(Item.getItemFromBlock(Blocks.CHEST), 1, 0.0F);
        }
    }

    public int getSizeInventory() {
        return 27;
    }

    public int getDefualtDisplayTileOffset() {
        return 8;
    }

    public String getGuiID() {
        return "minecraft:chest";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        this.addLoot(playerIn);
        return new ContainerChest(playerInventory, this, playerIn);
    }

    @Override
    public ItemStack getCartItem() {
        return new ItemStack(ItemInit.ITEM_CARGO_TRAIN);
    }
}
