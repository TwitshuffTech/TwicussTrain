package net.ddns.twicusstumble.twicuss_train.proxy;

import net.ddns.twicusstumble.twicuss_train.TwicussTrain;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
}
