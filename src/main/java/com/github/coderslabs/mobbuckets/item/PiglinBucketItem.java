package com.github.coderslabs.mobbuckets.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static com.github.coderslabs.mobbuckets.item.MainMobBucketInfo.getCompound;
import static com.github.coderslabs.mobbuckets.item.MainMobBucketInfo.spawn;

public class PiglinBucketItem extends Item {

    //Thanks to charm for the code help

    public static final String STORED_MOB = "stored_piglin";

    public PiglinBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() == null || context.getWorld().isClient)
            return ActionResult.FAIL;

        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction facing = context.getSide();
        Hand hand = context.getHand();
        ItemStack held = player.getStackInHand(hand);

        world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);

        if (!world.isClient) {

            double x = pos.getX() + 0.5F + facing.getOffsetX();
            double y = pos.getY() + 0.25F + (world.random.nextFloat() / 2.0F) + facing.getOffsetY();
            double z = pos.getZ() + 0.5F + facing.getOffsetZ();
            BlockPos spawnPos = new BlockPos(x, y, z);

            // spawn the piglin
            PiglinEntity piglin = spawn(EntityType.PIGLIN, (ServerWorld)world, spawnPos, SpawnReason.BUCKET);
            if (piglin != null) {

                CompoundTag data = getCompound(held, STORED_MOB);
                if (!data.isEmpty())
                    piglin.readCustomDataFromTag(data);

                world.spawnEntity(piglin);

            }
        }
        player.swingHand(hand);

        if (!player.isCreative())
            player.setStackInHand(hand, new ItemStack(Items.BUCKET));

        return ActionResult.SUCCESS;
    }

}

/*
public class AnimalBucketItem extends BucketItem {
    private final EntityType<?> animalType;

    public AnimalBucketItem(EntityType<?> type, Fluid fluid, Settings settings) {
        super(fluid, settings);
        this.animalType = type;
    }

    public void onEmptied(World world, ItemStack stack, BlockPos pos) {
        if (world instanceof ServerWorld) {
            this.spawnAnimal((ServerWorld)world, stack, pos);
        }

    }

    protected void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    private void spawnAnimal(ServerWorld serverWorld, ItemStack stack, BlockPos pos) {
        Entity entity = this.animalType.spawnFromItemStack(serverWorld, stack, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
        if (entity != null) {
            ((FishEntity)entity).setFromBucket(true);
        }

    }

}
 */
