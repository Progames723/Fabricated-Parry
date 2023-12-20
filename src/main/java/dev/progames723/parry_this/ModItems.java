package dev.progames723.parry_this;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
	public static final Item OP_STAR = registerItem("thisshitisopasf", new Item(new FabricItemSettings().maxCount(10).fireproof().rarity(Rarity.EPIC)));
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(FabricatedParry.MOD_ID, name), item);
	}
	private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
		entries.add(OP_STAR);
	}
	public static void registerModItems() {
		FabricatedParry.LOGGER.info("Registering Mod Items for " + FabricatedParry.MOD_ID);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
	}
}
