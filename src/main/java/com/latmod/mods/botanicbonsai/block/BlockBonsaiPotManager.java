package com.latmod.mods.botanicbonsai.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author LatvianModder
 */
public class BlockBonsaiPotManager extends Block
{
	public BlockBonsaiPotManager()
	{
		super(Material.WOOD);
		setHardness(1F);
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileBonsaiPotManager();
	}
}