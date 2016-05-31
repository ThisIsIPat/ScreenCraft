package org.splitscreen.mc;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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

    // onLoad() runs before onEnable() and therefore ensures that the variables are set up in the latter method
    @Override
    public void onLoad() {
        // Assign the random variable a value
        random = new Random();

        // Assign the Listener lists values
        listenersPSD = new ArrayList<>();
    }

    // Custom onEnable() and onDisable() ports
    abstract public void onStart();
    abstract public void onEnd();

    // Overriding of onEnable() and onDisable() to trigger event listeners defined here
    @Override
    public void onEnable() {
        // The plugin can only be assigned once it's loaded. That's why it gets declared in onEnable(), not onLoad()
        plugin = this;

        onStart();
    }

    @Override
    public void onDisable() {
        // onShutdown() event happens when the plugin disables itself.
        listenersPSD.forEach(PluginShutdownListener::onShutdown);

        onEnd();
    }

    // Set up a public random variable, accessible by all of the classes
    private static Random random;
    public static Random getRandom() {return random;}

    // Plugin shutdown event listeners
    private static List<PluginShutdownListener> listenersPSD;
    public static void addListener(PluginShutdownListener listener) {listenersPSD.add(listener);}

    public interface PluginShutdownListener {
        void onShutdown();  // This event turns active when the plugin calls onDisable()
    }
}
