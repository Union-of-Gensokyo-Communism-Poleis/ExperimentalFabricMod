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
     * <h3>Constructor provided by Mojang</h3>
     */
    public MaterialPile(Settings settings) {
        super(settings);
    }
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
    /**
     * <h3>implements right click use method</h3>
     * @param world Current world.
     * @param user  Player who used the item.
     * @param hand  Hand that player used with.
     * @return Action result.
     */
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        NbtCompound nbtCompound =
                itemStack.getNbt() != null?
                itemStack.getNbt().getCompound(MATERIAL_PILE_INGREDIENTS_KEY)
                : new NbtCompound();
        for (String i: nbtCompound.getKeys()) {
            System.out.println(i+": " +nbtCompound.getInt(i));
        }
        return TypedActionResult.consume(itemStack);
    }
}
