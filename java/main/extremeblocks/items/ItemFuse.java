package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.Init;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFuse extends ItemCustom
{
	public ItemFuse()
	{
		super("Fuse", Init.tab_tools);
		setTextureName(Init.MODID + ":fuse_powder");
		setInfo("When placed and lit on fire, will travel through adjacent fuses.");
		setShowRecipe();
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par3World.getBlock(par4, par5, par6) != Blocks.snow_layer)
		{
			if (par7 == 0)
			{
				--par5;
			}
			if (par7 == 1)
			{
				++par5;
			}
			if (par7 == 2)
			{
				--par6;
			}
			if (par7 == 3)
			{
				++par6;
			}
			if (par7 == 4)
			{
				--par4;
			}
			if (par7 == 5)
			{
				++par4;
			}
			if (!par3World.isAirBlock(par4, par5, par6)) return false;
		}
		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) return false;
		else
		{
			if (Init.fuse_block.canPlaceBlockAt(par3World, par4, par5, par6))
			{
				--par1ItemStack.stackSize;
				par3World.setBlock(par4, par5, par6, Init.fuse_block);
			}
			return true;
		}
	}
}
