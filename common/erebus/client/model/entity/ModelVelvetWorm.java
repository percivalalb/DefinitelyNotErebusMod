package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVelvetWorm extends ModelBase
{
  //fields
    ModelRenderer Back;
    ModelRenderer Center;
    ModelRenderer Centerback;
    ModelRenderer Centerfront;
    ModelRenderer Head;
    ModelRenderer Legcenterright;
    ModelRenderer Legcenterright2;
    ModelRenderer Legfrontright;
    ModelRenderer Legbackright;
    ModelRenderer Legcenterleft;
    ModelRenderer Legfrontright2;
    ModelRenderer Legfrontleft;
    ModelRenderer Legfrontleft2;
    ModelRenderer Legcenterleft2;
    ModelRenderer Legbackright2;
    ModelRenderer Legbackleft2;
    ModelRenderer Legbackleft;
    ModelRenderer Antenna;
    ModelRenderer rightAntenna;
  
  public ModelVelvetWorm()
  {
    textureWidth = 128;
    textureHeight = 256;
    
      Back = new ModelRenderer(this, 0, 60);
      Back.addBox(-6F, -6F, -8F, 12, 10, 12);
      Back.setRotationPoint(0F, 18F, 19F);
      Back.setTextureSize(128, 256);
      Back.mirror = true;
      setRotation(Back, 0F, 0F, 0F);
      Center = new ModelRenderer(this, 0, 0);
      Center.addBox(-8F, -8F, -8F, 16, 14, 16);
      Center.setRotationPoint(0F, 18F, 0F);
      Center.setTextureSize(128, 256);
      Center.mirror = true;
      setRotation(Center, 0F, 0F, 0F);
      Centerback = new ModelRenderer(this, 0, 32);
      Centerback.addBox(-7F, -7F, -10F, 14, 12, 14);
      Centerback.setRotationPoint(0F, 18F, 12F);
      Centerback.setTextureSize(128, 256);
      Centerback.mirror = true;
      setRotation(Centerback, 0F, 0F, 0F);
      Centerfront = new ModelRenderer(this, 0, 32);
      Centerfront.addBox(-7F, -7F, -8F, 14, 12, 14);
      Centerfront.setRotationPoint(0F, 18F, -8F);
      Centerfront.setTextureSize(128, 256);
      Centerfront.mirror = true;
      setRotation(Centerfront, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 60);
      Head.addBox(-6F, -6F, -8F, 12, 10, 12);
      Head.setRotationPoint(0F, 18F, -15F);
      Head.setTextureSize(128, 256);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Legcenterright = new ModelRenderer(this, 0, 0);
      Legcenterright.addBox(8F, 2F, -2F, 4, 6, 4);
      Legcenterright.setRotationPoint(0F, 16F, -4F);
      Legcenterright.setTextureSize(128, 256);
      Legcenterright.mirror = true;
      setRotation(Legcenterright, 0F, 3.141593F, 0F);
      Legcenterright2 = new ModelRenderer(this, 0, 0);
      Legcenterright2.addBox(8F, 2F, -2F, 4, 6, 4);
      Legcenterright2.setRotationPoint(0F, 16F, 4F);
      Legcenterright2.setTextureSize(128, 256);
      Legcenterright2.mirror = true;
      setRotation(Legcenterright2, 0F, 3.141593F, 0F);
      Legfrontright = new ModelRenderer(this, 0, 0);
      Legfrontright.addBox(6F, 2F, -2F, 4, 6, 4);
      Legfrontright.setRotationPoint(0F, 16F, -19F);
      Legfrontright.setTextureSize(128, 256);
      Legfrontright.mirror = true;
      setRotation(Legfrontright, 0F, 3.141593F, 0F);
      Legbackright = new ModelRenderer(this, 0, 0);
      Legbackright.addBox(6F, 2F, -2F, 4, 6, 4);
      Legbackright.setRotationPoint(0F, 16F, 19F);
      Legbackright.setTextureSize(128, 256);
      Legbackright.mirror = true;
      setRotation(Legbackright, 0F, 3.141593F, 0F);
      Legcenterleft = new ModelRenderer(this, 0, 0);
      Legcenterleft.addBox(8F, 2F, -2F, 4, 6, 4);
      Legcenterleft.setRotationPoint(0F, 16F, -4F);
      Legcenterleft.setTextureSize(128, 256);
      Legcenterleft.mirror = true;
      setRotation(Legcenterleft, 0F, 0F, 0F);
      Legfrontright2 = new ModelRenderer(this, 0, 0);
      Legfrontright2.addBox(7F, 2F, -2F, 4, 6, 4);
      Legfrontright2.setRotationPoint(0F, 16F, -12F);
      Legfrontright2.setTextureSize(128, 256);
      Legfrontright2.mirror = true;
      setRotation(Legfrontright2, 0F, 3.141593F, 0F);
      Legfrontleft = new ModelRenderer(this, 0, 0);
      Legfrontleft.addBox(6F, 2F, -2F, 4, 6, 4);
      Legfrontleft.setRotationPoint(0F, 16F, -19F);
      Legfrontleft.setTextureSize(128, 256);
      Legfrontleft.mirror = true;
      setRotation(Legfrontleft, 0F, 0F, 0F);
      Legfrontleft2 = new ModelRenderer(this, 0, 0);
      Legfrontleft2.addBox(7F, 2F, -2F, 4, 6, 4);
      Legfrontleft2.setRotationPoint(0F, 16F, -12F);
      Legfrontleft2.setTextureSize(128, 256);
      Legfrontleft2.mirror = true;
      setRotation(Legfrontleft2, 0F, 0F, 0F);
      Legcenterleft2 = new ModelRenderer(this, 0, 0);
      Legcenterleft2.addBox(8F, 2F, -2F, 4, 6, 4);
      Legcenterleft2.setRotationPoint(0F, 16F, 4F);
      Legcenterleft2.setTextureSize(128, 256);
      Legcenterleft2.mirror = true;
      setRotation(Legcenterleft2, 0F, 0F, 0F);
      Legbackright2 = new ModelRenderer(this, 0, 0);
      Legbackright2.addBox(7F, 2F, -2F, 4, 6, 4);
      Legbackright2.setRotationPoint(0F, 16F, 12F);
      Legbackright2.setTextureSize(128, 256);
      Legbackright2.mirror = true;
      setRotation(Legbackright2, 0F, 3.141593F, 0F);
      Legbackleft2 = new ModelRenderer(this, 0, 0);
      Legbackleft2.addBox(7F, 2F, -2F, 4, 6, 4);
      Legbackleft2.setRotationPoint(0F, 16F, 12F);
      Legbackleft2.setTextureSize(128, 256);
      Legbackleft2.mirror = true;
      setRotation(Legbackleft2, 0F, 0F, 0F);
      Legbackleft = new ModelRenderer(this, 0, 0);
      Legbackleft.addBox(6F, 2F, -2F, 4, 6, 4);
      Legbackleft.setRotationPoint(0F, 16F, 19F);
      Legbackleft.setTextureSize(128, 256);
      Legbackleft.mirror = true;
      setRotation(Legbackleft, 0F, 0F, 0F);
      Antenna = new ModelRenderer(this, 0, 84);
      Antenna.addBox(-1F, -1F, -10F, 2, 2, 10);
      Antenna.setRotationPoint(4F, 14F, -23F);
      Antenna.setTextureSize(128, 256);
      Antenna.mirror = true;
      setRotation(Antenna, 0F, 0F, 0F);
      Antenna.mirror = false;
      rightAntenna = new ModelRenderer(this, 0, 84);
      rightAntenna.addBox(-1F, -1F, -10F, 2, 2, 10);
      rightAntenna.setRotationPoint(-4F, 14F, -23F);
      rightAntenna.setTextureSize(128, 256);
      rightAntenna.mirror = true;
      setRotation(rightAntenna, 0F, 0F, 0F);
  }
  
  @Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Back.render(f5);
    Center.render(f5);
    Centerback.render(f5);
    Centerfront.render(f5);
    Head.render(f5);
    Legcenterright.render(f5);
    Legcenterright2.render(f5);
    Legfrontright.render(f5);
    Legbackright.render(f5);
    Legcenterleft.render(f5);
    Legfrontright2.render(f5);
    Legfrontleft.render(f5);
    Legfrontleft2.render(f5);
    Legcenterleft2.render(f5);
    Legbackright2.render(f5);
    Legbackleft2.render(f5);
    Legbackleft.render(f5);
    Antenna.render(f5);
    rightAntenna.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
