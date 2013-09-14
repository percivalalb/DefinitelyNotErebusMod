package erebus.core.helper;

import erebus.entity.EntityBeetleLarva;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * @author ProPercivalalb
 */
public class ClientHelper {

	public static Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}
	
	public static void spawnLarveBreakingFX(World worldObj, EntityBeetleLarva entity) {
		ClientHelper.getMinecraft().effectRenderer.addEffect(new EntityBreakingFX(worldObj, entity.posX + (entity.getRNG().nextDouble() - 0.5D) * (double)entity.width, entity.posY + entity.getRNG().nextDouble() * (double)entity.height - (double)entity.yOffset, entity.posZ + (entity.getRNG().nextDouble() - 0.5D) * (double)entity.width, Item.slimeBall));
	}
	
	public static void spawnLarveDiggingFX(World worldObj, EntityBeetleLarva entity) {
		double d0 = entity.getRNG().nextGaussian() * 0.5D;
    	double d1 = entity.getRNG().nextGaussian() * 0.01D;
    	double d2 = entity.getRNG().nextGaussian() * 0.5D;
		ClientHelper.getMinecraft().effectRenderer.addEffect(new EntityDiggingFX(entity.worldObj, entity.aiEatWoodItem.WoodX + 0.5D + (entity.getRNG().nextDouble() - 0.5D) * (double)entity.width, entity.aiEatWoodItem.WoodY + 0.2D + entity.getRNG().nextDouble() * (double)entity.height - (double)entity.yOffset, entity.aiEatWoodItem.WoodZ + 0.5D + (entity.getRNG().nextDouble() - 0.5D) * (double)entity.width, d0 ,d1, d2, Block.blocksList[5], 0));
	}
}
