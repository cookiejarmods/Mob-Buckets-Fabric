package com.github.coderslabs.mobbuckets;

import com.github.coderslabs.mobbuckets.init.MobBucketItems;
import com.github.coderslabs.mobbuckets.item.*;
import com.github.coderslabs.mobbuckets.mixin.accessor.ItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
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

import static com.github.coderslabs.mobbuckets.init.MobBucketItems.*;
import static com.github.coderslabs.mobbuckets.item.MainMobBucketInfo.*;

public class MobBuckets implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static String MOD_ID = "mobbuckets";
    public static String MOD_NAME = "Mob Buckets";

    public static ItemGroup MOB_BUCKETS_TAB = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "mob_buckets_tab"), () -> new ItemStack(CREEPER_BUCKET_ITEM));

    @Override
    public void onInitialize() {
        MobBucketItems.init();

        ((ItemAccessor)Items.WATER_BUCKET).setMaxCount(16);
        ((ItemAccessor)Items.LAVA_BUCKET).setMaxCount(16);
        ((ItemAccessor)Items.MILK_BUCKET).setMaxCount(16);

        UseEntityCallback.EVENT.register(this::tryCaptureBat);
        UseEntityCallback.EVENT.register(this::tryCaptureBee);
        UseEntityCallback.EVENT.register(this::tryCaptureBlaze);
        UseEntityCallback.EVENT.register(this::tryCaptureCat);
        UseEntityCallback.EVENT.register(this::tryCaptureCaveSpider);
        UseEntityCallback.EVENT.register(this::tryCaptureChicken);
        UseEntityCallback.EVENT.register(this::tryCaptureCow);
        UseEntityCallback.EVENT.register(this::tryCaptureCreeper);
        UseEntityCallback.EVENT.register(this::tryCaptureDolphin);
        UseEntityCallback.EVENT.register(this::tryCaptureDonkey);
        UseEntityCallback.EVENT.register(this::tryCaptureDrowned);
        UseEntityCallback.EVENT.register(this::tryCaptureElderGuardian);
        UseEntityCallback.EVENT.register(this::tryCaptureEnderDragon);
        UseEntityCallback.EVENT.register(this::tryCaptureEnderman);
        UseEntityCallback.EVENT.register(this::tryCaptureEndermite);
        UseEntityCallback.EVENT.register(this::tryCaptureEvoker);
        UseEntityCallback.EVENT.register(this::tryCaptureFox);
        UseEntityCallback.EVENT.register(this::tryCaptureGhast);
        UseEntityCallback.EVENT.register(this::tryCaptureGuardian);
        UseEntityCallback.EVENT.register(this::tryCaptureHoglin);
        UseEntityCallback.EVENT.register(this::tryCaptureHorse);
        UseEntityCallback.EVENT.register(this::tryCaptureIronGolem);
        UseEntityCallback.EVENT.register(this::tryCaptureLlama);
        UseEntityCallback.EVENT.register(this::tryCaptureMagmaCube);
        UseEntityCallback.EVENT.register(this::tryCaptureRedMooshroom);
        UseEntityCallback.EVENT.register(this::tryCaptureBrownMooshroom);
        UseEntityCallback.EVENT.register(this::tryCaptureMule);
        UseEntityCallback.EVENT.register(this::tryCaptureOcelot);
        UseEntityCallback.EVENT.register(this::tryCapturePanda);
        UseEntityCallback.EVENT.register(this::tryCaptureParrot);
        UseEntityCallback.EVENT.register(this::tryCapturePhantom);
        UseEntityCallback.EVENT.register(this::tryCapturePig);
        UseEntityCallback.EVENT.register(this::tryCapturePiglinBrute);
        UseEntityCallback.EVENT.register(this::tryCapturePiglin);
        UseEntityCallback.EVENT.register(this::tryCapturePolarBear);
        UseEntityCallback.EVENT.register(this::tryCaptureRabbit);
        UseEntityCallback.EVENT.register(this::tryCaptureRavager);
        UseEntityCallback.EVENT.register(this::tryCaptureSheep);
        UseEntityCallback.EVENT.register(this::tryCaptureShulker);
        UseEntityCallback.EVENT.register(this::tryCaptureSkeleton);
        UseEntityCallback.EVENT.register(this::tryCaptureSkeletonHorse);
        UseEntityCallback.EVENT.register(this::tryCaptureSlime);
        UseEntityCallback.EVENT.register(this::tryCaptureSnowGolem);
        UseEntityCallback.EVENT.register(this::tryCaptureSquid);
        UseEntityCallback.EVENT.register(this::tryCaptureStray);
        UseEntityCallback.EVENT.register(this::tryCaptureColdStrider);
        UseEntityCallback.EVENT.register(this::tryCaptureWarmStrider);
        UseEntityCallback.EVENT.register(this::tryCaptureTurtle);
        UseEntityCallback.EVENT.register(this::tryCaptureVex);
        UseEntityCallback.EVENT.register(this::tryCaptureVillager);
        UseEntityCallback.EVENT.register(this::tryCaptureVindicator);
        UseEntityCallback.EVENT.register(this::tryCaptureWanderingTrader);
        UseEntityCallback.EVENT.register(this::tryCaptureWitch);
        UseEntityCallback.EVENT.register(this::tryCaptureWitherSkeleton);
        UseEntityCallback.EVENT.register(this::tryCaptureWolf);
        UseEntityCallback.EVENT.register(this::tryCaptureZoglin);
        UseEntityCallback.EVENT.register(this::tryCaptureZombieRelatedMobs);

        log(Level.INFO, "Mobing the Buckets");
    }

    private ActionResult tryCaptureZombieRelatedMobs(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        /* Capture Zombie */
        if (!entity.world.isClient && entity instanceof ZombieEntity && ((ZombieEntity)entity).getHealth() > 0) {
            ZombieEntity mob = (ZombieEntity) entity;
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
        } /* Capture Zombie Villager */ else if (!entity.world.isClient && entity instanceof ZombieVillagerEntity && ((ZombieVillagerEntity)entity).getHealth() > 0) {
            ZombieVillagerEntity mob = (ZombieVillagerEntity) entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(ZOMBIE_VILLAGER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, ZombieVillagerBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        } /* Capture Husk */ else if (!entity.world.isClient && entity instanceof HuskEntity && ((HuskEntity)entity).getHealth() > 0) {
            HuskEntity mob = (HuskEntity) entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(HUSK_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, HuskBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        } /* Capture Drowned */ else if (!entity.world.isClient && entity instanceof DrownedEntity && ((DrownedEntity)entity).getHealth() > 0) {
            DrownedEntity mob = (DrownedEntity) entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(DROWNED_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, DrownedBucketItem.STORED_MOB, mob.toTag(tag));

            if (held.getCount() == 1) {
                player.setStackInHand(hand, mobBucket);
            } else {
                held.decrement(1);
                addOrDropStack(player, mobBucket);
            }

            player.swingHand(hand);
            entity.remove();
            return ActionResult.SUCCESS;
        } /* Capture Zombified Piglin */ else if (!entity.world.isClient && entity instanceof ZombifiedPiglinEntity && ((ZombifiedPiglinEntity)entity).getHealth() > 0) {
            ZombifiedPiglinEntity mob = (ZombifiedPiglinEntity) entity;
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

    private ActionResult tryCaptureZoglin(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof ZoglinEntity
                && ((ZoglinEntity)entity).getHealth() > 0
        ) {
            ZoglinEntity mob = (ZoglinEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(ZOGLIN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, ZoglinBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureWitherSkeleton(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof WitherSkeletonEntity
                && ((WitherSkeletonEntity)entity).getHealth() > 0
        ) {
            WitherSkeletonEntity mob = (WitherSkeletonEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(WITHER_SKELETON_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, WitherSkeletonBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureWitch(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof WitchEntity
                && ((WitchEntity)entity).getHealth() > 0
        ) {
            WitchEntity mob = (WitchEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(WITCH_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, WitchBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureVindicator(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof VindicatorEntity
                && ((VindicatorEntity)entity).getHealth() > 0
        ) {
            VindicatorEntity mob = (VindicatorEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(VINDICATOR_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, VindicatorBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureVex(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof VexEntity
                && ((VexEntity)entity).getHealth() > 0
        ) {
            VexEntity mob = (VexEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(VEX_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, VexBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureStray(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof StrayEntity
                && ((StrayEntity)entity).getHealth() > 0
        ) {
            StrayEntity mob = (StrayEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(STRAY_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, StrayBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureSlime(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof SlimeEntity
                && ((SlimeEntity)entity).getHealth() > 0
        ) {
            SlimeEntity mob = (SlimeEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(SLIME_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, SlimeBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureSkeleton(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof SkeletonEntity
                && ((SkeletonEntity)entity).getHealth() > 0
        ) {
            SkeletonEntity mob = (SkeletonEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(SKELETON_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, SkeletonBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureShulker(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof ShulkerEntity
                && ((ShulkerEntity)entity).getHealth() > 0
        ) {
            ShulkerEntity mob = (ShulkerEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(SHULKER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, ShulkerBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureRavager(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof RavagerEntity
                && ((RavagerEntity)entity).getHealth() > 0
        ) {
            RavagerEntity mob = (RavagerEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(RAVAGER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, RavagerBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCapturePiglinBrute(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof PiglinBruteEntity
                && ((PiglinBruteEntity)entity).getHealth() > 0
        ) {
            PiglinBruteEntity mob = (PiglinBruteEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(PIGLIN_BRUTE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, PiglinBruteBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCapturePhantom(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof PhantomEntity
                && ((PhantomEntity)entity).getHealth() > 0
        ) {
            PhantomEntity mob = (PhantomEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(PHANTOM_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, PhantomBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureMagmaCube(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof MagmaCubeEntity
                && ((MagmaCubeEntity)entity).getHealth() > 0
        ) {
            MagmaCubeEntity mob = (MagmaCubeEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(MAGMA_CUBE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, MagmaCubeBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureHoglin(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof HoglinEntity
                && ((HoglinEntity)entity).getHealth() > 0
        ) {
            HoglinEntity mob = (HoglinEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(HOGLIN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, HoglinBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureGuardian(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof GuardianEntity
                && ((GuardianEntity)entity).getHealth() > 0
        ) {
            GuardianEntity mob = (GuardianEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(GUARDIAN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, GuardianBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureGhast(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof GhastEntity
                && ((GhastEntity)entity).getHealth() > 0
        ) {
            GhastEntity mob = (GhastEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(GHAST_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, GhastBucketItem.STORED_MOB, mob.toTag(tag));

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





    private ActionResult tryCaptureBat(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof BatEntity
                && ((BatEntity)entity).getHealth() > 0
        ) {
            BatEntity mob = (BatEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(BAT_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, BatBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureDrowned(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof DrownedEntity
                && ((DrownedEntity)entity).getHealth() > 0
        ) {
            DrownedEntity mob = (DrownedEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(DROWNED_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, DrownedBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureElderGuardian(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof ElderGuardianEntity
                && ((ElderGuardianEntity)entity).getHealth() > 0
        ) {
            ElderGuardianEntity mob = (ElderGuardianEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(ELDER_GUARDIAN_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, ElderGuardianBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureEnderDragon(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof EnderDragonEntity
                && ((EnderDragonEntity)entity).getHealth() > 0
        ) {
            EnderDragonEntity mob = (EnderDragonEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(ENDER_DRAGON_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, EnderDragonBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureEndermite(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof EndermiteEntity
                && ((EndermiteEntity)entity).getHealth() > 0
        ) {
            EndermiteEntity mob = (EndermiteEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(ENDERMITE_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, EndermiteBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureEvoker(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
        if (!entity.world.isClient
                && entity instanceof EvokerEntity
                && ((EvokerEntity)entity).getHealth() > 0
        ) {
            EvokerEntity mob = (EvokerEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(EVOKER_BUCKET_ITEM);
            CompoundTag tag = new CompoundTag();
            setCompound(mobBucket, EvokerBucketItem.STORED_MOB, mob.toTag(tag));

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

    private ActionResult tryCaptureRedMooshroom(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof MooshroomEntity
                && ((MooshroomEntity)entity).getHealth() > 0
                && MooshroomEntity.Type.RED == MooshroomEntity.Type.valueOf("red")
        ) {
            MooshroomEntity mob = (MooshroomEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(RED_MOOSHROOM_BUCKET_ITEM);
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

    private ActionResult tryCaptureBrownMooshroom(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof MooshroomEntity
                && ((MooshroomEntity)entity).getHealth() > 0
                && MooshroomEntity.Type.BROWN == MooshroomEntity.Type.valueOf("brown")
        ) {
            MooshroomEntity mob = (MooshroomEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(BROWN_MOOSHROOM_BUCKET_ITEM);
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

    private ActionResult tryCaptureColdStrider(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof StriderEntity
                && ((StriderEntity)entity).getHealth() > 0
        ) {
            StriderEntity mob = (StriderEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(COLD_STRIDER_BUCKET_ITEM);
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

    private ActionResult tryCaptureWarmStrider(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!entity.world.isClient
                && entity instanceof StriderEntity
                && ((StriderEntity)entity).getHealth() > 0
        ) {
            StriderEntity mob = (StriderEntity)entity;
            ItemStack held = player.getStackInHand(hand);

            if (held.isEmpty() || held.getItem() != Items.LAVA_BUCKET)
                return ActionResult.PASS;

            ItemStack mobBucket = new ItemStack(WARM_STRIDER_BUCKET_ITEM);
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

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}