package erebus.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ErebusMod;
import erebus.ModItems;
import erebus.client.model.armor.ModelArmorGlider;

public class ItemArmorGlider extends ItemArmor {

	public ItemArmorGlider(int id, int armorType) {
		super(id, ErebusMod.armorREINEXOSKELETON, 2, armorType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return "erebus:textures/armor/ModelArmorGlider.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, int slot) {
		ModelArmorGlider model = new ModelArmorGlider();
		model.bipedHead.showModel = false;
		model.bipedHeadwear.showModel = false;
		model.bipedBody.showModel = false;
		model.bipedRightArm.showModel = false;
		model.bipedLeftArm.showModel = false;
		model.bipedRightLeg.showModel = false;
		model.bipedLeftLeg.showModel = false;
		if (stack.hasTagCompound())
			model.isGliding = stack.getTagCompound().getBoolean("isGliding");

		return model;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		if (!itemStack.hasTagCompound()) {
			itemStack.stackTagCompound = new NBTTagCompound();
			return;
		} else if (itemStack.getTagCompound().getBoolean("isGliding")) {
			player.fallDistance = 0.0F;
			if (!player.onGround) {
				player.motionX *= 1.0D;
				player.motionZ *= 1.0D;
				player.motionY *= 0.5D;
			}
		}
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.stackTagCompound.setBoolean("isGliding", false);
	}

	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void onPlayerRenderPre(RenderPlayerEvent.Pre e){
		GL11.glPushMatrix();

		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
		ItemStack chestPlate = player.inventory.armorInventory[3];
		if (chestPlate != null && chestPlate.getItem() == ModItems.armorGlider) {
			if (!chestPlate.hasTagCompound()) {
				chestPlate.stackTagCompound = new NBTTagCompound();
				return;
			}

			if (chestPlate.getTagCompound().getBoolean("isGliding") && !e.entityPlayer.onGround) {
				GL11.glTranslated(0D, -e.entityPlayer.height / 2, 0D);
				GL11.glRotated(60D,1F,0F,0F);
			}
		}
		GL11.glPopMatrix();
	}
}