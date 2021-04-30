package com.github.coderslabs.mobbuckets.extra;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BucketOfVanillaEntityItem extends BucketOfEntityItem {
    private final EntityType<?> entity;

    public BucketOfVanillaEntityItem(Settings settings, EntityType entity) {
        super(settings);
        this.entity = entity;
    }

    protected boolean tryPlaceEntity(World world, ItemStack item, BlockPos position, PlayerEntity player) {
        CompoundTag nbt = item.getSubTag("EntityData");
        if (nbt != null) {
            return super.tryPlaceEntity(world, item, position, player);
        } else {
            Entity spawnResult = this.entity.spawnFromItemStack((ServerWorld)world, item, player, position, SpawnReason.BUCKET, false, false);
            return spawnResult != null;
        }
    }

    public EntityType<?> getEntityType() {
        return this.entity;
    }
}