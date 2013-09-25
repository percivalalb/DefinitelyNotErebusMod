package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityVelvetWorm extends EntityMob implements IRangedAttackMob {
	// Help for animation
	// private RenderInfo renderdata = new RenderInfo();

	private final EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.25F, 20, 60, 15.0F);

	public EntityVelvetWorm(World par1World) {
		super(par1World);
		// this.moveSpeed = 0.8F;
		setSize(1.35F, 1.0F/*
							 * Never put lower than 1.0F else it would be
							 * nervous to hit
							 */);
		getNavigator().setAvoidsWater(false);
		experienceValue = 15;
		fireResistance = 10;
		isImmuneToFire = false;
		// this.health = 25;
		tasks.addTask(0, new EntityAISwimming(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		// TODO this.targetTasks.addTask(2, new
		// EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0,
		// true));
		tasks.addTask(3, new EntityAILookIdle(this));
		tasks.addTask(5, new EntityAIWander(this, 0.8F));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));

		if (par1World != null && !par1World.isRemote)
			tasks.addTask(4, aiArrowAttack);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(13, new Byte((byte) 0));
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.8D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(getAttackStrength()); // atkDmg
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	// Ten percent change to get the first else take the other
	public int skin = rand.nextInt(99);

	public String getTexture() {
		if (skin <= 10)
			return "/Erebus/Textures/Mob/Velvet worm2.png";
		else
			return "/Erebus/Textures/Mob/Velvet worm.png";
	}

	/**
	 * Returns the amount of damage a mob should deal.
	 */
	public double getAttackStrength() {
		switch (worldObj.difficultySetting) {
			default:
				return 4.0D;
			case 1:
				return 4.0D;
			case 2:
				return 5.0D;
			case 3:
				return 6.0D;
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity))
			return true;
		else
			return false;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int chanceFiftyFifty = rand.nextInt(1) + 1;

		dropItem(Item.slimeBall.itemID, chanceFiftyFifty + par2);
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving, float par2) {
		EntityArrow entityarrow = new EntityArrow(worldObj, this, par1EntityLiving, 1.0F, 14 - worldObj.difficultySetting * 4);
		entityarrow.setDamage(par2 * 2.0F + rand.nextGaussian() * 0.25D + worldObj.difficultySetting * 0.11F);
		worldObj.spawnEntityInWorld(entityarrow);
	}
}
