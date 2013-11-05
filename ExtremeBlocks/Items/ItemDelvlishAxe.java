package ExtremeBlocks.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ExtremeBlocks.ExtremeBlocksMain;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class ItemDelvlishAxe extends ItemAxe
{
	public ItemDelvlishAxe(int par1, EnumToolMaterial par2) 
	{
		super(par1, par2);
		this.setCreativeTab(ExtremeBlocksMain.EBToolsTab);
		this.setUnlocalizedName("DelvlishAxe");
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }
}
