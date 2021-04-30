//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.coderslabs.mobbuckets.extra;

import java.lang.reflect.Field;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
    public Main() {
    }

    public void onInitialize() {
        Class<ItemList> itemList = ItemList.class;
        Field[] var2 = itemList.getFields();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field item = var2[var4];

            try {
                Registry.register(Registry.ITEM, new Identifier("getinthebucketmod", item.getName()), (Item)item.get(ItemList.class));
                if (!item.getName().equals("bucket_of_entity")) {
                    BucketOfVanillaEntityItem moddedEntity = (BucketOfVanillaEntityItem)item.get(itemList);
                    MobGetter.dictionary.put(moddedEntity.getEntityType(), moddedEntity);
                }
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            }
        }

    }
}