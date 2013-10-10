package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import erebus.ModItems;

public class EntityAnimatedBlock extends IEntityMobBlock implements IEntityAdditionalSpawnData {
	public int blockloot;
	public int blockmeta;
	public int thisblockdrop = blockloot;
	int thisblockdropmeta = blockmeta;
	private final double moveSpeed;

	public EntityAnimatedBlock(World world, int blockloot, int blockmeta) {
		super(world);
		this.blockloot = blockloot;
		this.blockmeta = blockmeta;
		moveSpeed = 0.5D;
		setSize(1.0F, 1.5F);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityMob.class, moveSpeed, false));
		tasks.addTask(2, new EntityAIWander(this, moveSpeed));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
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
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
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
		return EnumCreatureAttribute.UNDEFINED;
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
		if (!worldObj.isRemote && isDead) {
			EntityItem entity_item = new EntityItem(worldObj, posX, posY, posZ, new ItemStack(Block.blocksList[thisblockdrop], 1, thisblockdropmeta));
			worldObj.spawnEntityInWorld(entity_item);
		}
	}

	public boolean isClimbing() {
		return !onGround && isOnLadder();
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		if (!worldObj.isRemote && itemstack != null && itemstack.itemID == ModItems.wandOfAnimation.itemID) {
			setDead();
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), thisblockdrop, thisblockdropmeta, 3);
			return true;
		} else
			return super.interact(par1EntityPlayer);
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		if (par2 > 0.0F && par2 < 2.0F)
			attackEntityAsMob(par1Entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		boolean atk = false;
		if (super.attackEntityAsMob(par1Entity))
			if (par1Entity instanceof EntityMob)
				atk = true;
			else
				atk = false;
		return atk;
	}

	// Write NBT for Block type persistence
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("BlockType", thisblockdrop);
		par1NBTTagCompound.setInteger("BlockMeta", thisblockdropmeta);
	}

	// Read NBT for Block type persistence
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		if (par1NBTTagCompound.hasKey("BlockType"))
			;
		{
			setthisblockdrop(par1NBTTagCompound.getInteger("BlockType"));
		}
		if (par1NBTTagCompound.hasKey("BlockMeta"))
			;
		{
			setthisblockdropmeta(par1NBTTagCompound.getInteger("BlockMeta"));
		}
	}

	// Set Block type using NBT and datawatcher
	protected void setthisblockdrop(int par1) {
		thisblockdrop = par1;
		dataWatcher.updateObject(16, Byte.valueOf((byte) 1));
		worldObj.setEntityState(this, (byte) 16);
	}

	protected void setthisblockdropmeta(int par1) {
		thisblockdropmeta = par1;
		dataWatcher.updateObject(17, Byte.valueOf((byte) 1));
		worldObj.setEntityState(this, (byte) 17);
	}

	// This is a much easier method to get the required data than custom packet
	// handling - thanks Forge!
	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeInt(thisblockdrop);
		data.writeInt(thisblockdropmeta);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		thisblockdrop = data.readInt();
		thisblockdropmeta = data.readInt();
	}
}
