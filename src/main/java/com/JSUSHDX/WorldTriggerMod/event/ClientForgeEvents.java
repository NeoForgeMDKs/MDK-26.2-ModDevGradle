package com.JSUSHDX.WorldTriggerMod.event;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import com.JSUSHDX.WorldTriggerMod.client.screen.BaseTriggerMenuScreen;
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
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@EventBusSubscriber(modid = WorldTriggerMod.MODID, value = Dist.CLIENT)
public class ClientForgeEvents {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static boolean shouldOpenMenu = false;

    @SubscribeEvent
    public static void onInput(InputEvent.InteractionKeyMappingTriggered event) {
        if (event.isUseItem()) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                if (player.getMainHandItem().is(ModItems.ASTEROID_TRIGGER.get()) || player.getOffhandItem().is(ModItems.ASTEROID_TRIGGER.get())) {
                    Window window = Minecraft.getInstance().getWindow();
                    if (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_ALT) || InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_ALT)) {
                        event.setCanceled(true);
                        event.setSwingHand(false);
                        shouldOpenMenu = true;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onMouseButton(InputEvent.MouseButton.Pre event) {
        if (BaseTriggerMenuScreen.INSTANCE != null) {
            // Left click in radial menu
            if (event.getAction() == GLFW.GLFW_PRESS && event.getButton() == 0) {
                if (BaseTriggerMenuScreen.INSTANCE.hoveredSlot != -1) {
                    BaseTriggerMenuScreen.INSTANCE.onSlotClicked(BaseTriggerMenuScreen.INSTANCE.hoveredSlot);
                    event.setCanceled(true);
                }
            }
        } else {
            // Right click outside radial menu (button 1 is right click)
            if (event.getAction() == GLFW.GLFW_PRESS && event.getButton() == 1) {
                Player player = Minecraft.getInstance().player;
                if (player != null && (player.getMainHandItem().is(ModItems.ASTEROID_TRIGGER.get()) || player.getOffhandItem().is(ModItems.ASTEROID_TRIGGER.get()))) {
                    Window window = Minecraft.getInstance().getWindow();
                    if (InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_ALT) || InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_ALT)) {
                        event.setCanceled(true);
                        shouldOpenMenu = true;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        
        if (shouldOpenMenu) {
            shouldOpenMenu = false;
            mc.setScreenAndShow(new AsteroidMenuScreen());
        }
    }
}
