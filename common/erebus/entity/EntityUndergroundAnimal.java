package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class EntityUndergroundAnimal extends EntityCreature implements IMob {

	public EntityUndergroundAnimal(World par1World) {
		super(par1World);
		experienceValue = 5;
	}

	@Override
	public void onLivingUpdate() {
		updateArmSwingProgress();
		float var1 = getBrightness(1.0F);

		if (var1 > 0.5F)
			entityAge += 2;

		super.onLivingUpdate();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!worldObj.isRemote && worldObj.difficultySetting == 0)
			setDead();
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1 != null && canEntityBeSeen(var1) ? var1 : null;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (isEntityInvulnerable())
			return false;
		else if (super.attackEntityFrom(par1DamageSource, par2)) {
			Entity var3 = par1DamageSource.getEntity();

			if (riddenByEntity != var3 && ridingEntity != var3) {
				if (var3 != this)
					entityToAttack = var3;

				return true;
			} else
				return true;
		} else
			return false;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		int var2 = getAttackStrength(par1Entity);

		if (this.isPotionActive(Potion.damageBoost))
			var2 += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();

		if (this.isPotionActive(Potion.weakness))
			var2 -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();

		int var3 = 0;

		if (par1Entity instanceof EntityLiving) {
			var2 += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLiving) par1Entity);
			var3 += EnchantmentHelper.getKnockbackModifier(this, (EntityLiving) par1Entity);
		}

		boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);

		if (var4) {
			if (var3 > 0) {
				par1Entity.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * var3 * 0.5F, 0.1D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * var3 * 0.5F);
				motionX *= 0.6D;
				motionZ *= 0.6D;
			}

			int var5 = EnchantmentHelper.getFireAspectModifier(this);

			if (var5 > 0)
				par1Entity.setFire(var5 * 4);

			if (par1Entity instanceof EntityLiving)
				EnchantmentThorns.func_92096_a(this, (EntityLiving) par1Entity, rand);
		}

		return var4;
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		if (attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > boundingBox.minY && par1Entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 20;
			attackEntityAsMob(par1Entity);
		}
	}

	@Override
	public float getBlockPathWeight(int par1, int par2, int par3) {
		return 0.5F - worldObj.getLightBrightness(par1, par2, par3);
	}

	protected boolean isValidLightLevel() {
		int var1 = MathHelper.floor_double(posX);
		int var2 = MathHelper.floor_double(boundingBox.minY);
		int var3 = MathHelper.floor_double(posZ);

		if (worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > rand.nextInt(32))
			return false;
		else {
			int var4 = worldObj.getBlockLightValue(var1, var2, var3);

			if (worldObj.isThundering()) {
				int var5 = worldObj.skylightSubtracted;
				worldObj.skylightSubtracted = 10;
				var4 = worldObj.getBlockLightValue(var1, var2, var3);
				worldObj.skylightSubtracted = var5;
			}

			return var4 <= rand.nextInt(8);
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(posX);
		int var2 = MathHelper.floor_double(boundingBox.minY);
		int var3 = MathHelper.floor_double(posZ);
		return worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID && super.getCanSpawnHere();
	}

	public int getAttackStrength(Entity par1Entity) {
		return 2;
	}
}
