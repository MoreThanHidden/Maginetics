package morethanhidden.maginetics.client.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class LightModelBaked implements IBakedModel
{
    private static VertexFormat format;
    private final TextureAtlasSprite base, overlay;

    public LightModelBaked(VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, ResourceLocation base, ResourceLocation overlay)
    {
        this.format = format;
        this.base = bakedTextureGetter.apply(base);
        this.overlay = bakedTextureGetter.apply(overlay);
    }

    private void putVertex(UnpackedBakedQuad.Builder builder, IBlockState state, Vec3d normal, double x, double y, double z, TextureAtlasSprite sprite, float u, float v, boolean hasBrightness, int brightness)
    {

        for (int e = 0; e < format.getElementCount(); e++)
        {
            switch (format.getElement(e).getUsage())
            {
                case POSITION:
                    builder.put(e, (float) x, (float) y, (float) z);
                    break;
                case COLOR:
                    builder.put(e, 1.0F, 1.0F, 1.0F, 1.0F);
                    break;
                case UV:
                    if (format.getElement(e).getIndex() == 1)
                    {
                        if (hasBrightness)
                        {
                            float blockLight = ((float) ((brightness >> 4) & 15) * 32) / 65535;
                            float skyLight = ((float) ((brightness >> 20) & 15) * 32) / 65535;
                            builder.put(e, blockLight, skyLight);
                        }
                        else
                        {
                            builder.put(e);
                        }
                    }
                    else
                    {
                        u = sprite.getInterpolatedU(u);
                        v = sprite.getInterpolatedV(v);
                        builder.put(e, u, v);
                    }
                    break;
                case NORMAL:
                    if (hasBrightness)
                    {
                        builder.put(e, 0.0F, 1.0F, 0.0F);
                        builder.setApplyDiffuseLighting(false);
                    }
                    else
                    {
                        builder.put(e,(float) normal.x, (float) normal.y, (float) normal.z);
                    }
                    break;
                default:
                    builder.put(e);
                    break;
            }
        }
    }

    private BakedQuad createQuad(IBlockState state, Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite, boolean hasBrightness, int brightness)
    {
        Vec3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();

        UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
        builder.setTexture(sprite);
        putVertex(builder, state, normal, v1.x, v1.y, v1.z, sprite, 16, 16, hasBrightness, brightness);
        putVertex(builder, state, normal, v2.x, v2.y, v2.z, sprite, 16, 0, hasBrightness, brightness);
        putVertex(builder, state, normal, v3.x, v3.y, v3.z, sprite, 0, 0, hasBrightness, brightness);
        putVertex(builder, state, normal, v4.x, v4.y, v4.z, sprite, 0, 16, hasBrightness, brightness);
        return builder.build();
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand)
    {
        if (side != null)
        {
            return Collections.emptyList();
        }

        List<BakedQuad> quads = new ArrayList<BakedQuad>();

        quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 0.0D), this.base, false, 0));
        quads.add(createQuad(state, new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 0.0D), this.base, false, 0));
        quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), this.base, false, 0));
        quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 0.0D, 0.0D), this.base, false, 0));
        quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 0.0D, 0.0D), this.base, false, 0));
        quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 0.0D, 1.0D), this.base, false, 0));

        quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 0.0D), this.overlay, true, 250));
        quads.add(createQuad(state, new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 0.0D), this.overlay, true, 250));
        quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), this.overlay, true, 250));
        quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 0.0D, 0.0D), this.overlay, true, 250));
        quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 0.0D, 0.0D), this.overlay, true, 250));
        quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 0.0D, 1.0D), this.overlay, true, 250));

        return quads;
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return true;
    }

    @Override
    public boolean isGui3d()
    {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return this.overlay;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms()
    {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return ItemOverrideList.NONE;
    }
}