package com.JSUSHDX.WorldTriggerMod.item.custom;

import com.JSUSHDX.WorldTriggerMod.data.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class ShieldTriggerItem extends net.minecraft.world.item.ShieldItem {
    public ShieldTriggerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            boolean newState = !stack.getOrDefault(ModDataComponents.IS_ON, false);
            stack.set(ModDataComponents.IS_ON, newState);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    // Give enchanted visual effect
    public boolean isFoil(ItemStack itemStack) {
        return itemStack.getOrDefault(ModDataComponents.IS_ON, false);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if (itemStack.has(ModDataComponents.IS_ON)) {
            Boolean isOn = itemStack.getOrDefault(ModDataComponents.IS_ON, false);
            Component msg = Component.translatable("tooltip.wtmod.is_on", (isOn ? "On" : "Off")).withColor(TextColor.GREEN);
            builder.accept(msg);
        }
    }
}
