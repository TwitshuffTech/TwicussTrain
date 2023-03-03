package net.ddns.twicusstumble.twicusstrain.proxy;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.entity.EntityTrain;
import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.ddns.twicusstumble.twicusstrain.renderer.RenderTrain;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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

        RenderingRegistry.registerEntityRenderingHandler(EntityTrain.class, new IRenderFactory<EntityTrain>() {
            @Override
            public Render<? super EntityTrain> createRenderFor(RenderManager manager) {
                return new RenderTrain(manager);
            }
        });
    }
}
