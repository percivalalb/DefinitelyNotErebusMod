package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;

public class EntityCentipede extends EntityMob {

	public int skin = rand.nextInt(3);

	public EntityCentipede(World par1World) {
		super(par1World);
		setSize(1.0F, 0.8F);
		getNavigator().setAvoidsWater(true);

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(1.0D); // Movespeed
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D); // MaxHealth
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(getAttackStrength()); // atkDmg
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(16.0D); // followRange
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}

	public double getAttackStrength() {
		switch (worldObj.difficultySetting) {
			default:
				return 2.0D;
			case 1:
				return 2.0D;
			case 2:
				return 2.0D;
			case 3:
				return 4.0D;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 8;
	}

	@Override
	protected String getLivingSound() {
		return "erebus:CentipedeSound";
	}

	@Override
	protected String getHurtSound() {
		return "erebus:CentipedeHurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		worldObj.playSoundAtEntity(this, "erebus:CentipedeWalk", 0.15F, 1.0F);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		entityDropItem(new ItemStack(ModItems.erebusMaterials, 1, 8), 0.0F);
		entityDropItem(new ItemStack(ModItems.erebusMaterials, rand.nextInt(3) + 1, 0), 0.0F);
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
		super.attackEntity(par1Entity, par2);
		if (par2 < 1.0F && par1Entity.boundingBox.maxY > boundingBox.minY && par1Entity.boundingBox.minY < boundingBox.maxY)
			attackEntityAsMob(par1Entity);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity)) {
			if (par1Entity instanceof EntityLiving) {
				byte var2 = 0;
				if (worldObj.difficultySetting > 1)
					if (worldObj.difficultySetting == 2)
						var2 = 7;
					else if (worldObj.difficultySetting == 3)
						var2 = 15;
				if (var2 > 0)
					((EntityLiving) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
			}
			return true;
		} else
			return false;
	}
}
