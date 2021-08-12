package com.playzzbros.mobbuckets.mixin;

import com.playzzbros.mobbuckets.util.MobBucketHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBucketItem.class)
public abstract class PowderSnowBucketItemMixin {
    @Shadow public abstract ActionResult useOnBlock(ItemUsageContext context);

    @Inject(method = "useOnBlock", at = @At(value = "HEAD"), cancellable = true)
    private void stackablePowderedSnow(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        var actionResult = this.useOnBlock(context);
        var player = context.getPlayer();
        var held = context.getStack();
        var hand = context.getHand();

        if (actionResult.isAccepted() && player != null && !player.isCreative()) {
            if (held.getCount() == 1) {
                player.setStackInHand(hand, new ItemStack(Items.BUCKET));
            } else {
                held.decrement(1);
                MobBucketHelper.addOrDropStack(player, new ItemStack(Items.BUCKET));
            }
        }

        cir.setReturnValue(actionResult);
    }
}