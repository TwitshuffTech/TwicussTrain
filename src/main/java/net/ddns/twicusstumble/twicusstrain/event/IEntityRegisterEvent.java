package net.ddns.twicusstumble.twicusstrain.event;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

public interface IEntityRegisterEvent {
    void registerEntity(RegistryEvent.Register<EntityEntry> event);

    void registerModel(ModelRegistryEvent event);
}
