package morethanhidden.maginetics.registry;
import morethanhidden.maginetics.Maginetics;
import morethanhidden.maginetics.items.BaseItem;
import morethanhidden.maginetics.items.ScrollItem;
import morethanhidden.maginetics.items.StaffItem;
import morethanhidden.maginetics.items.WandItem;
import morethanhidden.maginetics.util.ModelHelper;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;

public class ItemRegistry {

    @ObjectHolder(Maginetics.MODID + ":gem")
    public static BaseItem gem;
    @ObjectHolder(Maginetics.MODID + ":depletedgem")
    public static BaseItem depletedgem;
    @ObjectHolder(Maginetics.MODID + ":wand")
    public static WandItem wand;
    @ObjectHolder(Maginetics.MODID + ":staff")
    public static StaffItem staff;
    @ObjectHolder(Maginetics.MODID + ":blankscroll")
    public static BaseItem blankscroll;
    @ObjectHolder(Maginetics.MODID + ":scroll")
    public static ScrollItem scroll;

    @Mod.EventBusSubscriber(modid = Maginetics.MODID)
    public static class Registration{

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> itemRegistry = event.getRegistry();
            //Items
            itemRegistry.register(new BaseItem("gem", "tooltip.maginetics.gem"));
            itemRegistry.register(new BaseItem("depletedgem", "tooltip.maginetics.depletedgem"));
            itemRegistry.register(new BaseItem("blankscroll"));
            itemRegistry.register(new WandItem());
            itemRegistry.register(new StaffItem());
            itemRegistry.register(new ScrollItem());
        }

        @SubscribeEvent
         public static void registerModels(ModelRegistryEvent event){
            //Models
            ModelHelper.registerItemModel(gem, 0);
            ModelHelper.registerItemModel(depletedgem, 0);
            ModelHelper.registerItemModel(wand, 0);
            ModelHelper.registerItemModel(staff, 0);
            ModelHelper.registerItemModel(blankscroll, 0);
            ModelHelper.registerItemModel(scroll, 0);
        }


    }

}