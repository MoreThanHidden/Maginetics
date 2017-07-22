package morethanhidden.maginetics.client.render;

import morethanhidden.maginetics.IModelGlow;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

import java.util.function.Function;

public class LightModelWrapper implements IModelGlow {

    private final ResourceLocation base;
    private final ResourceLocation overlay;

    private static final VertexFormat BRIGHTNESS = new VertexFormat();

    public LightModelWrapper(ResourceLocation base, ResourceLocation overlay) {
        this.base = base;
        this.overlay = overlay;
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        for (VertexFormatElement e : format.getElements())
        {
            BRIGHTNESS.addElement(e);
        }

        BRIGHTNESS.addElement(DefaultVertexFormats.TEX_2S);
        return new LightModelBaked(BRIGHTNESS, bakedTextureGetter, base, overlay);

    }

}
