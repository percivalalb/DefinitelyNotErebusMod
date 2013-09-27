package erebus.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAntlion extends EntityMob{
	public boolean isCaptured;

	public EntityAntlion(World par1World){
		super(par1World);
		isImmuneToFire=true;
		setSize(2.5F,0.9F);
	}

	@Override
	protected void entityInit(){
		super.entityInit();
	}

	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.7D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(1.0D); // knockback
	}

	@Override
	public boolean isAIEnabled(){
		return false;
	}

	@Override
	public boolean canRiderInteract(){
		return true;
	}

	@Override
	public boolean shouldRiderSit(){
		return false;
	}

	@Override
	public int getTotalArmorValue(){
		return 8;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute(){
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected String getLivingSound(){
		return "Antlion:AntlionSound";
	}

	@Override
	protected String getHurtSound(){
		return "Antlion:Antlionhurt";
	}

	@Override
	protected String getDeathSound(){
		return "Antlion:Squish";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4){
		worldObj.playSoundAtEntity(this,"mob.zombie.wood",0.15F,1.0F);
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
		if (!worldObj.isRemote&&entityToAttack==null&&isOnSand()) yOffset=-1;
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
				if (worldObj.difficultySetting>1) {
					if (worldObj.difficultySetting==2) var2=7;
					else if (worldObj.difficultySetting==3) var2=15;
				}
				if (var2>0) ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id,var2*20,0));
			}
			return true;
		}
		else return false;
	}
}