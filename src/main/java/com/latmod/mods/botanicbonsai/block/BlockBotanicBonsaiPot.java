package com.latmod.mods.botanicbonsai.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.dave.bonsaitrees.block.BlockBonsaiPot;
import org.dave.bonsaitrees.tile.TileBonsaiPot;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			if (!player.isCreative())
			{
				if (world.getTileEntity(pos) instanceof TileBonsaiPot)
				{
					List<ItemStack> drops = new ArrayList<>();
					TileBonsaiPot pot = (TileBonsaiPot) world.getTileEntity(pos);

					if (pot.hasSapling())
					{
						if (pot.isHarvestable())
						{
							drops.addAll(pot.getRandomizedDrops());
						}

						drops.add(pot.getSapling());
					}

					if (pot.hasSoil())
					{
						drops.add(pot.getSoilStack());
					}

					ItemStack potStack = new ItemStack(this, 1, this.getMetaFromState(state));

					if (pot.getColor() != null)
					{
						NBTTagCompound potNbt = new NBTTagCompound();
						potNbt.setInteger("color", pot.getColor().getMetadata());
						potStack.setTagCompound(potNbt);
					}

					drops.add(potStack);

					for (ItemStack drop : drops)
					{
						spawnAsEntity(world, pos, drop);
					}
				}
			}
		}
	}
}