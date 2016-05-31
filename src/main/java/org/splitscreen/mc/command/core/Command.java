package org.splitscreen.mc.command.core;

import org.bukkit.command.CommandExecutor;

public abstract class Command implements CommandExecutor {
    abstract boolean onCommand(CommandInstance commandInstance);

    // TODO: Call onCommand
}
