package erebus.world;

import erebus.world.biomes.*;
import erebus.ErebusMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderErebus extends WorldProvider {
	
	public WorldProviderErebus() {}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return true;
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.5F;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Vec3 getFogColor(float par1, float par2) {
		BiomeGenBase b = worldObj.getBiomeGenForCoords((int)Minecraft.getMinecraft().thePlayer.posX, (int)Minecraft.getMinecraft().thePlayer.posZ);
		double red = 0.029999999329447746D * 255D;
		double green = 0.49999999329447746D * 255D;
		double blue = 0.029999999329447746D * 255D;
		if(b instanceof BiomeGenBaseErebus) {
			BiomeGenBaseErebus biome = (BiomeGenBaseErebus)b;
			if(biome instanceof BiomeGenUndergroundSavannah) {
				red = 140D;
				green = 116D;
				blue = 9D;
			}
			else if(biome instanceof BiomeGenUndergroundDesert) {
				red = 255D;
				green = 231D;
				blue = 10D;
			}
			else if(biome instanceof BiomeGenCavern) {
				red = 100D;
				green = 100D;
				blue = 100D;
			}
		}
		return this.worldObj.getWorldVec3Pool().getVecFromPool(red/255D, green/255D, blue/255D);
	}

	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for (int i = 0; i <= 15; i++) {
			float f1 = 1.0F - (float)i / 15F;
			lightBrightnessTable[i] = ((1.0F - f1) / (f1 * 3F + 1.0F)) * (1.0F - f) + f;
		}
	}

	@Override
	public void registerWorldChunkManager() {
		worldChunkMgr = new WorldChunkManagerErebus(1.0F, 0.0F, this.worldObj);    
		this.hasNoSky = true;
		this.dimensionId = 66;
	}        

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderUndergroundJungle(this.worldObj, this.worldObj.getSeed());
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2) {
		return false;
	}
	
	@Override
	public String getDimensionName() {
		return "Erebus";
	}
	
	@Override
	public ChunkCoordinates getRandomizedSpawnPoint() {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());

        boolean isAdventure = worldObj.getWorldInfo().getGameType() == EnumGameType.ADVENTURE;
        int spawnFuzz = 100;
        int spawnFuzzHalf = spawnFuzz / 2;

        if (!hasNoSky && !isAdventure) {
            chunkcoordinates.posX += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            chunkcoordinates.posZ += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            chunkcoordinates.posY = this.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
        }

        return chunkcoordinates;
    }
}