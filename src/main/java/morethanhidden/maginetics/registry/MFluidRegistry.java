package morethanhidden.maginetics.registry;

import morethanhidden.maginetics.Maginetics;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class MFluidRegistry {
    public static Fluid liquidMana = new Fluid("mana", new ResourceLocation(Maginetics.MODID, "blocks/liquidmana_still"), new ResourceLocation(Maginetics.MODID, "blocks/liquidmana_flow"));
    public static Fluid liquidGrass = new Fluid("grass", new ResourceLocation(Maginetics.MODID, "blocks/liquidgrass_still"), new ResourceLocation(Maginetics.MODID, "blocks/liquidgrass_flow"));
    public static Fluid liquidFire = new Fluid("fire", new ResourceLocation(Maginetics.MODID, "blocks/liquidfire_still"), new ResourceLocation(Maginetics.MODID, "blocks/liquidfire_flow"));

    public static void preinit() {

        liquidMana = registerFluid(liquidMana);
        liquidGrass = registerFluid(liquidGrass);
        liquidFire = registerFluid(liquidFire);

        FluidRegistry.addBucketForFluid(liquidMana);
        FluidRegistry.addBucketForFluid(liquidGrass);
        FluidRegistry.addBucketForFluid(liquidFire);

    }

    public static void init(){
        FluidUtil.getFilledBucket(new FluidStack(MFluidRegistry.liquidMana, 1000)).getItem().setCreativeTab(Maginetics.tabmaginetics);
        FluidUtil.getFilledBucket(new FluidStack(MFluidRegistry.liquidFire, 1000)).getItem().setCreativeTab(Maginetics.tabmaginetics);
        FluidUtil.getFilledBucket(new FluidStack(MFluidRegistry.liquidGrass, 1000)).getItem().setCreativeTab(Maginetics.tabmaginetics);
    }



    public static Fluid registerFluid(Fluid fluid) {

        if (!FluidRegistry.isFluidRegistered(fluid.getName())) {
            FluidRegistry.registerFluid(fluid);
        }
        return FluidRegistry.getFluid(fluid.getName());
    }

}