package erebus.client.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWandOfAnimation extends ModelBase {

	ModelRenderer Jewel1, Jewel2, Jewel3, TopF1, TopF2, TopF3, TopF4, TopF5, TopR1, TopR2, TopR3, TopR4, TopR5, TopB1, TopB2, TopB3, TopB4, TopB5, TopL1, TopL2, TopL3, TopL4, TopL5, Dec4, Dec3, Dec2, Dec1, Shaft, Pommel1, Pommel2, Pommel3;

	public ModelWandOfAnimation() {
		textureWidth = 32;
		textureHeight = 64;

		Jewel1 = new ModelRenderer(this, 0, 7).setTextureSize(32, 64);
		Jewel1.addBox(-2F, -2F, -2F, 4, 4, 4);
		Jewel1.setRotationPoint(0F, -9.5F, 0F);
		setRotation(Jewel1, 0F, 0.7853982F, 0F);

		Jewel2 = new ModelRenderer(this, 0, 7).setTextureSize(32, 64);;
		Jewel2.addBox(-2F, -2F, -2F, 4, 4, 4);
		Jewel2.setRotationPoint(0F, -9.5F, 0F);
		setRotation(Jewel2, 0.7853982F, 0F, 0F);

		Jewel3 = new ModelRenderer(this, 0, 7).setTextureSize(32, 64);
		Jewel3.addBox(-2F, -2F, -2F, 4, 4, 4);
		Jewel3.setRotationPoint(0F, -9.5F, 0F);
		setRotation(Jewel3, 0F, 0F, 0.7853982F);

		TopF1 = new ModelRenderer(this, 5, 0).setTextureSize(32, 64);
		TopF1.addBox(-2F, -4F, -0.5F, 1, 4, 1);
		TopF1.setRotationPoint(0F, -4F, 0F);
		setRotation(TopF1, 0F, -1.570796F, -0.6981317F);

		TopF2 = new ModelRenderer(this, 10, 0).setTextureSize(32, 64);
		TopF2.addBox(-2F, -6F, -1F, 1, 2, 2);
		TopF2.setRotationPoint(0F, -4F, 0F);
		setRotation(TopF2, 0F, -1.570796F, -0.6981317F);

		TopF3 = new ModelRenderer(this, 0, 0).setTextureSize(32, 64);
		TopF3.addBox(4F, -7F, -0.5F, 1, 5, 1);
		TopF3.setRotationPoint(0F, -5.5F, 0F);
		setRotation(TopF3, 0F, 1.570796F, 0F);

		TopF4 = new ModelRenderer(this, 17, 0).setTextureSize(32, 64);
		TopF4.addBox(9F, -6F, -1F, 1, 2, 2);
		TopF4.setRotationPoint(0F, -3F, 0F);
		setRotation(TopF4, 0F, 1.570796F, -0.6981317F);

		TopF5 = new ModelRenderer(this, 24, 0).setTextureSize(32, 64);
		TopF5.addBox(6F, -6F, -0.5F, 1, 5, 1);
		TopF5.setRotationPoint(0F, -8F, 0F);
		setRotation(TopF5, 0F, 1.570796F, -0.8726646F);

		TopR1 = new ModelRenderer(this, 5, 0).setTextureSize(32, 64);
		TopR1.addBox(-2F, -4F, -0.5F, 1, 4, 1);
		TopR1.setRotationPoint(0F, -4F, 0F);
		setRotation(TopR1, 0F, 0F, -0.6981317F);

		TopR2 = new ModelRenderer(this, 10, 0).setTextureSize(32, 64);
		TopR2.addBox(-2F, -6F, -1F, 1, 2, 2);
		TopR2.setRotationPoint(0F, -4F, 0F);
		setRotation(TopR2, 0F, 0F, -0.6981317F);

		TopR3 = new ModelRenderer(this, 0, 0).setTextureSize(32, 64);
		TopR3.addBox(4F, -7F, -0.5F, 1, 5, 1);
		TopR3.setRotationPoint(0F, -5.5F, 0F);
		setRotation(TopR3, 0F, 3.141593F, 0F);

		TopR4 = new ModelRenderer(this, 17, 0).setTextureSize(32, 64);
		TopR4.addBox(9F, -6F, -1F, 1, 2, 2);
		TopR4.setRotationPoint(0F, -3F, 0F);
		setRotation(TopR4, 0F, 3.141593F, -0.6981317F);

		TopR5 = new ModelRenderer(this, 24, 0).setTextureSize(32, 64);
		TopR5.addBox(6F, -6F, -0.5F, 1, 5, 1);
		TopR5.setRotationPoint(0F, -8F, 0F);
		setRotation(TopR5, 0F, 3.141593F, -0.8726646F);

		TopB1 = new ModelRenderer(this, 5, 0).setTextureSize(32, 64);
		TopB1.addBox(-2F, -4F, -0.5F, 1, 4, 1);
		TopB1.setRotationPoint(0F, -4F, 0F);
		setRotation(TopB1, 0F, 1.570796F, -0.6981317F);

		TopB2 = new ModelRenderer(this, 10, 0).setTextureSize(32, 64);
		TopB2.addBox(-2F, -6F, -1F, 1, 2, 2);
		TopB2.setRotationPoint(0F, -4F, 0F);
		setRotation(TopB2, 0F, 1.570796F, -0.6981317F);

		TopB3 = new ModelRenderer(this, 0, 0).setTextureSize(32, 64);
		TopB3.addBox(4F, -7F, -0.5F, 1, 5, 1);
		TopB3.setRotationPoint(0F, -5.5F, 0F);
		setRotation(TopB3, 0F, -1.570796F, 0F);

		TopB4 = new ModelRenderer(this, 17, 0).setTextureSize(32, 64);
		TopB4.addBox(9F, -6F, -1F, 1, 2, 2);
		TopB4.setRotationPoint(0F, -3F, 0F);
		setRotation(TopB4, 0F, -1.570796F, -0.6981317F);

		TopB5 = new ModelRenderer(this, 24, 0).setTextureSize(32, 64);
		TopB5.addBox(6F, -6F, -0.5F, 1, 5, 1);
		TopB5.setRotationPoint(0F, -8F, 0F);
		setRotation(TopB5, 0F, -1.570796F, -0.8726646F);

		TopL1 = new ModelRenderer(this, 5, 0).setTextureSize(32, 64);
		TopL1.addBox(-2F, -4F, -0.5F, 1, 4, 1);
		TopL1.setRotationPoint(0F, -4F, 0F);
		setRotation(TopL1, 0F, 3.141593F, -0.6981317F);

		TopL2 = new ModelRenderer(this, 10, 0).setTextureSize(32, 64);
		TopL2.addBox(-2F, -6F, -1F, 1, 2, 2);
		TopL2.setRotationPoint(0F, -4F, 0F);
		setRotation(TopL2, 0F, 3.141593F, -0.6981317F);

		TopL3 = new ModelRenderer(this, 0, 0).setTextureSize(32, 64);
		TopL3.addBox(4F, -7F, -0.5F, 1, 5, 1);
		TopL3.setRotationPoint(0F, -5.5F, 0F);
		setRotation(TopL3, 0F, 0F, 0F);

		TopL4 = new ModelRenderer(this, 17, 0).setTextureSize(32, 64);
		TopL4.addBox(9F, -6F, -1F, 1, 2, 2);
		TopL4.setRotationPoint(0F, -3F, 0F);
		setRotation(TopL4, 0F, 0F, -0.6981317F);

		TopL5 = new ModelRenderer(this, 24, 0).setTextureSize(32, 64);
		TopL5.addBox(6F, -6F, -0.5F, 1, 5, 1);
		TopL5.setRotationPoint(0F, -8F, 0F);
		setRotation(TopL5, 0F, 0F, -0.8726646F);

		Dec4 = new ModelRenderer(this, 0, 16).setTextureSize(32, 64);
		Dec4.addBox(-1.5F, -14F, -1.5F, 3, 3, 3);
		Dec4.setRotationPoint(0F, 10F, 0F);
		setRotation(Dec4, 0F, 0F, 0F);

		Dec3 = new ModelRenderer(this, 0, 23).setTextureSize(32, 64);
		Dec3.addBox(-1F, -11F, -1F, 2, 2, 2);
		Dec3.setRotationPoint(0F, 10F, 0F);
		setRotation(Dec3, 0F, 0F, 0F);

		Dec2 = new ModelRenderer(this, 0, 28).setTextureSize(32, 64);
		Dec2.addBox(-1.5F, -9F, -1.5F, 3, 1, 3);
		Dec2.setRotationPoint(0F, 10F, 0F);
		setRotation(Dec2, 0F, 0F, 0F);

		Dec1 = new ModelRenderer(this, 0, 28).setTextureSize(32, 64);
		Dec1.addBox(-1.5F, -9F, -1.5F, 3, 1, 3);
		Dec1.setRotationPoint(0F, 10F, 0F);
		setRotation(Dec1, 0F, 0.7853982F, 0F);

		Shaft = new ModelRenderer(this, 0, 33).setTextureSize(32, 64);
		Shaft.addBox(-1F, -8F, -1F, 2, 19, 2);
		Shaft.setRotationPoint(0F, 10F, 0F);
		setRotation(Shaft, 0F, 0F, 0F);

		Pommel1 = new ModelRenderer(this, 0, 55).setTextureSize(32, 64);
		Pommel1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		Pommel1.setRotationPoint(0F, 22F, 0F);
		setRotation(Pommel1, 0.7853982F, 0F, 0F);

		Pommel2 = new ModelRenderer(this, 0, 55).setTextureSize(32, 64);
		Pommel2.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		Pommel2.setRotationPoint(0F, 22F, 0F);
		setRotation(Pommel2, 0F, 0F, 0.7853982F);

		Pommel3 = new ModelRenderer(this, 0, 55).setTextureSize(32, 64);
		Pommel3.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
		Pommel3.setRotationPoint(0F, 22F, 0F);
		setRotation(Pommel3, 0F, 0.7853982F, 0F);
	}

	public void render() {
		Jewel1.render(0.0625F);
		Jewel2.render(0.0625F);
		Jewel3.render(0.0625F);
		TopF1.render(0.0625F);
		TopF2.render(0.0625F);
		TopF3.render(0.0625F);
		TopF4.render(0.0625F);
		TopF5.render(0.0625F);
		TopR1.render(0.0625F);
		TopR2.render(0.0625F);
		TopR3.render(0.0625F);
		TopR4.render(0.0625F);
		TopR5.render(0.0625F);
		TopB1.render(0.0625F);
		TopB2.render(0.0625F);
		TopB3.render(0.0625F);
		TopB4.render(0.0625F);
		TopB5.render(0.0625F);
		TopL1.render(0.0625F);
		TopL2.render(0.0625F);
		TopL3.render(0.0625F);
		TopL4.render(0.0625F);
		TopL5.render(0.0625F);
		Dec4.render(0.0625F);
		Dec3.render(0.0625F);
		Dec2.render(0.0625F);
		Dec1.render(0.0625F);
		Shaft.render(0.0625F);
		Pommel1.render(0.0625F);
		Pommel2.render(0.0625F);
		Pommel3.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}