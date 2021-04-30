package com.github.coderslabs.mobbuckets.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import static com.github.coderslabs.mobbuckets.util.MainMobBucketInfo.*;

public class AnimalBucketItem extends BucketItem {
    public final EntityType<?> animalType;
    public static String storedMobString;
    public final Fluid fluid;

    public AnimalBucketItem(EntityType<?> type, Fluid fluid, Settings settings, String storedMobString) {
        super(fluid, settings);
        this.animalType = type;
        this.fluid = fluid;
        AnimalBucketItem.storedMobString = storedMobString;
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
        BlockHitResult hitResult = raycast(world, player, this.fluid == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE);

        world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);

        if (!world.isClient) {
            double x = pos.getX() + 0.5F + facing.getOffsetX();
            double y = pos.getY() + 0.25F + (world.random.nextFloat() / 2.0F) + facing.getOffsetY();
            double z = pos.getZ() + 0.5F + facing.getOffsetZ();
            BlockPos spawnPos = new BlockPos(x, y, z);

            // spawn the mob
            Entity mob = spawn(animalType, (ServerWorld)world, spawnPos, SpawnReason.BUCKET);
            if (mob != null) {

                CompoundTag data = getCompound(held, storedMobString);
                if (!data.isEmpty())
                    mob.readCustomDataFromTag(data);

                this.placeFluid(player, world, pos, hitResult);
                world.spawnEntity(mob);
            }
        }
        player.swingHand(hand);

        if (!player.isCreative())
            player.setStackInHand(hand, new ItemStack(Items.BUCKET));

        return ActionResult.SUCCESS;
    }

    @Override
    protected void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }
}