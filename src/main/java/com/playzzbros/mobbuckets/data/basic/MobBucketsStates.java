package com.playzzbros.mobbuckets.data.basic;

import com.playzzbros.mobbuckets.MobBuckets;
import com.playzzbros.mobbuckets.data.MobBucketData;
import com.playzzbros.mobbuckets.init.MobBucketItems;
import me.shedaniel.cloth.api.datagen.v1.ModelStateData;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Pair;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.util.Collection;

public class MobBucketsStates {
    public static void init(ModelStateData modelStates) {
        try {
            Class<MobBucketItems> itemList = MobBucketItems.class;
            Field[] values = itemList.getFields();
            int valuesLength = values.length;

            for(int valuesLengthZero = 0; valuesLengthZero < valuesLength; ++valuesLengthZero) {
                Field item = values[valuesLengthZero];
                modelStates.addGeneratedItemModel((Item)item.get(MobBucketItems.class));
            }

            MobBucketData.handler.run();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            MobBuckets.log(Level.INFO, "MobBucketsStates Data Failed!");
            System.exit(1);
        }
        MobBuckets.log(Level.INFO, "MobBucketsStates Data Loaded!");
    }
}