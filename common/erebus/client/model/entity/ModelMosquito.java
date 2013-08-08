package erebus.client.model.entity;

import org.lwjgl.opengl.GL11;

import erebus.Entity.EntityMosquito;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMosquito extends ModelBase
{
	 //fields
    ModelRenderer Tail;
    ModelRenderer Body;
    ModelRenderer Head1;
    ModelRenderer LegRight2;
    ModelRenderer LegLeft3;
    ModelRenderer LegLeft1;
    ModelRenderer LegLeft2;
    ModelRenderer ArmLeft1;
    ModelRenderer LegRight3;
    ModelRenderer LegRight1;
    ModelRenderer ArmLeft2;
    ModelRenderer ArmRight1;
    ModelRenderer ArmRight2;
    ModelRenderer Head4;
    ModelRenderer Head2;
    ModelRenderer Head3;
    ModelRenderer WingLeft;
    ModelRenderer WingRight;
  
  public ModelMosquito()
  {
    textureWidth = 128;
    textureHeight = 128;
    //loads default model
    defaultModel();
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    EntityMosquito mosquito = (EntityMosquito)entity;
    GL11.glPushMatrix();
    Tail.render(f5);
    Head1.render(f5);
    LegRight2.render(f5);
    LegLeft3.render(f5);
    LegLeft1.render(f5);
    LegLeft2.render(f5);
    ArmLeft1.render(f5);
    LegRight3.render(f5);
    LegRight1.render(f5);
    ArmLeft2.render(f5);
    ArmRight1.render(f5);
    ArmRight2.render(f5);
    WingLeft.render(f5);
    WingRight.render(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glScalef(1.0F + (mosquito.getBloodConsumed()/10F), 1.0F ,1.0F);
    Body.render(f5);
    GL11.glScalef(1.0F -(mosquito.getBloodConsumed()/10F), 1.0F ,1.0F);
    GL11.glScalef(mosquito.suckFloat, 1.0F ,1.0F);
    Head4.render(f5);
    Head2.render(f5);
    Head3.render(f5);
    GL11.glPopMatrix();
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    //makes the choice between sitting and default model by checking if it is riding an entity
    if(entity.ridingEntity != null)
    {
    	sittingModel();
    }
    else
    {
    	defaultModel();
    }
    
    
	//wing animation
    EntityMosquito mosquito = (EntityMosquito)entity;
    WingLeft.rotateAngleZ = mosquito.wingFloat;
    WingRight.rotateAngleZ =-mosquito.wingFloat;

  }
  
  public void defaultModel()
  {
	  Tail = new ModelRenderer(this, 0, 41);
      Tail.addBox(-3F, -3F, 16F, 6, 6, 40);
      Tail.setRotationPoint(0F, 0F, 0F);
      Tail.setTextureSize(128, 128);
      Tail.mirror = true;
      setRotation(Tail, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 0);
      Body.addBox(-8F, -8F, 0F, 16, 16, 16);
      Body.setRotationPoint(0F, 0F, 0F);
      Body.setTextureSize(128, 128);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Head1 = new ModelRenderer(this, 0, 61);
      Head1.addBox(-5F, 0F, 0F, 10, 10, 10);
      Head1.setRotationPoint(0F, 0F, 5F);
      Head1.setTextureSize(128, 128);
      Head1.mirror = true;
      setRotation(Head1, -2.356194F, 0F, 0F);
      LegRight2 = new ModelRenderer(this, 80, 6);
      LegRight2.addBox(-1F, 2F, -23F, 2, 16, 2);
      LegRight2.setRotationPoint(-6F, 6F, 16F);
      LegRight2.setTextureSize(128, 128);
      LegRight2.mirror = false;
      setRotation(LegRight2, 0.7853982F, 3.141593F, 0F);
      LegLeft3 = new ModelRenderer(this, 64, 26);
      LegLeft3.addBox(-0.5F, 3.5F, -51F, 1, 1, 24);
      LegLeft3.setRotationPoint(6F, 6F, 16F);
      LegLeft3.setTextureSize(128, 128);
      LegLeft3.mirror = true;
      setRotation(LegLeft3, 1.308997F, 3.141593F, 0F);
      LegLeft1 = new ModelRenderer(this, 52, 53);
      LegLeft1.addBox(-2F, -2F, -24F, 4, 4, 24);
      LegLeft1.setRotationPoint(6F, 6F, 16F);
      LegLeft1.setTextureSize(128, 128);
      LegLeft1.mirror = true;
      setRotation(LegLeft1, 0.7853982F, 3.141593F, 0F);
      LegLeft2 = new ModelRenderer(this, 80, 6);
      LegLeft2.addBox(-1F, 2F, -23F, 2, 16, 2);
      LegLeft2.setRotationPoint(6F, 6F, 16F);
      LegLeft2.setTextureSize(128, 128);
      LegLeft2.mirror = false;
      setRotation(LegLeft2, 0.7853982F, 3.141593F, 0F);
      ArmLeft1 = new ModelRenderer(this, 64, 0);
      ArmLeft1.addBox(-1F, -1F, -24F, 2, 2, 24);
      ArmLeft1.setRotationPoint(6F, 8F, 4F);
      ArmLeft1.setTextureSize(128, 128);
      ArmLeft1.mirror = true;
      setRotation(ArmLeft1, 1.570796F, 0F, 0F);
      LegRight3 = new ModelRenderer(this, 64, 26);
      LegRight3.addBox(-0.5F, 3.5F, -51F, 1, 1, 24);
      LegRight3.setRotationPoint(-6F, 6F, 16F);
      LegRight3.setTextureSize(128, 128);
      LegRight3.mirror = false;
      setRotation(LegRight3, 1.308997F, 3.141593F, 0F);
      LegRight1 = new ModelRenderer(this, 52, 53);
      LegRight1.addBox(-2F, -2F, -24F, 4, 4, 24);
      LegRight1.setRotationPoint(-6F, 6F, 16F);
      LegRight1.setTextureSize(128, 128);
      LegRight1.mirror = false;
      setRotation(LegRight1, 0.7853982F, 3.141593F, 0F);
      ArmLeft2 = new ModelRenderer(this, 64, 0);
      ArmLeft2.addBox(-1F, -1F, -24F, 2, 2, 24);
      ArmLeft2.setRotationPoint(6F, 8F, 8F);
      ArmLeft2.setTextureSize(128, 128);
      ArmLeft2.mirror = true;
      setRotation(ArmLeft2, 1.570796F, 0F, 0F);
      ArmRight1 = new ModelRenderer(this, 64, 0);
      ArmRight1.addBox(-1F, -1F, -24F, 2, 2, 24);
      ArmRight1.setRotationPoint(-6F, 8F, 4F);
      ArmRight1.setTextureSize(128, 128);
      ArmRight1.mirror = false;
      setRotation(ArmRight1, 1.570796F, 0F, 0F);
      ArmRight2 = new ModelRenderer(this, 64, 0);
      ArmRight2.addBox(-1F, -1F, -24F, 2, 2, 24);
      ArmRight2.setRotationPoint(-6F, 8F, 8F);
      ArmRight2.setTextureSize(128, 128);
      ArmRight2.mirror = false;
      setRotation(ArmRight2, 1.570796F, 0F, 0F);
      Head4 = new ModelRenderer(this, 16, 35);
      Head4.addBox(-1F, 10F, 0F, 2, 8, 2);
      Head4.setRotationPoint(0F, 4F, -4F);
      Head4.setTextureSize(128, 128);
      Head4.mirror = true;
      setRotation(Head4, -0.3490659F, 0F, 0F);
      Head2 = new ModelRenderer(this, 0, 45);
      Head2.addBox(-4F, -4F, -4F, 8, 8, 8);
      Head2.setRotationPoint(0F, 4F, -4F);
      Head2.setTextureSize(128, 128);
      Head2.mirror = true;
      setRotation(Head2, 0F, 0F, 0F);
      Head3 = new ModelRenderer(this, 0, 33);
      Head3.addBox(-2F, 3F, -3F, 4, 8, 4);
      Head3.setRotationPoint(0F, 4F, -4F);
      Head3.setTextureSize(128, 128);
      Head3.mirror = true;
      setRotation(Head3, -0.1745329F, 0F, 0F);
      WingLeft = new ModelRenderer(this, 0, 87);
      WingLeft.addBox(-16F, 0F, 0F, 16, 1, 32);
      WingLeft.setRotationPoint(-4F, -9F, 4F);
      WingLeft.setTextureSize(128, 128);
      WingLeft.mirror = false;
      setRotation(WingLeft, 0F, 0F, 0F);
      WingRight = new ModelRenderer(this, 0, 87);
      WingRight.addBox(0F, 0F, 0F, 16, 1, 32);
      WingRight.setRotationPoint(4F, -9F, 4F);
      WingRight.setTextureSize(128, 128);
      WingRight.mirror = true;
      setRotation(WingRight, 0F, 0F, 0F);
  }
  
  public void sittingModel()
  {
	  Tail = new ModelRenderer(this, 0, 41);
      Tail.addBox(-3F, -3F, 16F, 6, 6, 40);
      Tail.setRotationPoint(0F, 0F, 0F);
      Tail.setTextureSize(128, 128);
      Tail.mirror = true;
      setRotation(Tail, -0.3141593F, 0F, 0F);
      Head1 = new ModelRenderer(this, 0, 61);
      Head1.addBox(-5F, 0F, 0F, 10, 10, 10);
      Head1.setRotationPoint(0F, 0F, 5F);
      Head1.setTextureSize(128, 128);
      Head1.mirror = true;
      setRotation(Head1, -2.129302F, 0F, 0F);
      LegRight2 = new ModelRenderer(this, 80, 6);
      LegRight2.addBox(-1F, -13F, -20F, 2, 16, 2);
      LegRight2.setRotationPoint(-6F, 6F, 16F);
      LegRight2.setTextureSize(128, 128);
      LegRight2.mirror = false;
      setRotation(LegRight2, 1.884956F, 3.141593F, 0.2617994F);
      LegLeft3 = new ModelRenderer(this, 64, 26);
      LegLeft3.addBox(-9.5F, -4.5F, -41F, 1, 1, 24);
      LegLeft3.setRotationPoint(6F, 6F, 16F);
      LegLeft3.setTextureSize(128, 128);
      LegLeft3.mirror = true;
      setRotation(LegLeft3, 2.303835F, 3.167191F, 0.2617994F);
      LegLeft1 = new ModelRenderer(this, 52, 53);
      LegLeft1.addBox(-2F, -2F, -24F, 4, 4, 24);
      LegLeft1.setRotationPoint(6F, 6F, 16F);
      LegLeft1.setTextureSize(128, 128);
      LegLeft1.mirror = true;
      setRotation(LegLeft1, 1.22173F, 3.141593F, -0.2617994F);
      LegLeft2.mirror = true;
      LegLeft2 = new ModelRenderer(this, 80, 6);
      LegLeft2.addBox(-1F, -13F, -20F, 2, 16, 2);
      LegLeft2.setRotationPoint(6F, 6F, 16F);
      LegLeft2.setTextureSize(128, 128);
      LegLeft2.mirror = true;
      setRotation(LegLeft2, 1.884956F, 3.141593F, -0.2617994F);
      LegRight3 = new ModelRenderer(this, 64, 26);
      LegRight3.addBox(8.5F, -4.5F, -41F, 1, 1, 24);
      LegRight3.setRotationPoint(-6F, 6F, 16F);
      LegRight3.setTextureSize(128, 128);
      LegRight3.mirror = false;
      setRotation(LegRight3, 2.303835F, 3.141593F, -0.2617994F);
      LegRight1 = new ModelRenderer(this, 52, 53);
      LegRight1.addBox(-2F, -2F, -24F, 4, 4, 24);
      LegRight1.setRotationPoint(-6F, 6F, 16F);
      LegRight1.setTextureSize(128, 128);
      LegRight1.mirror = false;
      setRotation(LegRight1, 1.22173F, 3.141593F, 0.2617994F);
      Head4 = new ModelRenderer(this, 16, 35);
      Head4.addBox(-1F, 10F, 0F, 2, 8, 2);
      Head4.setRotationPoint(0F, 4F, -4F);
      Head4.setTextureSize(128, 128);
      Head4.mirror = true;
      setRotation(Head4, 0.0174533F, 0F, 0F);
      Head2 = new ModelRenderer(this, 0, 45);
      Head2.addBox(-4F, -4F, -2F, 8, 8, 8);
      Head2.setRotationPoint(0F, 4F, -4F);
      Head2.setTextureSize(128, 128);
      Head2.mirror = true;
      setRotation(Head2, -0.2268928F, 0F, 0F);
      Head3 = new ModelRenderer(this, 0, 33);
      Head3.addBox(-2F, 3F, -3F, 4, 8, 4);
      Head3.setRotationPoint(0F, 4F, -4F);
      Head3.setTextureSize(128, 128);
      Head3.mirror = true;
      setRotation(Head3, 0.1745329F, 0F, 0F);
  }

}