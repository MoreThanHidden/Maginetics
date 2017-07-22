package morethanhidden.maginetics.client;

import morethanhidden.maginetics.Maginetics;
import morethanhidden.maginetics.registry.MFluidRegistry;
import morethanhidden.maginetics.util.FluidStateMapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;

public class FluidModelRegistry {

    public static void init() {
        registerFluidModels(MFluidRegistry.liquidFire);
        registerFluidModels(MFluidRegistry.liquidGrass);
        registerFluidModels(MFluidRegistry.liquidMana);
    }

    public static void registerFluidModels(Fluid fluid) {
        if(fluid == null) {
            return;
        }

        Block block = fluid.getBlock();
        if(block != null) {
            Item item = Item.getItemFromBlock(block);
            FluidStateMapper mapper = new FluidStateMapper(fluid, Maginetics.MODID, "fluid_block");

            if(item != null) {
                ModelLoader.registerItemVariants(item);
                ModelLoader.setCustomMeshDefinition(item, mapper);
            }

            ModelLoader.setCustomStateMapper(block, mapper);
        }
    }


}