package beer.ugcp.ExperimentalFabricMod.Utilities.Builders;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import org.lwjgl.system.CallbackI;

import java.util.Stack;

public final class NBTBuilder {
    private final Stack<NbtCompound> nbtStack;
    public NBTBuilder(){
        nbtStack = new Stack<>();
        nbtStack.push(new NbtCompound());
    }
    public NBTBuilder(String string) throws CommandSyntaxException{
        nbtStack = new Stack<>();
        StringReader sr = new StringReader(string);
        StringNbtReader snr = new StringNbtReader(sr);
        nbtStack.push(snr.parseCompound());
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
    public NBTBuilder enterCompound(String key){
        if(!nbtStack.peek().contains(key)){
            return this.putNewNBT(key);
        }
        nbtStack.push(nbtStack.peek().getCompound(key));
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
