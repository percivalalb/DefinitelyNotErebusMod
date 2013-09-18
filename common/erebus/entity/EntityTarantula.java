package erebus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import erebus.ModItems;

/**
 * @author ProPercivalalb
 */
public class EntityTarantula extends EntitySpider {

	public EntityTarantula(World par1World) {
		super(par1World);
		setSize(1.3F, 0.6F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.2D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(5.0D);
	}

	@Override
	public int getTotalArmorValue() {
		return 4;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!this.worldObj.isRemote) {
			this.setBesideClimbableBlock(this.isCollidedHorizontally);
		}
	}

	@Override
	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public void setBesideClimbableBlock(boolean par1) {
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1) {
			var2 = (byte) (var2 | 1);
		} else {
			var2 &= -2;
		}

		this.dataWatcher.updateObject(16, Byte.valueOf(var2));
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public int skin = rand.nextInt(99);

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {

		if (super.attackEntityAsMob(par1Entity)) {

			if (par1Entity instanceof EntityLiving) {
				byte var2 = 0;

				if (this.worldObj.difficultySetting > 1 && rand.nextInt(19) == 0) {
					if (this.worldObj.difficultySetting == 2) {
						var2 = 5;
					} else if (this.worldObj.difficultySetting == 3) {
						var2 = 10;
					}
				}

				if (var2 > 0) {
					((EntityLiving) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
				}
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int chanceFiftyFifty = this.rand.nextInt(1) + 1;

		int chance40x40x20 = this.rand.nextInt(4);
		int stringDrop = 0;

		switch (chance40x40x20) {
			case 0:
			case 1:
				stringDrop = 1;
				break;
			case 2:
			case 3:
				stringDrop = 2;
				break;
			case 4:
				stringDrop = 3;
				break;
		}

		int chance20x60x20 = this.rand.nextInt(4);
		int legDrop = 0;

		switch (chance20x60x20) {
			case 0:
				legDrop = 1;
				break;
			case 1:
			case 2:
			case 3:
				legDrop = 2;
				break;
			case 4:
				legDrop = 3;
				break;
		}

		this.dropItem(Item.silk.itemID, stringDrop + par2);

		if (this.isBurning()) {
			this.entityDropItem(new ItemStack(ModItems.erebusFood, legDrop + par2, 5), 0.0F);
		} else {
			this.entityDropItem(new ItemStack(ModItems.erebusFood, legDrop + par2, 4), 0.0F);
		}

		this.dropItem(Item.spiderEye.itemID, chanceFiftyFifty + par2);

		/*
		 * 50% chance for 1 Spider eye, 50% for two 40% chance for 1 String, 40%
		 * for two and 20% for three 20% for zero tarantula legs, 60% for 1 and
		 * 20% for two
		 */
	}
}
