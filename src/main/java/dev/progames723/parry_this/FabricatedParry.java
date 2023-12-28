package dev.progames723.parry_this;

import fuzs.puzzleslib.api.entity.v1.DamageSourcesHelper;
import fuzs.puzzleslib.api.event.v1.core.EventResult;
import fuzs.puzzleslib.api.event.v1.data.MutableFloat;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingHurtCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricatedParry implements ModInitializer {
	//Если не запускается то пропиши .\gradlew.bat clean и .\gradlew.bat --refresh-dependencies
	public static boolean parry = false;
	public static boolean lateParry = false;
	public static double vulnerable = 0.0;
	public static double resistant = 0.0;
	public static float eventDamage = 0.0f;

	public static final String MOD_ID = "parry_this";
	public static final Logger LOGGER = LoggerFactory.getLogger("Fabricated Parry");
	public static void sendMessage(Object message, PlayerEntity player){
		player.sendMessage(Text.of(String.valueOf(message)));
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized!");
		ModItems.registerModItems();
		// НЕ ТРОГАТЬ, КОД РАБОЧИЙ
		UseItemCallback.EVENT.register((player, world, hand) -> {
			if (player.getInventory().getMainHandStack().isIn(ItemTags.SWORDS) && !player.getItemCooldownManager().isCoolingDown(player.getInventory().getMainHandStack().getItem())){
				player.addExperience(1);
				player.getItemCooldownManager().set(player.getInventory().getMainHandStack().getItem(), 10);
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0), player);
				//TODO add parrying

				LOGGER.info("A sword");
				return TypedActionResult.success(player.getActiveItem());
			} else {
				LOGGER.info("Not a sword");
				return TypedActionResult.pass(player.getActiveItem());
			}
		});
	}
}