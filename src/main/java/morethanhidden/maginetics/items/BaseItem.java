package morethanhidden.maginetics.items;

import morethanhidden.maginetics.Maginetics;
import net.minecraft.item.Item;

public class BaseItem extends Item {

    public BaseItem(String registryName, String unlocalisedname) {
        setUnlocalizedName(unlocalisedname);
        setRegistryName(registryName);
        setCreativeTab(Maginetics.tabmaginetics);
    }

}
