package org.splitscreen.mc;

import org.bukkit.event.Listener;
import org.splitscreen.mc.command.core.Command;
import org.splitscreen.mc.config.core.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class to work with game logic and declare things such as command handlers etc.
 */

public class ScreenCraft extends BukkitPlugin {

    @Override
    public List<Config> getConfigs() {
        List<Config> configList = new ArrayList<>();
        return configList;
    }

    @Override
    public List<Command> getCommands() {
        List<Command> commandList = new ArrayList<>();
        return commandList;
    }

    @Override
    public List<Listener> getEventListeners() {
        List<Listener> listenerList = new ArrayList<>();
        return listenerList;
    }
}
