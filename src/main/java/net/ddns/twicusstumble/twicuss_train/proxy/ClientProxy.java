package net.ddns.twicusstumble.twicuss_train.proxy;

import net.ddns.twicusstumble.twicuss_train.TwicussTrain;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        TwicussTrain.logger.info("ClientProxy.preInit");
    }
}
