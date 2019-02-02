package com.latmod.mods.botanicbonsai;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = BotanicBonsai.MOD_ID)
@Config(modid = BotanicBonsai.MOD_ID, category = "")
public class BotanicBonsaiConfig
{
	public static final General general = new General();

	public static class General
	{
		@Config.RangeInt(min = 1, max = 20)
		@Config.Comment("Radius of how many Botanic Bonsai Pots can the Manager see.")
		public int manager_radius = 4;

		@Config.RangeInt(min = 1, max = 32000)
		@Config.Comment("Ticks it takes for manager to update.")
		public int manager_update_ticks = 60;

		@Config.RangeInt(min = 1)
		public int manager_mana_capacity = 1600;

		@Config.RangeInt(min = 1)
		public int mana_per_boost = 20;
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(BotanicBonsai.MOD_ID))
		{
			ConfigManager.sync(BotanicBonsai.MOD_ID, Config.Type.INSTANCE);
		}
	}
}