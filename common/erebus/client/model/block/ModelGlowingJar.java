package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelGlowingJar extends ModelBase
{
	ModelRenderer Jar;
	ModelRenderer Lid;
	ModelRenderer Neck;
	ModelRenderer Inside1;
	ModelRenderer Inside2;

	public ModelGlowingJar() {
		textureWidth = 64;
		textureHeight = 64;

		Jar = new ModelRenderer(this, 0, 27);
		Jar.addBox(-6F, -1F, -6F, 12, 1, 12);
		Jar.setRotationPoint(0F, 12F, 0F);
		Jar.setTextureSize(64, 64);
		Jar.mirror = true;
		setRotation(Jar, 0F, 0F, 0F);
		Lid = new ModelRenderer(this, 0, 41);
		Lid.addBox(-7F, -3F, -7F, 14, 3, 14);
		Lid.setRotationPoint(0F, 11F, 0F);
		Lid.setTextureSize(64, 64);
		Lid.mirror = true;
		setRotation(Lid, 0F, 0F, 0F);
		Neck = new ModelRenderer(this, 0, 0);
		Neck.addBox(-7F, 0F, -7F, 14, 12, 14);
		Neck.setRotationPoint(0F, 12F, 0F);
		Neck.setTextureSize(64, 64);
		Neck.mirror = true;
		setRotation(Neck, 0F, 0F, 0F);
		Inside1 = new ModelRenderer(this, 43, -7);
		Inside1.addBox(0F, 0F, -5F, 0, 10, 10);
		Inside1.setRotationPoint(0F, 12F, 0F);
		Inside1.setTextureSize(64, 64);
		Inside1.mirror = true;
		setRotation(Inside1, 0F, 0.7853982F, 0F);
		Inside2 = new ModelRenderer(this, 43, -7);
		Inside2.addBox(0F, 0F, -5F, 0, 10, 10);
		Inside2.setRotationPoint(0F, 12F, 0F);
		Inside2.setTextureSize(64, 64);
		Inside2.mirror = true;
		setRotation(Inside2, 0F, -0.7853982F, 0F);
	}

	public void render()
	{
		Lid.render(0.0625F);
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 0.3125F, 0F);
		GL11.glScalef(1F, 0.7F, 1F);
		Inside1.render(0.0625F);
		Inside2.render(0.0625F);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		float transparency = 0.6F;
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
		Neck.render(0.0625F);
		Jar.render(0.0625F);
		GL11.glDisable(3042);
		GL11.glPopMatrix();

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
