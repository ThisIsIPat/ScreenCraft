package org.splitscreen.mc.exception;

import org.splitscreen.mc.ScreenCraft;

public abstract class ScreenCraftException extends RuntimeException {
    public ScreenCraftException() {
        this("Unknown cause");
    }

    public ScreenCraftException(String tag) {
        this(tag, "Unknown error");
    }

    public ScreenCraftException(String tag, String message) {
        ScreenCraft.sendMessageToOPs(tag, message, ScreenCraft.MessageLevel.WARN);
    }

    public ScreenCraftException(Throwable cause) {
        super(cause);
    }
}
