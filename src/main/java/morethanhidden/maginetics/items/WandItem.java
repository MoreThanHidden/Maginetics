package morethanhidden.maginetics.items;

import com.google.common.base.Predicates;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WandItem extends BaseItem {

    //Magic Recipes need to move this to its own class or json registry
    public static Map<ResourceLocation, ResourceLocation> recipes = new HashMap<>();
    static {
        recipes.put(new ResourceLocation("maginetics:blankscroll"), new ResourceLocation("maginetics:scroll"));
    }

    public WandItem() {
        super("wand");
    }

    public WandItem(String name) {
        super(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        EntityItem entity = rayTraceItem();

        //This is working client side need to make a packet to update server side.
        if (entity != null && recipes.containsKey(entity.getItem().getItem().getRegistryName())){
            entity.setItem(new ItemStack(Item.getByNameOrId(recipes.get((entity.getItem().getItem().getRegistryName())).toString())));
            worldIn.spawnParticle(EnumParticleTypes.SPELL, playerIn.posX + playerIn.getLookVec().x , playerIn.posY + playerIn.getLookVec().y + 1.7, playerIn.posZ + playerIn.getLookVec().z, 0.0D, 0.0D, 0.0D);
            return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    private EntityItem rayTraceItem(){
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        Entity pointedEntity = null;

        if (entity != null) {
            if (mc.world != null) {

                double d0 = (double)mc.playerController.getBlockReachDistance();
                double d1 = d0;
                Vec3d vec3d = entity.getPositionEyes(1.0f);

                if (mc.playerController.extendedReach()){
                    d1 = 6.0D;
                    d0 = d1;
                }

                Vec3d look = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.addVector(look.x * d0, look.y * d0, look.z * d0);

                //Get the entities in the general direction
                List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(look.x * 8F, look.y * 8F, look.z * 8F).grow(1.0F, 1.0F, 1.0F), Predicates.and(EntitySelectors.NOT_SPECTATING, p_apply_1_ -> p_apply_1_ != null && !p_apply_1_.canBeCollidedWith()));
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j)
                {
                    Entity entity1 = list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);


                    if (axisalignedbb.contains(vec3d))
                    {
                        if (d2 >= 0.0D)
                        {
                            pointedEntity = entity1;
                            d2 = 0.0D;
                        }
                    }
                    else if (raytraceresult != null)
                    {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    pointedEntity = entity1;
                                }
                            }
                            else
                            {
                                pointedEntity = entity1;
                                d2 = d3;
                            }
                        }
                    }
                }
            }
        }
        if(pointedEntity instanceof EntityItem){
            return (EntityItem) pointedEntity;
        }
        return null;
    }
}
