package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityXPAltar;

public class ModelXPAltar extends ModelBase
{
	//fields
	ModelRenderer GlassTop;
	ModelRenderer GlassBot;
	ModelRenderer GlassMid;
	ModelRenderer BPlate;
	ModelRenderer TPlate;
	ModelRenderer RFSupport;
	ModelRenderer RBSupport;
	ModelRenderer LFSupport;
	ModelRenderer LBSupport;
	ModelRenderer Top;
	ModelRenderer Mid;
	ModelRenderer Bot;

	public ModelXPAltar()
	{
		textureWidth = 256;
		textureHeight = 64;

		GlassTop = new ModelRenderer(this, 0, 37);
		GlassTop.addBox(-3.5F, 8F, -3.5F, 7, 6, 7);
		GlassTop.setRotationPoint(0F, 0F, 0F);
		GlassTop.setTextureSize(256, 64);
		GlassTop.mirror = true;
		setRotation(GlassTop, 0F, 0F, 0F);
		GlassBot = new ModelRenderer(this, 0, 51);
		GlassBot.addBox(-3.5F, 16F, -3.5F, 7, 6, 7);
		GlassBot.setRotationPoint(0F, 0F, 0F);
		GlassBot.setTextureSize(256, 64);
		GlassBot.mirror = true;
		setRotation(GlassBot, 0F, 0F, 0F);
		GlassMid = new ModelRenderer(this, 29, 37);
		GlassMid.addBox(-1.5F, 14F, -1.5F, 3, 2, 3);
		GlassMid.setRotationPoint(0F, 0F, 0F);
		GlassMid.setTextureSize(256, 64);
		GlassMid.mirror = true;
		setRotation(GlassMid, 0F, 0F, 0F);
		BPlate = new ModelRenderer(this, 42, 37);
		BPlate.addBox(-7F, 22F, -7F, 14, 2, 14);
		BPlate.setRotationPoint(0F, 0F, 0F);
		BPlate.setTextureSize(256, 64);
		BPlate.mirror = true;
		setRotation(BPlate, 0F, 0F, 0F);
		TPlate = new ModelRenderer(this, 42, 37);
		TPlate.addBox(-7F, 6F, -7F, 14, 2, 14);
		TPlate.setRotationPoint(0F, 0F, 0F);
		TPlate.setTextureSize(256, 64);
		TPlate.mirror = true;
		setRotation(TPlate, 0F, 0F, 0F);
		RFSupport = new ModelRenderer(this, 99, 37);
		RFSupport.addBox(-6F, 5F, -6F, 2, 17, 2);
		RFSupport.setRotationPoint(0F, 0F, 0F);
		RFSupport.setTextureSize(256, 64);
		RFSupport.mirror = true;
		setRotation(RFSupport, 0F, 0F, 0F);
		RBSupport = new ModelRenderer(this, 99, 37);
		RBSupport.addBox(-6F, 5F, 4F, 2, 17, 2);
		RBSupport.setRotationPoint(0F, 0F, 0F);
		RBSupport.setTextureSize(256, 64);
		RBSupport.mirror = true;
		setRotation(RBSupport, 0F, 0F, 0F);
		LFSupport = new ModelRenderer(this, 99, 37);
		LFSupport.addBox(4F, 5F, -6F, 2, 17, 2);
		LFSupport.setRotationPoint(0F, 0F, 0F);
		LFSupport.setTextureSize(256, 64);
		LFSupport.mirror = true;
		setRotation(LFSupport, 0F, 0F, 0F);
		LBSupport = new ModelRenderer(this, 99, 37);
		LBSupport.addBox(4F, 5F, 4F, 2, 17, 2);
		LBSupport.setRotationPoint(0F, 0F, 0F);
		LBSupport.setTextureSize(256, 64);
		LBSupport.mirror = true;
		setRotation(LBSupport, 0F, 0F, 0F);
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

		EntityXPAltar var8 = (EntityXPAltar) par1Entity;
		GL11.glPushMatrix();
		float f1 = var8.animationTicks;
		GL11.glScalef(0.04F * f1, 0.04F * f1, 0.04F * f1);

		GlassTop.render(par7);
		GlassBot.render(par7);
		GlassMid.render(par7);
		BPlate.render(par7);
		TPlate.render(par7);
		RFSupport.render(par7);
		RBSupport.render(par7);
		LFSupport.render(par7);
		LBSupport.render(par7);

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
		EntityXPAltar var8 = (EntityXPAltar) par7Entity;
		float x = var8.animationTicks;
		Mid.rotateAngleY = x * -0.12568F;
		Top.rotateAngleY = x * 0.12568F;
		Bot.rotateAngleY = x * 0.12568F;
		GlassTop.setRotationPoint(0F, -7.0F - x, 0F);
		GlassBot.setRotationPoint(0F, -7.0F - x, 0F);
		GlassMid.setRotationPoint(0F, -7.0F - x, 0F);
		BPlate.setRotationPoint(0F, -7.0F - x, 0F);
		TPlate.setRotationPoint(0F, -7.0F - x, 0F);
		RFSupport.setRotationPoint(0F, -7.0F - x, 0F);
		RBSupport.setRotationPoint(0F, -7.0F - x, 0F);
		LFSupport.setRotationPoint(0F, -7.0F - x, 0F);
		LBSupport.setRotationPoint(0F, -7.0F - x, 0F);
	}

}
