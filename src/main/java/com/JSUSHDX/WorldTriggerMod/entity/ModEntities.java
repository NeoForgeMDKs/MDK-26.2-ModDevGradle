package com.JSUSHDX.WorldTriggerMod.entity;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.createEntities(WorldTriggerMod.MODID);

    public static final Supplier<EntityType<ModTrionBullet>> TRION_BULLET =
            ENTITIES.register("trion_bullet",
                    () -> EntityType.Builder.<ModTrionBullet>of(ModTrionBullet::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(WorldTriggerMod.MODID, "trion_bullet"))));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
