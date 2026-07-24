package com.JSUSHDX.WorldTriggerMod.client.screen;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

public abstract class BaseTriggerMenuScreen extends Screen {
    public static final Identifier BG_TEXTURE = Identifier.fromNamespaceAndPath(WorldTriggerMod.MODID, "textures/gui/radial_menu_bg.png");
    public static final Identifier HIGHLIGHT_TEXTURE = Identifier.fromNamespaceAndPath(WorldTriggerMod.MODID, "textures/gui/radial_menu_highlight.png");
    private static final Logger LOGGER = LogUtils.getLogger();

    public int hoveredSlot = -1;
    protected final int totalSlots = 8;
    public static BaseTriggerMenuScreen INSTANCE = null;

    public BaseTriggerMenuScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        INSTANCE = this;
    }

    @Override
    public void onClose() {
        super.onClose();
        if (INSTANCE == this) {
            INSTANCE = null;
        }
    }

    public void updateHoveredSlot(double mouseX, double mouseY) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        double dx = mouseX - centerX;
        double dy = mouseY - centerY;
        double distanceSq = dx * dx + dy * dy;

        if (distanceSq >= 40 * 40 && distanceSq <= 120 * 120) {
            double angle = Math.toDegrees(Math.atan2(dy, dx));
            angle += 90;
            if (angle < 0) {
                angle += 360;
            }
            angle = (angle + 22.5) % 360;
            this.hoveredSlot = (int) (angle / 45);
        } else {
            this.hoveredSlot = -1;
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(guiGraphicsExtractor, mouseX, mouseY, partialTick);
        
        // Update hovered slot using correct GUI scaled mouse coordinates
        updateHoveredSlot(mouseX, mouseY);
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int textureSize = 256;
        int x = centerX - textureSize / 2;
        int y = centerY - textureSize / 2;

        guiGraphicsExtractor.blit(BG_TEXTURE, x, y, x + textureSize, y + textureSize, 0.0f, 1.0f, 0.0f, 1.0f);

        if (this.hoveredSlot != -1) {
            float rotationDegrees = this.hoveredSlot * 45.0f;
            guiGraphicsExtractor.pose().pushMatrix();
            guiGraphicsExtractor.pose().translate(centerX, centerY);
            guiGraphicsExtractor.pose().rotate((float) Math.toRadians(rotationDegrees));
            guiGraphicsExtractor.pose().translate(-centerX, -centerY);
            guiGraphicsExtractor.blit(HIGHLIGHT_TEXTURE, x, y, x + textureSize, y + textureSize, 0.0f, 1.0f, 0.0f, 1.0f);
            guiGraphicsExtractor.pose().popMatrix();
        }

        renderCustom(guiGraphicsExtractor, mouseX, mouseY, partialTick);
    }

    protected void renderCustom(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY, float partialTick) {
    }

    public abstract void onSlotClicked(int slotIndex);

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
