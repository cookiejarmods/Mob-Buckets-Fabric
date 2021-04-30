package com.github.coderslabs.mobbuckets.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class ItemHelper {
    //Thanks to AllStackable by Conn_Lost under GNU 3.0
    private ItemHelper() {
    }

    public static void insertNewItem(PlayerEntity player, ItemStack stack2) {
        if (!player.inventory.insertStack(stack2)) {
            player.dropItem(stack2, false);
        }
        if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).refreshScreenHandler(player.playerScreenHandler);
        }
    }

    public static boolean isModified(ItemStack s){
        if (s.isEmpty()){
            return false;
        }
        Item i = s.getItem();
        return (((IItemMaxCount)i).getVanillaMaxCount()!=i.getMaxCount())&&s.getCount()>0;
    }
}