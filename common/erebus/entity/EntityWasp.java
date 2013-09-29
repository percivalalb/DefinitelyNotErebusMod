package erebus.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.client.render.entity.AnimationMathHelper;

public class EntityWasp extends EntityMob {

	private float heightOffset = 0.5F;
	public float wingFloat;
	AnimationMathHelper mathWings = new AnimationMathHelper();
	Class[] preys = { EntityGrasshopper.class };

	public EntityWasp(World par1World) {
		super(par1World);
		setSize(2.0F, 2.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected String getLivingSound() {
		return "erebus:WaspSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:WaspHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4) {
		worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {

		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, 10), 0.0F);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, 0), 0.0F);
	}

	@Override
	protected void dropRareDrop(int par1) {
		dropItem(ModItems.waspSwordID, 1);
	}

	public boolean isFlying() {
		return !onGround;
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public void onUpdate() {
		if (!isFlying())
			wingFloat = 0.0F;
		if (isFlying())
			wingFloat = mathWings.swing(4.0F, 0.1F);
		// find enemies
		if (findPlayerToAttack() != null)
			entityToAttack = findPlayerToAttack();
		else if (findEnemyToAttack() != null)
			entityToAttack = findEnemyToAttack();
		else
			entityToAttack = null;
		super.onUpdate();
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote) {
			heightOffset = 0.5F + (float) rand.nextGaussian() * 5.0F;
			if (getEntityToAttack() != null && getEntityToAttack().posY + getEntityToAttack().getEyeHeight() > posY + getEyeHeight() + heightOffset)
				motionY += (0.350000011920929D - motionY) * 0.350000011920929D;
		}
		if (!onGround && motionY < 0.0D)
			motionY *= 0.5D;
		super.onLivingUpdate();
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return var1 != null && canEntityBeSeen(var1) ? var1 : null;
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
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity)) {
			if (par1Entity instanceof EntityLivingBase) {
				byte var2 = 0;
				if (worldObj.difficultySetting > 1)
					if (worldObj.difficultySetting == 2)
						var2 = 7;
					else if (worldObj.difficultySetting == 3)
						var2 = 15;
				if (var2 > 0)
					((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
			}
			return true;
		}
		return false;
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		if (par2 < 2.0F && par1Entity.boundingBox.maxY > boundingBox.minY && par1Entity.boundingBox.minY < boundingBox.maxY)
			attackEntityAsMob(par1Entity);
	}
}