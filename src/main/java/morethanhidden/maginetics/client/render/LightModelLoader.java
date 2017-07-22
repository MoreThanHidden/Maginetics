package morethanhidden.maginetics.client.render;

import com.google.common.collect.Maps;
import com.google.gson.*;
import morethanhidden.maginetics.Maginetics;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

import javax.annotation.Nonnull;
import java.io.InputStreamReader;
import java.util.Map;

public class LightModelLoader implements ICustomModelLoader{

    private IResourceManager resourceManager;
    private Map<ResourceLocation, JsonElement> jsonCache = Maps.newHashMap();

    @Override
    public boolean accepts(ResourceLocation res) {
        if (res.getResourceDomain().equals(Maginetics.MODID)) {
            String[] splitRes = res.toString().split("#");
            if(splitRes.length > 1 && splitRes[1].equals("normal")){
                JsonElement json = getJSON(new ResourceLocation(res.getResourceDomain(), "models/block/" + res.getResourcePath()));
                if(json.isJsonObject() && json.getAsJsonObject().get("parent").getAsString().equals("maginetics:block/glowoverlay")){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public IModel loadModel(ResourceLocation res) throws Exception {
        String path = "models/block/" + res.getResourcePath();
        res = new ResourceLocation(res.getResourceDomain(), path);
        JsonObject textures = getJSON(res).getAsJsonObject().getAsJsonObject("textures");
        ResourceLocation overlay = new ResourceLocation( textures.get("overlay").getAsString());
        ResourceLocation base =  new ResourceLocation( textures.get("all").getAsString());
        return new LightModelWrapper(base, overlay);
    }

    @SuppressWarnings("null")
    public @Nonnull
    JsonElement getJSON(ResourceLocation modelLocation) {
        return jsonCache.computeIfAbsent(modelLocation, res -> {
            String path = modelLocation.getResourcePath() + ".json";
            ResourceLocation absolute = new ResourceLocation(modelLocation.getResourceDomain(), path);

            try {
                IResource resource = resourceManager.getResource(absolute);
                JsonElement ele = new JsonParser().parse(new InputStreamReader(resource.getInputStream()));
                if (ele != null) {
                    return ele;
                }
            } catch (Exception e) {}

            return JsonNull.INSTANCE;
        });
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

}