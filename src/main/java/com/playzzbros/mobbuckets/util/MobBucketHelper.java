package com.playzzbros.mobbuckets.util;

import com.playzzbros.mobbuckets.mixin.PlayerEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class MobBucketHelper extends Item {
    public MobBucketHelper(Settings settings) {
        super(settings);
    }

    public static <T extends Entity> T spawn(EntityType<T> type, ServerWorld world, BlockPos pos, SpawnReason reason) {
        return type.create(world, null, null, null, pos, reason, false, false);
    }

    public static NbtCompound getCompound(ItemStack stack, String tag) {
        return getCompound(stack, tag, false);
    }

    public static NbtCompound getCompound(ItemStack stack, String tag, boolean nullify) {
        return tagExists(stack, tag) ? getNBT(stack).getCompound(tag) : (nullify ? null : new NbtCompound());
    }

    public static boolean tagExists(ItemStack stack, String tag) {
        return !stack.isEmpty() && stack.hasNbt() && getNBT(stack).contains(tag);
    }

    public static NbtCompound getNBT(ItemStack stack) {
        if (!stack.hasNbt()) {
            stack.setNbt(new NbtCompound());
        }
        return stack.getNbt();
    }

    public static void setCompound(ItemStack stack, String tag, NbtCompound cmp) {
        getNBT(stack).put(tag, cmp);
    }

    public static void addOrDropStack(PlayerEntity player, ItemStack stack) {
        if (!player.getInventory().insertStack(stack)) {
            player.dropItem(stack, true);
        }
    }

    public static void insertNewItem(PlayerEntity player, ItemStack stack2) {
        if (!player.getInventory().insertStack(stack2)) {
            player.dropItem(stack2, false);
        }
        if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).onSpawn();
        }
    }

    public static boolean isModified(ItemStack s){
        if (s.isEmpty()){
            return false;
        }
        Item i = s.getItem();
        return (((IItemMaxCount)i).getVanillaMaxCount()!=i.getMaxCount())&&s.getCount()>0;
    }
}