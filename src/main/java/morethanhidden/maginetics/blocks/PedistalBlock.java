package morethanhidden.maginetics.blocks;

import morethanhidden.maginetics.Maginetics;
import morethanhidden.maginetics.blocks.tile.PedistalTile;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PedistalBlock extends BlockContainer{


    public PedistalBlock() {
        super(Material.ROCK);
        setUnlocalizedName(Maginetics.MODID + ".pedistal");
        setRegistryName("pedistal");
        setHardness(3);
        setCreativeTab(Maginetics.tabmaginetics);
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(2.0/ 16, 0.0/ 16, 2.0/ 16, 14.0/ 16, 2.0/ 16, 14.0 / 16);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new PedistalTile();
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return  BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        PedistalTile tile = (PedistalTile) worldIn.getTileEntity(pos);
        ItemStack item = tile.getItemHandler().getStackInSlot(0);
        if(item != ItemStack.EMPTY ){
            playerIn.inventory.addItemStackToInventory(tile.getItemHandler().extractItem(0, 1,false));
            return true;
        }else if (playerIn.getHeldItem(hand) != ItemStack.EMPTY ){
            playerIn.setHeldItem(hand, tile.getItemHandler().insertItem(0, playerIn.getHeldItem(hand), false));
            return true;
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
