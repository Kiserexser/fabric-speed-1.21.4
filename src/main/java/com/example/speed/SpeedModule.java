package com.example.speed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
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
        // Регистрация клавиши без Fabric API (но Fabric Loader позволяет использовать KeyBinding напрямую)
        keyBind = new KeyBinding(
                "key.speed.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.speed"
        );
        // Регистрация через Fabric Loader (без Fabric API) – на самом деле ClientTickEvents есть в fabric-loader? Нет, он в fabric-api.
        // Но мы заменим на миксин.
    }
}
