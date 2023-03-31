package net.ddns.twicusstumble.twicusstrain.client.gui;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.event.IGuiOverlayRenderEvent;
import net.ddns.twicusstumble.twicusstrain.init.GuiOverlayInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class GuiOverlayBase extends Gui implements IGuiOverlayRenderEvent {
    public GuiOverlayBase() {
        GuiOverlayInit.OVERLAYS.add(this);
    }

    public void render(RenderGameOverlayEvent event) {

    }
}
