package com.JSUSHDX.WorldTriggerMod.item.custom;

import com.JSUSHDX.WorldTriggerMod.entity.ModEntities;
import com.JSUSHDX.WorldTriggerMod.entity.ModTrionBullet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import com.JSUSHDX.WorldTriggerMod.WorldTriggerMod;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = WorldTriggerMod.MODID)
public class AsteroidTriggerItem extends Item {
    
    private static final Map<UUID, FiringState> activeShooters = new ConcurrentHashMap<>();

    private static class FiringState {
        int bulletsLeft;
        int ticksUntilNext;

        FiringState(int bulletsLeft, int ticksUntilNext) {
            this.bulletsLeft = bulletsLeft;
            this.ticksUntilNext = ticksUntilNext;
        }
    }

    public AsteroidTriggerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            // Start firing 8 bullets, 1 immediate, 7 more to come, interval of 4 ticks (0.2s)
            shootBullet(level, player);
            activeShooters.put(player.getUUID(), new FiringState(7, 2));
        }

        return InteractionResult.SUCCESS;
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        UUID playerId = player.getUUID();
        FiringState state = activeShooters.get(playerId);

        if (state != null) {
            state.ticksUntilNext--;
            if (state.ticksUntilNext <= 0) {
                shootBullet(player.level(), player);
                state.bulletsLeft--;
                
                if (state.bulletsLeft <= 0) {
                    activeShooters.remove(playerId);
                } else {
                    state.ticksUntilNext = 4; // Reset timer to 4 ticks (0.2 seconds)
                }
            }
        }
    }

    private static void shootBullet(Level level, Player player) {
        Vec3 playerPos = player.position();
        RandomSource random = level.getRandom();

        Vec3 randomOffset = new Vec3(random.nextDouble() * 0.5, random.nextDouble() * 0.5, random.nextDouble() * 0.5);

        ModTrionBullet bullet = new ModTrionBullet(ModEntities.TRION_BULLET.get(), level);

        bullet.setupStats(20.0d, 0.001d, 0.7d, 2.5d);
        bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.5f, 1.0f);
        bullet.setPos(playerPos.add(0, 2, 0).add(randomOffset));

        level.addFreshEntity(bullet);
    }
}
