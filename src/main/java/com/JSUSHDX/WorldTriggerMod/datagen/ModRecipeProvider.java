package com.JSUSHDX.WorldTriggerMod.datagen;

import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;
import com.JSUSHDX.WorldTriggerMod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "World Trigger Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.COMBAT, ModItems.TRIGGER.get())
                .pattern("A  ")
                .pattern(" B ")
                .pattern("  A")
                .define('A', Items.DIAMOND)
                .define('B', Items.REDSTONE)
                .unlockedBy(getHasName(ModItems.TRIGGER.get()), has(ModItems.TRIGGER))
                .group("worldtrigger")
                .save(output);
    }
}
