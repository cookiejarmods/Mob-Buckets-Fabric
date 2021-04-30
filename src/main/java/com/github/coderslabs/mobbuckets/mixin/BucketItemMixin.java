package com.github.coderslabs.mobbuckets.mixin;

import com.github.coderslabs.mobbuckets.init.MobBucketItems;
import com.github.coderslabs.mobbuckets.item.AnimalBucketItem;
import com.github.coderslabs.mobbuckets.util.ItemHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.github.coderslabs.mobbuckets.util.MainMobBucketInfo.*;

@Mixin(BucketItem.class)
public class BucketItemMixin extends Item {

    public BucketItemMixin(Settings settings) {
        super(settings);
    }

    //Thanks to AllStackable by Conn_Lost under GNU 3.0
    @Inject(method = "getEmptiedStack", at = @At(value = "HEAD"), cancellable = true)
    public void stackableBucket(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        if (ItemHelper.isModified(stack) && stack.getCount() > 1 && !player.abilities.creativeMode) {
            ItemHelper.insertNewItem(player, new ItemStack(Items.BUCKET));
            stack.decrement(1);
            cir.setReturnValue(stack);
        }
    }

    /*
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!entity.world.isClient && entity instanceof CreeperEntity && entity.getHealth() > 0) {
            CreeperEntity mob = (CreeperEntity) entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(MobBucketItems.CREEPER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, AnimalBucketItem.storedMobString, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
     */

    /*
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!entity.world.isClient && entity.getHealth() > 0) {
            BatEntity bat = (BatEntity) entity;
            ItemStack held = player.getStackInHand(hand);
            
            ItemStack mobBucket = new ItemStack(MobBucketItems.CREEPER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, AnimalBucketItem.storedMobString, bat.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
     */

}