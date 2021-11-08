package beer.ugcp.ExperimentalFabricMod.Recipe;

import beer.ugcp.ExperimentalFabricMod.ExperimentalFabricMod;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;
import java.util.Vector;

import static beer.ugcp.ExperimentalFabricMod.ExperimentalFabricMod.EXP_MP;
import static beer.ugcp.ExperimentalFabricMod.Items.MaterialPileItem.MaterialPileTools.GetMergedPile;
public class MaterialPileRecipe implements Recipe<CraftingInventory>, CraftingRecipe {
    private static final MaterialPileRecipeSerializer SERIALIZER = MaterialPileRecipeSerializer.getInstance();
    private ItemStack result = new ItemStack(EXP_MP,1);
    private final Identifier id;

    public MaterialPileRecipe() {
        this.id = new Identifier(ExperimentalFabricMod.MOD_ID,"material_pile_recipe");
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
        result = GetMergedPile(list);
        return result;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return result;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    public static class Type implements RecipeType<MaterialPileRecipe>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "material_pile_recipe_type";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public class MPRecipeFormat {
        public MPRecipeFormat(){

        }
    }

    public static class MaterialPileRecipeSerializer implements RecipeSerializer<MaterialPileRecipe>{

        private MaterialPileRecipeSerializer(){}
        public static final MaterialPileRecipeSerializer INSTANCE = new MaterialPileRecipeSerializer();
        public static MaterialPileRecipeSerializer getInstance(){return INSTANCE;}
        @Override
        public MaterialPileRecipe read(Identifier id, JsonObject json) {
            return new MaterialPileRecipe();
        }

        @Override
        public MaterialPileRecipe read(Identifier id, PacketByteBuf buf) {
            return new MaterialPileRecipe();
        }

        @Override
        public void write(PacketByteBuf buf, MaterialPileRecipe recipe) {
            buf.writeItemStack(recipe.result);
        }
    }
}
