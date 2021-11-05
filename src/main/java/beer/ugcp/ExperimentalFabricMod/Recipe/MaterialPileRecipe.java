package beer.ugcp.ExperimentalFabricMod.Recipe;

import beer.ugcp.ExperimentalFabricMod.ExperimentalFabricMod;
import beer.ugcp.ExperimentalFabricMod.Items.MaterialPileItem.MaterialPile;
import beer.ugcp.ExperimentalFabricMod.Utilities.Builders.ItemStackBuilder;
import beer.ugcp.ExperimentalFabricMod.Utilities.Builders.NBTBuilder;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;
import java.util.Vector;

import static beer.ugcp.ExperimentalFabricMod.Items.MaterialPileItem.MaterialPileTools.GetMergedPile;
public class MaterialPileRecipe implements Recipe<CraftingInventory> {

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        return false;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return null;
    }

    @Override
    public Identifier getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
}
