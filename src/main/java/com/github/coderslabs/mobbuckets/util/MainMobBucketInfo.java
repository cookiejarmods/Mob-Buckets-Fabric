package com.github.coderslabs.mobbuckets.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class MainMobBucketInfo extends Item {
    public MainMobBucketInfo(Settings settings) {
        super(settings);
    }

    public static <T extends Entity> T spawn(EntityType<T> type, ServerWorld world, BlockPos pos, SpawnReason reason) {
        return type.create(world, null, null, null, pos, reason, false, false);
    }

    public static CompoundTag getCompound(ItemStack stack, String tag) {
        return getCompound(stack, tag, false);
    }

    public static CompoundTag getCompound(ItemStack stack, String tag, boolean nullify) {
        return tagExists(stack, tag) ? getNBT(stack).getCompound(tag) : (nullify ? null : new CompoundTag());
    }

    public static boolean tagExists(ItemStack stack, String tag) {
        return !stack.isEmpty() && stack.hasTag() && getNBT(stack).contains(tag);
    }

    public static CompoundTag getNBT(ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundTag());
        }
        return stack.getTag();
    }

    public static void setCompound(ItemStack stack, String tag, CompoundTag cmp) {
        getNBT(stack).put(tag, cmp);
    }

    public static void addOrDropStack(PlayerEntity player, ItemStack stack) {
        if (!player.inventory.insertStack(stack)) {
            player.dropItem(stack, true);
        }
    }
}