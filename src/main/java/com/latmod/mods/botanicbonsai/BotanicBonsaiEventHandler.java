package com.latmod.mods.botanicbonsai;

import com.latmod.mods.botanicbonsai.block.BlockBonsaiPotManager;
import com.latmod.mods.botanicbonsai.block.BlockBotanicBonsaiPot;
import com.latmod.mods.botanicbonsai.block.BotanicBonsaiBlocks;
import com.latmod.mods.botanicbonsai.block.TileBonsaiPotManager;
import com.latmod.mods.botanicbonsai.block.TileBotanicBonsaiPot;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = BotanicBonsai.MOD_ID)
public class BotanicBonsaiEventHandler
{
	private static Block withName(Block block, String name)
	{
		block.setCreativeTab(CreativeTabs.DECORATIONS);
		block.setRegistryName(name);
		block.setTranslationKey(BotanicBonsai.MOD_ID + "." + name);
		return block;
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(withName(new BlockBotanicBonsaiPot(), "botanic_bonsai_pot"));
		event.getRegistry().register(withName(new BlockBonsaiPotManager(), "bonsai_pot_manager"));

		GameRegistry.registerTileEntity(TileBotanicBonsaiPot.class, new ResourceLocation(BotanicBonsai.MOD_ID, "botanic_bonsai_pot"));
		GameRegistry.registerTileEntity(TileBonsaiPotManager.class, new ResourceLocation(BotanicBonsai.MOD_ID, "bonsai_pot_manager"));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new ItemBlock(BotanicBonsaiBlocks.BOTANIC_BONSAI_POT).setRegistryName("botanic_bonsai_pot"));
		event.getRegistry().register(new ItemBlock(BotanicBonsaiBlocks.BONSAI_POT_MANAGER).setRegistryName("bonsai_pot_manager"));
	}
}