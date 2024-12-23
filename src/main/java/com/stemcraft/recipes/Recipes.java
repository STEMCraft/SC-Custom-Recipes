package com.stemcraft.recipes;

import com.stemcraft.STEMCraftLib;
import com.stemcraft.common.PluginBase;
import com.stemcraft.variable.VariableManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

import java.util.logging.Level;

public final class Recipes extends JavaPlugin implements Listener {
    public STEMCraftLib stemCraftLib;
    public VariableManager variableManager;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        stemCraftLib = (STEMCraftLib) getServer().getPluginManager().getPlugin("STEMCraftLib");
        if (stemCraftLib == null) {
            getLogger().log(Level.SEVERE, "STEMCraftLib not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(this, this);
        addStonecutterRecipe(Material.COBBLESTONE, Material.GRAVEL, 2);
        addStonecutterRecipe(Material.GRAVEL, Material.SAND, 2);
    }

    @Override
    public void onDisable() {
        // empty
    }

    @EventHandler
    public void onPlayerJoins(PlayerJoinEvent event) {
        String s = "THis is a test of the {hahaha} variable {player}";
        Player player = event.getPlayer();
        s = variableManager.parse(player, s);

        player.sendMessage(s);
    }

    private void addStonecutterRecipe(Material input, Material output, int amount) {
        NamespacedKey key = new NamespacedKey(this, input.name().toLowerCase() + "_to_" + output.name().toLowerCase());
        StonecuttingRecipe recipe = new StonecuttingRecipe(key, new ItemStack(output, amount), input);
        Bukkit.addRecipe(recipe);
    }
}
