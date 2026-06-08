package com.example.speed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SpeedModule implements ModInitializer {
    private static KeyBinding keyBind;
    private static boolean toggled = false;
    public static int ticks = 0;
    public static int groundTicks = 0;

    @Override
    public void onInitialize() {
        keyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.speed.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.speed"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBind.wasPressed()) {
                toggled = !toggled;
                if (!toggled) {
                    ticks = 0;
                    groundTicks = 0;
                    TimerManager.setTimer(1.0F);
                }
            }
        });
        EventManager.register();
    }

    public static boolean isEnabled() { return toggled; }
    public static int getTicks() { return ticks; }
    public static void incTicks() { ticks++; }
    public static int getGroundTicks() { return groundTicks; }
    public static void setGroundTicks(int v) { groundTicks = v; }
    public static void incGroundTicks() { groundTicks++; }
}
