package net.ddns.twicusstumble.twicusstrain.entity;

import net.ddns.twicusstumble.twicusstrain.event.IEntityRegisterEvent;
import net.ddns.twicusstumble.twicusstrain.renderer.RenderTrain;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityTrain extends EntityMinecart implements IEntityRegisterEvent {

    public EntityTrain(World worldIn) {
        super(worldIn);
    }

    @Override
    public EntityMinecart.Type getType()
    {
        return EntityMinecart.Type.RIDEABLE;
    }

    @Override
    public void registerEntity(RegistryEvent.Register<EntityEntry> event) {
        EntityRegistry.registerModEntity(new ResourceLocation(""), EntityTrain.class, "train", 0, this, 50, 1, true);
    }

    @Override
    public void registerModel(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityTrain.class, new IRenderFactory<EntityTrain>() {
            @Override
            public Render<? super EntityTrain> createRenderFor(RenderManager manager) {
                return new RenderTrain(manager);
            }
        });
    }
}
