package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class EntityBombardierBeetle extends EntityMob{
	private final double moveSpeed;
	public EntityBombardierBeetle(World par1World)
	{
		super(par1World);
		moveSpeed = 0.7D;
		stepHeight = 0.0F;
		isImmuneToFire = true;
		setSize(1.0F, 0.5F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
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
		if (rand.nextInt(9) <= 2 + (looting >> 1))
			dropItem(Item.gunpowder.itemID, 1);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, ItemErebusMaterial.dataExoPlate), 0.0F);
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		if (player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY)
			bang();
		super.onCollideWithPlayer(player);
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (!worldObj.isRemote && distance < 1.7F && entity.boundingBox.maxY >= boundingBox.minY && entity.boundingBox.minY <= boundingBox.maxY) {
			super.attackEntity(entity, distance);
			knockbackAttack(entity);
		}
	}

	@SideOnly(Side.CLIENT)
	public void bang() {
		float f = (rand.nextFloat() - 0.5F) * 8.0F;
		float f1 = (rand.nextFloat() - 0.5F) * 4.0F;
		float f2 = (rand.nextFloat() - 0.5F) * 8.0F;
		worldObj.spawnParticle("largeexplode", posX + f, posY + 1.0D + f1, posZ + f2, 0.0D, 0.0D, 0.0D);
		worldObj.playSoundAtEntity(this, "random.explode", 1.0F, 1.0F);
	}

	public void knockbackAttack(Entity entity) {
		double knockback = 0.5D;
		double direction = Math.toRadians(renderYawOffset);
		entity.addVelocity(-Math.sin(direction) * knockback, 0.25D, Math.cos(direction) * knockback);
	}
}
