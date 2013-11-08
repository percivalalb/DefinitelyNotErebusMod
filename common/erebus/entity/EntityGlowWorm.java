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
	private int prevX;
	private int prevY;
	private int prevZ;

	private int x;
	private int y;
	private int z;

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
	public void onLivingUpdate()
	{
		if (worldObj.isRemote && isGlowing())
			lightUp();
		else if (worldObj.isRemote && !isGlowing())
			switchOff();
		super.onLivingUpdate();
	}

	@SideOnly(Side.CLIENT)
	private void lightUp()
	{
		worldObj.setLightValue(EnumSkyBlock.Block, x, y, z, 9);
		int newX = MathHelper.floor_double(posX);
		int newY = MathHelper.floor_double(posY);
		int newZ = MathHelper.floor_double(posZ);
		if (newX != x || newY != y || newZ != z) {
			worldObj.updateLightByType(EnumSkyBlock.Block, x, y, z);
			worldObj.updateLightByType(EnumSkyBlock.Block, prevX, prevY, prevZ);
			prevX = x;
			prevY = y;
			prevZ = z;
			x = newX;
			y = newY;
			z = newZ;
		}
	}

	@SideOnly(Side.CLIENT)
	private void switchOff() {
		worldObj.updateLightByType(EnumSkyBlock.Block, prevX, prevY, prevZ);
		worldObj.updateLightByType(EnumSkyBlock.Block, x, y, z);
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