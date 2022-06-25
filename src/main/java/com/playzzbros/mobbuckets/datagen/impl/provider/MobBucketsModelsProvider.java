package com.playzzbros.mobbuckets.datagen.impl.provider;

import com.playzzbros.mobbuckets.MobBuckets;
import com.playzzbros.mobbuckets.init.MobBucketItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockStateDefinitionProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;

public class MobBucketsModelsProvider extends FabricBlockStateDefinitionProvider {
    public MobBucketsModelsProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        try {
            Class<MobBucketItems> itemList = MobBucketItems.class;
            Field[] values = itemList.getFields();
            int valuesLength = values.length;

            for(int valuesLengthZero = 0; valuesLengthZero < valuesLength; ++valuesLengthZero) {
                Field item = values[valuesLengthZero];
                itemModelGenerator.register((Item)item.get(MobBucketItems.class), Models.GENERATED);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            MobBuckets.log(Level.INFO, "MobBucketsStates Data Failed!");
            System.exit(1);
        }
        MobBuckets.log(Level.INFO, "MobBucketsStates Data Loaded!");
    }
}