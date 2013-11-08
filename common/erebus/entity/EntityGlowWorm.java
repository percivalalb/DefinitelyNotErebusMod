package erebus.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;

public class EntityGlowWorm extends EntityMob
{
	public int lastX;
	public int lastY;
	public int lastZ;

	public EntityGlowWorm(World par1World)
	{
		super(par1World);
		stepHeight = 0.0F;
		isImmuneToFire = true;
		setSize(1.5F, 0.5F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWander(this, 0.5D));
		tasks.addTask(4, new EntityAIPanic(this, 0.7F));
		tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	public boolean canDeSpawn()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(15.0D); // MaxHealth
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound()
	{
		return "erebus:glowwormsound";
	}

	@Override
	protected String getHurtSound()
	{
		return "erebus:glowwormhurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int looting) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, ItemErebusMaterial.dataBioluminescence), 0.0F);
	}

	@Override
	public void onUpdate()
	{
		if (worldObj.isRemote && isGlowing())
			lightUp(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
		if (worldObj.isRemote && !isGlowing())
			switchOff();
		super.onUpdate();
	}

	@SideOnly(Side.CLIENT)
	private void lightUp(World world, int x, int y, int z) {
		if (x != lastX || y != lastY || z != lastZ || isDead) {
			worldObj.setLightValue(EnumSkyBlock.Block, x, y, z, 9);
			worldObj.updateLightByType(EnumSkyBlock.Block, lastX, lastY, lastZ);
			lastX = x;
			lastY = y;
			lastZ = z;
		}
	}
	@SideOnly(Side.CLIENT)
	private void switchOff() {
		worldObj.updateLightByType(EnumSkyBlock.Block, lastX, lastY, lastZ);
		worldObj.updateLightByType(EnumSkyBlock.Block, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
	}

	public boolean isGlowing()
	{
		return worldObj.getSunBrightness(1.0F) < 0.5F;
	}

	@Override
	public void setDead() {
		super.setDead();
		if (worldObj.isRemote)
			switchOff();
	}
}