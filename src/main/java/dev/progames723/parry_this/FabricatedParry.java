package dev.progames723.parry_this;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.TypedActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricatedParry implements ModInitializer {
	//Если не запускается то пропиши .\gradlew.bat clean и .\gradlew.bat --refresh-dependencies
	public static boolean parry = false;
	public static boolean lateParry = false;
	public static double vulnerable = 0.0;
	public static double resistant = 0.0;

	public static final String MOD_ID = "parry_this";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static void waitTicks(int ticks, Runnable action) {
		//TODO Server wait n-ticks when called lol
	}

	@Override
	public void onInitialize() {
		//THIS IS A BREAKTHROUGH
		DamageType damageTypePenetration = new DamageType("penetration", DamageScaling.NEVER, 0.1f, DamageEffects.HURT);
		DamageSource damageSourcePenetration = new DamageSource(RegistryEntry.of(damageTypePenetration));
		//CUSTOM DAMAGE SOURCE THAT'S SO INSANE
		LOGGER.info("(fabricated_parry) Initialized!");
		ModItems.registerModItems();
		// НЕ ТРОГАТЬ, КОД РАБОЧИЙ
		UseItemCallback.EVENT.register((player, world, hand) -> {
			if (player.getInventory().getMainHandStack().isIn(ItemTags.SWORDS)){
				player.addExperience(1);
				//TODO add parrying
				LOGGER.info("A sword");
				return TypedActionResult.success(player.getActiveItem());
			} else {
				LOGGER.info("Not a sword");
				return TypedActionResult.pass(player.getActiveItem());
			}
		});
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((livingEntity, damageSource, damage) -> {
			livingEntity.damage(damageSourcePenetration, damage);
			return true;
		});
	}
}