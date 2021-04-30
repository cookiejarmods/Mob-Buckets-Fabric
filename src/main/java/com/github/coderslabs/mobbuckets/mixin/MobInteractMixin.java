package com.github.coderslabs.mobbuckets.mixin;

import com.github.coderslabs.mobbuckets.extra.BucketOfEntityItem;
import com.github.coderslabs.mobbuckets.extra.ItemList;
import com.github.coderslabs.mobbuckets.extra.MobGetter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class MobInteractMixin extends LivingEntity {
    @Final
    @Shadow
    public PlayerInventory inventory;
    @Final
    @Shadow
    public PlayerAbilities abilities;

    protected MobInteractMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public ItemEntity dropItem(ItemStack stack, boolean retainOwnership) {
        return null;
    }

    @Inject(at = @At("HEAD"), method = "interact", cancellable = true)
    public void onInteract(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack heldItem = this.getStackInHand(hand);
        Hand mainHand = Hand.MAIN_HAND;
        if (heldItem.getItem() == Items.BUCKET && !this.isSneaking() && (!hand.equals(Hand.OFF_HAND) || !(this.getStackInHand(mainHand).getItem() instanceof BucketOfEntityItem) && !(this.getStackInHand(mainHand).getItem() instanceof BucketItem))) {
            entity.playSound(SoundEvents.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);
            ItemStack newBucket = MobGetter.getBucket(entity);
            if (newBucket.getItem() != Items.BUCKET) {
                CompoundTag nbt = new CompoundTag();
                if (entity.saveSelfToTag(nbt)) {
                    newBucket.putSubTag("EntityData", nbt);
                }

                StringTag entityName = StringTag.of(entity.getEntityName());
                newBucket.putSubTag("id", entityName);
                if (newBucket.getItem() == ItemList.bucket_of_entity) {
                    ListTag customLore = new ListTag();
                    StringTag modifiedName = StringTag.of("\"" + entityName.toString() + "\"");
                    customLore.add(modifiedName);
                    CompoundTag display = new CompoundTag();
                    display.put("Lore", customLore);
                    newBucket.putSubTag("display", display);
                }

                if (!this.abilities.creativeMode) {
                    heldItem.decrement(1);
                    if (heldItem.isEmpty()) {
                        this.setStackInHand(hand, newBucket);
                    } else if (this.inventory.insertStack(newBucket)) {
                        this.dropItem(newBucket, false);
                    }
                }

                entity.remove();
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }

    }
}