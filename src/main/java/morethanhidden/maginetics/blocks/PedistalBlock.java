package morethanhidden.maginetics.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PedistalBlock extends BlockBase {

    public PedistalBlock() {
        super("pedistal");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(2.0/ 16, 0.0/ 16, 2.0/ 16, 14.0/ 16, 2.0/ 16, 14.0 / 16);
    }
}
