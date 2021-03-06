package com.playzzbros.mobbuckets.mixin;

import com.playzzbros.mobbuckets.util.MobBucketHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketItemMixin {
    @Inject(method = "getEmptiedStack", at = @At(value = "HEAD"), cancellable = true)
    private static void stackableBucket(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        if (MobBucketHelper.isModified(stack) && stack.getCount() > 1 && !player.isCreative()) {
            MobBucketHelper.insertNewItem(player, new ItemStack(Items.BUCKET));
            stack.decrement(1);
            cir.setReturnValue(stack);
        }
    }
}