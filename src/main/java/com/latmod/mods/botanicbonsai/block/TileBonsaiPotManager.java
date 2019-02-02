package com.latmod.mods.botanicbonsai.block;

import com.latmod.mods.botanicbonsai.BotanicBonsaiConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.dave.bonsaitrees.tile.TileBonsaiPot;
import vazkii.botania.api.mana.IManaReceiver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LatvianModder
 */
public class TileBonsaiPotManager extends TileEntity implements ITickable, IManaReceiver
{
	public int mana = 0;
	public int timer = BotanicBonsaiConfig.general.manager_update_ticks;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		if (mana > 0)
		{
			nbt.setInteger("mana", mana);
		}

		if (timer > 0)
		{
			nbt.setShort("timer", (short) timer);
		}

		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		mana = nbt.getInteger("mana");
		timer = nbt.getShort("timer");
		super.readFromNBT(nbt);
	}

	@Override
	public void update()
	{
		if (world.isRemote)
		{
			return;
		}

		timer--;

		if (timer <= 0L)
		{
			timer = BotanicBonsaiConfig.general.manager_update_ticks;

			List<TileBonsaiPot> tiles = new ArrayList<>();
			int botanic = 0;

			int r = BotanicBonsaiConfig.general.manager_radius;

			for (int x = -r; x <= r; x++)
			{
				for (int z = -r; z <= r; z++)
				{
					TileEntity t = world.getTileEntity(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z));

					if (t instanceof TileBonsaiPot && ((TileBonsaiPot) t).hasSapling() && (t instanceof TileBotanicBonsaiPot || ((TileBonsaiPot) t).isHarvestable()))
					{
						tiles.add((TileBonsaiPot) t);

						if (t instanceof TileBotanicBonsaiPot)
						{
							botanic++;
						}
					}
				}
			}

			if (botanic > 0 && mana >= BotanicBonsaiConfig.general.mana_per_boost)
			{
				for (TileBonsaiPot tile : tiles)
				{
					if (tile instanceof TileBotanicBonsaiPot)
					{
						tile.boostProgress();
						mana -= BotanicBonsaiConfig.general.mana_per_boost;
						world.playEvent(2005, tile.getPos(), 0);

						if (mana < BotanicBonsaiConfig.general.mana_per_boost)
						{
							break;
						}
					}
				}
			}

			IItemHandler handler = null;
			boolean foundHandler = false;

			for (TileBonsaiPot tile : tiles)
			{
				if (tile.isHarvestable())
				{
					if (!foundHandler)
					{
						foundHandler = true;

						for (EnumFacing facing : EnumFacing.VALUES)
						{
							TileEntity tileEntity = world.getTileEntity(pos.offset(facing));

							if (tileEntity != null)
							{
								handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing.getOpposite());

								if (handler != null)
								{
									break;
								}
							}
						}
					}

					if (handler != null)
					{
						for (ItemStack stack : tile.getRandomizedDrops())
						{
							for (int i = 0; i < handler.getSlots(); i++)
							{
								stack = handler.insertItem(i, stack, false);

								if (stack.isEmpty())
								{
									break;
								}
							}
						}
					}

					tile.setSapling(tile.getSapling());
				}
			}
		}
	}

	@Override
	public boolean isFull()
	{
		return mana >= BotanicBonsaiConfig.general.manager_mana_capacity;
	}

	@Override
	public void recieveMana(int i)
	{
		mana += i;
	}

	@Override
	public boolean canRecieveManaFromBursts()
	{
		return !isFull();
	}

	@Override
	public int getCurrentMana()
	{
		return mana;
	}
}