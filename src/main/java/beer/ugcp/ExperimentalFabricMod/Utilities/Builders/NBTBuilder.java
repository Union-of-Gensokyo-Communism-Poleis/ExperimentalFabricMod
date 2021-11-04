package beer.ugcp.ExperimentalFabricMod.Utilities.Builders;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import org.lwjgl.system.CallbackI;

import java.util.Stack;

/**
 * <h1>This class provides builder for NBT tags.</h1>
 */
public final class NBTBuilder {
    private final Stack<NbtCompound> nbtStack;

    /**
     * <h3>Empty constructor</h3>
     */
    public NBTBuilder(){
        nbtStack = new Stack<>();
        nbtStack.push(new NbtCompound());
    }

    /**
     * <h3>Build NBT tags based on NBT string</h3>
     * @param string NBT string
     * @throws CommandSyntaxException when received invalid NBT string
     */
    public NBTBuilder(String string) throws CommandSyntaxException{
        nbtStack = new Stack<>();
        StringReader sr = new StringReader(string);
        StringNbtReader snr = new StringNbtReader(sr);
        nbtStack.push(snr.parseCompound());
    }

    /**
     * <h3>Put new NbtCompound</h3>
     * @param key Key
     * @return Builder itself
     */
    public NBTBuilder putNewNBT(String key){
        NbtCompound newCompound = new NbtCompound();
        nbtStack.peek().put(key,newCompound);
        nbtStack.push(newCompound);
        return this;
    }
    /**
     * <h3>Put new Int</h3>
     * @param key Key
     * @return Builder itself
     */
    public NBTBuilder putInt(String key, int i){
        nbtStack.peek().putInt(key,i);
        return this;
    }
    /**
     * <h3>Enter a NbtCompound, put a new instance if not exists</h3>
     * @param key Key
     * @return Builder itself
     */
    public NBTBuilder enterCompound(String key){
        if(!nbtStack.peek().contains(key)){
            return this.putNewNBT(key);
        }
        nbtStack.push(nbtStack.peek().getCompound(key));
        return this;
    }
    /**
     * <h3>Return to the previous layer</h3>
     * @return Builder itself
     */
    public NBTBuilder getBack(){
        nbtStack.pop();
        return this;
    }
    /**
     * <h3>Build up the NBT</h3>
     * @return NbtCompound
     */
    public NbtCompound build(){
        return nbtStack.get(0);
    }
}
