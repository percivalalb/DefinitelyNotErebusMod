package erebus.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityScorpionPincer extends EntityThrowable {

	private boolean shouldReturn;

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
		if (MovingObjectPosition.entityHit != null) {
			byte byte0 = 6;
			if (!MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), byte0))
				;
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}
}