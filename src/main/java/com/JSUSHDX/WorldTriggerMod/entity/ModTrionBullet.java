package com.JSUSHDX.WorldTriggerMod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ModTrionBullet extends AbstractArrow {
    private double speed;
    private double  power;
    private double maxDistance;
    private Vec3 startPos;

    protected ModTrionBullet(double totalTrion, double speedRate, double powerRate, double rangeRate, EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public ModTrionBullet(EntityType<? extends ModTrionBullet> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.startPos == null) {
            this.startPos = this.position();
        }

        if (this.startPos.distanceToSqr(this.position()) > this.maxDistance * this.maxDistance && !this.level().isClientSide()) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (!this.level().isClientSide()) {
            this.discard();
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);

        output.putDouble("speed", this.speed);
        output.putDouble("maxDistance", this.maxDistance);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);

        this.speed = input.getDoubleOr("speed", 0.0);
        this.maxDistance = input.getDoubleOr("maxDistance", 0.0);
    }

    public void setupStats(double totalTrion, double speedRate, double powerRate, double rangeRate) {
        this.speed = getSpeed(totalTrion, speedRate);
        this.setBaseDamage(getPower(totalTrion, powerRate));
        this.maxDistance = getMaxDistance(totalTrion, rangeRate);

        this.pickup = Pickup.DISALLOWED;
        this.setNoGravity(true);
    }

    private double getSpeed(double totalTrion, double speedRate) {
        return totalTrion * speedRate;
    }

    private double getPower(double totalTrion, double powerRate) {
        return totalTrion * powerRate;
    }

    private double getMaxDistance(double totalTrion, double rangeRate) {
        return totalTrion * rangeRate;
    }
}
