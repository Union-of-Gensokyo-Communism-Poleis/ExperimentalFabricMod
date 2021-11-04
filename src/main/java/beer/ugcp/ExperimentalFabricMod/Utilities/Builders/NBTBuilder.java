package beer.ugcp.ExperimentalFabricMod.Utilities.Builders;

import net.minecraft.nbt.NbtCompound;

import java.util.Stack;

public final class NBTBuilder {
    private final Stack<NbtCompound> nbtStack;
    public NBTBuilder(){
        nbtStack = new Stack<>();
        nbtStack.push(new NbtCompound());
    }
    public NBTBuilder putNewNBT(String key){
        NbtCompound newCompound = new NbtCompound();
        nbtStack.peek().put(key,newCompound);
        nbtStack.push(newCompound);
        return this;
    }
    public NBTBuilder putInt(String key, int i){
        nbtStack.peek().putInt(key,i);
        return this;
    }
    public NBTBuilder getBack(){
        nbtStack.pop();
        return this;
    }
    public NbtCompound build(){
        return nbtStack.get(0);
    }
}
