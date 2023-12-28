package dev.progames723.parry_this;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class DamageTypes {
	public static final RegistryKey<DamageType> DAMAGE_TYPE_PENETRATION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(FabricatedParry.MOD_ID, "penetration"));

	@NotNull
	public static DamageSource of(@NotNull World world, RegistryKey<DamageType> key) {
		return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
	}
}
