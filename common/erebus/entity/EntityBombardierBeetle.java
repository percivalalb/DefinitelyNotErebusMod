package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class EntityBombardierBeetle extends EntityMob{
	private final double explosionRadius = 0.5;
	private final double moveSpeed;
	private int collideTick;
	public EntityBombardierBeetle(World par1World)
	{
		super(par1World);
		moveSpeed = 1.0D;
		stepHeight = 0.0F;
		isImmuneToFire = true;
		setSize(2.5F, 1.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		collideTick++;
		if (collideTick > 20 || entityToAttack == null)
			collideTick = 0;
		if (entityToAttack != null)
			if (!worldObj.isRemote && isCollidedHorizontally)
				if (collideTick == 20)
					clearpath();

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(75.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.75D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:BombardierBeetleSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:BombardierBeetleHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int x, int y, int z, int par4) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int looting) {
		if (rand.nextInt(9) <= 2 + (looting >> 1)) {
			dropItem(Item.gunpowder.itemID, 1);
			dropItem(Item.blazePowder.itemID, 1);
		}
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, ItemErebusMaterial.dataExoPlate), 0.0F);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		if (player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY)
			if (collideTick == 20) {
				boolean rule = worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
				worldObj.createExplosion(this, player.posX, player.posY, player.posZ, (float) explosionRadius, rule);
			}
	}

	private void clearpath() {
		boolean rule = worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
		double direction = Math.toRadians(renderYawOffset);
		int x = (int) (posX - Math.sin(direction) * 2.0D);
		int y = (int) posY;
		int z = (int) (posZ + Math.cos(direction) * 2.0D);
		worldObj.createExplosion(this, posX - Math.sin(direction) * 1.5D, posY + 1, posZ + Math.cos(direction) * 1.5D, (float) explosionRadius * 2, true);
		worldObj.destroyBlock(x, y, z, true);
		getMoveHelper().setMoveTo(entityToAttack.posX + 0.5D, entityToAttack.posY, entityToAttack.posZ + 0.5D, moveSpeed);
	}


	@Override
	protected void attackEntity(Entity entity, float par2) {
		super.attackEntity(entity, par2);
	}

}
