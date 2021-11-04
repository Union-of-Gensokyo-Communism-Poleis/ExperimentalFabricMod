package beer.ugcp.ExperimentalFabricMod.Items.MaterialPileItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * Universal material pile class.
 */
public class MaterialPile extends Item {
    public static final String MATERIAL_PILE_INGREDIENTS_KEY = "Ingredients";
    /**
     * <h3>NBT structure of Material Pile</h3>
     * <pre>
     * "Ingredients":
     * {
     *     "Component-A":int,
     *     "Component-B":int
     *     ...
     * }
     *</pre>
     * @param iStack ItemStack
     * @return Nbt compound of this stack.
     */
    public static NbtCompound GetMaterialPileNbt(ItemStack iStack){
        NbtCompound nbtCompound = iStack.getNbt();
        return nbtCompound !=null?
                nbtCompound.getCompound(MATERIAL_PILE_INGREDIENTS_KEY)
                : new NbtCompound();
    }
    public MaterialPile(Settings settings) {
        super(settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        NbtCompound nbtCompound = itemStack.getNbt();
        assert nbtCompound != null;
        for (String i: nbtCompound.getCompound(MATERIAL_PILE_INGREDIENTS_KEY).getKeys()) {
            System.out.println(i+": "
                    +nbtCompound.getCompound(MATERIAL_PILE_INGREDIENTS_KEY).getInt(i));
        }
        return TypedActionResult.consume(itemStack);
    }
}
