package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBamboo extends ModelBase {

	ModelRenderer bambooBase;
	ModelRenderer pieceBottom;
	ModelRenderer pieceTop;

	public ModelBamboo() {
		textureWidth = 64;
		textureHeight = 32;

		bambooBase = new ModelRenderer(this, 0, 0);
		bambooBase.addBox(-4F, 0F, -4F, 8, 16, 8);
		bambooBase.setRotationPoint(0F, 8F, 0F);
		bambooBase.setTextureSize(64, 32);
		bambooBase.mirror = true;
		setRotation(bambooBase, 0F, 0F, 0F);
		pieceBottom = new ModelRenderer(this, 28, 22);
		pieceBottom.addBox(-4.5F, 0F, -4.5F, 9, 1, 9);
		pieceBottom.setRotationPoint(0F, 8.1F, 0F);
		pieceBottom.setTextureSize(64, 32);
		pieceBottom.mirror = true;
		setRotation(pieceBottom, 0F, 0F, 0F);
		pieceTop = new ModelRenderer(this, 28, 22);
		pieceTop.addBox(-4.5F, 0F, -4.5F, 9, 1, 9);
		pieceTop.setRotationPoint(0F, 16.1F, 0F);
		pieceTop.setTextureSize(64, 32);
		pieceTop.mirror = true;
		setRotation(pieceTop, 0F, 0F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderModel(float f5) {
		bambooBase.render(f5);
		pieceBottom.render(f5);
		pieceTop.render(f5);
	}

}
