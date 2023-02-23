package net.ddns.twicusstumble.twicusstrain.proxy;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.init.EntityInit;
import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

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
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        TwicussTrain.logger.info("CommonProxy.registerEntities");
        EntityInit.ENTITIES.forEach(f -> f.registerEntity(event));
    }
}
