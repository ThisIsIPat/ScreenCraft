package org.splitscreen.mc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.splitscreen.mc.command.core.Command;
import org.splitscreen.mc.config.core.Config;
import org.splitscreen.mc.exception.CommandException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An abstract class for the Plugin to keep logic and variable declaration as distant from each other
 * as possible. That way we have cleaner code.
 *
 * The actual plugin class can be found in {@link ScreenCraft#onEnable() here}.
 */

abstract class BukkitPlugin extends JavaPlugin {

    // With the help of a static method, you can retrieve information such like
    // the standard file path for configs from the plugin.
    private static Plugin plugin;
    public static Plugin getPlugin() {return plugin;}

    // Set up a public random variable, accessible by all of the classes
    private static Random random;
    public static Random getRandom() {return random;}

    // onLoad() runs before onEnable() and therefore ensures that the variables are set up in the latter method
    @Override
    public void onLoad() {
        // Assign the random variable a value
        random = new Random();

        // Assign the Listener lists values
        listenersPSD = new ArrayList<>();
    }

    // Child getters return configs, commands and events to be initialized
    public abstract List<Config> getConfigs();
    public abstract List<Command> getCommands();
    public abstract List<Listener> getEventListeners();

    // Overriding of onEnable() and onDisable() to trigger event listeners defined here
    @Override
    public void onEnable() {
        // The plugin can only be assigned once it's loaded. That's why it gets declared in onEnable(), not onLoad()
        plugin = this;
    }

    @Override
    public void onDisable() {
        // onShutdown() event happens when the plugin disables itself.
        listenersPSD.forEach(PluginShutdownListener::onShutdown);

        plugin = null;
    }

    // Plugin shutdown event listeners
    private static List<PluginShutdownListener> listenersPSD;
    public static void addListener(PluginShutdownListener listener) {listenersPSD.add(listener);}

    public enum MessageLevel {
        NORM,    // Normal message
        WARN
    }

    // Send a message to all ops
    public static void sendMessageToOPs(String tag, String msg, MessageLevel msgLvl) {
        Bukkit.getServer().getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach(player -> {
            switch (msgLvl) {
                case NORM:
                    player.sendMessage(ChatColor.BOLD + tag + ": " + ChatColor.RESET + msg);
                case WARN:
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + tag + ":" + ChatColor.RESET + " "
                            + ChatColor.DARK_RED + msg);
            }
        });
    }

    public interface PluginShutdownListener {
        void onShutdown();  // This event turns active when the plugin calls onDisable()
    }
}
