package com.playzzbros.mobbuckets.mixin;

import com.playzzbros.mobbuckets.MobBuckets;
import com.playzzbros.mobbuckets.util.MobBucketHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow public abstract PlayerInventory getInventory();
    @Shadow @Nullable public abstract ItemEntity dropItem(ItemStack stack, boolean retainOwnership);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public void interact(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!entity.world.isClient && ((LivingEntity) entity).getHealth() > 0 && this.isSneaking()) {
            var player = this;
            var held = player.getStackInHand(hand);
            var mobBucket = new ItemStack(MobBuckets.bucketsMap.get(entity.getType()));

            if (held.isEmpty() || held.getItem() != Items.BUCKET || held.getItem() != Items.WATER_BUCKET || held.getItem() != Items.LAVA_BUCKET) {
                cir.setReturnValue(ActionResult.PASS);
            }

            var nbt = new NbtCompound();
            MobBucketHelper.setCompound(mobBucket, "", entity.writeNbt(nbt));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.discard();

            cir.setReturnValue(ActionResult.PASS);
        }

        cir.setReturnValue(ActionResult.PASS);
    }

    public void addOrDropStack(PlayerEntityMixin player, ItemStack stack) {
        if (!player.getInventory().insertStack(stack)) {
            player.dropItem(stack, true);
        }
    }
}