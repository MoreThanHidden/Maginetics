package morethanhidden.maginetics.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SubBlock extends BlockBase{

    public final String[] variants;

    public SubBlock(String name, String[] variants) {
        super(name);
        this.variants = variants;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int j = 0; j < variants.length; ++j)
        {
            items.add(new ItemStack(this, 1, j));
        }
    }
}
