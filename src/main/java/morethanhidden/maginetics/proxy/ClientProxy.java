package morethanhidden.maginetics.proxy;

import morethanhidden.maginetics.blocks.tile.PedestalTile;
import morethanhidden.maginetics.client.ClientHandler;
import morethanhidden.maginetics.client.FluidModelRegistry;
import morethanhidden.maginetics.client.ItemModelRegistry;
import morethanhidden.maginetics.client.render.RenderPedestalItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends common {


    @Override
    public void registerRenderers() {

        ClientRegistry.bindTileEntitySpecialRenderer(PedestalTile.class, new RenderPedestalItem());

        //Register Item Models
        ItemModelRegistry.init();

        //Register Fluids
        FluidModelRegistry.init();

    }

    @Override
    public void preInit() {
        super.preInit();
        MinecraftForge.EVENT_BUS.register(ClientHandler.INSTANCE);
    }

}