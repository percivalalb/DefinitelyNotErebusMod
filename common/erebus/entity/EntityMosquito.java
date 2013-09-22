package erebus.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import erebus.client.render.entity.AnimationMathHelper;

public class EntityMosquito extends EntityFlying implements IMob {
	// this integer makes flying smoother
	public int courseChangeCooldown;

	// waypoints used for movement
	public double waypointX;
	public double waypointY;
	public double waypointZ;

	// used for cooldown of causing damage
	private int drainage;

	// entity it will attack
	Entity entityToAttack;

	// determines constant animation movement
	AnimationMathHelper mathWings = new AnimationMathHelper();
	AnimationMathHelper mathSucking = new AnimationMathHelper();
	public float wingFloat;
	public float suckFloat;

	// used for checking position on first tick
	public boolean firstTickCheck;

	// feel free to play around with the three following variables:

	// variable that determines the max amount of hits a Mosquito does before
	// being content
	// it also determines a part of the maximum health
	private final int maxBloodLevel = 5;
	// variable that determines the time between hits when sucking
	public int hitInterval = 30;
	// add classes to this array to make it attack specific mobs! I put pig for
	// testing.
	// example: EntityPig.class,EntityCow.class,EntityZombie.class etc.
	Class[] preys = { EntityPig.class, EntityCow.class };

	public EntityMosquito(World par1World) {
		super(par1World);
		drainage = 0;
		entityToAttack = null;
		setSize(1.0F, 1.8F);
		wingFloat = 0.0F;
		suckFloat = 1.0F;
		firstTickCheck = false;
		setBloodConsumed(0);
		// TODO health = 10;
	}

	// gets stored NBTcompound data
	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(15, Byte.valueOf((byte) 0));
	}

	@Override
	public void onUpdate() {
		// revent the mosquito the glitch when the game is saved on top of an
		// entity
		if (!firstTickCheck) {
			mountEntity(null);
			ridingEntity = null;
			firstTickCheck = true;
		}

		// calculate wing movement for animation
		// calculate sucking movement for animation and particles
		if (ridingEntity != null) {
			suckFloat = 1.0F + mathSucking.swing(1.0F, 0.15F);
			if (rand.nextInt(10) == 0)
				for (int i = 0; i < 8; i++)
					worldObj.spawnParticle("reddust", posX + (rand.nextFloat() - rand.nextFloat()), posY + rand.nextFloat() + 1D, posZ + (rand.nextFloat() - rand.nextFloat()), 0, 0, 0);
			wingFloat = 0.0F;
		} else {
			suckFloat = 1.0F;
			wingFloat = mathWings.swing(4.0F, 0.1F);
		}

		// find enemies
		if (findPlayerToAttack() != null && getBloodConsumed() < maxBloodLevel)
			entityToAttack = findPlayerToAttack();
		else if (findEnemyToAttack() != null && getBloodConsumed() < maxBloodLevel)
			entityToAttack = findEnemyToAttack();
		else
			entityToAttack = null;

		// mount enemies to start sucking blood
		if (entityToAttack != null && ridingEntity == null && getDistanceToEntity(entityToAttack) <= 1.2D && !worldObj.isRemote && entityToAttack.riddenByEntity == null && getBloodConsumed() < maxBloodLevel)
			mountEntity(entityToAttack);

		super.onUpdate();
	}

	// determine offset when mounting mobs
	@Override
	public double getYOffset() {
		if (ridingEntity != null && ridingEntity instanceof EntityPlayer)
			return -2D;
		else if (ridingEntity != null)
			return ridingEntity.height * 0.75D - 1.0D;
		else
			return yOffset;
	}

	@Override
	protected void updateEntityActionState() {
		super.updateEntityActionState();
		double d = waypointX - posX;
		double d1 = waypointY - posY;
		double d2 = waypointZ - posZ;
		double d3 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		int i = worldObj.getHeightValue((int) waypointX, (int) waypointZ);
		int j = MathHelper.floor_double(posX);
		int k = MathHelper.floor_double(posY);
		int l = MathHelper.floor_double(posZ);
		int m = worldObj.getBlockId(j, k - 1, l);

		// get random waypoints to fly to
		if (d3 < 1.0D || d3 > 60D || waypointY > i + 5 || waypointY <= i + 1 && waypointY > i - 1) {
			waypointX = posX + (rand.nextFloat() * 2.0F - 1.0F) * 2F;
			waypointY = posY + (rand.nextFloat() * 2.0F - 1.0F) * 2F;
			waypointZ = posZ + (rand.nextFloat() * 2.0F - 1.0F) * 2F;
		}

		// get position of enemy and use it to determine waypoints
		if (entityToAttack != null) {
			waypointX = entityToAttack.posX;
			waypointY = entityToAttack.posY;
			waypointZ = entityToAttack.posZ;
		}

		// damaging when riding enemy
		if (ridingEntity != null && ridingEntity instanceof EntityLiving && getBloodConsumed() < maxBloodLevel) {
			drainage++;
			if (drainage >= hitInterval) {
				ridingEntity.attackEntityFrom(DamageSource.causeMobDamage(this), getDamage());
				drainage = 0;
				setBloodConsumed(getBloodConsumed() + 1);
				// TODO health += 1;
			}
		} else if (ridingEntity != null && ridingEntity instanceof EntityLiving && getBloodConsumed() >= maxBloodLevel) {
			entityToAttack = null;
			mountEntity(ridingEntity);
		}

		// for smooth turning while flying
		if (courseChangeCooldown-- <= 0) {
			courseChangeCooldown = 1;

			if (isCourseTraversable(waypointX, waypointY, waypointZ, d3)) {
				motionX += (d / d3) * 0.1D;
				motionY += (d1 / d3) * 0.1D;
				motionZ += (d2 / d3) * 0.1D;
			} else {
				waypointX = posX;
				waypointY = posY;
				waypointZ = posZ;
			}
		}

		// prevent underwater flying
		if (m == Block.waterStill.blockID && rand.nextInt(10) == 0 && (motionX > 0.05D || motionZ > 0.05D || motionX < -0.05D || motionZ < -0.05D))
			motionY = 0.25D;
	}

	// removes step sounds when on the ground
	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
	}

	@Override
	public void onLivingUpdate() {
		// rotate towards waypoints
		float var7 = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - rotationYaw);
		rotationYaw += var8;
		super.onLivingUpdate();
	}

	// used to determine if waypoints will not cause problems
	private boolean isCourseTraversable(double d, double d1, double d2, double d3) {
		double d4 = (waypointX - posX) / d3;
		double d5 = (waypointY - posY) / d3;
		double d6 = (waypointZ - posZ) / d3;
		AxisAlignedBB axisalignedbb = boundingBox.copy();

		for (int i = 1; i < d3; i++) {
			axisalignedbb.offset(d4, d5, d6);

			if (worldObj.getCollidingBoundingBoxes(this, axisalignedbb).size() > 0)
				return false;
		}

		return true;
	}

	// determines if it should fight back when hit
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (isEntityInvulnerable())
			return false;
		else if (super.attackEntityFrom(par1DamageSource, par2)) {
			Entity var3 = par1DamageSource.getEntity();

			if (riddenByEntity != var3 && ridingEntity != var3) {
				if (var3 != this)
					entityToAttack = var3;

				return true;
			} else if (ridingEntity == var3 && !worldObj.isRemote) {
				mountEntity(ridingEntity);
				motionY += 0.5F;
				return true;
			} else
				return true;
		} else
			return false;
	}

	@Override
	public boolean canDespawn() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 10.0D);
		return var1 != null && canEntityBeSeen(var1) ? var1 : null;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int i = 1 + getBloodConsumed();
		dropItem(Item.netherStalkSeeds.itemID, i);
	}

	// find enemies other than players to attack. uses preys array
	protected Entity findEnemyToAttack() {
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(10D, 10D, 10D));

		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity != null) {
				if (!(entity instanceof EntityCreature))
					continue;

				for (int j = 0; j < preys.length; j++)
					if (entity.getClass() == preys[j] && entity.riddenByEntity == null)
						return canEntityBeSeen(entity) ? entity : null;
			}
		}

		return null;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10 + maxBloodLevel);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.3D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	// determine damage it deals
	public int getDamage() {
		int var2 = 2;

		if (this.isPotionActive(Potion.damageBoost))
			var2 += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();

		if (this.isPotionActive(Potion.weakness))
			var2 -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();

		return var2;

	}

	@Override
	protected String getLivingSound() {
		if (ridingEntity != null)
			return "erebus:mosquito_sucking";
		else
			return "erebus:mosquito_flying";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:mosquito_hit";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:mosquito_death";
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	protected float getSoundPitch() {
		return 1.0F;
	}

	// the part below saves and keeps track of the Blood Level of the mosquito
	public int getBloodConsumed() {
		return dataWatcher.getWatchableObjectByte(15);
	}

	public void setBloodConsumed(int par1) {
		dataWatcher.updateObject(15, Byte.valueOf((byte) par1));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("BloodLevel", getBloodConsumed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setBloodConsumed(nbttagcompound.getInteger("BloodLevel"));
	}

	// checks if entity can spawn. in this case it checks of water is nearby
	@Override
	public boolean getCanSpawnHere() {
		// if(this.worldObj.checkIfAABBIsClear(this.boundingBox) &&
		// this.worldObj.getCollidingBoundingBoxes(this,
		// this.boundingBox).isEmpty())
		{
			AxisAlignedBB axisalignedbb = boundingBox.expand(5D, 5D, 5D);
			int n = MathHelper.floor_double(axisalignedbb.minX);
			int o = MathHelper.floor_double(axisalignedbb.maxX + 1.0D);
			int p = MathHelper.floor_double(axisalignedbb.minY);
			int q = MathHelper.floor_double(axisalignedbb.maxY + 1.0D);
			int n1 = MathHelper.floor_double(axisalignedbb.minZ);
			int o1 = MathHelper.floor_double(axisalignedbb.maxZ + 1.0D);

			for (int p1 = n; p1 < o; p1++)
				for (int q1 = p; q1 < q; q1++)
					for (int n2 = n1; n2 < o1; n2++) {
						int o2 = worldObj.getBlockId(p1, q1, n2);

						if (o2 == 0)
							continue;

						if (o2 == Block.waterStill.blockID)
							return true;
					}
		}

		return false;
	}

}