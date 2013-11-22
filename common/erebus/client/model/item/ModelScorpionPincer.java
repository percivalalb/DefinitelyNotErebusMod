
package erebus.client.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScorpionPincer extends ModelBase
{
	ModelRenderer ClawR4;
	ModelRenderer ClawR5Top;
	ModelRenderer ClawR5Bot;

	public ModelScorpionPincer()
	{
		textureWidth = 64;
		textureHeight = 128;

		ClawR4 = new ModelRenderer(this, 44, 3);
		ClawR4.addBox(-8.5F, -2F, 0F, 6, 4, 4);
		ClawR4.setRotationPoint(0F, 16F, 4F);
		ClawR4.setTextureSize(64, 128);
		ClawR4.mirror = true;
		setRotation(ClawR4, 0F, -1.745329F, 0F);
		ClawR5Top = new ModelRenderer(this, 43, 0);
		ClawR5Top.addBox(-9.5F, -1.5F, 6F, 4, 1, 1);
		ClawR5Top.setRotationPoint(0F, 16F, 4F);
		ClawR5Top.setTextureSize(64, 128);
		ClawR5Top.mirror = true;
		setRotation(ClawR5Top, 0F, -2.268928F, 0F);
		ClawR5Bot = new ModelRenderer(this, 54, 0);
		ClawR5Bot.addBox(-9.5F, 0.5F, 6F, 4, 1, 1);
		ClawR5Bot.setRotationPoint(0F, 16F, 4F);
		ClawR5Bot.setTextureSize(64, 128);
		ClawR5Bot.mirror = true;
		setRotation(ClawR5Bot, 0F, -2.268928F, 0F);
	}

	public void render()
	{
		ClawR4.render(0.0625F);
		ClawR5Top.render(0.0625F);
		ClawR5Bot.render(0.0625F);;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par6Entity) {
	}

}
