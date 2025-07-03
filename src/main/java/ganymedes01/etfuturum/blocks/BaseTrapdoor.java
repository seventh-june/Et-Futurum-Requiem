package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.core.utils.Utils;
import ganymedes01.etfuturum.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class BaseTrapdoor extends BlockTrapDoor {

	String mod;

	public BaseTrapdoor(Material material, String type) {
		super(material);
		disableStats();
		setHardness(3.0F);
		setBlockName(Utils.getUnlocalisedName(type + "_trapdoor"));
		if(type.split("_")[0].equals("bop")) {
			mod = "biomesoplenty:";
			type = type.substring(4, type.length());
		} else {
			mod = "";
		}
		setBlockTextureName(mod + type + "_trapdoor");
		setCreativeTab(EtFuturum.creativeTabBlocks);
		setBlockSound(getMaterial() == Material.iron ? Block.soundTypeMetal : Block.soundTypeWood);
	}

	public BaseTrapdoor(String type) {
		this(Material.wood, type);
	}

	public BaseTrapdoor setBlockSound(SoundType type) {
		Utils.setBlockSound(this, type);
		return this;
	}

	@Override
	public int getRenderType() {
		return RenderIDs.TRAPDOOR;
	}

}