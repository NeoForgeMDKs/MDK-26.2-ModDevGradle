package com.JSUSHDX.WorldTriggerMod.client.screen.custom;

import com.JSUSHDX.WorldTriggerMod.client.screen.BaseTriggerMenuScreen;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class AsteroidMenuScreen extends BaseTriggerMenuScreen {
    private static final Logger LOGGER = LogUtils.getLogger();

    public AsteroidMenuScreen() {
        super(Component.literal("Asteroid Menu"));
    }

    @Override
    protected void renderCustom(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY, float partialTick) {
        if (this.minecraft == null || this.minecraft.font == null) return;
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        String normalText = "Normal Mode";
        String placeText = "Place Mode";

        int nWidth = this.minecraft.font.width(normalText);
        int pWidth = this.minecraft.font.width(placeText);

        // Colors must be ARGB. 0xFF000000 is the alpha mask for fully opaque.
        int colorYellow = 0xFFFFFF00;
        int colorWhite = 0xFFFFFFFF;

        if (this.hoveredSlot == 0) {
            guiGraphicsExtractor.text(this.minecraft.font, normalText, centerX - nWidth / 2, centerY - 100, colorYellow, true);
        } else {
            guiGraphicsExtractor.text(this.minecraft.font, normalText, centerX - nWidth / 2, centerY - 100, colorWhite, true);
        }

        if (this.hoveredSlot == 4) {
            guiGraphicsExtractor.text(this.minecraft.font, placeText, centerX - pWidth / 2, centerY + 90, colorYellow, true);
        } else {
            guiGraphicsExtractor.text(this.minecraft.font, placeText, centerX - pWidth / 2, centerY + 90, colorWhite, true);
        }
    }

    @Override
    public void onSlotClicked(int slotIndex) {
        if (slotIndex == 0) {
            LOGGER.info("Asteroid Menu: Selected Normal Mode (Index 0)");
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(Component.literal("Selected Normal Mode"));
            }
            this.onClose();
        } else if (slotIndex == 4) {
            LOGGER.info("Asteroid Menu: Selected Place Mode (Index 4)");
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(Component.literal("Selected Place Mode"));
            }
            this.onClose();
        }
    }
}
