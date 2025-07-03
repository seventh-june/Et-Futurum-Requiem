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
import static ganymedes01.etfuturum.recipes.ModRecipes.thaumcraftWoodTypes;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.configuration.configs.ConfigFunctions;

public class BlockModernWoodFenceThaumcraft extends BlockModernWoodFence {

    public BlockModernWoodFenceThaumcraft() {
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(Block.soundTypeWood);
        for (int i = 0; i < thaumcraftWoodTypes.length; i++) {
            thaumcraftWoodTypes[i] = "thaumcraft_" + thaumcraftWoodTypes[i] + "_fence";
        }
        setCreativeTab(EtFuturum.creativeTabBlocks);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, 0));
        list.add(new ItemStack(itemIn, 1, 1));
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return GameRegistry.findBlock("Thaumcraft", "blockWoodenDevice").getIcon(side, meta + 6);
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
        return thaumcraftWoodTypes;
    }

    @Override
    public String getNameFor(ItemStack stack) {
        return thaumcraftWoodTypes[stack.getItemDamage() % thaumcraftWoodTypes.length];
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
