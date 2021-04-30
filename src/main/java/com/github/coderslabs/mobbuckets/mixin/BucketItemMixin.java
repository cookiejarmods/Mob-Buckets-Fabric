package com.github.coderslabs.mobbuckets.mixin;

import com.github.coderslabs.mobbuckets.util.ItemHelper;
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
    //Thanks to AllStackable by Conn_Lost under GNU 3.0
    @Inject(method = "getEmptiedStack", at = @At(value = "HEAD"), cancellable = true)
    public void stackableBucket(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir){
        if (ItemHelper.isModified(stack) && stack.getCount() > 1 && !player.abilities.creativeMode){
            ItemHelper.insertNewItem(player, new ItemStack(Items.BUCKET));
            stack.decrement(1);
            cir.setReturnValue(stack);
        }
    }
}