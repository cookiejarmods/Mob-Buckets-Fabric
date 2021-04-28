package com.github.coderslabs.mobbuckets.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.CaveSpiderEntity;
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

import static com.github.coderslabs.mobbuckets.item.MainMobBucketInfo.*;

public class CaveSpiderBucketItem extends Item {
    public static final String STORED_MOB = "stored_mob";
    public static final EntityType<CaveSpiderEntity> MOB_ENTITY_TYPE = EntityType.CAVE_SPIDER;

    public CaveSpiderBucketItem(Settings settings) {
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

            // spawn the mob
            CaveSpiderEntity mob = spawn(MOB_ENTITY_TYPE, (ServerWorld)world, spawnPos, SpawnReason.BUCKET);
            if (mob != null) {

                CompoundTag data = getCompound(held, STORED_MOB);
                if (!data.isEmpty())
                    mob.readCustomDataFromTag(data);

                world.spawnEntity(mob);
            }
        }
        player.swingHand(hand);

        if (!player.isCreative())
            player.setStackInHand(hand, new ItemStack(Items.BUCKET));

        return ActionResult.SUCCESS;
    }
}