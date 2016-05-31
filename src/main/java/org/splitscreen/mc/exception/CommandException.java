package org.splitscreen.mc.exception;

public class CommandException extends ScreenCraftException {
    public CommandException() {
        this("Unknown error");
    }

    public CommandException(String message) {
        super("Command error", message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
