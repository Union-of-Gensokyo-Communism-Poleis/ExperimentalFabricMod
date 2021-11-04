package beer.ugcp.ExperimentalFabricMod;

import beer.ugcp.ExperimentalFabricMod.Items.EXPItem;
import beer.ugcp.ExperimentalFabricMod.Items.MaterialPileItem.MaterialPile;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.util.registry.Registry;

public class ExperimentalFabricMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "experimental-fabric-mod";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final ItemGroup EFM_Group = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "general"),
			()->new ItemStack(ExperimentalFabricMod.EXP_ITEM)
	);

	public static final EXPItem EXP_ITEM = new EXPItem(
			new FabricItemSettings().group(ExperimentalFabricMod.EFM_Group).maxCount(64)
	);
	public static final MaterialPile EXP_MP = new MaterialPile(
			new FabricItemSettings().group(ExperimentalFabricMod.EFM_Group).maxCount(1)
	);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"exp_item"), EXP_ITEM);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "material_pile"), EXP_MP);
		LOGGER.info("Hello Fabric world!");
	}
}
