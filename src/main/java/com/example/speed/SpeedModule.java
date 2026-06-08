package com.example.speed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

// Этот класс временно оставим с fabric-api для простоты, но потом уберём совсем
// На самом деле ClientTickEvents и KeyBinding есть в fabric-api, без них нельзя.
// Поэтому без API не обойтись. Значит, нужно решить проблему с API.

// Но я попробую дать код, использующий только Fabric Loader, без API.
// Для этого нужно зарегистрировать KeyBinding через FabricLoader и слушать тики через Mixin.

// Ниже — альтернативный SpeedModule без fabric-api, но с дополнительным миксином.
