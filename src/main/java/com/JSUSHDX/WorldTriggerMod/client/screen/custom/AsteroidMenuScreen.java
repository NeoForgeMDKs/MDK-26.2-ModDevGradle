package com.JSUSHDX.WorldTriggerMod.client.screen.custom;

import com.JSUSHDX.WorldTriggerMod.client.screen.BaseTriggerMenuScreen;
import net.minecraft.network.chat.Component;

public class AsteroidMenuScreen extends BaseTriggerMenuScreen {

    public AsteroidMenuScreen() {
        super(Component.translatable("screen.wtmod.asteroid_menu"));
    }

    @Override
    protected void init() {
        super.init();
        // Custom UI components specific to Asteroid Trigger
    }
}
