package erebus.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import erebus.ModItems;
import erebus.entity.ai.EntityAIEatWoodenItem;
import erebus.network.PacketHandler;
import erebus.network.packet.PacketParticle;

public class EntityBeetleLarva extends EntityCreature {

	private final EntityAIWander aiWander = new EntityAIWander(this, 0.48D);
	private final EntityAIWatchClosest aiWatchClosest = new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F);
	public EntityAIEatWoodenItem aiEatWoodItem = new EntityAIEatWoodenItem(this, 0.48D);

	public boolean isEating;
	private final double moveSpeed;
	public boolean isSquashed;

	public EntityBeetleLarva(World world) {
		super(world);
		setSize(0.9F, 0.5F);
		moveSpeed = 0.35D;
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiEatWoodItem);
		tasks.addTask(2, new EntityAITempt(this, 0.48D, Item.stick.itemID, false));
		tasks.addTask(3, aiWander);
		tasks.addTask(4, aiWatchClosest);
		tasks.addTask(5, new EntityAILookIdle(this));
		tasks.addTask(6, new EntityAIPanic(this, 0.48D));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0F); // Max
		// Health
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(moveSpeed); // Movespeed
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere() {
		float f1 = getBrightness(1.0F);

		if (f1 >= 0.0F)
			return true;
		return super.getCanSpawnHere();
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		super.onCollideWithPlayer(player);
		byte var2 = 0;
		if (!worldObj.isRemote && player.boundingBox.maxY >= boundingBox.minY && player.boundingBox.minY <= boundingBox.maxY && player.boundingBox.maxX >= boundingBox.minX && player.boundingBox.minX <= boundingBox.maxX &&
		player.boundingBox.maxZ >= boundingBox.minZ && player.boundingBox.minZ <= boundingBox.maxZ && player.lastTickPosY > player.posY) {
			if (worldObj.difficultySetting > 1)
				if (worldObj.difficultySetting == 2)
					var2 = 7;
				else if (worldObj.difficultySetting == 3)
					var2 = 15;

			if (var2 > 0)
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, var2 * 20, 0));
			setisSquashed(true);
			setDead();
			onDeathUpdate();
		}
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		String actionSound = "erebus:beetlelarvasound";
		if (isEating)
			actionSound = "erebus:beetlelarvamunch";
		return actionSound;
	}

	@Override
	protected String getHurtSound() {
		return "erebus:beetlelarvahurt";
	}

	@Override
	protected String getDeathSound() {
		return "erebus:squish";
	}

	protected String getJumpedOnSound() {
		return "erebus:beetlelarvasplat";
	}

	protected static String getHasMunched() {
		return "erebus:beetlelarvamunch";
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(moveSpeed); // Movespeed
	}

	@Override
	public void onDeathUpdate() {

		super.onDeathUpdate();

		if (isSquashed) {
			PacketDispatcher.sendPacketToAllAround(posX, posY, posZ, 64D, dimension, PacketHandler.buildPacket(2, PacketParticle.BEETLE_LARVA_SQUISH, entityId));

			worldObj.playSoundEffect(posX, posY, posZ, getJumpedOnSound(), 1.0F, 0.5F);
			worldObj.playSoundEffect(posX, posY, posZ, getDeathSound(), 1.0F, 0.7F);
			if (rand.nextInt(200) == 0)
				entityDropItem(new ItemStack(Item.diamond, 1, 1), 0.0F);
		}
	}

	@Override
	protected void dropFewItems(boolean hitByPlayer, int looting) {
		if (isBurning())
			entityDropItem(new ItemStack(ModItems.erebusFood, 1, 1), 0.0F);
		else
			entityDropItem(new ItemStack(ModItems.erebusFood, 1, 0), 0.0F);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack is = player.inventory.getCurrentItem();
		if (!worldObj.isRemote && is != null && is.itemID == Item.wheat.itemID) {
			System.out.println("Should do something here");
			return true;
		}

		return super.interact(player);
	}

	public void setMoveTasks(boolean par1) {
		if (par1 == false) {
			tasks.removeTask(aiWander);
			tasks.removeTask(aiWatchClosest);
		}

		if (par1 == true) {
			tasks.addTask(2, aiWander);
			tasks.addTask(4, aiWatchClosest);
		}
	}

	public void setIsEating(boolean par1) {
		isEating = par1;
	}

	public void munchBlock() {
		if (isEating && worldObj.getWorldTime() % 5 == 0)
			PacketDispatcher.sendPacketToAllAround(
			posX,
			posY,
			posZ,
			64D,
			dimension,
			PacketHandler.buildPacket(2, PacketParticle.BEETLE_LARVA_AND_GRASSHOPPER_EAT, entityId, aiEatWoodItem.WoodX, aiEatWoodItem.WoodY, aiEatWoodItem.WoodZ, worldObj.getBlockId(aiEatWoodItem.WoodX, aiEatWoodItem.WoodY, aiEatWoodItem.WoodZ),
			Byte.valueOf((byte) worldObj.getBlockMetadata(aiEatWoodItem.WoodX, aiEatWoodItem.WoodY, aiEatWoodItem.WoodZ))));
	}

	public void setisSquashed(boolean par1) {
		isSquashed = par1;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}
}
