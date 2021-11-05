package beer.ugcp.ExperimentalFabricMod.Items.MaterialPileItem;

import beer.ugcp.ExperimentalFabricMod.ExperimentalFabricMod;
import beer.ugcp.ExperimentalFabricMod.Utilities.Builders.ItemStackBuilder;
import beer.ugcp.ExperimentalFabricMod.Utilities.Builders.NBTBuilder;
import beer.ugcp.ExperimentalFabricMod.Utilities.StringParser.ChemicalHelper;
import net.fabricmc.loader.util.sat4j.core.Vec;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;
import java.util.Vector;

public final class MaterialPileTools {
    public static ItemStack GenEmptyMaterialPile(){
        return new ItemStackBuilder(ExperimentalFabricMod.EXP_MP,1)
                .setNbt(new NBTBuilder()
                        .putNewNBT(MaterialPile.MATERIAL_PILE_INGREDIENTS_KEY)
                        .build())
                .build();
    }
    public static ItemStack GetMergedPile(List<ItemStack> stackList){
        Vector<Pair<String,Integer>> targetIngredients = new Vector<>();
        for(ItemStack stack : stackList){
            if(stack.getNbt() == null) continue;
            if(!stack.getNbt().contains(MaterialPile.MATERIAL_PILE_INGREDIENTS_KEY)) continue;
            if(stack.getNbt().getCompound(MaterialPile.MATERIAL_PILE_INGREDIENTS_KEY).isEmpty()) continue;
            NbtCompound ingredients = stack.getNbt().getCompound(MaterialPile.MATERIAL_PILE_INGREDIENTS_KEY);
            for(String entry : ingredients.getKeys()){
                ChemicalHelper.addComponent(targetIngredients, entry, ingredients.getInt(entry));
            }
        }
        NbtCompound nbt = new NBTBuilder()
                .putNewNBT(MaterialPile.MATERIAL_PILE_INGREDIENTS_KEY)
                .build();
        for(Pair<String,Integer> i : targetIngredients){
            nbt.getCompound(MaterialPile.MATERIAL_PILE_INGREDIENTS_KEY).putInt(i.getLeft(),i.getRight());
        }
        return new ItemStackBuilder(ExperimentalFabricMod.EXP_MP,1)
                .setNbt(nbt)
                .build();
    }
}
