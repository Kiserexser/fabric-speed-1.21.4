package com.example.speed;

import com.example.speed.events.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;

public class EventManager {
    private static MinecraftClient mc = MinecraftClient.getInstance();

    public static void register() {}

    public static void onMovePost(EventOnMovePost e) {
        if (!SpeedModule.isEnabled()) return;
        int ticks = SpeedModule.getTicks();
        TimerManager.setTimer(1.7F);
        if (ticks > 3) {
            double bst = 0.03;
            if (ticks % 2 == 0) {
                if (mc.player != null) {
                    mc.player.addVelocityInternal(new Vec3d(0, 0.03F, 0));
                    if (mc.player.isOnGround()) bst = 0.085;
                    else bst = 0.03;
                }
            }
            double yaw = Math.toRadians(MoveUtil.getdir());
            double xt = -Math.sin(yaw);
            double zt = Math.cos(yaw);
            if (MoveUtil.getdir() == -1.0F) { xt = 0.0; zt = 0.0; }
            if (mc.player != null) {
                mc.player.addVelocityInternal(new Vec3d(xt * bst, 0, zt * bst));
            }
        }
        SpeedModule.incTicks();
    }

    public static void onMoveInput(EventMoveInput e) {
        if (!SpeedModule.isEnabled()) return;
        if (mc.player == null) return;
        if (mc.player.verticalCollision) SpeedModule.incGroundTicks();
        else SpeedModule.setGroundTicks(0);
        if (SpeedModule.getGroundTicks() >= 1) mc.player.jump();
    }

    public static void onPostMotion(EventPostMotion e) {
        if (!SpeedModule.isEnabled()) return;
        if (SpeedModule.getTicks() % 2 == 0) {
            TimerManager.setTimer(0.3F);
            if (mc.player != null) {
                NetworkUtils.sendSilentPacket(new net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket(mc.player, net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            }
        }
    }

    public static void onPacket(EventPacket e) {
        if (!SpeedModule.isEnabled()) return;
        if (e.getPacket() instanceof PlayerPositionLookS2CPacket) {
            if (SpeedModule.getTicks() % 2 == 1) {
                SpeedModule.incTicks();
            }
            TimerManager.setTimer(1.0F);
        }
    }
}
