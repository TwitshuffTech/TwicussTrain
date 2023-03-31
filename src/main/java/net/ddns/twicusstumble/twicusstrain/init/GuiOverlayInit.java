package net.ddns.twicusstumble.twicusstrain.init;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.client.gui.GuiOverlayBase;
import net.ddns.twicusstumble.twicusstrain.client.gui.GuiThrottleBar;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class GuiOverlayInit {
    public static final List<GuiOverlayBase> OVERLAYS = new ArrayList<>();

    public static final GuiThrottleBar THROTTLE_BAR = new GuiThrottleBar();
}
