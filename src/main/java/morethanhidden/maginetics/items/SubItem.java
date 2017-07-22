package morethanhidden.maginetics.items;

import morethanhidden.maginetics.util.ModelHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class SubItem extends Item {
    private String[] itemNames;
    int itemcount;

    public SubItem(String modid, String uname, String[] itemnames) {
        this.itemNames = itemnames;
        this.itemcount = itemNames.length;
        setHasSubtypes(true);
        maxStackSize = 64;
        setUnlocalizedName(uname);
        setRegistryName(modid, uname);
    }

    public String getUnlocalizedName(ItemStack par1ItemStack)	{
        int i = MathHelper.clamp(par1ItemStack.getItemDamage(), 0, itemcount-1);
        return itemNames[i];
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int j = 0; j < itemcount; ++j) {
            items.add(new ItemStack(this, 1, j));
        }
    }


}