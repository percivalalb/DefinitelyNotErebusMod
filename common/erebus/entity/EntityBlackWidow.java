package erebus.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityBlackWidow extends EntityMob {

	private int shouldDo;
	Class[] preys = { EntityFly.class, EntityBotFly.class };

	public EntityBlackWidow(World world) {
		super(world);
		setSize(1.0F, 1.0F);
		isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, 0.4F + rand.nextFloat());
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1;
	}

	protected Entity findEnemyToAttack() {
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(10D, 10D, 10D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity != null) {
				if (!(entity instanceof EntityLivingBase))
					continue;
				for (int j = 0; j < preys.length; j++)
					if (entity.getClass() == preys[j])
						return entity;
			}
		}
		return null;
	}

	@Override
	public void onUpdate() {
		setSize(getWidowSize() * 2.0F, getWidowSize());
		if (!worldObj.isRemote && getWidowSize() <= 0.7F) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(15.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D); // atkDmg
		}
		if (!worldObj.isRemote && getWidowSize() > 0.7F && getWidowSize() <= 1.0F) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.5D); // atkDmg
		}
		if (!worldObj.isRemote && getWidowSize() > 1.0F) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		}
		if (findPlayerToAttack() != null)
			entityToAttack = findPlayerToAttack();
		else if (findEnemyToAttack() != null)
			entityToAttack = findEnemyToAttack();
		else
			entityToAttack = null;
		super.onUpdate();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float distance) {
	}

	@Override
	public void setInWeb() {
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:blackwidowsound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:blackwidowhurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected String getWebSlingThrowSound() {
		return "erebus:webslingthrow";
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected int getDropItemId() {
		return Item.silk.itemID;
	}

	@Override
	protected void dropFewItems(boolean attackedByPlayer, int looting) {
		super.dropFewItems(attackedByPlayer, looting);
		if (attackedByPlayer && (rand.nextInt(3) == 0 || rand.nextInt(1 + looting) > 0))
			dropItem(Item.spiderEye.itemID, 1);
	}

	@Override
	protected void attackEntity(Entity entity, float damage) {
		if (attackTime <= 0 && damage < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 20;
			attackEntityAsMob(entity);
		} else if (damage > 5.0F & damage < 8.0F)
			if (attackTime == 0) {
				++shouldDo;
				if (shouldDo == 1)
					attackTime = 60;
				else if (shouldDo <= 4)
					attackTime = 6;
				else {
					attackTime = 100;
					shouldDo = 0;
				}
				if (shouldDo > 1 && getWidowSize() > 0.7F && entity instanceof EntityPlayer) {
					worldObj.playSoundAtEntity(this, getWebSlingThrowSound(), 1.0F, 1.0F);
					for (int var10 = 0; var10 < 1; ++var10) {
						EntityWebSling var11 = new EntityWebSling(worldObj, this);
						var11.posY = posY + height / 2.0F + 0.5D;
						worldObj.spawnEntityInWorld(var11);
					}
				}
			}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte var2 = 0;
				if (worldObj.difficultySetting > 1)
					if (worldObj.difficultySetting == 2)
						var2 = 7;
					else if (worldObj.difficultySetting == 3)
						var2 = 15;
				if (var2 > 0) {
					int chanceFiftyFifty = rand.nextInt(2);
					if (chanceFiftyFifty == 1)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, var2 * 20, 0));
				}
			}
			return true;
		} else
			return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setFloat("size", getWidowSize());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("size"))
			setWidowSize(nbt.getFloat("size"));
	}

	private void setWidowSize(float size) {
		dataWatcher.updateObject(16, size);
	}

	public float getWidowSize() {
		return dataWatcher.getWatchableObjectFloat(16);
	}
}