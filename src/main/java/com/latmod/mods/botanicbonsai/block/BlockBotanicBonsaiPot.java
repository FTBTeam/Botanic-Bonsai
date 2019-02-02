package com.latmod.mods.botanicbonsai.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import org.dave.bonsaitrees.block.BlockBonsaiPot;

/**
 * @author LatvianModder
 */
public class BlockBotanicBonsaiPot extends BlockBonsaiPot
{
	public BlockBotanicBonsaiPot()
	{
		super(Material.ROCK);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileBotanicBonsaiPot();
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return "";
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		items.add(new ItemStack(this));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState();
	}
}