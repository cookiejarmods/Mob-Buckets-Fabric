package com.playzzbros.mobbuckets.datagen.impl;

import com.playzzbros.mobbuckets.MobBuckets;
import com.playzzbros.mobbuckets.datagen.impl.provider.MobBucketsModelsProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apache.logging.log4j.Level;

public class MobBucketsDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(MobBucketsModelsProvider::new);
        MobBuckets.log(Level.INFO, "Mob Buckets Data Loaded!");
    }
}