package morethanhidden.maginetics.client;

import morethanhidden.maginetics.Maginetics;
import morethanhidden.maginetics.items.WandItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientHandler {

    public static final ClientHandler INSTANCE = new ClientHandler();

    @SubscribeEvent
    public void onMouseEvent(MouseEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        ItemStack mainHandItem = player.getHeldItem(EnumHand.MAIN_HAND);
        if (event.getDwheel() != 0 && player.isSneaking() && mainHandItem.getItem() instanceof WandItem) {
                player.openGui(Maginetics.INSTANCE, MagineticsGuiHandler.SPELL_GUI, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
                event.setCanceled(true);
        }
    }
}
