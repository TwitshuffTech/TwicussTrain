package net.ddns.twicusstumble.twicusstrain.event;

import net.minecraft.block.Block;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;

public interface IBlockRegisterEvent {
    void registerBlock(RegistryEvent.Register<Block> event);

    void registerModel(ModelRegistryEvent event);
}
