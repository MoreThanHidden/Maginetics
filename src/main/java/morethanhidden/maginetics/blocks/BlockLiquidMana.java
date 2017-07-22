package morethanhidden.maginetics.blocks;

import morethanhidden.maginetics.Maginetics;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockLiquidMana extends BlockFluidClassic {

    public BlockLiquidMana(Fluid fluid, Material material) {
        super(fluid, material);
        setCreativeTab(Maginetics.tabmaginetics);
        setLightLevel(0.625F);
        setUnlocalizedName("blockliquidmana");
        setRegistryName(Maginetics.MODID, "fluid_block#mana");
    }

}
