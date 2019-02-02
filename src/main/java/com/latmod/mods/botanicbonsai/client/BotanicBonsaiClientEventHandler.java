package com.latmod.mods.botanicbonsai.client;

import com.latmod.mods.botanicbonsai.BotanicBonsai;
import com.latmod.mods.botanicbonsai.block.BotanicBonsaiBlocks;
import com.latmod.mods.botanicbonsai.block.TileBotanicBonsaiPot;
import com.latmod.mods.botanicbonsai.item.BotanicBonsaiItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.dave.bonsaitrees.block.BlockBonsaiPot;

/**
 * @author LatvianModder
 */
@Mod.EventBusSubscriber(modid = BotanicBonsai.MOD_ID, value = Side.CLIENT)
public class BotanicBonsaiClientEventHandler
{
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		ModelLoader.setCustomStateMapper(BotanicBonsaiBlocks.BOTANIC_BONSAI_POT, new StateMap.Builder().ignore(BlockBonsaiPot.IS_HOPPING).build());
		ModelLoader.setCustomModelResourceLocation(BotanicBonsaiItems.BOTANIC_BONSAI_POT, 0, new ModelResourceLocation(BotanicBonsaiItems.BOTANIC_BONSAI_POT.getRegistryName(), "normal"));
		ModelLoader.setCustomModelResourceLocation(BotanicBonsaiItems.BONSAI_POT_MANAGER, 0, new ModelResourceLocation(BotanicBonsaiItems.BONSAI_POT_MANAGER.getRegistryName(), "normal"));
	}

	@SubscribeEvent
	public static void registerBlockColors(ColorHandlerEvent.Block event)
	{
		event.getBlockColors().registerBlockColorHandler((state, blockAccess, pos, tintIndex) -> {

			if (pos != null && blockAccess != null)
			{
				TileEntity tileEntity = blockAccess.getTileEntity(pos);

				if (tileEntity instanceof TileBotanicBonsaiPot)
				{
					EnumDyeColor color = ((TileBotanicBonsaiPot) tileEntity).getColor();
					return color == EnumDyeColor.WHITE ? 0xFFFFFFFF : color.getColorValue();
				}
			}

			return 0xFFFFFFFF;
		}, BotanicBonsaiBlocks.BOTANIC_BONSAI_POT);
	}

	@SubscribeEvent
	public static void registerItemColors(ColorHandlerEvent.Item event)
	{
		event.getItemColors().registerItemColorHandler((stack, tintIndex) -> {
			NBTTagCompound stackTag = stack.getTagCompound();

			if (stackTag == null || !stackTag.hasKey("color"))
			{
				return 0xFFFFFFFF;
			}

			EnumDyeColor color = EnumDyeColor.byMetadata(stackTag.getInteger("color"));
			return color == EnumDyeColor.WHITE ? 0xFFFFFFFF : color.getColorValue();
		}, BotanicBonsaiItems.BOTANIC_BONSAI_POT);
	}
}