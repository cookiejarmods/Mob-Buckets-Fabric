package com.playzzbros.mobbuckets.init;

import com.playzzbros.mobbuckets.MobBuckets;
import com.playzzbros.mobbuckets.item.AnimalBucketItem;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class MobBucketItems {
    public static Item BAT_BUCKET_ITEM = registerMobBucket("bat_bucket", EntityType.BAT, Fluids.EMPTY);
    public static Item BEE_BUCKET_ITEM = registerMobBucket("bee_bucket", EntityType.BEE, Fluids.EMPTY);
    public static Item BLAZE_BUCKET_BUCKET_ITEM = registerMobBucket("blaze_bucket", EntityType.BLAZE, Fluids.EMPTY);
    public static Item CAT_BUCKET_ITEM = registerMobBucket("cat_bucket", EntityType.CAT, Fluids.EMPTY);
    public static Item CAVE_SPIDER_BUCKET_ITEM = registerMobBucket("cave_spider_bucket", EntityType.CAVE_SPIDER, Fluids.EMPTY);
    public static Item CHICKEN_BUCKET_ITEM = registerMobBucket("chicken_bucket", EntityType.CHICKEN, Fluids.EMPTY);
    public static Item COW_BUCKET_ITEM = registerMobBucket("cow_bucket", EntityType.COW, Fluids.EMPTY);
    public static Item CREEPER_BUCKET_ITEM = registerMobBucket("creeper_bucket", EntityType.CREEPER, Fluids.EMPTY);
    public static Item DOLPHIN_BUCKET_ITEM = registerMobBucket("dolphin_bucket", EntityType.DOLPHIN, Fluids.EMPTY);
    public static Item DONKEY_BUCKET_ITEM = registerMobBucket("donkey_bucket", EntityType.DONKEY, Fluids.EMPTY);
    public static Item DROWNED_BUCKET_ITEM = registerMobBucket("drowned_bucket", EntityType.DROWNED, Fluids.WATER);
    public static Item ELDER_GUARDIAN_BUCKET_ITEM = registerMobBucket("elder_guardian_bucket", EntityType.ELDER_GUARDIAN, Fluids.WATER);
    //Maybe ENDER_DRAGON?
    public static Item ENDERMAN_BUCKET_ITEM = registerMobBucket("enderman_bucket", EntityType.ENDERMAN, Fluids.EMPTY);
    public static Item ENDERMITE_BUCKET_ITEM = registerMobBucket("endermite_bucket", EntityType.ENDERMITE, Fluids.EMPTY);
    public static Item EVOKER_BUCKET_ITEM = registerMobBucket("evoker_bucket", EntityType.EVOKER, Fluids.EMPTY);
    public static Item FOX_BUCKET_ITEM = registerMobBucket("fox_bucket", EntityType.FOX, Fluids.EMPTY);
//    public static Item SNOPW_FOX_BUCKET_ITEM = registerMobBucket("snow_fox_bucket", EntityType.FOX, Fluids.EMPTY);
    public static Item GHAST_BUCKET_ITEM = registerMobBucket("ghast_bucket", EntityType.GHAST, Fluids.EMPTY);
    //Maybe GIANT?
    public static Item GLOW_SQUID_BUCKET_ITEM = registerMobBucket("glow_squid_bucket", EntityType.GLOW_SQUID, Fluids.WATER);
    public static Item GOAT_BUCKET_ITEM = registerMobBucket("goat_bucket", EntityType.GOAT, Fluids.EMPTY);
    public static Item GUARDIAN_BUCKET_ITEM = registerMobBucket("guardian_bucket", EntityType.GUARDIAN, Fluids.WATER);
    public static Item HOGLIN_BUCKET_ITEM = registerMobBucket("hoglin_bucket", EntityType.HOGLIN, Fluids.EMPTY);
    public static Item HORSE_BUCKET_ITEM = registerMobBucket("horse_bucket", EntityType.HORSE, Fluids.EMPTY);
    public static Item HUSK_BUCKET_ITEM = registerMobBucket("husk_bucket", EntityType.HUSK, Fluids.EMPTY);
    //Maybe ILLUSIONER?
    public static Item IRON_GOLEM_BUCKET_ITEM = registerMobBucket("iron_golem_bucket", EntityType.IRON_GOLEM, Fluids.EMPTY);
    public static Item LLAMA_BUCKET_ITEM = registerMobBucket("llama_bucket", EntityType.LLAMA, Fluids.EMPTY);
    public static Item MAGMA_CUBE_BUCKET_ITEM = registerMobBucket("magma_cube_bucket", EntityType.MAGMA_CUBE, Fluids.LAVA);
    public static Item MULE_BUCKET_ITEM = registerMobBucket("mule_bucket", EntityType.MULE, Fluids.EMPTY);
    public static Item RED_MOOSHROOM_BUCKET_ITEM = registerMobBucket("red_mooshroom_bucket", EntityType.MOOSHROOM, Fluids.EMPTY);
//    public static Item BROWN_MOOSHROOM_BUCKET_ITEM = registerMobBucket("brown_mooshroom_bucket", EntityType.MOOSHROOM, Fluids.EMPTY);
    public static Item OCELOT_BUCKET_ITEM = registerMobBucket("ocelot_bucket", EntityType.OCELOT, Fluids.EMPTY);
    public static Item PANDA_BUCKET_ITEM = registerMobBucket("panda_bucket", EntityType.PANDA, Fluids.EMPTY);
    public static Item PARROT_BUCKET_ITEM = registerMobBucket("parrot_bucket", EntityType.PARROT, Fluids.EMPTY);
    public static Item PHANTOM_BUCKET_ITEM = registerMobBucket("phantom_bucket", EntityType.PHANTOM, Fluids.EMPTY);
    public static Item PIG_BUCKET_ITEM = registerMobBucket("pig_bucket", EntityType.PIG, Fluids.EMPTY);
    public static Item PIGLIN_BUCKET_ITEM = registerMobBucket("piglin_bucket", EntityType.PIGLIN, Fluids.EMPTY);
    public static Item PIGLIN_BRUTE_BUCKET_ITEM = registerMobBucket("piglin_brute_bucket", EntityType.PIGLIN_BRUTE, Fluids.EMPTY);
    public static Item PILLAGER_BUCKET_ITEM = registerMobBucket("pillager_bucket", EntityType.PILLAGER, Fluids.EMPTY);
    public static Item POLAR_BEAR_BUCKET_ITEM = registerMobBucket("polar_bear_bucket", EntityType.POLAR_BEAR, Fluids.EMPTY);
    public static Item RABBIT_BUCKET_ITEM = registerMobBucket("rabbit_bucket", EntityType.RABBIT, Fluids.EMPTY);
    public static Item RAVAGER_BUCKET_ITEM = registerMobBucket("ravager_bucket", EntityType.RAVAGER, Fluids.EMPTY);
    public static Item SHEEP_BUCKET_ITEM = registerMobBucket("sheep_bucket", EntityType.SHEEP, Fluids.EMPTY);
    public static Item SHULKER_BUCKET_ITEM = registerMobBucket("shulker_bucket", EntityType.SHULKER, Fluids.EMPTY);
    public static Item SILVERFISH_BUCKET_ITEM = registerMobBucket("silverfish_bucket", EntityType.SILVERFISH, Fluids.EMPTY);
    public static Item SKELETON_BUCKET_ITEM = registerMobBucket("skeleton_bucket", EntityType.SKELETON, Fluids.EMPTY);
    public static Item SKELETON_HORSE_BUCKET_ITEM = registerMobBucket("skeleton_horse_bucket", EntityType.SKELETON_HORSE, Fluids.EMPTY);
    public static Item SLIME_BUCKET_ITEM = registerMobBucket("slime_bucket", EntityType.SLIME, Fluids.EMPTY);
    public static Item SNOW_GOLEM_BUCKET_ITEM = registerMobBucket("snow_golem_bucket", EntityType.SNOW_GOLEM, Fluids.EMPTY);
    public static Item SPIDER_BUCKET_ITEM = registerMobBucket("spider_bucket", EntityType.SPIDER, Fluids.EMPTY);
    public static Item SQUID_BUCKET_ITEM = registerMobBucket("squid_bucket", EntityType.SQUID, Fluids.EMPTY);
    public static Item STRAY_BUCKET_ITEM = registerMobBucket("stray_bucket", EntityType.STRAY, Fluids.EMPTY);
    public static Item COLD_STRIDER_BUCKET_ITEM = registerMobBucket("cold_strider_bucket", EntityType.STRIDER, Fluids.EMPTY);
    public static Item WARM_STRIDER_BUCKET_ITEM = registerMobBucket("warm_strider_bucket", EntityType.STRIDER, Fluids.LAVA);
    public static Item TRADER_LLAMA_BUCKET_ITEM = registerMobBucket("trader_llama_bucket", EntityType.TRADER_LLAMA, Fluids.EMPTY);
    public static Item TURTLE_BUCKET_ITEM = registerMobBucket("turtle_bucket", EntityType.TURTLE, Fluids.EMPTY);
    public static Item VEX_BUCKET_ITEM = registerMobBucket("vex_bucket", EntityType.VEX, Fluids.EMPTY);
    public static Item VIlLAGER_BUCKET_ITEM = registerMobBucket("villager_bucket", EntityType.VILLAGER, Fluids.EMPTY);
    public static Item VINDICATOR_BUCKET_ITEM = registerMobBucket("vindicator_bucket", EntityType.VINDICATOR, Fluids.EMPTY);
    public static Item WANDERING_TRADER_BUCKET_ITEM = registerMobBucket("wandering_trader_bucket", EntityType.WANDERING_TRADER, Fluids.EMPTY);
    public static Item WITCH_BUCKET_ITEM = registerMobBucket("witch_bucket", EntityType.WITCH, Fluids.EMPTY);
    //Maybe WITHER?
    public static Item WITHER_SKELETON_BUCKET_ITEM = registerMobBucket("wither_skeleton_bucket", EntityType.WITHER_SKELETON, Fluids.EMPTY);
    public static Item WOLF_BUCKET_BUCKET_ITEM = registerMobBucket("wolf_bucket", EntityType.WOLF, Fluids.EMPTY);
    public static Item ZOGLIN_BUCKET_ITEM = registerMobBucket("zoglin_bucket", EntityType.ZOGLIN, Fluids.EMPTY);
    public static Item ZOMBIE_BUCKET_ITEM = registerMobBucket("zombie_bucket", EntityType.ZOMBIE, Fluids.EMPTY);
    //Maybe ZOMBIE_HORSE?
    public static Item ZOMBIE_VILLAGER_BUCKET_ITEM = registerMobBucket("zombie_villager_bucket", EntityType.ZOMBIE_VILLAGER, Fluids.EMPTY);
    public static Item ZOMBIFIED_PIGLIN_BUCKET_ITEM = registerMobBucket("zombified_piglin_bucket", EntityType.ZOMBIFIED_PIGLIN, Fluids.EMPTY);
    //Maybe PLAYER?

    public static void init(){}

    private static Item registerMobBucket(String id, EntityType<?> type, Fluid fluid) {
        var item = new AnimalBucketItem(type, fluid, Settings.MOB_BUCKET, "");
        return Registry.register(Registry.ITEM, new Identifier(MobBuckets.MOD_ID, id), item);
    }

    public static class Settings {
        public static final Item.Settings MOB_BUCKET = new Item.Settings().maxCount(1).group(ItemGroup.MISC);
    }
}