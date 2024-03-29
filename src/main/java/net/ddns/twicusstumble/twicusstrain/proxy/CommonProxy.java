package net.ddns.twicusstumble.twicusstrain.proxy;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.entity.EntityCargoTrain;
import net.ddns.twicusstumble.twicusstrain.entity.EntityTrain;
import net.ddns.twicusstumble.twicusstrain.init.BlockInit;
import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = TwicussTrain.MOD_ID)
public abstract class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        TwicussTrain.logger.info("CommonProxy.preInit");
    }

    public void init(FMLInitializationEvent event) {
        TwicussTrain.logger.info("CommonProxy.init");
    }

    public void postInit(FMLPostInitializationEvent event) {
        TwicussTrain.logger.info("CommonProxy.postInit");
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        TwicussTrain.logger.info("CommonProxy.registerItems");
        ItemInit.ITEMS.forEach(f -> f.registerItem(event));
        ItemInit.ITEM_BLOCKS.forEach(f -> event.getRegistry().register(f));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        BlockInit.BLOCKS.forEach(f -> f.registerBlock(event));
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        TwicussTrain.logger.info("CommonProxy.registerEntities");

        EntityRegistry.registerModEntity(new ResourceLocation(TwicussTrain.MOD_ID, "train"), EntityTrain.class, "train", 0, TwicussTrain.INSTANCE, 50, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(TwicussTrain.MOD_ID, "cargo_train"), EntityCargoTrain.class, "cargo_train", 1, TwicussTrain.INSTANCE, 50, 1, true);
    }
}
