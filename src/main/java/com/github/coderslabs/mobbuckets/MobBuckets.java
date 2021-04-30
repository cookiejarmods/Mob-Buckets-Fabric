package com.github.coderslabs.mobbuckets;

import com.github.coderslabs.mobbuckets.init.MobBucketItems;
import com.github.coderslabs.mobbuckets.util.IItemMaxCount;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.coderslabs.mobbuckets.init.MobBucketItems.*;

public class MobBuckets implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static String MOD_ID = "mobbuckets";
    public static String MOD_NAME = "Mob Buckets";

    public static ItemGroup MOB_BUCKETS_TAB = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "mob_buckets_tab"), () -> new ItemStack(CREEPER_BUCKET_ITEM));

    @Override
    public void onInitialize() {
        MobBucketItems.init();

        ((IItemMaxCount)Items.WATER_BUCKET).setMaxCount(16);
        ((IItemMaxCount)Items.LAVA_BUCKET).setMaxCount(16);
        ((IItemMaxCount)Items.MILK_BUCKET).setMaxCount(16);

        log(Level.INFO, "Mobing the Buckets");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}