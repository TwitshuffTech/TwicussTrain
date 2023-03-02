package net.ddns.twicusstumble.twicusstrain.item;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.entity.EntityTrain;
import net.ddns.twicusstumble.twicusstrain.event.IItemRegisterEvent;
import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class ItemWrench extends Item implements IItemRegisterEvent {
    public EntityTrain cachedTrain;
    private final String name;

    public ItemWrench(String name) {
        super();
        this.name = name;
        this.setTranslationKey(this.name);
        this.setRegistryName(TwicussTrain.MOD_ID, this.name);
        this.setCreativeTab(TwicussTrain.twicussTrainTab);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(this);
    }

    @Override
    public void registerModel(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(TwicussTrain.MOD_ID, this.name), "inventory"));
    }
}
