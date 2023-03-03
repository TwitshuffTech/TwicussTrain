package net.ddns.twicusstumble.twicusstrain.tab;

import net.ddns.twicusstumble.twicusstrain.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TwicussTrainTab extends CreativeTabs {
    public TwicussTrainTab() {
        super("twicusstrain_tab");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemInit.ITEM_TRAIN);
    }
}
