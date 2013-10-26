package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityEngine;

@SideOnly(Side.CLIENT)
public class ModelEngineEntity extends ModelBase {

	ModelRenderer Mid;
	ModelRenderer Top;
	ModelRenderer Bot;
	ModelRenderer SmallBox;
	ModelRenderer ElectrodeF1;
	ModelRenderer ElectrodeF2;
	ModelRenderer ElectrodeL1;
	ModelRenderer ElectrodeL2;
	ModelRenderer ElectrodeB1;
	ModelRenderer ElectrodeB2;
	ModelRenderer ElectrodeR1;
	ModelRenderer ElectrodeR2;
	ModelRenderer Sparks;

	public ModelEngineEntity() {
		textureWidth = 256;
		textureHeight = 64;

		Mid = new ModelRenderer(this, 130, 0);
		Mid.addBox(-12F, 0F, -12F, 24, 24, 24);
		Mid.setRotationPoint(0F, -4F, 0F);
		Mid.setTextureSize(256, 320);
		Mid.mirror = true;
		setRotation(Mid, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 0, 0);
		Top.addBox(-16F, 0F, -16F, 32, 4, 32);
		Top.setRotationPoint(0F, -8F, 0F);
		Top.setTextureSize(256, 320);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Bot = new ModelRenderer(this, 0, 0);
		Bot.addBox(-16F, 0F, -16F, 32, 4, 32);
		Bot.setRotationPoint(0F, 20F, 0F);
		Bot.setTextureSize(256, 320);
		Bot.mirror = true;
		setRotation(Bot, 0F, 0F, 0F);
		SmallBox = new ModelRenderer(this, 0, 36);
		SmallBox.addBox(-7F, 0F, -7F, 14, 14, 14);
		SmallBox.setRotationPoint(0F, -22F, 0F);
		SmallBox.setTextureSize(256, 320);
		SmallBox.mirror = true;
		setRotation(SmallBox, 0F, 0.7853982F, 0F);
		ElectrodeF1 = new ModelRenderer(this, 58, 38);
		ElectrodeF1.addBox(-2F, -9F, -11F, 4, 14, 4);
		ElectrodeF1.setRotationPoint(0F, -22F, 0F);
		ElectrodeF1.setTextureSize(256, 320);
		ElectrodeF1.mirror = true;
		setRotation(ElectrodeF1, 0.5235988F, 0F, 0F);
		ElectrodeF2 = new ModelRenderer(this, 78, 38);
		ElectrodeF2.addBox(-1F, 1F, -11F, 2, 10, 2);
		ElectrodeF2.setRotationPoint(0F, -22F, 0F);
		ElectrodeF2.setTextureSize(256, 320);
		ElectrodeF2.mirror = true;
		setRotation(ElectrodeF2, -1.047198F, 0F, 0F);
		ElectrodeL1 = new ModelRenderer(this, 58, 38);
		ElectrodeL1.addBox(-2F, -9F, -11F, 4, 14, 4);
		ElectrodeL1.setRotationPoint(0F, -22F, 0F);
		ElectrodeL1.setTextureSize(256, 320);
		ElectrodeL1.mirror = true;
		setRotation(ElectrodeL1, 0.5235988F, -1.570796F, 0F);
		ElectrodeL2 = new ModelRenderer(this, 78, 38);
		ElectrodeL2.addBox(-1F, 1F, -11F, 2, 10, 2);
		ElectrodeL2.setRotationPoint(0F, -22F, 0F);
		ElectrodeL2.setTextureSize(256, 320);
		ElectrodeL2.mirror = true;
		setRotation(ElectrodeL2, -1.047198F, -1.570796F, 0F);
		ElectrodeB1 = new ModelRenderer(this, 58, 38);
		ElectrodeB1.addBox(-2F, -9F, -11F, 4, 14, 4);
		ElectrodeB1.setRotationPoint(0F, -22F, 0F);
		ElectrodeB1.setTextureSize(256, 320);
		ElectrodeB1.mirror = true;
		setRotation(ElectrodeB1, 0.5235988F, 3.141593F, 0F);
		ElectrodeB2 = new ModelRenderer(this, 78, 38);
		ElectrodeB2.addBox(-1F, 1F, -11F, 2, 10, 2);
		ElectrodeB2.setRotationPoint(0F, -22F, 0F);
		ElectrodeB2.setTextureSize(256, 320);
		ElectrodeB2.mirror = true;
		setRotation(ElectrodeB2, -1.047198F, 3.141593F, 0F);
		ElectrodeR1 = new ModelRenderer(this, 58, 38);
		ElectrodeR1.addBox(-2F, -9F, -11F, 4, 14, 4);
		ElectrodeR1.setRotationPoint(0F, -22F, 0F);
		ElectrodeR1.setTextureSize(256, 320);
		ElectrodeR1.mirror = true;
		setRotation(ElectrodeR1, 0.5235988F, 1.570796F, 0F);
		ElectrodeR2 = new ModelRenderer(this, 78, 38);
		ElectrodeR2.addBox(-1F, 1F, -11F, 2, 10, 2);
		ElectrodeR2.setRotationPoint(0F, -22F, 0F);
		ElectrodeR2.setTextureSize(256, 320);
		ElectrodeR2.mirror = true;
		setRotation(ElectrodeR2, -1.047198F, 1.570796F, 0F);
		Sparks = new ModelRenderer(this, 90, 166);
		Sparks.addBox(-5F, -11F, -5F, 10, 6, 10);
		Sparks.setRotationPoint(0F, -22F, 0F);
		Sparks.setTextureSize(256, 320);
		Sparks.mirror = true;
		setRotation(Sparks, 0F, 0.7853982F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Mid.render(par7);
		Top.render(par7);
		Bot.render(par7);

		EntityEngine var8 = (EntityEngine) par1Entity;
		GL11.glPushMatrix();
		float f1 = var8.animationTicks;
		GL11.glScalef(0.04F * f1, 0.04F * f1, 0.04F * f1);
		SmallBox.render(par7);
		Sparks.render(par7);

		ElectrodeF1.render(par7);
		ElectrodeF2.render(par7);

		ElectrodeR1.render(par7);
		ElectrodeR2.render(par7);

		ElectrodeB1.render(par7);
		ElectrodeB2.render(par7);

		ElectrodeL1.render(par7);
		ElectrodeL2.render(par7);
		GL11.glPopMatrix();

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		EntityEngine var8 = (EntityEngine) par7Entity;
		float x = var8.animationTicks;
		Mid.rotateAngleY = x * -0.12568F;
		Top.rotateAngleY = x * 0.12568F;
		Bot.rotateAngleY = x * 0.12568F;
		SmallBox.setRotationPoint(0F, 3.0F - x, 0F);
		Sparks.setRotationPoint(0F, 3.0F - x, 0F);
		ElectrodeF1.setRotationPoint(0F, 3.0F - x, 0F);
		ElectrodeF2.setRotationPoint(0F, 3.0F - x, 0F);
		ElectrodeR1.setRotationPoint(0F, 3.0F - x, 0F);
		ElectrodeR2.setRotationPoint(0F, 3.0F - x, 0F);
		ElectrodeB1.setRotationPoint(0F, 3.0F - x, 0F);
		ElectrodeB2.setRotationPoint(0F, 3.0F - x, 0F);
		ElectrodeL1.setRotationPoint(0F, 3.0F - x, 0F);
		ElectrodeL2.setRotationPoint(0F, 3.0F - x, 0F);
	}

}
