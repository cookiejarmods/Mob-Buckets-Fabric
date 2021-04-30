package com.github.coderslabs.mobbuckets.extra;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MobGetter {
    public static Map<EntityType<?>, Item> dictionary = new HashMap();

    public MobGetter() {
    }

    public static ItemStack getBucket(Entity entity) {
        EntityType<?> type = entity.getType();
        return new ItemStack((ItemConvertible)dictionary.get(type));
    }
}
