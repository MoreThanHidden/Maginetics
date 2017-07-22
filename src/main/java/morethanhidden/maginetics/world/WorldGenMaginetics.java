package morethanhidden.maginetics.world;

import morethanhidden.maginetics.Maginetics;
import morethanhidden.maginetics.registry.BlockRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenMaginetics  implements IWorldGenerator {


    private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        for(int i = 0; i < Maginetics.config_gemrate; i++){
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(10);
            int randPosZ = chunkZ + rand.nextInt(16);

            (new WorldGenMinable(BlockRegistry.gemOre.getDefaultState(), 5)).generate(world, rand, new BlockPos(randPosX, randPosY, randPosZ));
        }
    }


    private void generateNether(World world, Random rand, int i, int j) {


    }

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()){
            case -1:
                generateNether(world, rand, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, rand, chunkX * 16, chunkZ * 16);
        }
    }
}