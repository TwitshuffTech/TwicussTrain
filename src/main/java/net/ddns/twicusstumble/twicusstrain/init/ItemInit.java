package net.ddns.twicusstumble.twicusstrain.init;

import net.ddns.twicusstumble.twicusstrain.event.IItemRegisterEvent;
import net.ddns.twicusstumble.twicusstrain.item.ItemCargoTrain;
import net.ddns.twicusstumble.twicusstrain.item.ItemTrain;
import net.ddns.twicusstumble.twicusstrain.item.ItemWrench;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<IItemRegisterEvent> ITEMS = new ArrayList<IItemRegisterEvent>();

    public static final Item ITEM_TRAIN = new ItemTrain("train");
    public static final Item ITEM_CARGO_TRAIN = new ItemCargoTrain("cargo_train");
    public static final Item ITEM_WRENCH = new ItemWrench("wrench");
}
