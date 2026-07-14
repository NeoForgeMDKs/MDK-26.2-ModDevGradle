package com.JSUSHDX.WorldTriggerMod.creativemodetab;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import com.JSUSHDX.WorldTriggerMod.blocks.ModBlocks;
import com.JSUSHDX.WorldTriggerMod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WorldTriggerMod.MODID);

    public static final Supplier<CreativeModeTab> WORLD_TRIGGER_TAB =
            CREATIVE_MODE_TABS.register("wt_items_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.TRIGGER.get()))
                            .title(Component.translatable("creativetab.wtmod.wt_items"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ModItems.TRIGGER);
                                output.accept(ModBlocks.ASSEMBLY_BENCH);
                                output.accept(ModItems.SHIELD_TRIGGER);
                            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
