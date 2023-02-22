package net.ddns.twicusstumble.twicusstrain.event;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;

public interface IItemRegisterEvent {
    void registerItem(RegistryEvent.Register<Item> event);

    void registerModel(ModelRegistryEvent event);
}
