package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityAntlion extends EntityMob implements
IEntityAdditionalSpawnData {
	public boolean isBoss;
	public EntityAntlion(World par1World) {
		super(par1World);
		isImmuneToFire=true;
		if (!isBoss)
			setSize(1.5F, 0.6F);
		if (isBoss)
			setSize(3.0F, 1.2F);
	}

	@Override
	protected void entityInit(){
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte) 0));
	}

	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		if (!isBoss) {
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.7D); // Movespeed
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(1.0D); // atkDmg
			getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
			getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.5D); // knockback
		}
		if (isBoss) {
			getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.7D); // Movespeed
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(50.0D); // MaxHealth
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D); // atkDmg
			getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(6.0D); // followRange
			getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(1.0D); // knockback
		}
	}

	@Override
	public int getTotalArmorValue(){
		if(isBoss)return 15;
		else return 8;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute(){
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound(){
		return "erebus:AntlionSound";
	}

	@Override
	protected String getHurtSound(){
		return "erebus:Antlionhurt";
	}

	@Override
	protected String getDeathSound(){
		return "erebus:Squish";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4){
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	protected int getDropItemId(){
		return Block.sand.blockID;
	}

	@Override
	protected void dropRareDrop(int par1){
		dropItem(Item.ingotIron.itemID,1);
	}

	@Override
	public boolean isOnLadder(){
		return (isCollidedHorizontally);
	}

	@Override
	public boolean getCanSpawnHere(){
		return isOnSand()&&super.getCanSpawnHere();
	}

	public boolean isOnSand(){
		return worldObj.getBlockId(MathHelper.floor_double(posX),MathHelper.floor_double(boundingBox.minY)-1,MathHelper.floor_double(posZ))==Block.sand.blockID;
	}

	@Override
	public void onUpdate(){
		super.onUpdate();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.7D);
		if (!worldObj.isRemote&&entityToAttack==null&&isOnSand()&&(!isBoss)) yOffset=-1;
		else yOffset=0;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source.equals(DamageSource.inWall)||source.equals(DamageSource.drown)) return false;
		return super.attackEntityFrom(source,damage);
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2){
		super.attackEntity(par1Entity,par2);
		if (par2>0.0F&&par2<2.0F) attackEntityAsMob(par1Entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity){
		if (super.attackEntityAsMob(par1Entity)){
			if (par1Entity instanceof EntityLiving){
				byte var2=0;
				byte var3=5;
				if (worldObj.difficultySetting>1)
					if (worldObj.difficultySetting==2) var2=7;
					else if (worldObj.difficultySetting==3) var2=15;
				if (var2>0&&(!isBoss)) ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id,var2*20,0));
				else if (var2>0&&(isBoss)) ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id,var2+var3*20,0));
			}
			return true;
		}
		else return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("AntlionType", isBoss);

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		if (par1NBTTagCompound.hasKey("AntlionType"))
			setIsBoss(par1NBTTagCompound.getBoolean("AntlionType"));
	}

	protected void setIsBoss(boolean par1) {
		isBoss = par1;
		dataWatcher.updateObject(16, Byte.valueOf((byte) 1));
		worldObj.setEntityState(this, (byte) 16);
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		data.writeBoolean(isBoss);

	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		isBoss = data.readBoolean();

	}
}