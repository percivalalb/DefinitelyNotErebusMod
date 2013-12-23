package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWebSling extends EntitySnowball {

	public EntityWebSling(World world) {
		super(world);
	}

	public EntityWebSling(World world, EntityLiving par2EntityLiving) {
		super(world, par2EntityLiving);
	}

	public EntityWebSling(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	protected String getWebSlingSplatSound() {
		return "erebus:webslingsplat";
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (!worldObj.isRemote) {
			int var1 = MathHelper.floor_double(posX);
			int var2 = MathHelper.floor_double(posY);
			int var3 = MathHelper.floor_double(posZ);

			if (mop.entityHit != null)
				worldObj.setBlock(var1, var2, var3, Block.web.blockID);
			else if (mop.entityHit == null && Block.web.canPlaceBlockAt(worldObj, var1, var2, var3))
				worldObj.setBlock(var1, var2, var3, Block.web.blockID);
			if (!worldObj.isRemote)
				setDead();
		}
		worldObj.playSoundAtEntity(this, getWebSlingSplatSound(), 1.0F, 1.0F);
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, int par2) {
		return false;
	}
}