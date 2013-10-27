package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;

public class TileEntityErebusAltarLightning extends TileEntity {

	public int animationTicks = 0;
	boolean active = true;

	@Override
	public void updateEntity() {

		if (active)
			if (animationTicks <= 24)
				animationTicks++;
		if (!active) {
			if (animationTicks >= 1)
				animationTicks--;
			if (animationTicks == 0)
				// setDead();
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.erebusAltar.blockID);
		}
		if (animationTicks <= 24)
			flameOn(worldObj, xCoord, yCoord, zCoord);
		System.out.println(active);
		System.out.println(animationTicks);

	}

	@SideOnly(Side.CLIENT)
	public void flameOn(World par1World, int par2, int par3, int par4) {
		double d0 = par2 + 0.53125F;
		double d1 = par3 + 1.25F;
		double d2 = par4 + 0.53125F;
		par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
	}

	public void setActive(boolean par1) {
		active = par1;
	}

}
