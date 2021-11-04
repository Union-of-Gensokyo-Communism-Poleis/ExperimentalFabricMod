package beer.ugcp.ExperimentalFabricMod.Utilities.Builders;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;


public final class ItemStackBuilder {
    private final ItemStack itemStack;
    public ItemStackBuilder(Item item, int count){
        itemStack = new ItemStack(item, count);
    }
    public ItemStackBuilder setNbt(NbtCompound nbtCompound){
        this.itemStack.setNbt(nbtCompound);
        return this;
    }
    public ItemStack build(){
        return this.itemStack;
    }
}
