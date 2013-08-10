package erebus.structures;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenSpiderTunnels extends MapGenStructure
{
    private List spawnList = new ArrayList();

    public MapGenSpiderTunnels()
    {
        this.spawnList.add(new SpawnListEntry(EntitySpider.class, 96, 4, 4));
        this.spawnList.add(new SpawnListEntry(EntityCaveSpider.class, 24, 2, 3));
    }

    public List getSpawnList()
    {
        return this.spawnList;
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        int var3 = par1 >> 4;
        int var4 = par2 >> 4;
        this.rand.setSeed((long)(var3 ^ var4 << 4) ^ this.worldObj.getSeed());
        this.rand.nextInt();
        return this.rand.nextInt(3) != 0 ? false : (par1 != (var3 << 4) + 4 + this.rand.nextInt(8) ? false : par2 == (var4 << 4) + 4 + this.rand.nextInt(8));
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new StructureSpiderTunnelsStart(this.worldObj, this.rand, par1, par2);
    }
}
