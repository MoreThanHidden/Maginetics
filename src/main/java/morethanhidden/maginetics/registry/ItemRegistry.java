package morethanhidden.maginetics.registry;
import morethanhidden.maginetics.Maginetics;
import morethanhidden.maginetics.items.BaseItem;
import morethanhidden.maginetics.util.ModelHelper;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {

    @ObjectHolder(Maginetics.MODID + ":gem")
    public static BaseItem gem;

    @Mod.EventBusSubscriber(modid = Maginetics.MODID)
    public static class Registration{

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> itemRegistry = event.getRegistry();
            //Items
            itemRegistry.register(new BaseItem("gem", Maginetics.MODID + ".gem"));
        }

        @SubscribeEvent
         public static void registerModels(ModelRegistryEvent event){
            //Models
            ModelHelper.registerItemModel(gem, 0);
        }


    }

}