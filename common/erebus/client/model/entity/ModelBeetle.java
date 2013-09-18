package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBeetle extends ModelBase {
	// fields
	ModelRenderer backbody;
	ModelRenderer body;
	ModelRenderer head;
	ModelRenderer bum;
	ModelRenderer head2;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer leg5;
	ModelRenderer leg6;

	// The Base Rotation around Y when still
	float baseLeg1RotateY = 0.9250245F;
	float baseLeg2RotateY = -baseLeg1RotateY;

	float baseLeg3RotateY = 0;
	float baseLeg4RotateY = -baseLeg3RotateY;

	float baseLeg5RotateY = -baseLeg1RotateY;// -0.9599311F;
	float baseLeg6RotateY = -baseLeg5RotateY;

	// The Base Rotation around Z when still
	float baseLeg1RotateZ = 0.3316126F;
	float baseLeg2RotateZ = -baseLeg1RotateZ;

	float baseLeg3RotateZ = 0.1516126F;
	float baseLeg4RotateZ = -baseLeg3RotateZ;

	float baseLeg5RotateZ = baseLeg1RotateZ;// 0.4270796F;
	float baseLeg6RotateZ = -baseLeg5RotateZ;

	// To put the entity in the centre
	float centre = -5F;

	public ModelBeetle() {
		textureWidth = 128;
		textureHeight = 128;

		backbody = new ModelRenderer(this, 35, 0);
		backbody.addBox(-7F, -4F, 0F, 14, 9, 17);
		backbody.setRotationPoint(-1F, 15F, 0F + centre);
		backbody.setTextureSize(128, 128);
		backbody.mirror = true;
		setRotation(backbody, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 20);
		body.addBox(-4F, -2F, -5F, 12, 8, 5);
		body.setRotationPoint(-3F, 14F, 0F + centre);
		body.setTextureSize(128, 128);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		head = new ModelRenderer(this, 1, 10);
		head.addBox(-2F, -1F, -2F, 6, 5, 3);
		head.setRotationPoint(-2F, 15F, -6F + centre);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		bum = new ModelRenderer(this, 35, 27);
		bum.addBox(-6F, -2F, 0F, 12, 6, 3);
		bum.setRotationPoint(-1F, 15F, 17F + centre);
		bum.setTextureSize(128, 128);
		bum.mirror = true;
		setRotation(bum, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 2, 6);
		head2.addBox(-5F, 1F, -5F, 12, 0, 4);
		head2.setRotationPoint(-2F, 15F, -6F + centre);
		head2.setTextureSize(128, 128);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);

		// Front Legs
		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(0F, 0F, 0F, 11, 2, 2);
		leg1.setRotationPoint(4F, 19F, -2F + centre);
		leg1.setTextureSize(128, 128);
		leg1.mirror = true;
		setRotation(leg1, 0F, baseLeg1RotateY, baseLeg1RotateZ); // 0.9250245F,
																	// 0.3316126F);
		leg2 = new ModelRenderer(this, 0, 0);
		leg2.addBox(-11F, 0F, 0F, 11, 2, 2);
		leg2.setRotationPoint(-6F, 19F, -2F + centre);
		leg2.setTextureSize(128, 128);
		leg2.mirror = true;
		setRotation(leg2, 0F, baseLeg2RotateY, baseLeg2RotateZ); // 1.9750245F,
																	// 0.3316126F);

		// Middle Legs
		leg3 = new ModelRenderer(this, 0, 0);
		leg3.addBox(0F, 0F, 0F, 11, 2, 2);
		leg3.setRotationPoint(5F, 19F, 2F + centre);
		leg3.setTextureSize(128, 128);
		leg3.mirror = true;
		setRotation(leg3, 0F, baseLeg3RotateY, baseLeg3RotateZ); // 0.0698132F,
																	// 0.3316126F);
		leg4 = new ModelRenderer(this, 0, 0);
		leg4.addBox(-11F, 0F, 0F, 11, 2, 2);
		leg4.setRotationPoint(-7F, 19F, 2F + centre);
		leg4.setTextureSize(128, 128);
		leg4.mirror = true;
		setRotation(leg4, 0F, baseLeg4RotateY, baseLeg4RotateZ); // 0F,
																	// -0.2792527F);

		// Back Legs
		leg5 = new ModelRenderer(this, 0, 0);
		leg5.addBox(0F, -1F, 1F, 15, 2, 2);
		leg5.setRotationPoint(5F, 20F, 6F + centre);
		leg5.setTextureSize(128, 128);
		leg5.mirror = true;
		setRotation(leg5, 0F, baseLeg5RotateY, baseLeg5RotateZ); // -0.9599311F,
																	// 0.1570796F);
		leg6 = new ModelRenderer(this, 0, 0);
		leg6.addBox(-15F, -1F, 1F, 15, 2, 2);
		leg6.setRotationPoint(-7F, 20F, 6F + centre);
		leg6.setTextureSize(128, 128);
		leg6.mirror = true;
		setRotation(leg6, 0F, baseLeg6RotateY, baseLeg6RotateZ); // -2.181662F,
																	// 0.1570796F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		backbody.render(f5);
		body.render(f5);
		head.render(f5);
		bum.render(f5);
		head2.render(f5);
		// Front Right leg
		leg1.render(f5);
		// Front Left leg
		leg2.render(f5);
		// Middle Right leg
		leg3.render(f5);
		// Middle Left Leg
		leg4.render(f5);
		// Back Right Leg?
		leg5.render(f5);
		// Back Left Leg?
		leg6.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	float legSpeed = 0.7662F;

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);

		float varY = MathHelper.sin(par1 * legSpeed) * 1.4F * par2;
		float varY2 = MathHelper.sin(par1 * legSpeed) * 0.4F * par2;

		// Beetles walk like this right?
		this.leg1.rotateAngleY = baseLeg1RotateY + varY;
		this.leg5.rotateAngleY = baseLeg5RotateY + varY2;

		this.leg4.rotateAngleY = baseLeg4RotateY - varY;

		// Left
		this.leg2.rotateAngleY = baseLeg2RotateY + varY;
		this.leg6.rotateAngleY = baseLeg6RotateY + varY2;

		this.leg3.rotateAngleY = baseLeg3RotateY - varY;

		float varZ = Math.abs(MathHelper.cos(par1 * legSpeed)) * 0.8F * par2;
		float varZ2 = Math.abs(MathHelper.cos(par1 * legSpeed)) * 0.4F * par2;

		// + is clockwise front front view
		this.leg1.rotateAngleZ = baseLeg1RotateZ - varZ;
		this.leg5.rotateAngleZ = baseLeg5RotateZ - varZ2;

		this.leg4.rotateAngleZ = baseLeg4RotateZ + varZ;

		// Left Legs are inverted
		this.leg2.rotateAngleZ = baseLeg2RotateZ + varZ;
		this.leg6.rotateAngleZ = baseLeg6RotateZ + varZ;

		this.leg3.rotateAngleZ = baseLeg3RotateZ - varZ2;
	}

}
