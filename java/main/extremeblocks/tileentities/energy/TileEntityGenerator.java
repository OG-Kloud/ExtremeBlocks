package main.extremeblocks.tileentities.energy;

import java.util.List;
import main.com.hk.eb.util.IEnergyHolder;
import main.com.hk.eb.util.IWailaInfo;
import main.com.hk.eb.util.MathHelp;
import main.extremeblocks.tileentities.TileEntitySidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyProvider;

public abstract class TileEntityGenerator extends TileEntitySidedInventory implements IEnergyProvider, IEnergyHolder, IWailaInfo
{
	public EnergyStorage storage = new EnergyStorage(120000, 1024, 1024);

	public TileEntityGenerator(String name)
	{
		super(name);
	}

	public abstract int[] getBatterySlots();

	@Override
	public abstract List<Slot> getSlots();

	public void sendNearby()
	{
		PowerHelper.sendNearby(this);
	}

	public void calculatePowerFromInventory()
	{
		PowerHelper.calculatePowerFromInventory(this, storage, getBatterySlots());
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		boolean isBattery = stack != null && stack.getItem() instanceof IEnergyContainerItem;
		for (int i = 0; i < getBatterySlots().length; i++)
		{
			if (getBatterySlots()[i] == i && isBattery) return true;
		}
		return false;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		int i = storage.extractEnergy(maxExtract, simulate);
		if (!simulate)
		{
			markDirty();
		}
		return i;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return storage.getMaxEnergyStored();
	}

	@Override
	public boolean canWrench()
	{
		return true;
	}

	@Override
	public int getEnergyStoredScaled(int scale)
	{
		return MathHelp.clamp(Math.round(scale * ((float) storage.getEnergyStored() / storage.getMaxEnergyStored())), 0, scale);
	}

	@Override
	public int getMaxEnergyStored()
	{
		return storage.getMaxEnergyStored();
	}

	@Override
	public int getEnergyStored()
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxExtract()
	{
		return storage.getMaxExtract();
	}

	@Override
	public int getMaxReceive()
	{
		return storage.getMaxReceive();
	}

	@Override
	public void readFrom(NBTTagCompound nbt)
	{
		super.readFrom(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeTo(NBTTagCompound nbt)
	{
		super.writeTo(nbt);
		storage.writeToNBT(nbt);
	}
}