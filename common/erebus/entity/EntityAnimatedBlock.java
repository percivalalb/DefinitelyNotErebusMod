package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ErebusMod;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.proxy.CommonProxy;
import erebus.utils.Utils;

public class EntityAnimatedBlock extends EntityMobBlock implements IEntityAdditionalSpawnData {

	public int blockID, blockMeta;
	private int lastX = 0, lastY = 0, lastZ = 0;
	protected final EntityAIWander aiWander = new EntityAIWander(this, 0.5D);
	protected final EntityAINearestAttackableTarget aiAttackNearestTarget = new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true);
	protected final EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityMob.class, 0.5D, false);
	protected final EntityAITempt aiTempt = new EntityAITempt(this, 1.0D, ModItems.wandOfAnimation.itemID, false);
	public EntityAnimatedBlock(World world) {
		super(world);
		setSize(1.0F, 1.5F);
		setBlock(Block.stone.blockID, 0);
		tasks.addTask(0, new EntityAISwimming(this));
		// tasks.addTask(1, aiTempt);
		tasks.addTask(2, aiAttackOnCollide);
		tasks.addTask(3, aiWander);
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, aiAttackNearestTarget);
		experienceValue = 0;
	}

	public void setBlock(int blockID, int blockMeta) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
		dataWatcher.addObject(17, new Byte((byte) 0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound() {
		return "";
	}

	@Override
	protected String getHurtSound() {
		return "";
	}

	@Override
	protected String getDeathSound() {
		return "";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4) {
		worldObj.playSoundAtEntity(this, "mob.zombie.wood", 0.15F, 1.0F);
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && isDead)
			Utils.dropStack(worldObj, (int) posX, (int) posY, (int) posZ, new ItemStack(Block.blocksList[blockID], 1, blockMeta));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (worldObj.isRemote)
			if (worldObj.getSunBrightness(1.0F) < 0.5F)
				lightUp();
			else
				switchOff();
	}

	private void lightUp() {
		if ((int) posX != lastX || (int) posY != lastY || (int) posZ != lastZ || isDead) {
			worldObj.setLightValue(EnumSkyBlock.Block, (int) posX, (int) posY, (int) posZ, Block.lightValue[blockID]);
			worldObj.updateLightByType(EnumSkyBlock.Block, lastX, lastY, lastZ);
			lastX = (int) posX;
			lastY = (int) posY;
			lastZ = (int) posZ;
		}
	}

	private void switchOff() {
		worldObj.updateLightByType(EnumSkyBlock.Block, lastX, lastY, lastZ);
	}

	@Override
	public void setDead() {
		super.setDead();
		if (worldObj.isRemote)
			switchOff();
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();
		if (!worldObj.isRemote && stack != null && stack.itemID == ModItems.wandOfAnimation.itemID) {
			setDead();
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), blockID, blockMeta, 3);
			worldObj.playSoundEffect(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), "erebus:altaroffering", 0.2F, 1.0F);
			return true;
		} else if (blockID == ModBlocks.petrifiedCraftingTable.blockID && stack == null) {
			player.openGui(ErebusMod.instance, CommonProxy.GUI_ID_PETRIFIED_CRAFT, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
			return true;
		} else
			return false;
	}

	@Override
	protected void attackEntity(Entity entity, float distance) {
		if (distance > 0.0F && distance < 2.0F)
			attackEntityAsMob(entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		boolean atk = false;
		if (super.attackEntityAsMob(entity))
			if (entity instanceof EntityMob)
				atk = true;
			else
				atk = false;
		return atk;
	}

	public void setCanBeTempted() {
		if (blockID == ModBlocks.petrifiedCraftingTable.blockID)
			tasks.addTask(1, aiTempt);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound data) {
		super.writeEntityToNBT(data);
		data.setInteger("blockID", blockID);
		data.setInteger("blockMeta", blockMeta);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound data) {
		super.readEntityFromNBT(data);
		blockID = data.getInteger("blockID");
		blockMeta = data.getInteger("blockMeta");
		setCanBeTempted();
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeInt(blockID);
		data.writeInt(blockMeta);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		blockID = data.readInt();
		blockMeta = data.readInt();
	}
}