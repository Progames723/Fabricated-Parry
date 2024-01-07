package dev.progames723.parry_this;

import fuzs.puzzleslib.api.event.v1.core.EventResult;
import fuzs.puzzleslib.api.event.v1.data.MutableFloat;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingHurtCallback;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricatedParry implements ModInitializer {
	//Если не запускается то пропиши .\gradlew.bat clean и .\gradlew.bat --refresh-dependencies
	public static boolean perfectParry = false;
	public static boolean parry = false;
	public static boolean lateParry = false;
	public static double vulnerable = 0.0;
	public static double resistant = 0.0;
	public static int waitingTicks = 0;
	public static int cooldown = 0;
	public static float eventDamage = 0.0f;
	public static final float maxFloatValue = 340282356779733661637539395458142568447.999999999f; //help me
	public static final float minFloatValue = -340282356779733661637539395458142568447.999999999f; //help me
	public static int waitTicks = 0;
	public static Runnable runnable = null;
	public static final String MOD_ID = "parry_this";
	public static final Logger LOGGER = LoggerFactory.getLogger("Fabricated Parry");
	public static void sendMessage(Object message, PlayerEntity player){
		player.sendMessage(Text.of(String.valueOf(message)));
	}
	public static void waitNumberTicks(int ticks, Runnable action){
		//Usage: waitNumberTicks(anyInt, () -> {});
		//Using ServerTickEvents.START_WORLD_TICK for ticking
		if (ticks < 0){
			ticks = ticks * -1;
		}
		waitTicks = ticks;
		runnable = action;
	}
	@Override
	public void onInitialize() {
		LOGGER.info("Initialized!");
		PacketByteBuf data = PacketByteBufs.create();
		ModItems.registerModItems();
		// НЕ ТРОГАТЬ, КОД РАБОЧИЙ
		ServerTickEvents.START_WORLD_TICK.register((serverWorld) -> {
			MinecraftServer server = serverWorld.getServer();
			if (runnable != null) {
				if (waitTicks > 0){
					waitTicks--;
				} else if (waitTicks == 0) {
					runnable.run();
				}
			}
			if (cooldown > 20){
				cooldown = 20;
			} else if (cooldown < 0) {
				cooldown = cooldown*-1;
			}
			if (cooldown > 16 && cooldown <= 20){
				perfectParry = true;
				parry = false;
				lateParry = false;
			} else if (cooldown > 8 && cooldown <= 16){
				perfectParry = false;
				parry = true;
				lateParry = false;
			} else if (cooldown > 0 && cooldown <= 8){
				perfectParry = false;
				parry = false;
				lateParry = true;
			} else if (cooldown == 0){
				perfectParry = false;
				parry = false;
				lateParry = false;
			}
			if (cooldown > 0){
				cooldown--;
			}
		});
		LivingHurtCallback.EVENT.register((entity, damageSource, damage) -> {
			if (entity instanceof PlayerEntity){
				PlayerEntity player = (PlayerEntity) entity;
				if (player.getInventory().getMainHandStack().isIn(ItemTags.SWORDS) && player.getItemCooldownManager().isCoolingDown(player.getInventory().getMainHandStack().getItem())){
					if (perfectParry && !parry && !lateParry){
						Object message = Text.translatableWithFallback("perfect_parry.message", "A perfect parry! You ignored %1$s damage!", damage.getAsFloat());
						player.sendMessage(Text.of(String.valueOf(message)), true);
						return EventResult.INTERRUPT;
					} else if (!perfectParry && parry && !lateParry){
						eventDamage = damage.getAsFloat();
						damage = MutableFloat.fromValue((float) Math.pow(eventDamage, 0.05)*0.5f);
						return EventResult.PASS;
					} else if (!perfectParry && !parry && lateParry) {
						eventDamage = damage.getAsFloat();
						damage = MutableFloat.fromValue((float) (Math.pow(eventDamage, 1.25)*Math.pow(eventDamage, 0.5)));
						if (damage.getAsFloat() >= maxFloatValue){
							damage = MutableFloat.fromValue(10000);
						}
						return EventResult.PASS;
					}
				}
			}
			return EventResult.PASS;
		});
		UseItemCallback.EVENT.register((player, world, hand) -> {
			MinecraftServer server = world.getServer();
			ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
			if (player.getInventory().getMainHandStack().isIn(ItemTags.SWORDS) && !player.getItemCooldownManager().isCoolingDown(player.getInventory().getMainHandStack().getItem())){
				player.addExperience(1);
				player.getItemCooldownManager().set(player.getInventory().getMainHandStack().getItem(), 20);
				cooldown = 20;
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