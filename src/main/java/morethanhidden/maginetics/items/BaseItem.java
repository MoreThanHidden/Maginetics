package morethanhidden.maginetics.items;

import morethanhidden.maginetics.Maginetics;
import net.minecraft.item.Item;

public class BaseItem extends Item {

    public BaseItem(String name) {
        setUnlocalizedName(Maginetics.MODID + "." + name);
        setRegistryName(name);
        setCreativeTab(Maginetics.tabmaginetics);
    }

}
