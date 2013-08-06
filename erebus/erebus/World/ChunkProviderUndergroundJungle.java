package erebus.World;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

import erebus.mod_Erebus;
import erebus.World.Biomes.BiomeGenBaseErebus;
import erebus.World.Biomes.BiomeGenUndergroundDesert;
import erebus.World.Biomes.BiomeGenUndergroundSavannah;
import erebus.World.Gen.WorldGenSpiderDungeons;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;

public class ChunkProviderUndergroundJungle implements IChunkProvider
{
    private Random hellRNG;

    /** A NoiseGeneratorOctaves used in generating nether terrain */
    private NoiseGeneratorOctaves netherNoiseGen1;
    private NoiseGeneratorOctaves netherNoiseGen2;
    private NoiseGeneratorOctaves netherNoiseGen3;

    /** Determines whether slowsand or gravel can be generated at a location */
    private NoiseGeneratorOctaves slowsandGravelNoiseGen;

    /**
     * Determines whether something other than nettherack can be generated at a location
     */
    private NoiseGeneratorOctaves stoneExculsivityNoiseGen;
    public NoiseGeneratorOctaves netherNoiseGen6;
    public NoiseGeneratorOctaves netherNoiseGen7;

    /** Is the world that the nether is getting generated. */
    private World worldObj;
    private double field_4163_o[];
    private double slowsandNoise[];
    private double gravelNoise[];
    private double stoneExclusivityNoise[];
    private MapGenBase netherCaveGenerator;
    double noiseData1[];
    double noiseData2[];
    double noiseData3[];
    double noiseData4[];
    double noiseData5[];

    public ChunkProviderUndergroundJungle(World par1World, long par2)
    {
        slowsandNoise = new double[256];
        gravelNoise = new double[256];
        stoneExclusivityNoise = new double[256];
        netherCaveGenerator = new MapGenCavesHell();
        worldObj = par1World;
        hellRNG = new Random(par2);
        rand = new Random(par2);
        netherNoiseGen1 = new NoiseGeneratorOctaves(hellRNG, 16);
        netherNoiseGen2 = new NoiseGeneratorOctaves(hellRNG, 16);
        netherNoiseGen3 = new NoiseGeneratorOctaves(hellRNG, 8);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        slowsandGravelNoiseGen = new NoiseGeneratorOctaves(hellRNG, 4);
        stoneExculsivityNoiseGen = new NoiseGeneratorOctaves(hellRNG, 4);
        netherNoiseGen6 = new NoiseGeneratorOctaves(hellRNG, 10);
        netherNoiseGen7 = new NoiseGeneratorOctaves(hellRNG, 16);
    }

    /**
     * Generates the shape of the terrain in the nether.
     */
    public void generateNetherTerrain(int par1, int par2, byte par3ArrayOfByte[])
    {
        byte byte0 = 4;
        byte byte1 = 32;
        int i = byte0 + 1;
        byte byte2 = 17;
        int j = byte0 + 1;
        field_4163_o = func_4057_a(field_4163_o, par1 * byte0, 0, par2 * byte0, i, byte2, j);

        for (int k = 0; k < byte0; k++)
        {
            for (int l = 0; l < byte0; l++)
            {
                for (int i1 = 0; i1 < 16; i1++)
                {
                    double d = 0.125D;
                    double d1 = field_4163_o[((k + 0) * j + (l + 0)) * byte2 + (i1 + 0)];
                    double d2 = field_4163_o[((k + 0) * j + (l + 1)) * byte2 + (i1 + 0)];
                    double d3 = field_4163_o[((k + 1) * j + (l + 0)) * byte2 + (i1 + 0)];
                    double d4 = field_4163_o[((k + 1) * j + (l + 1)) * byte2 + (i1 + 0)];
                    double d5 = (field_4163_o[((k + 0) * j + (l + 0)) * byte2 + (i1 + 1)] - d1) * d;
                    double d6 = (field_4163_o[((k + 0) * j + (l + 1)) * byte2 + (i1 + 1)] - d2) * d;
                    double d7 = (field_4163_o[((k + 1) * j + (l + 0)) * byte2 + (i1 + 1)] - d3) * d;
                    double d8 = (field_4163_o[((k + 1) * j + (l + 1)) * byte2 + (i1 + 1)] - d4) * d;

                    for (int j1 = 0; j1 < 8; j1++)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k1 = 0; k1 < 4; k1++)
                        {
                            int l1 = k1 + k * 4 << 11 | 0 + l * 4 << 7 | i1 * 8 + j1;
                            char c = '\200';
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int i2 = 0; i2 < 4; i2++)
                            {
                                int j2 = 0;

                                //Underground Water
                                if (i1 * 8 + j1 < byte1)
                                {
                                    j2 = mod_Erebus.umberstone.blockID;
                                }

                                if (d15 > 0.0D)
                                {
                                    j2 = mod_Erebus.umberstone.blockID;
                                }

                                par3ArrayOfByte[l1] = (byte)j2;
                                l1 += c;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void func_4058_b(int par1, int par2, byte par3ArrayOfByte[])
    {
        byte byte0 = 64;
        double d = 0.03125D;
        slowsandNoise = slowsandGravelNoiseGen.generateNoiseOctaves(slowsandNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d, d, 1.0D);
        gravelNoise = slowsandGravelNoiseGen.generateNoiseOctaves(gravelNoise, par1 * 16, 109, par2 * 16, 16, 1, 16, d, 1.0D, d);
        stoneExclusivityNoise = stoneExculsivityNoiseGen.generateNoiseOctaves(stoneExclusivityNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, d * 2D, d * 2D, d * 2D);

        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 16; j++)
            {
                boolean flag = slowsandNoise[i + j * 16] + 10 > 0.0D;
                boolean flag1 = gravelNoise[i + j * 16] + 10 > 0.0D;
                int k = (int)(stoneExclusivityNoise[i + j * 16] / 3D + 3D + hellRNG.nextDouble() * 0.25D);
                int l = -1;
                byte byte1 = (byte)mod_Erebus.umberstone.blockID;
                byte byte2 = (byte)mod_Erebus.umberstone.blockID;

                for (int i1 = 127; i1 >= 0; i1--)
                {
                    int j1 = (j * 16 + i) * 128 + i1;

                    if (i1 >= 127 - hellRNG.nextInt(5))
                    {
                        par3ArrayOfByte[j1] = (byte)Block.bedrock.blockID;
                        continue;
                    }

                    if (i1 <= 0 + hellRNG.nextInt(5))
                    {
                        par3ArrayOfByte[j1] = (byte)Block.bedrock.blockID;
                        continue;
                    }

                    byte byte3 = par3ArrayOfByte[j1];

                    if (byte3 == 0)
                    {
                        l = -1;
                        
                        if (par3ArrayOfByte[j1] == 0 && par3ArrayOfByte[j1 - 1] == mod_Erebus.umberstone.blockID)
                        {
                        	par3ArrayOfByte[j1 - 1] = (byte)mod_Erebus.umberstone.blockID;
                        }
                        continue;
                    }

                    if (l == -1)
                    {
                        if (k <= 2)
                        {
                            byte1 = (byte)mod_Erebus.umberstone.blockID;
                            byte2 = (byte)mod_Erebus.umberstone.blockID;
                        }
                        else if (i1 >= byte0 - 4 && i1 <= byte0 + 1)
                        {
                            byte1 = (byte)mod_Erebus.umberstone.blockID;
                            byte2 = (byte)mod_Erebus.umberstone.blockID;
                        }

                        //Underground Water
                        if (i1 < byte0 && byte1 == 0)
                        {
                            byte1 = (byte)mod_Erebus.umberstone.blockID;
                        }

                        l = k;

                        if (i1 >= byte0 - 1)
                        {
                            par3ArrayOfByte[j1] = byte1;
                        }
                        else
                        {
                            par3ArrayOfByte[j1] = byte2;
                        }
                    }
                    
                    if (l > 0)
                    {
                        l--;
                        par3ArrayOfByte[j1] = byte2;
                    }
                    
                    if (par3ArrayOfByte[j1 + 1] == 0 && par3ArrayOfByte[j1] == Block.dirt.blockID)
                    {
                    	par3ArrayOfByte[j1] = (byte)mod_Erebus.umberstone.blockID;
                    }
                }
            }
        }
    }

    @Override
    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }

    @Override
    public Chunk provideChunk(int par1, int par2)
    {
        hellRNG.setSeed((long)par1 * 0x4f9939f508L + (long)par2 * 0x1ef1565bd5L);
        rand.setSeed((long)par1 * 0x4f9939f508L + (long)par2 * 0x1ef1565bd5L);
        byte abyte0[] = new byte[32768];
        BiomeGenBase abiomegenbase[] = worldObj.getWorldChunkManager().loadBlockGeneratorData(null, par1 * 16, par2 * 16, 16, 16);
        generateNetherTerrain(par1, par2, abyte0);
        func_4058_b(par1, par2, abyte0);
        this.replaceBlocksForBiome(par1, par2, abyte0, abiomegenbase);
        //this.genSpiderTunnels.generate(this, this.worldObj, par1, par2, abyte0);
        netherCaveGenerator.generate(this, worldObj, par1, par2, abyte0);
        Chunk chunk = new Chunk(worldObj, abyte0, par1, par2);
        /*byte abyte1[] = chunk.getBiomeArray();

        for (int i = 0; i < abyte1.length; i++)
        {
            abyte1[i] = (byte)abiomegenbase[i].biomeID;
        }*/

        chunk.resetRelightChecks();
        
        return chunk;
    }

    private double[] func_4057_a(double par1ArrayOfDouble[], int par2, int par3, int par4, int par5, int par6, int par7)
    {
        if (par1ArrayOfDouble == null)
        {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        double d = 684.41200000000003D;
        double d1 = 2053.2359999999999D;
        noiseData4 = netherNoiseGen6.generateNoiseOctaves(noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
        noiseData5 = netherNoiseGen7.generateNoiseOctaves(noiseData5, par2, par3, par4, par5, 1, par7, 100D, 0.0D, 100D);
        noiseData1 = netherNoiseGen3.generateNoiseOctaves(noiseData1, par2, par3, par4, par5, par6, par7, d / 80D, d1 / 60D, d / 80D);
        noiseData2 = netherNoiseGen1.generateNoiseOctaves(noiseData2, par2, par3, par4, par5, par6, par7, d, d1, d);
        noiseData3 = netherNoiseGen2.generateNoiseOctaves(noiseData3, par2, par3, par4, par5, par6, par7, d, d1, d);
        int i = 0;
        int j = 0;
        double ad[] = new double[par6];

        for (int k = 0; k < par6; k++)
        {
            ad[k] = Math.cos(((double)k * Math.PI * 6D) / (double)par6) * 2D;
            double d2 = k;

            if (k > par6 / 2)
            {
                d2 = par6 - 1 - k;
            }

            if (d2 < 4D)
            {
                d2 = 4D - d2;
                ad[k] -= d2 * d2 * d2 * 10D;
            }
        }

        for (int l = 0; l < par5; l++)
        {
            for (int i1 = 0; i1 < par7; i1++)
            {
                double d3 = (noiseData4[j] + 256D) / 512D;

                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = noiseData5[j] / 8000D;

                if (d5 < 0.0D)
                {
                    d5 = -d5;
                }

                d5 = d5 * 3D - 3D;

                if (d5 < 0.0D)
                {
                    d5 /= 2D;

                    if (d5 < -1D)
                    {
                        d5 = -1D;
                    }

                    d5 /= 1.3999999999999999D;
                    d5 /= 2D;
                    d3 = 0.0D;
                }
                else
                {
                    if (d5 > 1.0D)
                    {
                        d5 = 1.0D;
                    }

                    d5 /= 6D;
                }

                d3 += 0.5D;
                d5 = (d5 * (double)par6) / 16D;
                j++;

                for (int j1 = 0; j1 < par6; j1++)
                {
                    double d6 = 0.0D;
                    double d7 = ad[j1];
                    double d8 = noiseData2[i] / 512D;
                    double d9 = noiseData3[i] / 512D;
                    double d10 = (noiseData1[i] / 10D + 1.0D) / 2D;

                    if (d10 < 0.0D)
                    {
                        d6 = d8;
                    }
                    else if (d10 > 1.0D)
                    {
                        d6 = d9;
                    }
                    else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;

                    if (j1 > par6 - 4)
                    {
                        double d11 = (float)(j1 - (par6 - 4)) / 3F;
                        d6 = d6 * (1.0D - d11) + -10D * d11;
                    }

                    if ((double)j1 < d4)
                    {
                        double d12 = (d4 - (double)j1) / 4D;

                        if (d12 < 0.0D)
                        {
                            d12 = 0.0D;
                        }

                        if (d12 > 1.0D)
                        {
                            d12 = 1.0D;
                        }

                        d6 = d6 * (1.0D - d12) + -10D * d12;
                    }

                    par1ArrayOfDouble[i] = d6;
                    i++;
                }
            }
        }

        return par1ArrayOfDouble;
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockSand.fallInstantly = true;
        //this.genSpiderTunnels.generateStructuresInChunk(this.worldObj, this.hellRNG, par2, par3);
        
        /**Convert chunk coords to world coords**/
        int i = par2 * 16;
        int j = par3 * 16;

        /**Get biome at world coords**/
        BiomeGenBase b = worldObj.getBiomeGenForCoords(i, j);
        if(b instanceof BiomeGenBaseErebus)
        {
        	/**Generate biome specific world generation**/
        	((BiomeGenBaseErebus)b).generateTerrain(worldObj, hellRNG, par1IChunkProvider, i, j);
        	
        	/**Generate default world generation (e.g. ores)**/
        	((BiomeGenBaseErebus)b).generateDefault(worldObj, hellRNG, par1IChunkProvider, i, j);
        }   
        
        int k1;
        int l1;
        int i2;
        
        int k = par2 * 16;
        int l = par3 * 16;
        
        for (l1 = 0; l1 < 8; ++l1)
        {
            k1 = k + this.rand.nextInt(16) + 8;
            i2 = this.rand.nextInt(128);
            int j2 = l + this.rand.nextInt(16) + 8;
            (new WorldGenSpiderDungeons()).generate(this.worldObj, this.rand, k1, i2, j2);
        }
        
        
        
        BlockSand.fallInstantly = false;
    }
    
    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    /**
     * Unloads the 100 oldest chunks from memory, due to a bug with chunkSet.add() never being called it thinks the list
     * is always empty and will not remove any chunks.
     */
    public boolean unload100OldestChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "HellRandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        /*if (par1EnumCreatureType == EnumCreatureType.monster && this.genSpiderTunnels.hasStructureAt(par2, par3, par4))
        {
            return this.genSpiderTunnels.getSpawnList();
        }
        else
        {*/
            BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
            return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
        //}
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int i, int j)
    {
        return null;
    }

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int par1, int par2)
    {
        //this.genSpiderTunnels.generate(this, this.worldObj, par1, par2, (byte[])null);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**Testing stuff**/
    private double[] noiseArray;
    private double[] stoneNoise = new double[256];
    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen1;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen2;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen3;

    /** A NoiseGeneratorOctaves used in generating terrain */
    private NoiseGeneratorOctaves noiseGen4;

    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen5;

    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen6;
    
    public Random rand;
	
    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, par1, par2, par3ArrayOfByte, par4ArrayOfBiomeGenBase);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;

        byte var5 = 0;
        double var6 = 0.03125D;
        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
                BiomeGenBase var10 = par4ArrayOfBiomeGenBase[var9 + var8 * 16];
                float var11 = var10.getFloatTemperature();
                int var12 = (int)(this.stoneNoise[var8 + var9 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var13 = -1;
                byte var14 = var10.topBlock;
                byte var15 = var10.fillerBlock;

                for (int var16 = 127; var16 >= 0; --var16)
                {
                    int var17 = (var9 * 16 + var8) * 128 + var16;

                    if (var16 <= 0 + this.rand.nextInt(5))
                    {
                        par3ArrayOfByte[var17] = (byte)Block.bedrock.blockID;
                    }
                    else
                    {
                        byte var18 = par3ArrayOfByte[var17];
                        
                        if (var18 == 0)
                        {
                            var13 = -1;
                        }
                        else if (var18 == mod_Erebus.umberstone.blockID || var18 == mod_Erebus.umberstoneID - 256) //Double check for the "itemblock"
                        {
                            if (var13 == -1)
                            {
                                if (var12 <= 0)
                                {
                                    var14 = 0;
                                    var15 = (byte)mod_Erebus.umberstone.blockID;
                                }
                                else if (var16 >= var5 - 4 && var16 <= var5 + 1)
                                {
                            		var14 = var10.topBlock;
                                	var15 = var10.fillerBlock;                               
                                }

                                if (var16 < var5 && var14 == 0)
                                {
                                    if (var11 < 0.15F)
                                    {
                                        var14 = (byte)Block.ice.blockID;
                                    }
                                    else
                                    {
                                        var14 = (byte)Block.waterStill.blockID;
                                    }
                                }

                                var13 = var12;

                                if (var16 >= var5 - 1)
                                {
                                    par3ArrayOfByte[var17] = var14;
                                }
                                else
                                {
                                    par3ArrayOfByte[var17] = var15;
                                }
                            }
                            else if (var13 > 0)
                            {
                                --var13;
                                par3ArrayOfByte[var17] = var15;

                                if (var13 == 0 && var15 == Block.sand.blockID)
                                {
                                    var13 = this.rand.nextInt(4);
                                    var15 = (byte)Block.sandStone.blockID;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

	@Override
	public boolean unloadQueuedChunks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void func_104112_b() {
		// TODO Auto-generated method stub
		
	}

}
