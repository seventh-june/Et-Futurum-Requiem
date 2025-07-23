package ganymedes01.etfuturum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.BlockNewLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class BlockStrippedWitcheryLog extends BlockNewLog implements ISubBlocksBlock {

	public static final String[] icon_names = new String[]{"rowan", "alder", "hawthorn"};
	private String prefix;
	private String suffix;
	private boolean isWood;
	private boolean isStripped;

	public BlockStrippedWitcheryLog(String name, boolean wood, boolean stripped) {
		setBlockName(Utils.getUnlocalisedName(name));
		setCreativeTab(EtFuturum.creativeTabBlocks);

		if(stripped) {
			prefix = "stripped_";
			isStripped = true;
		} else {
			prefix = "";
		}
		if(wood) {
			suffix = "_wood";
			isWood = true;
		} else {
			suffix = "_log";
		}
	}

	@Override
	public IIcon[] getIcons() {
		return field_150167_a;
	}

	@Override
	public String[] getTypes() {
		return icon_names;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
	{
		list.add(new ItemStack(itemIn, 1, 0));
		list.add(new ItemStack(itemIn, 1, 1));
		list.add(new ItemStack(itemIn, 1, 2));
	}

	@Override
	public String getNameFor(ItemStack stack) {
		return "witchery_" + prefix + getTypes()[stack.getItemDamage() % getTypes().length] + suffix;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.field_150167_a = new IIcon[icon_names.length];
		this.field_150166_b = new IIcon[icon_names.length];

		for (int i = 0; i < this.field_150167_a.length; ++i) {

			if(isStripped) {
				this.field_150167_a[i] = iconRegister.registerIcon("witchery:stripped_" + icon_names[i] + "_log");
			} else {
				this.field_150167_a[i] = iconRegister.registerIcon("witchery:log_" + icon_names[i]);
			}

			if(isWood) {
				this.field_150166_b[i] = this.field_150167_a[i];
			} else {
				this.field_150166_b[i] = iconRegister.registerIcon("witchery:stripped_" + icon_names[i] + "_log_top");
			}
		}
	}

	@Override
	public boolean isFlammable(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
		return true;
	}

	@Override
	public int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
		return 5;
	}
}
