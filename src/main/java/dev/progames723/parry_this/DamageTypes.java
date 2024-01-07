package dev.progames723.parry_this;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

public class DamageTypes {
	public static final RegistryKey<DamageType> weDontTalkAboutThisOne = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(FabricatedParry.MOD_ID, "penetration"));

	@NotNull
	public static DamageSource of(@NotNull World world, RegistryKey<DamageType> key) {
		return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
	@NotNull
	public static DamageSource of(@NotNull PlayerEntity entity, RegistryKey<DamageType> key) {
		return new DamageSource(entity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
	@NotNull
	public static DamageSource of(@NotNull MinecraftServer server, RegistryKey<DamageType> key) {
		return new DamageSource(server.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
	@NotNull
	public static DamageSource of(@NotNull WorldView worldView, RegistryKey<DamageType> key) {
		return new DamageSource(worldView.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
	@NotNull
	public static DamageSource applyArmorIgnoringDamageTags(@NotNull PlayerEntity entity, RegistryKey<DamageType> key){
		World world = entity.getWorld();
		TagKey<DamageType> damageTypeTag1 = DamageTypeTags.BYPASSES_ARMOR;
		TagKey<DamageType> damageTypeTag2 = DamageTypeTags.BYPASSES_COOLDOWN;
		TagKey<DamageType> damageTypeTag3 = DamageTypeTags.BYPASSES_EFFECTS;
		TagKey<DamageType> damageTypeTag4 = DamageTypeTags.BYPASSES_ENCHANTMENTS;
		TagKey<DamageType> damageTypeTag5 = DamageTypeTags.BYPASSES_SHIELD;
		TagKey<DamageType> damageTypeTag6 = DamageTypeTags.BYPASSES_INVULNERABILITY;
		TagKey<DamageType> damageTypeTag7 = DamageTypeTags.BYPASSES_RESISTANCE;
		Vector<TagKey<DamageType>> vector = new Vector<>(6);
		vector.add(damageTypeTag1);
		vector.add(damageTypeTag2);
		vector.add(damageTypeTag3);
		vector.add(damageTypeTag4);
		vector.add(damageTypeTag5);
		vector.add(damageTypeTag6);
		vector.add(damageTypeTag7);
		Enumeration<TagKey<DamageType>> enumeration = vector.elements();
		Collection<TagKey<DamageType>> damageTypeTags = Collections.list(enumeration);
		world.getDamageSources().registry.entryOf(key).setTags(damageTypeTags);
		return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
	@NotNull
	public static DamageSource applyArmorIgnoringDamageTags(@NotNull World world, RegistryKey<DamageType> key){
		TagKey<DamageType> damageTypeTag1 = DamageTypeTags.BYPASSES_ARMOR;
		TagKey<DamageType> damageTypeTag2 = DamageTypeTags.BYPASSES_COOLDOWN;
		TagKey<DamageType> damageTypeTag3 = DamageTypeTags.BYPASSES_EFFECTS;
		TagKey<DamageType> damageTypeTag4 = DamageTypeTags.BYPASSES_ENCHANTMENTS;
		TagKey<DamageType> damageTypeTag5 = DamageTypeTags.BYPASSES_SHIELD;
		TagKey<DamageType> damageTypeTag6 = DamageTypeTags.BYPASSES_INVULNERABILITY;
		TagKey<DamageType> damageTypeTag7 = DamageTypeTags.BYPASSES_RESISTANCE;
		Vector<TagKey<DamageType>> vector = new Vector<>(6);
		vector.add(damageTypeTag1);
		vector.add(damageTypeTag2);
		vector.add(damageTypeTag3);
		vector.add(damageTypeTag4);
		vector.add(damageTypeTag5);
		vector.add(damageTypeTag6);
		vector.add(damageTypeTag7);
		Enumeration<TagKey<DamageType>> enumeration = vector.elements();
		Collection<TagKey<DamageType>> damageTypeTags = Collections.list(enumeration);
		world.getDamageSources().registry.entryOf(key).setTags(damageTypeTags);
		return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
	@NotNull
	public static DamageSource applyArmorIgnoringDamageTags(@NotNull MinecraftServer server, RegistryKey<DamageType> key){
		World world = server.getOverworld();
		TagKey<DamageType> damageTypeTag1 = DamageTypeTags.BYPASSES_ARMOR;
		TagKey<DamageType> damageTypeTag2 = DamageTypeTags.BYPASSES_COOLDOWN;
		TagKey<DamageType> damageTypeTag3 = DamageTypeTags.BYPASSES_EFFECTS;
		TagKey<DamageType> damageTypeTag4 = DamageTypeTags.BYPASSES_ENCHANTMENTS;
		TagKey<DamageType> damageTypeTag5 = DamageTypeTags.BYPASSES_SHIELD;
		TagKey<DamageType> damageTypeTag6 = DamageTypeTags.BYPASSES_INVULNERABILITY;
		TagKey<DamageType> damageTypeTag7 = DamageTypeTags.BYPASSES_RESISTANCE;
		Vector<TagKey<DamageType>> vector = new Vector<>(6);
		vector.add(damageTypeTag1);
		vector.add(damageTypeTag2);
		vector.add(damageTypeTag3);
		vector.add(damageTypeTag4);
		vector.add(damageTypeTag5);
		vector.add(damageTypeTag6);
		vector.add(damageTypeTag7);
		Enumeration<TagKey<DamageType>> enumeration = vector.elements();
		Collection<TagKey<DamageType>> damageTypeTags = Collections.list(enumeration);
		world.getDamageSources().registry.entryOf(key).setTags(damageTypeTags);
		return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
}
