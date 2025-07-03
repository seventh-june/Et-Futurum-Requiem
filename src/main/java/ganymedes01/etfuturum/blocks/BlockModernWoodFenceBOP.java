package ganymedes01.etfuturum.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import static ganymedes01.etfuturum.recipes.ModRecipes.bopWoodTypes;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.configuration.configs.ConfigFunctions;

public class BlockModernWoodFenceBOP extends BlockModernWoodFence {

    public BlockModernWoodFenceBOP() {
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(Block.soundTypeWood);
        for (int i = 0; i < bopWoodTypes.length; i++) {
            bopWoodTypes[i] = "bop_" + bopWoodTypes[i] + "_fence";
        }
        setCreativeTab(EtFuturum.creativeTabBlocks);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        GameRegistry.findBlock("BiomesOPlenty", "planks").getSubBlocks(itemIn, tab, list);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return GameRegistry.findBlock("BiomesOPlenty", "planks").getIcon(side, meta);
    }

    @Override
    public void registerBlockIcons(IIconRegister ignored) {
    }

    @Override
    public int getDamageValue(World worldIn, int x, int y, int z) {
        return damageDropped(worldIn.getBlockMetadata(x, y, z));
    }

    @Override
    public int damageDropped(int meta) {
        return meta % getTypes().length;
    }

    @Override
    public String[] getTypes() {
        return bopWoodTypes;
    }

    @Override
    public String getNameFor(ItemStack stack) {
        return bopWoodTypes[stack.getItemDamage() % bopWoodTypes.length];
    }

    @Override
    public boolean isFlammable(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
        return ConfigFunctions.enableExtraBurnableBlocks;
    }

    @Override
    public int getFlammability(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
        return ConfigFunctions.enableExtraBurnableBlocks ? 20 : 0;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess aWorld, int aX, int aY, int aZ, ForgeDirection aSide) {
        return ConfigFunctions.enableExtraBurnableBlocks ? 5 : 0;
    }
}
