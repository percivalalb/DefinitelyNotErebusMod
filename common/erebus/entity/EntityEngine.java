package erebus.entity;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;

public class EntityEngine extends IEntityMobBlock implements IRangedAttackMob

{
	public int animationTicks = 0;
	boolean active = true;

	public EntityEngine(World world) {
		super(world);
		setSize(1.0F, 2.0F);
		tasks.addTask(1, new IEntityArrowAttack(this, 0F, 60, 16.0F));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		setActive(true);
	}

	@Override
	public boolean isEntityInvulnerable() {
		return true;
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		if (itemstack != null && itemstack.itemID == ModItems.wandOfAnimation.itemID) {
			setActive(false);
			return true;
		} else
			return super.interact(par1EntityPlayer);
	}

	private void setActive(boolean par1) {
		active = par1;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (animationTicks <= 24)
			flameOn(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), rand);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		stationaryEntity();

		if (active)
			if (animationTicks <= 24)
				animationTicks++;
		if (!active) {
			if (animationTicks >= 1)
				animationTicks--;
			if (animationTicks == 0) {
				setDead();
				worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), ModBlocks.erebusAltar.blockID);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void flameOn(World par1World, int par2, int par3, int par4, Random par5Random) {
		double d0 = par2 + 0.53125F;
		double d1 = par3 + 1.25F;
		double d2 = par4 + 0.53125F;
		par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1, d2 - 0.265625, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1, d2 + 0.265625, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0 - 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0 + 0.265625, d1, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1 + 0.25, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("smoke", d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
		par1World.spawnParticle("flame", d0, d1 + 0.5, d2, 0.0D, 0.0D, 0.0D);
	}

	public void stationaryEntity() {
		posX = prevPosX;
		posY = prevPosY;
		posZ = prevPosZ;
		rotationPitch = 0.0F;
		rotationYaw = 0.0F;
		randomYawVelocity = 0.0F;
		int x = (int) posX;
		int y = (int) (posY - 1);
		int z = (int) posZ;
		if (worldObj.getBlockId(x, y, z) == 0)
			posY -= 1;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float par2) {
		if (entity.getCreatureAttribute().equals(EnumCreatureAttribute.ARTHROPOD) && !(entity instanceof EntityAnimatedBlock)) {
			double x = entity.posX;
			double y = entity.boundingBox.minY;
			double z = entity.posZ;
			EntityLightningBolt entitybolt = new EntityLightningBolt(worldObj, 0D, 0D, 0D);
			entitybolt.setLocationAndAngles(x, y, z, rotationYaw, rotationPitch);
			worldObj.addWeatherEffect(entitybolt);
		}
	}
}
