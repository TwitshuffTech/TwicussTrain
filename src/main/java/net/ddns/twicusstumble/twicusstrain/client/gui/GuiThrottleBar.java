package net.ddns.twicusstumble.twicusstrain.client.gui;

import net.ddns.twicusstumble.twicusstrain.TwicussTrain;
import net.ddns.twicusstumble.twicusstrain.entity.EntityTrain;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.HashMap;
import java.util.Map;

public class GuiThrottleBar extends GuiOverlayBase {
    private final ResourceLocation bar = new ResourceLocation(TwicussTrain.MOD_ID, "textures/gui/bar.png");
    private final ResourceLocation throttleBar = new ResourceLocation(TwicussTrain.MOD_ID, "textures/gui/throttle.png");

    private int x = 0;
    private int y = 0;

    public GuiThrottleBar() {
        super();
    }

    @Override
    public void render(RenderGameOverlayEvent event) {
        super.render(event);

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            Minecraft minecraft = Minecraft.getMinecraft();
            Entity ridingEntity = minecraft.player.getRidingEntity();
            if (ridingEntity instanceof EntityTrain && ((EntityTrain) ridingEntity).isPowerCar()) {
                calculatePosition(minecraft.displayWidth, minecraft.displayHeight);
                renderBar(minecraft);
                renderThrottle(minecraft, (EntityTrain) ridingEntity);
            }
        }
    }

    public void renderBar(Minecraft minecraft) {
        minecraft.renderEngine.bindTexture(bar);
        drawModalRectWithCustomSizedTexture(8 + x, 9 + y, 0, 0, 30, 92, 30, 92);

        minecraft.renderEngine.deleteTexture(bar);
    }

    public void renderThrottle(Minecraft minecraft, EntityTrain train) {
        minecraft.renderEngine.bindTexture(throttleBar);
        float throttleHeight = getThrottleHeight(train);
        drawModalRectWithCustomSizedTexture(0 + x, Math.round(90 * (1 - throttleHeight)) + y, 0, 0, 50, 20, 50, 20);

        minecraft.renderEngine.deleteTexture(throttleBar);
    }

    public float getThrottleHeight(EntityTrain train) {
        float throttle = train.getThrottle();
        float maxThrottle = train.getMaxThrottle();
        float minThrottle = train.getMinThrottle();

        return (Math.abs(minThrottle) + throttle) / (Math.abs(minThrottle) + maxThrottle);
    }

    public void calculatePosition(int displayX, int displayY) {
        x = Math.round(displayX / 200 + 17);
        y = Math.round(displayY / 20 + 96);
    }
}
