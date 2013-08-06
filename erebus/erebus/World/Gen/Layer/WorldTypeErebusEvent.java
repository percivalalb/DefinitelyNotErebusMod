package erebus.World.Gen.Layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.WorldType;
import net.minecraftforge.event.Event;

public class WorldTypeErebusEvent extends Event
{
    public final WorldType worldType;

    public WorldTypeErebusEvent(WorldType worldType)
    {
        this.worldType = worldType;
    }

    public static class BiomeSize extends WorldTypeErebusEvent
    {
        public final byte originalSize;
        public byte newSize;
        
        public BiomeSize(WorldType worldType, byte original)
        {
            super(worldType);
            originalSize = original;
            newSize = original;
        }
    }

    public static class InitBiomeGens extends WorldTypeErebusEvent
    {
        public final long seed;
        public final GenLayerErebus[] originalBiomeGens;
        public GenLayerErebus[] newBiomeGens;
        
        public InitBiomeGens(WorldType worldType, long seed, GenLayerErebus[] original)
        {
            super(worldType);
            this.seed = seed;
            originalBiomeGens = original;
            newBiomeGens = original.clone();
        }
    }
}
