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

public class AsteroidTriggerItem extends Item {
    public AsteroidTriggerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            addBullets(8, level, player);
        }

        return InteractionResult.SUCCESS;
    }

    private void addBullets(int amount, Level level, Player player) {
        Vec3 playerPos = player.position();
        RandomSource random = level.getRandom();
        int gap = 19;
        int iterateTimes = amount * (gap + 1);

        for (int i = 0; i < iterateTimes; ++i) {
            if (Math.floorMod(i, gap + 1) != 0) {
                continue;
            }

            Vec3 randomOffset = new Vec3(random.nextDouble() * 0.5, random.nextDouble() * 0.5, random.nextDouble() * 0.5);

            ModTrionBullet bullet = new ModTrionBullet(ModEntities.TRION_BULLET.get(), level);

            bullet.setupStats(20.0d, 0.001d, 0.7d, 2.5d);
            bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.5f, 1.0f);
            bullet.setPos(playerPos.add(0, 2, 0).add(randomOffset));

            level.addFreshEntity(bullet);
        }
    }
}
