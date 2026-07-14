package com.JSUSHDX.WorldTriggerMod.event;

import com.JSUSHDX.WorldTriggerMod.client.renderer.entity.TrionBulletRenderer;
import com.JSUSHDX.WorldTriggerMod.entity.ModEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.TRION_BULLET.get(), TrionBulletRenderer::new);
    }
}
