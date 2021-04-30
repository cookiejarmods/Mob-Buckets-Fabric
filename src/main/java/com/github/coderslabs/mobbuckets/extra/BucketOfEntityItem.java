package com.github.coderslabs.mobbuckets.extra;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class BucketOfEntityItem extends Item {
    public BucketOfEntityItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack heldItem = user.getMainHandStack();
        ItemStack emptyBucket = new ItemStack(Items.BUCKET);
        BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return new TypedActionResult<>(ActionResult.PASS, heldItem);
        } else {
            BlockPos position = hitResult.getBlockPos();
            if (world.canPlayerModifyAt(user, position) && user.canPlaceOn(position, hitResult.getSide(), heldItem)) {
                user.incrementStat(Stats.USED.getOrCreateStat(this));
                SoundEvent soundEvent = SoundEvents.ITEM_BUCKET_EMPTY_FISH;
                user.playSound(soundEvent, 1.0F, 1.0F);
                Direction direction = hitResult.getSide();
                BlockState blockState = world.getBlockState(position);
                BlockPos positionOffset;
                if (blockState.getCollisionShape(world, position).isEmpty()) {
                    positionOffset = position;
                } else {
                    positionOffset = position.offset(direction);
                }

                if (this.tryPlaceEntity(world, heldItem, positionOffset, user)) {
                    if (!user.abilities.creativeMode) {
                        heldItem.decrement(1);
                        if (heldItem.isEmpty()) {
                            return new TypedActionResult<>(ActionResult.SUCCESS, emptyBucket);
                        }

                        if (!user.inventory.insertStack(emptyBucket)) {
                            user.dropItem(emptyBucket, false);
                        }
                    }

                    return new TypedActionResult<>(ActionResult.SUCCESS, heldItem);
                }
            }

            return new TypedActionResult<>(ActionResult.FAIL, heldItem);
        }
    }

    protected boolean tryPlaceEntity(World world, ItemStack item, BlockPos position, PlayerEntity player) {
        CompoundTag nbt = item.getSubTag("EntityData");
        if (nbt == null) {
            return false;
        } else {
            Entity entity = EntityType.loadEntityWithPassengers(nbt, world, (entityPlaced) -> {
                entityPlaced.updatePosition((float)position.getX() + 0.5F, position.getY(), (float)position.getZ() + 0.5F);
                return !world.spawnEntity(entityPlaced) ? null : entityPlaced;
            });
            return entity != null;
        }
    }
}