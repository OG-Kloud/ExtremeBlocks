package main.extremeblocks.blocks.abstractblocks;

import java.util.ArrayList;
import java.util.Random;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.RegistryHelper;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLightedBlock extends Block
{
	private final Block parent;
	private BlockLightedBlock opposite;
	private final boolean isLighted;
	private static int ID;
	private int id;

	private BlockLightedBlock(Block parent, boolean isLighted)
	{
		super(parent.getMaterial());
		this.setBlockName((isLighted ? "Lighted_" : "Off_") + parent.getLocalizedName() + "[" + ID++ + "]");
		this.setHardness(0.3F);
		if (isLighted) this.setCreativeTab(Init.tab_lightedBlocks);
		this.setLightLevel(isLighted ? 0.9375F : 0.0F);
		this.id = ID;
		this.isLighted = isLighted;
		this.parent = parent;
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(isLighted ? this : opposite);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return parent.getIcon(side, meta);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		for (int i = 0; i < lightedBlocks.size(); i++)
		{
			if (isLighted && parent == lightedBlocks.get(i).parent)
			{
				world.setBlock(x, y, z, offBlocks.get(i));
			}
			else if (!isLighted && parent == offBlocks.get(i).parent)
			{
				world.setBlock(x, y, z, lightedBlocks.get(i));
			}
		}
		return true;
	}

	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = this.parent.getBlockTextureFromSide(0);
	}

	public static void createPair(Block parent)
	{
		BlockLightedBlock lighted = new BlockLightedBlock(parent, true);
		BlockLightedBlock off = new BlockLightedBlock(parent, false);
		off.opposite = lighted;
		lighted.opposite = off;
		lightedBlocks.add(lighted);
		offBlocks.add(off);
		RegistryHelper.register(lighted);
		RegistryHelper.register(off);
		LanguageRegistry.addName(off, "Off " + parent.getLocalizedName());
		LanguageRegistry.addName(lighted, "Lighted " + parent.getLocalizedName());
		MPUtil.addRecipe(new ItemStack(lighted), "TTT", "TBT", "TTT", 'T', Blocks.torch, 'B', parent);
	}

	public int damageDropped(int p_149692_1_)
	{
		return parent.damageDropped(p_149692_1_);
	}

	public String getUnlocalizedName()
	{
		return "Lighted " + parent.getLocalizedName() + "[" + id + "]";
	}

	public String getLocalizedName()
	{
		return "Lighted " + parent.getLocalizedName();
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return Item.getItemFromBlock(isLighted ? this : opposite);
	}

	private static ArrayList<BlockLightedBlock> lightedBlocks = JavaHelp.newArrayList();
	private static ArrayList<BlockLightedBlock> offBlocks = JavaHelp.newArrayList();
}
