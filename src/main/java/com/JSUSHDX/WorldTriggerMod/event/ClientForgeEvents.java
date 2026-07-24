package com.JSUSHDX.WorldTriggerMod.event;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import com.JSUSHDX.WorldTriggerMod.client.screen.custom.AsteroidMenuScreen;
import com.JSUSHDX.WorldTriggerMod.item.ModItems;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = WorldTriggerMod.MODID, value = Dist.CLIENT)
public class ClientForgeEvents {

    @SubscribeEvent
    public static void onInput(InputEvent.InteractionKeyMappingTriggered event) {
        if (event.isUseItem()) {
            Player player = Minecraft.getInstance().player;
            if (player != null && player.getItemInHand(event.getHand()).is(ModItems.ASTEROID_TRIGGER.get())) {
                Window window = Minecraft.getInstance().getWindow();
                if (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_ALT) || InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_ALT)) {
                    // Cancel the input entirely so no packet is sent
                    event.setCanceled(true);
                    event.setSwingHand(false);
                    // Open the screen
                    Minecraft.getInstance().setScreenAndShow(new AsteroidMenuScreen());
                }
            }
        }
    }
}







