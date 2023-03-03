package net.ddns.twicusstumble.twicusstrain.item;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.entity.EntityCargoTrain;
import net.ddns.twicusstumble.twicusstrain.entity.EntityTrain;
import net.ddns.twicusstumble.twicusstrain.event.IItemRegisterEvent;
import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class ItemCargoTrain extends Item implements IItemRegisterEvent {
    private final String name;

    public ItemCargoTrain(String name) {
        super();
        this.name = name;
        this.setTranslationKey(this.name);
        this.setRegistryName(TwicussTrain.MOD_ID, this.name);
        this.setCreativeTab(TwicussTrain.twicussTrainTab);
        this.setMaxStackSize(1);

        ItemInit.ITEMS.add(this);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (!BlockRailBase.isRailBlock(iblockstate)) {
            return EnumActionResult.FAIL;
        } else {
            ItemStack itemstack = player.getHeldItem(hand);

            if (!worldIn.isRemote) {
                BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = iblockstate.getBlock() instanceof BlockRailBase ? ((BlockRailBase)iblockstate.getBlock()).getRailDirection(worldIn, pos, iblockstate, null) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
                double d0 = 0.0D;

                if (blockrailbase$enumraildirection.isAscending()) {
                    d0 = 0.5D;
                }

                EntityCargoTrain entityCargoTrain = new EntityCargoTrain(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.0625D + d0, (double)pos.getZ() + 0.5D);

                if (itemstack.hasDisplayName()) {
                    entityCargoTrain.setCustomNameTag(itemstack.getDisplayName());
                }

                worldIn.spawnEntity(entityCargoTrain);
            }

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
    }

    @Override
    public void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(this);
    }

    @Override
    public void registerModel(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(TwicussTrain.MOD_ID, this.name), "inventory"));
    }
}
