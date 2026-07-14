package com.JSUSHDX.WorldTriggerMod.datagen;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import com.JSUSHDX.WorldTriggerMod.blocks.ModBlocks;
import com.JSUSHDX.WorldTriggerMod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, WorldTriggerMod.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // Items
        itemModels.generateFlatItem(ModItems.TRIGGER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SHIELD_TRIGGER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ASTEROID_TRIGGER.get(), ModelTemplates.FLAT_ITEM);

        // BLOCKS
        blockModels.createTrivialCube(ModBlocks.ASSEMBLY_BENCH.get());


    }
}
