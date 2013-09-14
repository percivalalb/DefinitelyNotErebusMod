package erebus.entity;

import erebus.client.render.entity.AnimationMathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityWasp extends EntityUndergroundAnimal{
	private float heightOffset=0.5F;
	public float wingFloat;
	AnimationMathHelper mathWings=new AnimationMathHelper();

	public EntityWasp(World par1World){
		super(par1World);
		this.setSize(2.0F,2.0F);
		// no AI needed - just seems to work o.k. being a 'monster'
	}

	protected boolean isAIEnabled(){
		return false;
	}

	protected void entityInit(){
		super.entityInit();
		this.dataWatcher.addObject(16,new Byte((byte)0));
	}

	@Override
	protected void applyEntityAttributes() {
	    super.applyEntityAttributes();
	    getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
	}

	public int getAttackStrength(Entity par1Entity){
		return 5;
	}

	public EnumCreatureAttribute getCreatureAttribute(){
		return EnumCreatureAttribute.ARTHROPOD;
	}

	protected void fall(float par1){}

	protected String getLivingSound(){
		return "erebus:WaspSound";
	}

	protected String getHurtSound(){
		return "erebus:WaspHurt";
	}

	protected String getDeathSound(){
		return "erebus:squish";
	}

	protected void getStepSound(int par1, int par2, int par3, int par4){
		this.worldObj.playSoundAtEntity(this,"mob.zombie.wood",0.15F,1.0F);
	}

	protected int getDropItemId(){
		return Item.coal.itemID;
	}

	protected void dropRareDrop(int par1){
		switch(this.rand.nextInt(2)){
			case 0:
				dropItem(Item.appleRed.itemID,1);
			case 1:
				dropItem(Item.goldNugget.itemID,1);
		}
	}

	public boolean isFlying(){
		return !this.onGround;
	}

	public boolean isClimbing(){
		return (!this.onGround)&&(isOnLadder());
	}

	public boolean isOnLadder(){
		return this.isCollidedHorizontally;
	}

	public void onUpdate(){
		super.onUpdate();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.75D);
		
		if (!this.isFlying()){
			this.wingFloat=0.0F;
		}
		if (this.isFlying()){
			this.wingFloat=mathWings.swing(4.0F,0.1F);
		}
	}

	public void onLivingUpdate(){
		if (!this.worldObj.isRemote){
			this.heightOffset=(0.5F+(float)this.rand.nextGaussian()*5.0F);
			if ((getEntityToAttack()!=null)&&(getEntityToAttack().posY+getEntityToAttack().getEyeHeight()>this.posY+getEyeHeight()+this.heightOffset)){
				this.motionY+=(0.350000011920929D-this.motionY)*0.350000011920929D;
			}
		}
		if ((!this.onGround)&&(this.motionY<0.0D)){
			this.motionY*=0.5D;
		}
		super.onLivingUpdate();
	}

	public boolean attackEntityAsMob(Entity par1Entity){
		if (super.attackEntityAsMob(par1Entity)){
			if ((par1Entity instanceof EntityLivingBase)){
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
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id,var2*20,0));
				}
			}

			return true;
		}

		return false;
	}

	protected void attackEntity(Entity par1Entity, float par2){
		if ((par2<2.0F)&&(par1Entity.boundingBox.maxY>this.boundingBox.minY)&&(par1Entity.boundingBox.minY<this.boundingBox.maxY)){
			attackEntityAsMob(par1Entity);
		}
	}
}