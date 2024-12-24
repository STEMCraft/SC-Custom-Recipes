package com.stemcraft;

import com.stemcraft.common.STEMCraftPlugin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public final class CustomRecipes extends STEMCraftPlugin {
    @Override
    public void onEnable() {
        addStonecutterRecipe(Material.COBBLESTONE, Material.GRAVEL, 2);
        addStonecutterRecipe(Material.GRAVEL, Material.SAND, 2);
    }

    @Override
    public void onDisable() {
        // empty
    }

    /**
     * Add a new recipe to the stone cutter
     *
     * @param input The input material
     * @param output The output material
     * @param amount The output amount
     */
    private void addStonecutterRecipe(Material input, Material output, int amount) {
        NamespacedKey key = new NamespacedKey(this, input.name().toLowerCase() + "_to_" + output.name().toLowerCase());
        StonecuttingRecipe recipe = new StonecuttingRecipe(key, new ItemStack(output, amount), input);
        Bukkit.addRecipe(recipe);
    }
}
