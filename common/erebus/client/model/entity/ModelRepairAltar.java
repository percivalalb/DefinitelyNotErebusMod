package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityRepairAltar;

public class ModelRepairAltar extends ModelBase
{
	//fields
	ModelRenderer AnvilFrontFoot;
	ModelRenderer AnvilRearFoot;
	ModelRenderer AnvilBase;
	ModelRenderer AnvilWaist;
	ModelRenderer AnvilFace;
	ModelRenderer AnvilTable;
	ModelRenderer AnvilHorn;
	ModelRenderer AnvilHeel;
	ModelRenderer Top;
	ModelRenderer Mid;
	ModelRenderer Bot;

	public ModelRepairAltar()
	{
		textureWidth = 256;
		textureHeight = 64;

		AnvilFrontFoot = new ModelRenderer(this, 0, 37);
		AnvilFrontFoot.addBox(-6F, 20F, -6F, 4, 4, 12);
		AnvilFrontFoot.setRotationPoint(0F, 0F, 0F);
		AnvilFrontFoot.setTextureSize(256, 64);
		AnvilFrontFoot.mirror = true;
		setRotation(AnvilFrontFoot, 0F, 0F, 0F);
		AnvilRearFoot = new ModelRenderer(this, 0, 37);
		AnvilRearFoot.addBox(2F, 20F, -6F, 4, 4, 12);
		AnvilRearFoot.setRotationPoint(0F, 0F, 0F);
		AnvilRearFoot.setTextureSize(256, 64);
		AnvilRearFoot.mirror = true;
		setRotation(AnvilRearFoot, 0F, 0F, 0F);
		AnvilBase = new ModelRenderer(this, 33, 37);
		AnvilBase.addBox(-5F, 19F, -4F, 10, 5, 8);
		AnvilBase.setRotationPoint(0F, 0F, 0F);
		AnvilBase.setTextureSize(256, 64);
		AnvilBase.mirror = true;
		setRotation(AnvilBase, 0F, 0F, 0F);
		AnvilWaist = new ModelRenderer(this, 0, 54);
		AnvilWaist.addBox(-4F, 15F, -2F, 8, 4, 4);
		AnvilWaist.setRotationPoint(0F, 0F, 0F);
		AnvilWaist.setTextureSize(256, 64);
		AnvilWaist.mirror = true;
		setRotation(AnvilWaist, 0F, 0F, 0F);
		AnvilFace = new ModelRenderer(this, 70, 37);
		AnvilFace.addBox(-7F, 8F, -5F, 14, 7, 10);
		AnvilFace.setRotationPoint(0F, 0F, 0F);
		AnvilFace.setTextureSize(256, 64);
		AnvilFace.mirror = true;
		setRotation(AnvilFace, 0F, 0F, 0F);
		AnvilTable = new ModelRenderer(this, 33, 51);
		AnvilTable.addBox(-10F, 9F, -3.5F, 3, 5, 7);
		AnvilTable.setRotationPoint(0F, 0F, 0F);
		AnvilTable.setTextureSize(256, 64);
		AnvilTable.mirror = true;
		setRotation(AnvilTable, 0F, 0F, 0F);
		AnvilHorn = new ModelRenderer(this, 55, 51);
		AnvilHorn.addBox(-13F, 9F, -2F, 3, 3, 4);
		AnvilHorn.setRotationPoint(0F, 0F, 0F);
		AnvilHorn.setTextureSize(256, 64);
		AnvilHorn.mirror = true;
		setRotation(AnvilHorn, 0F, 0F, 0F);
		AnvilHeel = new ModelRenderer(this, 109, 45);
		AnvilHeel.addBox(7F, 8F, -5F, 3, 4, 10);
		AnvilHeel.setRotationPoint(0F, 0F, 0F);
		AnvilHeel.setTextureSize(256, 64);
		AnvilHeel.mirror = true;
		setRotation(AnvilHeel, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 0, 0);
		Top.addBox(-16F, 0F, -16F, 32, 4, 32);
		Top.setRotationPoint(0F, -8F, 0F);
		Top.setTextureSize(256, 64);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Mid = new ModelRenderer(this, 130, 0);
		Mid.addBox(-12F, 0F, -12F, 24, 24, 24);
		Mid.setRotationPoint(0F, -4F, 0F);
		Mid.setTextureSize(256, 64);
		Mid.mirror = true;
		setRotation(Mid, 0F, 0F, 0F);
		Bot = new ModelRenderer(this, 0, 0);
		Bot.addBox(-16F, 0F, -16F, 32, 4, 32);
		Bot.setRotationPoint(0F, 20F, 0F);
		Bot.setTextureSize(256, 64);
		Bot.mirror = true;
		setRotation(Bot, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Mid.render(par7);
		Top.render(par7);
		Bot.render(par7);

		EntityRepairAltar var8 = (EntityRepairAltar) par1Entity;
		GL11.glPushMatrix();
		float f1 = var8.animationTicks;
		GL11.glScalef(0.04F * f1, 0.04F * f1, 0.04F * f1);

		AnvilFrontFoot.render(par7);
		AnvilRearFoot.render(par7);
		AnvilBase.render(par7);
		AnvilWaist.render(par7);
		AnvilFace.render(par7);
		AnvilTable.render(par7);
		AnvilHorn.render(par7);
		AnvilHeel.render(par7);

		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		EntityRepairAltar var8 = (EntityRepairAltar) par7Entity;
		float x = var8.animationTicks;
		Mid.rotateAngleY = x * -0.12568F;
		Top.rotateAngleY = x * 0.12568F;
		Bot.rotateAngleY = x * 0.12568F;
		AnvilFrontFoot.setRotationPoint(0F, -7.0F - x, 0F);
		AnvilRearFoot.setRotationPoint(0F, -7.0F - x, 0F);
		AnvilBase.setRotationPoint(0F, -7.0F - x, 0F);
		AnvilWaist.setRotationPoint(0F, -7.0F - x, 0F);
		AnvilFace.setRotationPoint(0F, -7.0F - x, 0F);
		AnvilTable.setRotationPoint(0F, -7.0F - x, 0F);
		AnvilHorn.setRotationPoint(0F, -7.0F - x, 0F);
		AnvilHeel.setRotationPoint(0F, -7.0F - x, 0F);
	}

}
