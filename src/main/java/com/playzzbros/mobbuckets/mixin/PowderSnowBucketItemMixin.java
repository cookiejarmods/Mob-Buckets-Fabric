package com.playzzbros.mobbuckets.mixin;

import com.playzzbros.mobbuckets.util.MobBucketHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
        ItemStack stack = context.getStack();
        PlayerEntity player = context.getPlayer();
        ActionResult actionResult = this.useOnBlock(context);

        if (MobBucketHelper.isModified(stack) && stack.getCount() > 1 && actionResult.isAccepted() && player != null && !player.isCreative()) {
            MobBucketHelper.insertNewItem(player, new ItemStack(Items.BUCKET));
            stack.decrement(1);
        }

        cir.setReturnValue(actionResult);
    }
}