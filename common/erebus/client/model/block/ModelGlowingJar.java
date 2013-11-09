package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGlowingJar extends ModelBase
{
	//fields
	ModelRenderer Lid1;
	ModelRenderer Lid2;
	ModelRenderer Lid3;
	ModelRenderer Lid4;
	ModelRenderer Lid5;
	ModelRenderer Mid1;
	ModelRenderer Mid2;
	ModelRenderer Mid3;
	ModelRenderer Mid4;
	ModelRenderer Mid5;
	ModelRenderer Main1;
	ModelRenderer Main2;
	ModelRenderer Main3;
	ModelRenderer Main4;
	ModelRenderer Main5;
	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer Base3;
	ModelRenderer Base4;
	ModelRenderer Base5;

	public ModelGlowingJar()
	{
		textureWidth = 128;
		textureHeight = 64;

		Lid1 = new ModelRenderer(this, 40, 0);
		Lid1.addBox(-8F, 0.01F, -3F, 16, 2, 6);
		Lid1.setRotationPoint(0F, 8F, 0F);
		Lid1.setTextureSize(128, 64);
		Lid1.mirror = true;
		setRotation(Lid1, 0F, 1.570796F, 0F);
		Lid2 = new ModelRenderer(this, 40, 10);
		Lid2.addBox(-7F, 0.012F, -5F, 14, 2, 10);
		Lid2.setRotationPoint(0F, 8F, 0F);
		Lid2.setTextureSize(128, 64);
		Lid2.mirror = true;
		setRotation(Lid2, 0F, 0F, 0F);
		Lid3 = new ModelRenderer(this, 50, 24);
		Lid3.addBox(-6F, 0.013F, -6F, 12, 2, 12);
		Lid3.setRotationPoint(0F, 8F, 0F);
		Lid3.setTextureSize(128, 64);
		Lid3.mirror = true;
		setRotation(Lid3, 0F, 0F, 0F);
		Lid4 = new ModelRenderer(this, 40, 10);
		Lid4.addBox(-7F, 0.011F, -5F, 14, 2, 10);
		Lid4.setRotationPoint(0F, 8F, 0F);
		Lid4.setTextureSize(128, 64);
		Lid4.mirror = true;
		setRotation(Lid4, 0F, 1.570796F, 0F);
		Lid5 = new ModelRenderer(this, 40, 0);
		Lid5.addBox(-8F, 0F, -3F, 16, 2, 6);
		Lid5.setRotationPoint(0F, 8F, 0F);
		Lid5.setTextureSize(128, 64);
		Lid5.mirror = true;
		setRotation(Lid5, 0F, 0F, 0F);
		Mid1 = new ModelRenderer(this, 0, 0);
		Mid1.addBox(-7F, 2F, -2F, 14, 1, 4);
		Mid1.setRotationPoint(0F, 8F, 0F);
		Mid1.setTextureSize(128, 64);
		Mid1.mirror = true;
		setRotation(Mid1, 0F, 1.570796F, 0F);
		Mid2 = new ModelRenderer(this, 0, 6);
		Mid2.addBox(-6F, 2F, -4F, 12, 1, 8);
		Mid2.setRotationPoint(0F, 8F, 0F);
		Mid2.setTextureSize(128, 64);
		Mid2.mirror = true;
		setRotation(Mid2, 0F, 1.570796F, 0F);
		Mid3 = new ModelRenderer(this, 83, 0);
		Mid3.addBox(-5F, 2F, -5F, 10, 1, 10);
		Mid3.setRotationPoint(0F, 8F, 0F);
		Mid3.setTextureSize(128, 64);
		Mid3.mirror = true;
		setRotation(Mid3, 0F, 0F, 0F);
		Mid4 = new ModelRenderer(this, 0, 6);
		Mid4.addBox(-6F, 2F, -4F, 12, 1, 8);
		Mid4.setRotationPoint(0F, 8F, 0F);
		Mid4.setTextureSize(128, 64);
		Mid4.mirror = true;
		setRotation(Mid4, 0F, 0F, 0F);
		Mid5 = new ModelRenderer(this, 0, 0);
		Mid5.addBox(-7F, 2F, -2F, 14, 1, 4);
		Mid5.setRotationPoint(0F, 8F, 0F);
		Mid5.setTextureSize(128, 64);
		Mid5.mirror = true;
		setRotation(Mid5, 0F, 0F, 0F);
		Main1 = new ModelRenderer(this, 0, 16);
		Main1.addBox(-7F, 0F, -5F, 14, 12, 10);
		Main1.setRotationPoint(0F, 11F, 0F);
		Main1.setTextureSize(128, 64);
		Main1.mirror = true;
		setRotation(Main1, 0F, 0F, 0F);
		Main2 = new ModelRenderer(this, 50, 39);
		Main2.addBox(-6F, 0F, -6F, 12, 12, 12);
		Main2.setRotationPoint(0F, 11F, 0F);
		Main2.setTextureSize(128, 64);
		Main2.mirror = true;
		setRotation(Main2, 0F, 0F, 0F);
		Main3 = new ModelRenderer(this, 0, 16);
		Main3.addBox(-7F, 0F, -5F, 14, 12, 10);
		Main3.setRotationPoint(0F, 11F, 0F);
		Main3.setTextureSize(128, 64);
		Main3.mirror = true;
		setRotation(Main3, 0F, 1.570796F, 0F);
		Main4 = new ModelRenderer(this, 0, 45);
		Main4.addBox(-8F, 0F, -3F, 16, 12, 6);
		Main4.setRotationPoint(0F, 11F, 0F);
		Main4.setTextureSize(128, 64);
		Main4.mirror = true;
		setRotation(Main4, 0F, 0F, 0F);
		Main5 = new ModelRenderer(this, 0, 45);
		Main5.addBox(-8F, 0F, -3F, 16, 12, 6);
		Main5.setRotationPoint(0F, 11F, 0F);
		Main5.setTextureSize(128, 64);
		Main5.mirror = true;
		setRotation(Main5, 0F, 1.570796F, 0F);
		Base1 = new ModelRenderer(this, 0, 6);
		Base1.addBox(-6F, 2F, -4F, 12, 1, 8);
		Base1.setRotationPoint(0F, 21F, 0F);
		Base1.setTextureSize(128, 64);
		Base1.mirror = true;
		setRotation(Base1, 0F, 0F, 0F);
		Base2 = new ModelRenderer(this, 83, 0);
		Base2.addBox(-5F, 2F, -5F, 10, 1, 10);
		Base2.setRotationPoint(0F, 21F, 0F);
		Base2.setTextureSize(128, 64);
		Base2.mirror = true;
		setRotation(Base2, 0F, 0F, 0F);
		Base3 = new ModelRenderer(this, 0, 6);
		Base3.addBox(-6F, 2F, -4F, 12, 1, 8);
		Base3.setRotationPoint(0F, 21F, 0F);
		Base3.setTextureSize(128, 64);
		Base3.mirror = true;
		setRotation(Base3, 0F, 1.570796F, 0F);
		Base4 = new ModelRenderer(this, 0, 0);
		Base4.addBox(-7F, 2F, -2F, 14, 1, 4);
		Base4.setRotationPoint(0F, 21F, 0F);
		Base4.setTextureSize(128, 64);
		Base4.mirror = true;
		setRotation(Base4, 0F, 0F, 0F);
		Base5 = new ModelRenderer(this, 0, 0);
		Base5.addBox(-7F, 2F, -2F, 14, 1, 4);
		Base5.setRotationPoint(0F, 21F, 0F);
		Base5.setTextureSize(128, 64);
		Base5.mirror = true;
		setRotation(Base5, 0F, 1.570796F, 0F);
	}

	public void render()
	{
		Lid1.render(0.0625F);
		Lid2.render(0.0625F);
		Lid3.render(0.0625F);
		Lid4.render(0.0625F);
		Lid5.render(0.0625F);
		Mid1.render(0.0625F);
		Mid2.render(0.0625F);
		Mid3.render(0.0625F);
		Mid4.render(0.0625F);
		Mid5.render(0.0625F);
		Main1.render(0.0625F);
		Main2.render(0.0625F);
		Main3.render(0.0625F);
		Main4.render(0.0625F);
		Main5.render(0.0625F);
		Base1.render(0.0625F);
		Base2.render(0.0625F);
		Base3.render(0.0625F);
		Base4.render(0.0625F);
		Base5.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderAll() {

	}
}
