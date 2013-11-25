package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityScorpionPincer extends EntityThrowable {

	public EntityLivingBase shootingEntity;
	public EntityScorpionPincer(World par1World) {
		super(par1World);
	}

	public EntityScorpionPincer(World par1World, EntityLivingBase par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public EntityScorpionPincer(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition MovingObjectPosition) {
		if (!worldObj.isRemote) {
			if (MovingObjectPosition.entityHit != null) {
				if (!MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 4))
					;
				if (!MovingObjectPosition.entityHit.isImmuneToFire())
					MovingObjectPosition.entityHit.setFire(5);
			} else {
				int i = MovingObjectPosition.blockX;
				int j = MovingObjectPosition.blockY;
				int k = MovingObjectPosition.blockZ;

				switch (MovingObjectPosition.sideHit) {
					case 0:
						--j;
						break;
					case 1:
						++j;
						break;
					case 2:
						--k;
						break;
					case 3:
						++k;
						break;
					case 4:
						--i;
						break;
					case 5:
						++i;
				}

				if (worldObj.isAirBlock(i, j, k))
					worldObj.setBlock(i, j, k, Block.fire.blockID);
				worldObj.createExplosion(this, i, j, k, 1, true);
			}

			setDead();
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}
}