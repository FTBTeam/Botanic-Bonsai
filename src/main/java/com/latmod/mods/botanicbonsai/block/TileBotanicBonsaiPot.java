package com.latmod.mods.botanicbonsai.block;

import net.minecraft.item.EnumDyeColor;
import org.dave.bonsaitrees.tile.TileBonsaiPot;

/**
 * @author LatvianModder
 */
public class TileBotanicBonsaiPot extends TileBonsaiPot
{
	public TileBotanicBonsaiPot()
	{
		color = EnumDyeColor.WHITE;
	}

	@Override
	public boolean isHoppingPot()
	{
		return false;
	}
}