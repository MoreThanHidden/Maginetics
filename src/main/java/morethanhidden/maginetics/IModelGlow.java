package morethanhidden.maginetics;

import net.minecraftforge.client.model.IModel;

public interface IModelGlow extends IModel {

    default IModel layerGlows(boolean value) {
        return this;
    }

}
