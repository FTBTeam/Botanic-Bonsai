package com.latmod.mods.botanicbonsai;

import com.latmod.mods.botanicbonsai.item.BotanicBonsaiItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import org.dave.bonsaitrees.jei.BonsaiTreeRecipeCategory;

/**
 * @author LatvianModder
 */
@JEIPlugin
public class JEIIntegration implements IModPlugin
{
	@Override
	public void register(IModRegistry registry)
	{
		registry.addRecipeCatalyst(new ItemStack(BotanicBonsaiItems.BOTANIC_BONSAI_POT), BonsaiTreeRecipeCategory.UID);
	}
}