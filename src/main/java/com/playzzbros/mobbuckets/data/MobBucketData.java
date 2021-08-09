package com.playzzbros.mobbuckets.data;

import com.playzzbros.mobbuckets.MobBuckets;
import com.playzzbros.mobbuckets.data.basic.MobBucketsStates;
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler;
import me.shedaniel.cloth.api.datagen.v1.ModelStateData;
import org.apache.logging.log4j.Level;

import java.nio.file.Paths;

public class MobBucketData {
    public static final DataGeneratorHandler handler = DataGeneratorHandler.create(Paths.get("../src/generated/resources"));

    public static void init() {
        ModelStateData modelStates = handler.getModelStates();

        MobBucketsStates.init(modelStates);

        MobBuckets.log(Level.INFO, "Data Mob Buckets Loaded!");
    }
}