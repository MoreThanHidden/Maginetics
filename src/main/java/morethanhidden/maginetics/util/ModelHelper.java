package morethanhidden.maginetics.util;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Locale;

public class ModelHelper {
    public static void registerItemModel(Item item, int metadata) {
        ResourceLocation loc = Item.REGISTRY.getNameForObject(item);
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(loc, "inventory"));
    }

    public static void registerItemModel(Item item, int metadata, ModelResourceLocation loc) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, loc);
    }

    public static void registerItemModel(Item item, int metadata, String name, String suffix) {
        if (!name.contains(":")) {
            name = String.format("%s:%s", item.getRegistryName().getResourceDomain(), name);
        }

        String type = name;
        type = type.toLowerCase(Locale.ROOT);

        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(type, suffix));
    }

    public void registerVariant(Item item, ResourceLocation... resources) {
        ModelBakery.registerItemVariants(item, resources);
    }

}
