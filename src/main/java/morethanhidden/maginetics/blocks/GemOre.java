package morethanhidden.maginetics.blocks;

import morethanhidden.maginetics.Maginetics;
import morethanhidden.maginetics.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;

import java.util.Random;

public class GemOre extends BlockBase{

    public GemOre () {
        super("gemore");
        setHardness(4.0F);
        setHarvestLevel("pickaxe",3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemRegistry.gem;
    }

    @Override
    public int quantityDropped(Random random) {
        return 2 + random.nextInt(1);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return 2 + random.nextInt(1 + fortune);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}