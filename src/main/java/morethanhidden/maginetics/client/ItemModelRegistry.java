package morethanhidden.maginetics.client;
import morethanhidden.maginetics.client.render.LightModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class ItemModelRegistry {

    public static void init() {
        ModelLoaderRegistry.registerLoader(new LightModelLoader());
    }
}