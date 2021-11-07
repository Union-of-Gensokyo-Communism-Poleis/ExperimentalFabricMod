package beer.ugcp.ExperimentalFabricMod.Recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;
import java.util.Vector;

import static beer.ugcp.ExperimentalFabricMod.ExperimentalFabricMod.EXP_MP;
import static beer.ugcp.ExperimentalFabricMod.Items.MaterialPileItem.MaterialPileTools.GetMergedPile;
public class MaterialPileRecipe implements Recipe<CraftingInventory>, CraftingRecipe {
    private static final RecipeSerializer<ShapelessRecipe> SERIALIZER = RecipeSerializer.SHAPELESS;
    private final DefaultedList<Ingredient> input;
    private final ItemStack result = new ItemStack(EXP_MP,1);
    private final Identifier id;

    public MaterialPileRecipe(Identifier id,DefaultedList<Ingredient> input) {
        this.input = input;
        this.id = id;
    }


    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        for(int i =0; i<inventory.size();i++){
            if(inventory.getStack(i).getItem() != EXP_MP &&
                    inventory.getStack(i).getItem() != Items.AIR) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        List<ItemStack> list = new Vector<>();
        for(int i =0; i<inventory.size();i++){
            if(inventory.getStack(i).getItem() == EXP_MP){
                list.add(inventory.getStack(i));
            }
        }
        return GetMergedPile(list);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= this.input.size();
    }

    @Override
    public ItemStack getOutput() {
        return this.result;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    public static class Type implements RecipeType<MaterialPileRecipe>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "material_pile_recipe";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
}
