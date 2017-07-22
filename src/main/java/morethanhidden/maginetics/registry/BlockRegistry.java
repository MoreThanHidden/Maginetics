package morethanhidden.maginetics.registry;

import morethanhidden.maginetics.Maginetics;
import morethanhidden.maginetics.blocks.*;
import morethanhidden.maginetics.util.ModelHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {
    @ObjectHolder(Maginetics.MODID + ":gemore")
    public static Block gemOre;
    @ObjectHolder(Maginetics.MODID + ":blockgem")
    public static Block blockGem;
    @ObjectHolder(Maginetics.MODID + ":blockliquidmana")
    public static Block blockLiquidMana;
    @ObjectHolder(Maginetics.MODID + ":blockliquidgrass")
    public static Block blockLiquidGrass;
    @ObjectHolder(Maginetics.MODID + ":blockliquidfire")
    public static Block blockLiquidFire;

    @Mod.EventBusSubscriber(modid = Maginetics.MODID)
    public static class Registration {


        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            IForgeRegistry<Block> blockRegistry = event.getRegistry();

            blockRegistry.register(gemOre = new GemOre());
            blockRegistry.register(new BlockBase("blockgem"));
            blockRegistry.register(blockLiquidMana = new BlockLiquidMana(MFluidRegistry.liquidMana, Material.WATER));
            blockRegistry.register(blockLiquidFire = new BlockLiquidFire(MFluidRegistry.liquidFire, Material.LAVA));
            blockRegistry.register(blockLiquidGrass = new BlockLiquidGrass(MFluidRegistry.liquidGrass, Material.WATER));
        }

        @SubscribeEvent
        public static void registerBlockItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> itemRegistry = event.getRegistry();
            itemRegistry.register(new ItemBlock(gemOre).setRegistryName(gemOre.getRegistryName()));
            itemRegistry.register(new ItemBlock(blockGem).setRegistryName(blockGem.getRegistryName()));
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event){
            //Models
            ModelHelper.registerItemModel(Item.getItemFromBlock(gemOre), 0);
            ModelHelper.registerItemModel(Item.getItemFromBlock(blockGem), 0);

        }

    }

}