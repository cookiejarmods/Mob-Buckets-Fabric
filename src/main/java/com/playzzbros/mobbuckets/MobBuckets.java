package com.playzzbros.mobbuckets;

import com.google.common.reflect.Reflection;
import com.playzzbros.mobbuckets.datagen.impl.MobBucketsDataGen;
import com.playzzbros.mobbuckets.init.MobBucketItems;
import com.playzzbros.mobbuckets.util.IItemMaxCount;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"UnstableApiUsage", "rawtypes"})
public class MobBuckets implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static String MOD_ID = "mobbuckets";
    public static String MOD_NAME = "Mob Buckets";

    public static Map<EntityType<?>, Item> bucketsMap = new HashMap();

    @Override
    public void onInitialize() {
        Reflection.initialize(MobBucketItems.class);
        MobBucketItems.init();

//        ((IItemMaxCount)Items.WATER_BUCKET).setMaxCount(16);
////        ((IItemMaxCount)Items.POWDER_SNOW_BUCKET).setMaxCount(16);
//        ((IItemMaxCount)Items.LAVA_BUCKET).setMaxCount(16);
//        ((IItemMaxCount)Items.MILK_BUCKET).setMaxCount(16);

        put(MobBucketItems.BAT_BUCKET_ITEM, EntityType.BAT);
        put(MobBucketItems.BEE_BUCKET_ITEM, EntityType.BEE);
        put(MobBucketItems.BLAZE_BUCKET_BUCKET_ITEM, EntityType.BLAZE);
        put(MobBucketItems.CAT_BUCKET_ITEM, EntityType.CAT);
        put(MobBucketItems.CAVE_SPIDER_BUCKET_ITEM, EntityType.CAVE_SPIDER);
        put(MobBucketItems.CHICKEN_BUCKET_ITEM, EntityType.CHICKEN);
        put(MobBucketItems.COW_BUCKET_ITEM, EntityType.COW);
        put(MobBucketItems.CREEPER_BUCKET_ITEM, EntityType.CREEPER);
        put(MobBucketItems.DOLPHIN_BUCKET_ITEM, EntityType.DOLPHIN);
        put(MobBucketItems.DONKEY_BUCKET_ITEM, EntityType.DONKEY);
        put(MobBucketItems.DROWNED_BUCKET_ITEM, EntityType.DROWNED);
        put(MobBucketItems.ELDER_GUARDIAN_BUCKET_ITEM, EntityType.ELDER_GUARDIAN);
        put(MobBucketItems.ENDERMAN_BUCKET_ITEM, EntityType.ENDERMAN);
        put(MobBucketItems.ENDERMITE_BUCKET_ITEM, EntityType.ENDERMITE);
        put(MobBucketItems.EVOKER_BUCKET_ITEM, EntityType.EVOKER);
        put(MobBucketItems.FOX_BUCKET_ITEM, EntityType.FOX);
//        put(MobBucketItems.SNOPW_FOX_BUCKET_ITEM, EntityType.FOX);
        put(MobBucketItems.GHAST_BUCKET_ITEM, EntityType.GHAST);
        put(MobBucketItems.GLOW_SQUID_BUCKET_ITEM, EntityType.GLOW_SQUID);
        put(MobBucketItems.GOAT_BUCKET_ITEM, EntityType.GOAT);
        put(MobBucketItems.GUARDIAN_BUCKET_ITEM, EntityType.GUARDIAN);
        put(MobBucketItems.HOGLIN_BUCKET_ITEM, EntityType.HOGLIN);
        put(MobBucketItems.HORSE_BUCKET_ITEM, EntityType.HORSE);
        put(MobBucketItems.HUSK_BUCKET_ITEM, EntityType.HUSK);
        put(MobBucketItems.IRON_GOLEM_BUCKET_ITEM, EntityType.IRON_GOLEM);
        put(MobBucketItems.LLAMA_BUCKET_ITEM, EntityType.LLAMA);
        put(MobBucketItems.MAGMA_CUBE_BUCKET_ITEM, EntityType.MAGMA_CUBE);
        put(MobBucketItems.MULE_BUCKET_ITEM, EntityType.MULE);
        put(MobBucketItems.RED_MOOSHROOM_BUCKET_ITEM, EntityType.MOOSHROOM);
//        put(MobBucketItems.BROWN_MOOSHROOM_BUCKET_ITEM, EntityType.MOOSHROOM);
        put(MobBucketItems.OCELOT_BUCKET_ITEM, EntityType.OCELOT);
        put(MobBucketItems.PANDA_BUCKET_ITEM, EntityType.PANDA);
        put(MobBucketItems.PARROT_BUCKET_ITEM, EntityType.PARROT);
        put(MobBucketItems.PHANTOM_BUCKET_ITEM, EntityType.PHANTOM);
        put(MobBucketItems.PIG_BUCKET_ITEM, EntityType.PIG);
        put(MobBucketItems.PIGLIN_BUCKET_ITEM, EntityType.PIGLIN);
        put(MobBucketItems.PIGLIN_BRUTE_BUCKET_ITEM, EntityType.PIGLIN_BRUTE);
        put(MobBucketItems.PILLAGER_BUCKET_ITEM, EntityType.PILLAGER);
        put(MobBucketItems.POLAR_BEAR_BUCKET_ITEM, EntityType.POLAR_BEAR);
        put(MobBucketItems.RABBIT_BUCKET_ITEM, EntityType.RABBIT);
        put(MobBucketItems.RAVAGER_BUCKET_ITEM, EntityType.RAVAGER);
        put(MobBucketItems.SHEEP_BUCKET_ITEM, EntityType.SHEEP);
        put(MobBucketItems.SHULKER_BUCKET_ITEM, EntityType.SHULKER);
        put(MobBucketItems.SILVERFISH_BUCKET_ITEM, EntityType.SILVERFISH);
        put(MobBucketItems.SKELETON_BUCKET_ITEM, EntityType.SKELETON);
        put(MobBucketItems.SKELETON_HORSE_BUCKET_ITEM, EntityType.SKELETON_HORSE);
        put(MobBucketItems.SLIME_BUCKET_ITEM, EntityType.SLIME);
        put(MobBucketItems.SNOW_GOLEM_BUCKET_ITEM, EntityType.SNOW_GOLEM);
        put(MobBucketItems.SPIDER_BUCKET_ITEM, EntityType.SPIDER);
        put(MobBucketItems.SQUID_BUCKET_ITEM, EntityType.SQUID);
        put(MobBucketItems.STRAY_BUCKET_ITEM, EntityType.STRAY);
        put(MobBucketItems.COLD_STRIDER_BUCKET_ITEM, EntityType.STRIDER);
        put(MobBucketItems.WARM_STRIDER_BUCKET_ITEM, EntityType.STRIDER);
        put(MobBucketItems.TRADER_LLAMA_BUCKET_ITEM, EntityType.TRADER_LLAMA);
        put(MobBucketItems.TURTLE_BUCKET_ITEM, EntityType.TURTLE);
        put(MobBucketItems.VEX_BUCKET_ITEM, EntityType.VEX);
        put(MobBucketItems.VIlLAGER_BUCKET_ITEM, EntityType.VILLAGER);
        put(MobBucketItems.VINDICATOR_BUCKET_ITEM, EntityType.VINDICATOR);
        put(MobBucketItems.WANDERING_TRADER_BUCKET_ITEM, EntityType.WANDERING_TRADER);
        put(MobBucketItems.WITCH_BUCKET_ITEM, EntityType.WITCH);
        put(MobBucketItems.WITHER_SKELETON_BUCKET_ITEM, EntityType.WITHER_SKELETON);
        put(MobBucketItems.WOLF_BUCKET_BUCKET_ITEM, EntityType.WOLF);
        put(MobBucketItems.ZOGLIN_BUCKET_ITEM, EntityType.ZOGLIN);
        put(MobBucketItems.ZOMBIE_BUCKET_ITEM, EntityType.ZOMBIE);
        put(MobBucketItems.ZOMBIE_VILLAGER_BUCKET_ITEM, EntityType.ZOMBIE_VILLAGER);
        put(MobBucketItems.ZOMBIFIED_PIGLIN_BUCKET_ITEM, EntityType.ZOMBIFIED_PIGLIN);

        log(Level.INFO, "Mobing the Buckets");
    }

    public static void put(Item item, EntityType<?> type){
        bucketsMap.put(type, item);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}