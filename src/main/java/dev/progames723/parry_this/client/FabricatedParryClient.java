package dev.progames723.parry_this.client;

import dev.progames723.parry_this.FabricatedParry;
import net.fabricmc.api.ClientModInitializer;

public class FabricatedParryClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		FabricatedParry.LOGGER.info("insane");
	}
}
