package morethanhidden.maginetics.client.render;

import morethanhidden.maginetics.blocks.tile.PedestalTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPedestalItem extends TileEntitySpecialRenderer {
    @Override
    public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

        ItemStack stack =  ((PedestalTile)te).getItemHandler().getStackInSlot(0);

        if(!stack.isEmpty()){
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x+0.5F, (float)y+0.6F, (float)z+0.5F);
            double time = Minecraft.getSystemTime() / 800D;
            GlStateManager.translate(0D, Math.sin(time%(2*Math.PI))*0.065, 0D);
            GlStateManager.rotate((float)(((time*40D)%360)), 0, 1, 0);
            GlStateManager.disableLighting();
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
