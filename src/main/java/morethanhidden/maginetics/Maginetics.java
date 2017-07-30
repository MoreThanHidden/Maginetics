package morethanhidden.maginetics;

import morethanhidden.maginetics.proxy.common;
import morethanhidden.maginetics.registry.ItemRegistry;
import morethanhidden.maginetics.registry.MFluidRegistry;
import morethanhidden.maginetics.blocks.tile.PedestalTile;
import morethanhidden.maginetics.util.MagineticsGuiHandler;
import morethanhidden.maginetics.world.MagineticsWorld;
import morethanhidden.maginetics.world.WorldGenMaginetics;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Maginetics.MODID, version = Maginetics.VERSION, name = "Maginetics")
public class Maginetics
{
    public static final String MODID = "maginetics";
    public static final String VERSION = "0.1";
    public static int config_gemrate = 7;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @SidedProxy(clientSide="morethanhidden.maginetics.proxy.ClientProxy", serverSide="morethanhidden.maginetics.proxy.common")
    public static common proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerTileEntity(PedestalTile.class, "maginetics.pedistaltile");
        MFluidRegistry.preinit();
        proxy.registerRenderers();

    }

    public static CreativeTabs tabmaginetics = new CreativeTabs("Maginetics") {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.gem);
        }
    };

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        MFluidRegistry.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new MagineticsGuiHandler());
        GameRegistry.registerWorldGenerator(new WorldGenMaginetics(), 10);
        MagineticsWorld.mainRegistry();

    }
}
