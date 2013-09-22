package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.ModItems;

public class EntitySolifuge extends EntityMob {
	protected EntityLiving theEntity;

	public EntitySolifuge(World par1World) {

		super(par1World);
		isImmuneToFire = true;
		setSize(2.0F, 1.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.9D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	/*
	 * just to avoid crashes
	 * 
	 * @Override protected String getLivingSound() { return
	 * "erebus:solifugesound"; }
	 * 
	 * @Override protected String getHurtSound() { return
	 * "erebus:solifugenhurt"; }
	 * 
	 * @Override protected String getDeathSound() { return "erebus:squish"; }
	 */
	protected void getStepSound(int par1, int par2, int par3, int par4) {
		worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, 8), 0.0F);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, 0), 0.0F);
	}

	@Override
	public boolean isOnLadder() {
		return (isCollidedHorizontally);
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {

		if (par2 > 2.0F && par2 < 6.0F && rand.nextInt(10) == 0) {
			if (onGround) {
				double d0 = par1Entity.posX - posX;
				double d1 = par1Entity.posZ - posZ;
				float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
				motionX = d0 / f2 * 0.5D * 0.900000011920929D + motionX * 0.60000000298023224D;
				motionZ = d1 / f2 * 0.5D * 0.900000011920929D + motionZ * 0.60000000298023224D;
				motionY = 0.5000000059604645D;
			}
		} else
			super.attackEntity(par1Entity, par2);
	}
}
