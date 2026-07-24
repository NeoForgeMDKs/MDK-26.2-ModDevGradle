package com.JSUSHDX.WorldTriggerMod.client.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class BaseTriggerMenuScreen extends Screen {

    public BaseTriggerMenuScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        // Base initialization for all trigger menus (e.g., generic background, common buttons)
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Don't pause the game when opening trigger menus
    }
}
