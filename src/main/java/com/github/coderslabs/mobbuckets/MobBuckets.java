package com.github.coderslabs.mobbuckets;

import com.github.coderslabs.mobbuckets.item.*;
import com.github.evoslab.cookiecore.datagen.MainGenerator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import static com.github.coderslabs.mobbuckets.item.MainMobBucketInfo.*;

public class MobBuckets implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static String MOD_ID = "mobbuckets";
    public static String MOD_NAME = "Mob Buckets";

    private static final MainGenerator generator = new MainGenerator(MOD_ID);

    public static BeeBucketItem BEE_BUCKET_ITEM;
    public static CatBucketItem CAT_BUCKET_ITEM;
    public static CaveSpiderBucketItem CAVE_SPIDER_BUCKET_ITEM;
    public static ChickenBucketItem CHICKEN_BUCKET_ITEM;
    public static CowBucketItem COW_BUCKET_ITEM;
    public static CreeperBucketItem CREEPER_BUCKET_ITEM;
    public static DolphinBucketItem DOLPHIN_BUCKET_ITEM;
    public static DonkeyBucketItem DONKEY_BUCKET_ITEM;
    public static EndermanBucketItem ENDERMAN_BUCKET_ITEM;
    public static FoxBucketItem FOX_BUCKET_ITEM;
    public static HorseBucketItem HORSE_BUCKET_ITEM;
    public static IronGolemBucketItem IRON_GOLEM_BUCKET_ITEM;
    public static LlamaBucketItem LLAMA_BUCKET_ITEM;
    public static MooshroomBucketItem MOOSHROOM_BUCKET_ITEM;
    public static MuleBucketItem MULE_BUCKET_ITEM;
    public static OcelotBucketItem OCELOT_BUCKET_ITEM;
    public static PandaBucketItem PANDA_BUCKET_ITEM;
    public static ParrotBucketItem PARROT_BUCKET_ITEM;
    public static PigBucketItem PIG_BUCKET_ITEM;
    public static PiglinBucketItem PIGLIN_BUCKET_ITEM;
    public static PolarBearBucketItem POLAR_BEAR_BUCKET_ITEM;
    public static RabbitBucketItem RABBIT_BUCKET_ITEM;
    public static SheepBucketItem SHEEP_BUCKET_ITEM;
    public static SkeletonHorseBucketItem SKELETON_HORSE_BUCKET_ITEM;
    public static SnowGolemBucketItem SNOW_GOLEM_BUCKET_ITEM;
    public static SquidBucketItem SQUID_BUCKET_ITEM;
    public static StriderBucketItem STRIDER_BUCKET_ITEM;
    public static TurtleBucketItem TURTLE_BUCKET_ITEM;
    public static VillagerBucketItem VIlLAGER_BUCKET_ITEM;
    public static WanderingTraderBucketItem WANDERING_TRADER_BUCKET_ITEM;
    public static WolfBucketItem WOLF_BUCKET_BUCKET_ITEM;
    public static BlazeBucketItem BLAZE_BUCKET_BUCKET_ITEM;
    public static ZombieBucketItem ZOMBIE_BUCKET_ITEM;
    public static ZombifiedPiglinBucketItem ZOMBIFIED_PIGLIN_BUCKET_ITEM;

    public static ItemGroup MOB_BUCKETS_TAB = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "mob_buckets_tab"), () -> new ItemStack(CREEPER_BUCKET_ITEM));

    @Override
    public void onInitialize() {
        BEE_BUCKET_ITEM = generator.item.registerBlandItem(new BeeBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "bee_bucket");
        CAT_BUCKET_ITEM = generator.item.registerBlandItem(new CatBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "cat_bucket");
        CAVE_SPIDER_BUCKET_ITEM = generator.item.registerBlandItem(new CaveSpiderBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "cave_spider_bucket");
        CHICKEN_BUCKET_ITEM = generator.item.registerBlandItem(new ChickenBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "chicken_bucket");
        COW_BUCKET_ITEM = generator.item.registerBlandItem(new CowBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "cow_bucket");
        CREEPER_BUCKET_ITEM = generator.item.registerBlandItem(new CreeperBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "creeper_bucket");
        DOLPHIN_BUCKET_ITEM = generator.item.registerBlandItem(new DolphinBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "dolphin_bucket");
        DONKEY_BUCKET_ITEM = generator.item.registerBlandItem(new DonkeyBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "donkey_bucket");
        ENDERMAN_BUCKET_ITEM = generator.item.registerBlandItem(new EndermanBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "enderman_bucket");
        FOX_BUCKET_ITEM = generator.item.registerBlandItem(new FoxBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "fox_bucket");
        HORSE_BUCKET_ITEM = generator.item.registerBlandItem(new HorseBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "horse_bucket");
        IRON_GOLEM_BUCKET_ITEM = generator.item.registerBlandItem(new IronGolemBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "iron_golem_bucket");
        LLAMA_BUCKET_ITEM = generator.item.registerBlandItem(new LlamaBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "llama_bucket");
        MOOSHROOM_BUCKET_ITEM = generator.item.registerBlandItem(new MooshroomBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "mooshroom_bucket");
        MULE_BUCKET_ITEM = generator.item.registerBlandItem(new MuleBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "mule_bucket");
        OCELOT_BUCKET_ITEM = generator.item.registerBlandItem(new OcelotBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "ocelot_bucket");
        PANDA_BUCKET_ITEM = generator.item.registerBlandItem(new PandaBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "panda_bucket");
        PARROT_BUCKET_ITEM = generator.item.registerBlandItem(new ParrotBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "parrot_bucket");
        PIG_BUCKET_ITEM = generator.item.registerBlandItem(new PigBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "pig_bucket");
        PIGLIN_BUCKET_ITEM = generator.item.registerBlandItem(new PiglinBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "piglin_bucket");
        POLAR_BEAR_BUCKET_ITEM = generator.item.registerBlandItem(new PolarBearBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "polar_bear_bucket");
        RABBIT_BUCKET_ITEM = generator.item.registerBlandItem(new RabbitBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "rabbit_bucket");
        SHEEP_BUCKET_ITEM = generator.item.registerBlandItem(new SheepBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "sheep_bucket");
        SKELETON_HORSE_BUCKET_ITEM = generator.item.registerBlandItem(new SkeletonHorseBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "skeleton_horse_bucket");
        SNOW_GOLEM_BUCKET_ITEM = generator.item.registerBlandItem(new SnowGolemBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "snow_golem_bucket");
        SQUID_BUCKET_ITEM = generator.item.registerBlandItem(new SquidBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "squid_bucket");
        STRIDER_BUCKET_ITEM = generator.item.registerBlandItem(new StriderBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "strider_bucket");
        TURTLE_BUCKET_ITEM = generator.item.registerBlandItem(new TurtleBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "turtle_bucket");
        VIlLAGER_BUCKET_ITEM = generator.item.registerBlandItem(new VillagerBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "villager_bucket");
        WANDERING_TRADER_BUCKET_ITEM = generator.item.registerBlandItem(new WanderingTraderBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "wandering_trader_bucket");
        WOLF_BUCKET_BUCKET_ITEM = generator.item.registerBlandItem(new WolfBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "wolf_bucket");
        BLAZE_BUCKET_BUCKET_ITEM = generator.item.registerBlandItem(new BlazeBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "blaze_bucket");
        ZOMBIE_BUCKET_ITEM = generator.item.registerBlandItem(new ZombieBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "zombie_bucket");
        ZOMBIFIED_PIGLIN_BUCKET_ITEM = generator.item.registerBlandItem(new ZombifiedPiglinBucketItem(new Item.Settings().group(MOB_BUCKETS_TAB)), "zombified_piglin_bucket");

        UseEntityCallback.EVENT.register(this::tryCaptureBee);
        UseEntityCallback.EVENT.register(this::tryCaptureCat);
        UseEntityCallback.EVENT.register(this::tryCaptureCaveSpider);
        UseEntityCallback.EVENT.register(this::tryCaptureChicken);
        UseEntityCallback.EVENT.register(this::tryCaptureCow);
        UseEntityCallback.EVENT.register(this::tryCaptureCreeper);
        UseEntityCallback.EVENT.register(this::tryCaptureDolphin);
        UseEntityCallback.EVENT.register(this::tryCaptureDonkey);
        UseEntityCallback.EVENT.register(this::tryCaptureEnderman);
        UseEntityCallback.EVENT.register(this::tryCaptureFox);
        UseEntityCallback.EVENT.register(this::tryCaptureHorse);
        UseEntityCallback.EVENT.register(this::tryCaptureIronGolem);
        UseEntityCallback.EVENT.register(this::tryCaptureLlama);
        UseEntityCallback.EVENT.register(this::tryCaptureMooshroom);
        UseEntityCallback.EVENT.register(this::tryCaptureMule);
        UseEntityCallback.EVENT.register(this::tryCaptureOcelot);
        UseEntityCallback.EVENT.register(this::tryCapturePanda);
        UseEntityCallback.EVENT.register(this::tryCaptureParrot);
        UseEntityCallback.EVENT.register(this::tryCapturePig);
        UseEntityCallback.EVENT.register(this::tryCapturePiglin);
        UseEntityCallback.EVENT.register(this::tryCapturePolarBear);
        UseEntityCallback.EVENT.register(this::tryCaptureRabbit);
        UseEntityCallback.EVENT.register(this::tryCaptureSheep);
        UseEntityCallback.EVENT.register(this::tryCaptureSkeletonHorse);
        UseEntityCallback.EVENT.register(this::tryCaptureSnowGolem);
        UseEntityCallback.EVENT.register(this::tryCaptureSquid);
        UseEntityCallback.EVENT.register(this::tryCaptureStrider);
        UseEntityCallback.EVENT.register(this::tryCaptureTurtle);
        UseEntityCallback.EVENT.register(this::tryCaptureVillager);
        UseEntityCallback.EVENT.register(this::tryCaptureWanderingTrader);
        UseEntityCallback.EVENT.register(this::tryCaptureWolf);
        UseEntityCallback.EVENT.register(this::tryCaptureBlaze);
        UseEntityCallback.EVENT.register(this::tryCaptureZombie);
        UseEntityCallback.EVENT.register(this::tryCaptureZombifiedPiglin);

        log(Level.INFO, "Mobbing the Buckets");
    }

    private ActionResult tryCaptureBee(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof BeeEntity
                && ((BeeEntity)entity).getHealth() > 0
        ) {
            BeeEntity mob = (BeeEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(BEE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, BeeBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureCat(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof CatEntity
                && ((CatEntity)entity).getHealth() > 0
        ) {
            CatEntity mob = (CatEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(CAT_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, CatBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureCaveSpider(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof CaveSpiderEntity
                && ((CaveSpiderEntity)entity).getHealth() > 0
        ) {
            CaveSpiderEntity mob = (CaveSpiderEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(CAVE_SPIDER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, CaveSpiderBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureChicken(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof ChickenEntity
                && ((ChickenEntity)entity).getHealth() > 0
        ) {
            ChickenEntity mob = (ChickenEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(CHICKEN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, ChickenBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureCow(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof CowEntity
                && ((CowEntity)entity).getHealth() > 0
        ) {
            CowEntity mob = (CowEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(COW_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, CowBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureCreeper(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof CreeperEntity
                && ((CreeperEntity)entity).getHealth() > 0
        ) {
            CreeperEntity mob = (CreeperEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(CREEPER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, CreeperBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureDolphin(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof DolphinEntity
                && ((DolphinEntity)entity).getHealth() > 0
        ) {
            DolphinEntity mob = (DolphinEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(DOLPHIN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, DolphinBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureDonkey(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof DonkeyEntity
                && ((DonkeyEntity)entity).getHealth() > 0
        ) {
            DonkeyEntity mob = (DonkeyEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(DONKEY_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, DonkeyBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureEnderman(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof EndermanEntity
                && ((EndermanEntity)entity).getHealth() > 0
        ) {
            EndermanEntity mob = (EndermanEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(ENDERMAN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, EndermanBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureFox(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof FoxEntity
                && ((FoxEntity)entity).getHealth() > 0
        ) {
            FoxEntity mob = (FoxEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(FOX_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, FoxBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureHorse(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof HorseEntity
                && ((HorseEntity)entity).getHealth() > 0
        ) {
            HorseEntity mob = (HorseEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(HORSE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, HorseBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureIronGolem(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof IronGolemEntity
                && ((IronGolemEntity)entity).getHealth() > 0
        ) {
            IronGolemEntity mob = (IronGolemEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(IRON_GOLEM_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, IronGolemBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureLlama(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof LlamaEntity
                && ((LlamaEntity)entity).getHealth() > 0
        ) {
            LlamaEntity mob = (LlamaEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(LLAMA_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, LlamaBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureMooshroom(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof MooshroomEntity
                && ((MooshroomEntity)entity).getHealth() > 0
        ) {
            MooshroomEntity mob = (MooshroomEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(MOOSHROOM_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, MooshroomBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureMule(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof MuleEntity
                && ((MuleEntity)entity).getHealth() > 0
        ) {
            MuleEntity mob = (MuleEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(MULE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, MuleBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureOcelot(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof OcelotEntity
                && ((OcelotEntity)entity).getHealth() > 0
        ) {
            OcelotEntity mob = (OcelotEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(OCELOT_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, OcelotBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCapturePanda(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof PandaEntity
                && ((PandaEntity)entity).getHealth() > 0
        ) {
            PandaEntity mob = (PandaEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(PANDA_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, PandaBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureParrot(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof ParrotEntity
                && ((ParrotEntity)entity).getHealth() > 0
        ) {
            ParrotEntity mob = (ParrotEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(PARROT_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, ParrotBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCapturePig(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof PigEntity
                && ((PigEntity)entity).getHealth() > 0
        ) {
            PigEntity mob = (PigEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(PIG_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, PigBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCapturePiglin(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof PiglinEntity
                && ((PiglinEntity)entity).getHealth() > 0
        ) {
            PiglinEntity mob = (PiglinEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(PIGLIN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, PiglinBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCapturePolarBear(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof PolarBearEntity
                && ((PolarBearEntity)entity).getHealth() > 0
        ) {
            PolarBearEntity mob = (PolarBearEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(POLAR_BEAR_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, PolarBearBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureRabbit(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof RabbitEntity
                && ((RabbitEntity)entity).getHealth() > 0
        ) {
            RabbitEntity mob = (RabbitEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(RABBIT_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, RabbitBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureSheep(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof SheepEntity
                && ((SheepEntity)entity).getHealth() > 0
        ) {
            SheepEntity mob = (SheepEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(SHEEP_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, SheepBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureSkeletonHorse(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof SkeletonHorseEntity
                && ((SkeletonHorseEntity)entity).getHealth() > 0
        ) {
            SkeletonHorseEntity mob = (SkeletonHorseEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(SKELETON_HORSE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, SkeletonHorseBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureSnowGolem(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof SnowGolemEntity
                && ((SnowGolemEntity)entity).getHealth() > 0
        ) {
            SnowGolemEntity mob = (SnowGolemEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(SNOW_GOLEM_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, SnowGolemBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureSquid(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof SquidEntity
                && ((SquidEntity)entity).getHealth() > 0
        ) {
            SquidEntity mob = (SquidEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(SQUID_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, SquidBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureStrider(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof StriderEntity
                && ((StriderEntity)entity).getHealth() > 0
        ) {
            StriderEntity mob = (StriderEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(STRIDER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, StriderBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureTurtle(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof TurtleEntity
                && ((TurtleEntity)entity).getHealth() > 0
        ) {
            TurtleEntity mob = (TurtleEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(TURTLE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, TurtleBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureVillager(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof VillagerEntity
                && ((VillagerEntity)entity).getHealth() > 0
        ) {
            VillagerEntity mob = (VillagerEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(VIlLAGER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, VillagerBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureWanderingTrader(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof WanderingTraderEntity
                && ((WanderingTraderEntity)entity).getHealth() > 0
        ) {
            WanderingTraderEntity mob = (WanderingTraderEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(WANDERING_TRADER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, WanderingTraderBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureWolf(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof WolfEntity
                && ((WolfEntity)entity).getHealth() > 0
        ) {
            WolfEntity mob = (WolfEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(WOLF_BUCKET_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, WolfBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureBlaze(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof BlazeEntity
                && ((BlazeEntity)entity).getHealth() > 0
        ) {
            BlazeEntity mob = (BlazeEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(BLAZE_BUCKET_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, BlazeBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureZombie(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof ZombieEntity
                && ((ZombieEntity)entity).getHealth() > 0
        ) {
            ZombieEntity mob = (ZombieEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(ZOMBIE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, ZombieBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private ActionResult tryCaptureZombifiedPiglin(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof ZombifiedPiglinEntity
                && ((ZombifiedPiglinEntity)entity).getHealth() > 0
        ) {
            ZombifiedPiglinEntity mob = (ZombifiedPiglinEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(ZOMBIFIED_PIGLIN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, ZombifiedPiglinBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}