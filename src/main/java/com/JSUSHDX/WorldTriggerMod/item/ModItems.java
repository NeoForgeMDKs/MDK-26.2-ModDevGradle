package com.JSUSHDX.WorldTriggerMod.item;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import com.JSUSHDX.WorldTriggerMod.item.custom.AsteroidTriggerItem;
import com.JSUSHDX.WorldTriggerMod.item.custom.ShieldTriggerItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WorldTriggerMod.MODID);

    public static final DeferredItem<Item> TRIGGER = ITEMS.registerSimpleItem("trigger");

    public static final DeferredItem<Item> SHIELD_TRIGGER = ITEMS.registerItem("shield_trigger",
            properties -> new ShieldTriggerItem(properties.useCooldown(1.0f)));

    public static final DeferredItem<Item> ASTEROID_TRIGGER = ITEMS.registerItem("asteroid_trigger",
            properties -> new AsteroidTriggerItem(properties.useCooldown(1.0f)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
