package net.ddns.twicusstumble.twicusstrain.proxy;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        TwicussTrain.logger.info("ClientProxy.preInit");
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        TwicussTrain.logger.info("ClientProxy.registerModels");

        ItemInit.ITEMS.forEach(f -> f.registerModel(event));
    }
}
