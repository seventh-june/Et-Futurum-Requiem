package ganymedes01.etfuturum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.block.material.Material;

import biomesoplenty.common.blocks.BlockBOPLog;
import biomesoplenty.common.blocks.BlockBOPLog.LogCategory;

public class BlockStrippedBOPLog extends BlockBOPLog implements ISubBlocksBlock {
	private static final String[] icon_names = new String[] {"sacredoak", "cherry", "dark", "fir", "ethereal", "magic", "mangrove", "palm", "redwood", "willow", "dead", "bigflowerstem", "pine", "hellbark", "jacaranda", "mahogany"};
	private IIcon[] strippedTextures;
	private IIcon[] strippedLogHearts;
	private String prefix;
	private String suffix;
	private boolean isWood;
	private boolean isStripped;
	private final LogCategory category;

	public BlockStrippedBOPLog(LogCategory cat, String name, boolean isWood0, boolean isStripped0) 
	{
		super(cat);
		this.setBlockName(Utils.getUnlocalisedName(name));
		this.setCreativeTab(EtFuturum.creativeTabBlocks);
		category = cat;
		if(isStripped0) {
			prefix = "stripped_";
			isStripped = true;
		} else {
			prefix = "";
		}
		if(isWood0) {
			suffix = "_wood";
			isWood = true;
		} else {
			suffix = "_log";
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		strippedTextures = new IIcon[icon_names.length];
		strippedLogHearts = new IIcon[icon_names.length];
		
		for (int i = 0; i < icon_names.length; ++i)
		{
			if (i != 11)
			{
				if(isStripped) {
					strippedTextures[i] = iconRegister.registerIcon("biomesoplenty:stripped_" + icon_names[i] + "_log");
				} else {
					strippedTextures[i] = iconRegister.registerIcon("biomesoplenty:log_"+ icon_names[i] +"_side");
				}
				if(isWood) {
					strippedLogHearts[i] = strippedTextures[i];
				} else {
					strippedLogHearts[i] = iconRegister.registerIcon("biomesoplenty:stripped_" + icon_names[i] + "_log_top");
				}
			}
		}

		if(isStripped) {
			strippedTextures[11] = iconRegister.registerIcon("biomesoplenty:stripped_bigflowerstem");
		} else {
			strippedTextures[11] = iconRegister.registerIcon("biomesoplenty:bigflowerstem_side");
		}
		if(isWood) {
			strippedLogHearts[11] = strippedTextures[11];
		} else {
			strippedLogHearts[11] = iconRegister.registerIcon("biomesoplenty:stripped_bigflowerstem_top");
		}
	}

	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		int pos = meta & 12;
		
		if (pos == 0 && (side == 1 || side == 0) || pos == 4 && (side == 5 || side == 4) || pos == 8 && (side == 2 || side == 3))
			return strippedLogHearts[(getTypeFromMeta(meta) + category.ordinal() * 4)];
		else
			return strippedTextures[(getTypeFromMeta(meta) + category.ordinal() * 4)];
	}

	@Override
	public IIcon[] getIcons() {
		return strippedTextures;
	}

	@Override
	public String[] getTypes() {
		return icon_names;
	}

	@Override
	public String getNameFor(ItemStack stack) {
		return "bop_" + prefix + getTypes()[(getTypeFromMeta(stack.getItemDamage()) + category.ordinal() * 4)] + suffix;
	}

	private static int getTypeFromMeta(int meta)
	{
		return meta & 3;
	}
}
