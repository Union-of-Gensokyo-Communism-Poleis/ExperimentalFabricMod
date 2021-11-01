package beer.ugcp.ExperimentalFabricMod;

import beer.ugcp.ExperimentalFabricMod.Items.EXPItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
			()->new ItemStack(ExperimentalFabricMod.FABRIC_ITEM)
	);

	public static final EXPItem FABRIC_ITEM = new EXPItem(
			new FabricItemSettings().group(ExperimentalFabricMod.EFM_Group)
	);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.ITEM, new Identifier(MOD_ID,"fabric_item"), FABRIC_ITEM);
		LOGGER.info("Hello Fabric world!");
	}
}