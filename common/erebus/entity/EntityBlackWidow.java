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
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBlackWidow extends EntityMob implements IEntityAdditionalSpawnData{
	private int shouldDo;
	public float size=0.4F+rand.nextFloat();
	Class[] preys={EntityFly.class,EntityBotFly.class};

	public EntityBlackWidow(World par1World){
		super(par1World);
		setSize(size*2.0F,size);
		this.isImmuneToFire=true;
	}

	@Override
	protected void entityInit(){
		super.entityInit();
		this.dataWatcher.addObject(16,new Byte((byte)0));
	}
	
	@Override
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20D);
	}

	@Override
	protected Entity findPlayerToAttack(){
		EntityPlayer var1=this.worldObj.getClosestVulnerablePlayerToEntity(this,16.0D);
		return var1!=null&&this.canEntityBeSeen(var1)?var1:null;
	}

	// find enemies other than players to attack. uses preys array
	protected Entity findEnemyToAttack(){
		List list=worldObj.getEntitiesWithinAABBExcludingEntity(this,boundingBox.expand(10D,10D,10D));

		for(int i=0; i<list.size(); i++){
			Entity entity=(Entity)list.get(i);
			if (entity!=null){
				if (!(entity instanceof EntityLivingBase)){
					continue;
				}

				for(int j=0; j<preys.length; j++){
					if (entity.getClass()==preys[j]){
						return this.canEntityBeSeen(entity)?entity:null;
					}
				}
			}
		}

		return null;
	}

	@Override
	public void onUpdate(){
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75D); // Movespeed
		// find enemies
		if (findPlayerToAttack()!=null){
			entityToAttack=findPlayerToAttack();
		}
		else if (findEnemyToAttack()!=null){
			entityToAttack=findEnemyToAttack();
		}
		else{
			entityToAttack=null;
		}
		super.onUpdate();
	}

	public int getAttackStrength(Entity par1Entity){
		return 2;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute(){
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void fall(float par1){}

	@Override
	public void setInWeb(){}

	public boolean isClimbing(){
		return (!this.onGround)&&(isOnLadder());
	}

	@Override
	public boolean isOnLadder(){
		return (this.isCollidedHorizontally);
	}

	@Override
	protected boolean canDespawn(){
		return false;
	}

	@Override
	protected String getLivingSound(){
		return "erebus:blackwidowsound";
	}

	@Override
	protected String getHurtSound(){
		return "erebus:blackwidowhurt";
	}

	@Override
	protected String getDeathSound(){
		return "erebus:squish";
	}

	protected String getWebSlingThrowSound(){
		return "erebus:webslingthrow";
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4){
		this.playSound("mob.spider.step",0.15F,1.0F);
	}

	@Override
	protected int getDropItemId(){
		return Item.silk.itemID;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2){
		super.dropFewItems(par1,par2);
		if (par1&&(this.rand.nextInt(3)==0||this.rand.nextInt(1+par2)>0)){
			this.dropItem(Item.spiderEye.itemID,1);
		}
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2){
		if (this.attackTime<=0&&par2<1.0F&&par1Entity.boundingBox.maxY>this.boundingBox.minY&&par1Entity.boundingBox.minY<this.boundingBox.maxY){
			this.attackTime=20;
			this.attackEntityAsMob(par1Entity);
		}
		else if (par2>5.0F&par2<8.0F){

			if (this.attackTime==0){
				++this.shouldDo;
				if (this.shouldDo==1){
					this.attackTime=60;
				}
				else if (this.shouldDo<=4){
					this.attackTime=6;
				}
				else{
					this.attackTime=100;
					this.shouldDo=0;
				}

				if (this.shouldDo>1&&this.size>0.7F){

					this.worldObj.playSoundAtEntity(this,getWebSlingThrowSound(),1.0F,1.0F);
					for(int var10=0; var10<1; ++var10){
						EntityWebSling var11=new EntityWebSling(this.worldObj,this);
						var11.posY=this.posY+(double)(this.height/2.0F)+0.5D;
						this.worldObj.spawnEntityInWorld(var11);
					}
				}

			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity){
		if (super.attackEntityAsMob(par1Entity)){
			if (par1Entity instanceof EntityLivingBase){
				byte var2=0;
				if (this.worldObj.difficultySetting>1){
					if (this.worldObj.difficultySetting==2){
						var2=7;
					}
					else if (this.worldObj.difficultySetting==3){
						var2=15;
					}
				}
				if (var2>0){
					int chanceFiftyFifty=this.rand.nextInt(2);
					if (chanceFiftyFifty==1){
						((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.wither.id,var2*20,0));
					}
				}
			}
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setFloat("WidowSize",this.size);

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readEntityFromNBT(par1NBTTagCompound);
		if (par1NBTTagCompound.hasKey("WidowSize"))
		{
			this.setWidowSize(par1NBTTagCompound.getFloat("WidowSize"));
		}
	}

	protected void setWidowSize(float par1){
		this.size=par1;
		this.dataWatcher.updateObject(16,Byte.valueOf((byte)1));
		this.worldObj.setEntityState(this,(byte)16);
	}

	// This is a much easier method to get the required data than custom packet
	// handling - thanks Forge!
	@Override
	public void writeSpawnData(ByteArrayDataOutput data){
		data.writeFloat(this.size);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data){
		this.size=data.readFloat();
	}
}
