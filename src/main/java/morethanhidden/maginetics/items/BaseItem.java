package morethanhidden.maginetics.items;

import morethanhidden.maginetics.Maginetics;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BaseItem extends Item {

    String info;

    public BaseItem(String name) {
        setUnlocalizedName(Maginetics.MODID + "." + name);
        setRegistryName(name);
        setCreativeTab(Maginetics.tabmaginetics);
    }
    public BaseItem(String name, String tooltip) {
        this(name);
        this.info = tooltip;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(info != null){tooltip.add(I18n.translateToLocal(info));}
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
