package morethanhidden.maginetics.proxy;

import morethanhidden.maginetics.client.FluidModelRegistry;
import morethanhidden.maginetics.client.ItemModelRegistry;
import morethanhidden.maginetics.proxy.common;

public class ClientProxy extends common {

    public static int renderPass;
    public static int GemOreRenderType;

    @Override
    public void registerRenderers() {

        //Register Item Models
        ItemModelRegistry.init();

        //Register Fluids
        FluidModelRegistry.init();
    }

}