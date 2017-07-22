package morethanhidden.maginetics.world;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MagineticsWorld {

    public static void mainRegistry(){
        initWorldGen();
    }

    public static void initWorldGen(){
        registerWorldGen(new WorldGenMaginetics(), 1);

    }

    public static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbability){
        GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
    }
}
