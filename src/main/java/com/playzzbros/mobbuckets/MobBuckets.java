package com.playzzbros.mobbuckets;

import com.google.common.reflect.Reflection;
import com.playzzbros.mobbuckets.init.MobBucketItems;
import com.playzzbros.mobbuckets.util.IItemMaxCount;
import com.playzzbros.mobbuckets.util.MobBucketHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@SuppressWarnings({"RedundantTypeArguments", "UnstableApiUsage", "rawtypes"})
public class MobBuckets implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static String MOD_ID = "mobbuckets";
    public static String MOD_NAME = "Mob Buckets";

    @Override
    public void onInitialize() {
        Reflection.initialize(
                MobBucketItems.class
        );
        MobBucketItems.init();

        ((IItemMaxCount)Items.WATER_BUCKET).setMaxCount(16);
        ((IItemMaxCount)Items.POWDER_SNOW_BUCKET).setMaxCount(16);
        ((IItemMaxCount)Items.LAVA_BUCKET).setMaxCount(16);
        ((IItemMaxCount)Items.MILK_BUCKET).setMaxCount(16);

        UseEntityCallback.EVENT.register(this::tryCaptures);

        log(Level.INFO, "Mobing the Buckets");
    }

    public ActionResult tryCaptures(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) {
         List<Pair<Item, EntityType>> bucketsList = List.<Pair<Item, EntityType>>of(
                new Pair<>(MobBucketItems.BAT_BUCKET_ITEM, EntityType.BAT),
                new Pair<>(MobBucketItems.BEE_BUCKET_ITEM, EntityType.BEE),
                new Pair<>(MobBucketItems.BLAZE_BUCKET_BUCKET_ITEM, EntityType.BLAZE),
                new Pair<>(MobBucketItems.CAT_BUCKET_ITEM, EntityType.CAT),
                new Pair<>(MobBucketItems.CAVE_SPIDER_BUCKET_ITEM, EntityType.CAVE_SPIDER),
                new Pair<>(MobBucketItems.CHICKEN_BUCKET_ITEM, EntityType.CHICKEN),
                new Pair<>(MobBucketItems.COW_BUCKET_ITEM, EntityType.COW),
                new Pair<>(MobBucketItems.CREEPER_BUCKET_ITEM, EntityType.CREEPER),
                new Pair<>(MobBucketItems.DOLPHIN_BUCKET_ITEM, EntityType.DOLPHIN),
                new Pair<>(MobBucketItems.DONKEY_BUCKET_ITEM, EntityType.DONKEY),
                new Pair<>(MobBucketItems.DROWNED_BUCKET_ITEM, EntityType.DROWNED),
                new Pair<>(MobBucketItems.ELDER_GUARDIAN_BUCKET_ITEM, EntityType.ELDER_GUARDIAN),
                new Pair<>(MobBucketItems.ENDERMAN_BUCKET_ITEM, EntityType.ENDERMAN),
                new Pair<>(MobBucketItems.ENDERMITE_BUCKET_ITEM, EntityType.ENDERMITE),
                new Pair<>(MobBucketItems.EVOKER_BUCKET_ITEM, EntityType.EVOKER),
                new Pair<>(MobBucketItems.FOX_BUCKET_ITEM, EntityType.FOX),
                new Pair<>(MobBucketItems.GHAST_BUCKET_ITEM, EntityType.GHAST),
                new Pair<>(MobBucketItems.GUARDIAN_BUCKET_ITEM, EntityType.GUARDIAN),
                new Pair<>(MobBucketItems.HOGLIN_BUCKET_ITEM, EntityType.HOGLIN),
                new Pair<>(MobBucketItems.HORSE_BUCKET_ITEM, EntityType.HORSE),
                new Pair<>(MobBucketItems.HUSK_BUCKET_ITEM, EntityType.HUSK),
                new Pair<>(MobBucketItems.IRON_GOLEM_BUCKET_ITEM, EntityType.IRON_GOLEM),
                new Pair<>(MobBucketItems.LLAMA_BUCKET_ITEM, EntityType.LLAMA),
                new Pair<>(MobBucketItems.MAGMA_CUBE_BUCKET_ITEM, EntityType.MAGMA_CUBE),
                new Pair<>(MobBucketItems.RED_MOOSHROOM_BUCKET_ITEM, EntityType.MOOSHROOM),
                new Pair<>(MobBucketItems.BROWN_MOOSHROOM_BUCKET_ITEM, EntityType.MOOSHROOM),
                new Pair<>(MobBucketItems.MULE_BUCKET_ITEM, EntityType.MULE),
                new Pair<>(MobBucketItems.OCELOT_BUCKET_ITEM, EntityType.OCELOT),
                new Pair<>(MobBucketItems.PANDA_BUCKET_ITEM, EntityType.PANDA),
                new Pair<>(MobBucketItems.PARROT_BUCKET_ITEM, EntityType.PARROT),
                new Pair<>(MobBucketItems.PHANTOM_BUCKET_ITEM, EntityType.PHANTOM),
                new Pair<>(MobBucketItems.PIG_BUCKET_ITEM, EntityType.PIG),
                new Pair<>(MobBucketItems.PIGLIN_BRUTE_BUCKET_ITEM, EntityType.PIGLIN_BRUTE),
                new Pair<>(MobBucketItems.PIGLIN_BUCKET_ITEM, EntityType.PIGLIN),
                new Pair<>(MobBucketItems.PILLAGER_BUCKET_ITEM, EntityType.PILLAGER),
                new Pair<>(MobBucketItems.POLAR_BEAR_BUCKET_ITEM, EntityType.POLAR_BEAR),
                new Pair<>(MobBucketItems.RABBIT_BUCKET_ITEM, EntityType.RABBIT),
                new Pair<>(MobBucketItems.RAVAGER_BUCKET_ITEM, EntityType.RAVAGER),
                new Pair<>(MobBucketItems.SHEEP_BUCKET_ITEM, EntityType.SHEEP),
                new Pair<>(MobBucketItems.SHULKER_BUCKET_ITEM, EntityType.SHULKER),
                new Pair<>(MobBucketItems.SKELETON_BUCKET_ITEM, EntityType.SKELETON),
                new Pair<>(MobBucketItems.SKELETON_HORSE_BUCKET_ITEM, EntityType.SKELETON_HORSE),
                new Pair<>(MobBucketItems.SLIME_BUCKET_ITEM, EntityType.SLIME),
                new Pair<>(MobBucketItems.SNOW_GOLEM_BUCKET_ITEM, EntityType.SNOW_GOLEM),
                new Pair<>(MobBucketItems.SQUID_BUCKET_ITEM, EntityType.SQUID),
                new Pair<>(MobBucketItems.STRAY_BUCKET_ITEM, EntityType.STRAY),
                new Pair<>(MobBucketItems.COLD_STRIDER_BUCKET_ITEM, EntityType.STRIDER),
                new Pair<>(MobBucketItems.WARM_STRIDER_BUCKET_ITEM, EntityType.STRIDER),
                new Pair<>(MobBucketItems.TURTLE_BUCKET_ITEM, EntityType.TURTLE),
                new Pair<>(MobBucketItems.VEX_BUCKET_ITEM, EntityType.VEX),
                new Pair<>(MobBucketItems.VIlLAGER_BUCKET_ITEM, EntityType.VILLAGER),
                new Pair<>(MobBucketItems.VINDICATOR_BUCKET_ITEM, EntityType.VINDICATOR),
                new Pair<>(MobBucketItems.WANDERING_TRADER_BUCKET_ITEM, EntityType.WANDERING_TRADER),
                new Pair<>(MobBucketItems.WITCH_BUCKET_ITEM, EntityType.WITCH),
                new Pair<>(MobBucketItems.WITHER_SKELETON_BUCKET_ITEM, EntityType.WITHER_SKELETON),
                new Pair<>(MobBucketItems.WOLF_BUCKET_BUCKET_ITEM, EntityType.WOLF),
                new Pair<>(MobBucketItems.ZOGLIN_BUCKET_ITEM, EntityType.ZOGLIN),
                new Pair<>(MobBucketItems.ZOMBIE_BUCKET_ITEM, EntityType.ZOMBIE),
                new Pair<>(MobBucketItems.ZOMBIE_VILLAGER_BUCKET_ITEM, EntityType.ZOMBIE_VILLAGER),
                new Pair<>(MobBucketItems.ZOMBIFIED_PIGLIN_BUCKET_ITEM, EntityType.ZOMBIFIED_PIGLIN)
        );

        for (Pair<Item, EntityType> bucketsLists : bucketsList) {
            return tryCapture(player, hand, entity, bucketsLists.getRight(), new ItemStack(bucketsLists.getLeft()));
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCapture(PlayerEntity player, Hand hand, Entity entity, EntityType<?> mobEntity, ItemStack mobBucket) {
        ItemStack held = player.getStackInHand(hand);

        if (!entity.world.isClient && entity.getType() == mobEntity && ((LivingEntity)entity).getHealth() > 0) {
            if (held.isEmpty() || held.getItem() != Items.BUCKET) {
                return ActionResult.PASS;
            }

            NbtCompound nbt = new NbtCompound();
            MobBucketHelper.setCompound(mobBucket, "", entity.writeNbt(nbt));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                MobBucketHelper.addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.discard();

            return ActionResult.PASS;
        }

        return ActionResult.PASS;
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}