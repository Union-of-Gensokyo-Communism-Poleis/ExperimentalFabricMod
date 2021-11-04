package beer.ugcp.ExperimentalFabricMod.Utilities.Builders;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

/**
 * <h1>This class provides build for ItemStack</h1>
 */
public final class ItemStackBuilder {
    private final ItemStack itemStack;

    /**
     * <h3>Constructor</h3>
     * @param item Item instance
     * @param count Count
     */
    public ItemStackBuilder(Item item, int count){
        itemStack = new ItemStack(item, count);
    }

    /**
     * <h3>Set NBT of target ItemStack</h3>
     * @param nbtCompound NBT
     * @return Builder itself
     */
    public ItemStackBuilder setNbt(NbtCompound nbtCompound){
        this.itemStack.setNbt(nbtCompound);
        return this;
    }

    /**
     * <h3>Build up the instance of ItemStack</h3>
     * @return Target instance of ItemStack
     */
    public ItemStack build(){
        return this.itemStack;
    }
}
