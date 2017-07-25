package morethanhidden.maginetics.proxy;

import morethanhidden.maginetics.blocks.tile.PedestalTile;
import morethanhidden.maginetics.client.FluidModelRegistry;
import morethanhidden.maginetics.client.ItemModelRegistry;
import morethanhidden.maginetics.client.render.RenderPedistalItem;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends common {

    public static int renderPass;
    public static int GemOreRenderType;

    @Override
    public void registerRenderers() {

        ClientRegistry.bindTileEntitySpecialRenderer(PedestalTile.class, new RenderPedistalItem());

        //Register Item Models
        ItemModelRegistry.init();

        //Register Fluids
        FluidModelRegistry.init();
    }

}