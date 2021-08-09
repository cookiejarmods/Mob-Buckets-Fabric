package com.playzzbros.mobbuckets.data.basic;

import com.playzzbros.mobbuckets.MobBuckets;
import com.playzzbros.mobbuckets.data.MobBucketData;
import me.shedaniel.cloth.api.datagen.v1.ModelStateData;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Pair;
import org.apache.logging.log4j.Level;

public class MobBucketsStates {
    public static void init(ModelStateData modelStates) {
        try {
            for (Pair<Item, EntityType> bucketsLists : MobBuckets.bucketsList) {
                modelStates.addGeneratedItemModel(bucketsLists.getLeft());
            }

            MobBucketData.handler.run();
//            System.exit(0);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            MobBuckets.log(Level.INFO, "MobBucketsStates Data Failed!");
            System.exit(1);
        }
        MobBuckets.log(Level.INFO, "MobBucketsStates Data Loaded!");
    }
}