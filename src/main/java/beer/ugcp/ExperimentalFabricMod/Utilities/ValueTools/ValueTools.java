package beer.ugcp.ExperimentalFabricMod.Utilities.ValueTools;

import beer.ugcp.ExperimentalFabricMod.ExperimentalFabricMod;
import beer.ugcp.ExperimentalFabricMod.Items.MaterialPileItem.MaterialPile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public final class ValueTools {
    public static byte getIronStoneGrade(ItemStack itemStack) throws IllegalArgumentException, IllegalStateException{
        if(!itemStack.isOf(ExperimentalFabricMod.EXP_MP)){
            throw new IllegalArgumentException("getIronStoneGrade(ItemStack) only accepts MaterialPile.");
        }
        if(itemStack.getNbt() == null){
            throw new IllegalStateException("NBT is null.");
        }
        assert itemStack.getItem() == ExperimentalFabricMod.EXP_MP;
        NbtCompound compound = itemStack.getNbt().getCompound(MaterialPile.MATERIAL_PILE_INGREDIENTS_KEY);
        return 0;//todo: Calculator of iron stone grade.
    }
}
