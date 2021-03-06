package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityTrophy;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTrophy extends BlockCustom implements ITileEntityProvider
{
	public final TrophyType type;

	public BlockTrophy(TrophyType type)
	{
		super(Material.iron, type.name + " Trophy");
		setHardness(0.5F);
		float f = 0.325F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		setCreativeTab(Init.tab_mainBlocks);
		switch (type)
		{
			case IRON:
				setBlockTextureName("iron_block");
				break;
			case BRONZE:
				setBlockTextureName(Init.MODID + ":Bronze Block");
				break;
			case DIAMOND:
				setBlockTextureName("diamond_block");
				break;
			case GOLD:
				setBlockTextureName("gold_block");
				break;
			case SILVER:
				setBlockTextureName(Init.MODID + ":Silver Block");
				break;
			case TRINQUANTIUM:
				setBlockTextureName(Init.MODID + ":Trinquantium Block");
				break;
			default:
				break;

		}
		this.type = type;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TileEntityTrophy();
	}

	@Override
	public String getInfo()
	{
		return "Really nice Trophy block. This aesthetic block can be used to show off your really nice creations. Also can be used to show off how many diamonds you have!";
	}

	@Override
	public Elements getElements()
	{
		return new Elements(true, true);
	}

	public static enum TrophyType
	{
		GOLD,
		TRINQUANTIUM,
		BRONZE,
		SILVER,
		DIAMOND,
		IRON;

		public final String name;

		private TrophyType()
		{
			name = name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
		}
	}
}
