package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityLocust;

public class ModelLocust extends ModelBase
{
	//fields
    ModelRenderer LAnt;
    ModelRenderer RAnt;
    ModelRenderer LEye;
    ModelRenderer REye;
    ModelRenderer Head1;
    ModelRenderer Head2;
    ModelRenderer Head3;
    ModelRenderer HeadLBot;
    ModelRenderer HeadCBot;
    ModelRenderer HeadRBot;
    ModelRenderer Thorax1;
    ModelRenderer Thorax2;
    ModelRenderer Ab1;
    ModelRenderer Ab2;
    ModelRenderer LFL1;
    ModelRenderer LFL2;
    ModelRenderer LFL3;
    ModelRenderer LFL4;
    ModelRenderer RFL1;
    ModelRenderer RFL2;
    ModelRenderer RFL3;
    ModelRenderer RFL4;
    ModelRenderer LML1;
    ModelRenderer LML2;
    ModelRenderer LML3;
    ModelRenderer LML4;
    ModelRenderer RML1;
    ModelRenderer RML2;
    ModelRenderer RML3;
    ModelRenderer RML4;
    ModelRenderer LBL1;
    ModelRenderer LBL2;
    ModelRenderer LBL3;
    ModelRenderer LBL4;
    ModelRenderer LBL5;
    ModelRenderer LBL6;
    ModelRenderer RBL1;
    ModelRenderer RBL2;
    ModelRenderer RBL3;
    ModelRenderer RBL4;
    ModelRenderer RBL5;
    ModelRenderer RBL6;
    ModelRenderer LFWing;
    ModelRenderer RFWing;
    ModelRenderer LBWing;
    ModelRenderer RBWing;

  
  public ModelLocust()
  {
	  textureWidth = 64;
	    textureHeight = 128;
	    
	      LAnt = new ModelRenderer(this, 0, 0);
	      LAnt.addBox(1.5F, -10.5F, -4F, 1, 7, 1);
	      LAnt.setRotationPoint(0F, 16F, -9F);
	      LAnt.setTextureSize(64, 128);
	      LAnt.mirror = true;
	      setRotation(LAnt, 0.1745329F, 0F, 0F);
	      RAnt = new ModelRenderer(this, 0, 0);
	      RAnt.addBox(-1.5F, -10.5F, -4F, 1, 7, 1);
	      RAnt.setRotationPoint(0F, 16F, -9F);
	      RAnt.setTextureSize(64, 128);
	      RAnt.mirror = true;
	      setRotation(RAnt, 0.1745329F, 0F, 0F);
	      LEye = new ModelRenderer(this, 5, 0);
	      LEye.addBox(2.5F, -2.5F, -3.5F, 1, 3, 2);
	      LEye.setRotationPoint(0F, 16F, -9F);
	      LEye.setTextureSize(64, 128);
	      LEye.mirror = true;
	      setRotation(LEye, -0.1745329F, 0F, 0F);
	      REye = new ModelRenderer(this, 45, 0);
	      REye.addBox(-3.5F, -2.5F, -3.5F, 1, 3, 2);
	      REye.setRotationPoint(0F, 16F, -9F);
	      REye.setTextureSize(64, 128);
	      REye.mirror = true;
	      setRotation(REye, -0.1745329F, 0F, 0F);
	      Head1 = new ModelRenderer(this, 22, 13);
	      Head1.addBox(-2F, -3.5F, -4F, 4, 1, 3);
	      Head1.setRotationPoint(0F, 16F, -9F);
	      Head1.setTextureSize(64, 128);
	      Head1.mirror = true;
	      setRotation(Head1, -0.1745329F, 0F, 0F);
	      Head2 = new ModelRenderer(this, 24, 8);
	      Head2.addBox(-1.5F, -2F, -6F, 3, 3, 1);
	      Head2.setRotationPoint(0F, 16F, -9F);
	      Head2.setTextureSize(64, 128);
	      Head2.mirror = true;
	      setRotation(Head2, -0.1745329F, 0F, 0F);
	      Head3 = new ModelRenderer(this, 19, 18);
	      Head3.addBox(-2.5F, -2.5F, -5F, 5, 4, 5);
	      Head3.setRotationPoint(0F, 16F, -9F);
	      Head3.setTextureSize(64, 128);
	      Head3.mirror = true;
	      setRotation(Head3, -0.1745329F, 0F, 0F);
	      HeadLBot = new ModelRenderer(this, 12, 0);
	      HeadLBot.addBox(1.5F, 1.5F, -4.5F, 1, 2, 3);
	      HeadLBot.setRotationPoint(0F, 16F, -9F);
	      HeadLBot.setTextureSize(64, 128);
	      HeadLBot.mirror = true;
	      setRotation(HeadLBot, -0.1745329F, 0F, 0F);
	      HeadCBot = new ModelRenderer(this, 21, 0);
	      HeadCBot.addBox(-1.5F, 1.5F, -5F, 3, 3, 4);
	      HeadCBot.setRotationPoint(0F, 16F, -9F);
	      HeadCBot.setTextureSize(64, 128);
	      HeadCBot.mirror = true;
	      setRotation(HeadCBot, -0.1745329F, 0F, 0F);
	      HeadRBot = new ModelRenderer(this, 36, 0);
	      HeadRBot.addBox(-2.5F, 1.5F, -4.5F, 1, 2, 3);
	      HeadRBot.setRotationPoint(0F, 16F, -9F);
	      HeadRBot.setTextureSize(64, 128);
	      HeadRBot.mirror = true;
	      setRotation(HeadRBot, -0.1745329F, 0F, 0F);
	      Thorax1 = new ModelRenderer(this, 18, 28);
	      Thorax1.addBox(-3F, -3.5F, 0F, 6, 6, 5);
	      Thorax1.setRotationPoint(0F, 16F, -9F);
	      Thorax1.setTextureSize(64, 128);
	      Thorax1.mirror = true;
	      setRotation(Thorax1, -0.0872665F, 0F, 0F);
	      Thorax2 = new ModelRenderer(this, 13, 40);
	      Thorax2.addBox(-2.5F, -3F, 5F, 5, 6, 9);
	      Thorax2.setRotationPoint(0F, 16F, -9F);
	      Thorax2.setTextureSize(64, 128);
	      Thorax2.mirror = true;
	      setRotation(Thorax2, -0.0872665F, 0F, 0F);
	      Ab1 = new ModelRenderer(this, 28, 42);
	      Ab1.addBox(-2F, -2F, 14F, 4, 4, 14);
	      Ab1.setRotationPoint(0F, 16F, -9F);
	      Ab1.setTextureSize(64, 128);
	      Ab1.mirror = true;
	      setRotation(Ab1, -0.0872665F, 0F, 0F);
	      Ab2 = new ModelRenderer(this, 13, 56);
	      Ab2.addBox(-1.5F, -1.5F, 28F, 3, 3, 1);
	      Ab2.setRotationPoint(0F, 16F, -9F);
	      Ab2.setTextureSize(64, 128);
	      Ab2.mirror = true;
	      setRotation(Ab2, -0.0872665F, 0F, 0F);
	      LFL1 = new ModelRenderer(this, 52, 0);
	      LFL1.addBox(0F, -1F, -1F, 4, 2, 2);
	      LFL1.setRotationPoint(2F, 17F, -7F);
	      LFL1.setTextureSize(64, 128);
	      LFL1.mirror = true;
	      setRotation(LFL1, 0F, 0F, -0.3490659F);
	      LFL2 = new ModelRenderer(this, 43, 6);
	      LFL2.addBox(3F, 0F, -1F, 2, 6, 2);
	      LFL2.setRotationPoint(2F, 17F, -7F);
	      LFL2.setTextureSize(64, 128);
	      LFL2.mirror = true;
	      setRotation(LFL2, 0F, 0F, -0.4363323F);
	      LFL3 = new ModelRenderer(this, 52, 12);
	      LFL3.addBox(1.5F, 6.5F, -0.5F, 2, 2, 1);
	      LFL3.setRotationPoint(2F, 17F, -7F);
	      LFL3.setTextureSize(64, 128);
	      LFL3.mirror = true;
	      setRotation(LFL3, 0F, 0F, -0.698F);
	      LFL4 = new ModelRenderer(this, 52, 18);
	      LFL4.addBox(0.5F, 8F, -0.5F, 1, 4, 1);
	      LFL4.setRotationPoint(2F, 17F, -7F);
	      LFL4.setTextureSize(64, 128);
	      LFL4.mirror = true;
	      setRotation(LFL4, 0F, 0F, -0.8726646F);
	      RFL1 = new ModelRenderer(this, 52, 0);
	      RFL1.addBox(-4F, -1F, -1F, 4, 2, 2);
	      RFL1.setRotationPoint(-2F, 17F, -7F);
	      RFL1.setTextureSize(64, 128);
	      RFL1.mirror = true;
	      setRotation(RFL1, 0F, 0F, 0.3490659F);
	      RFL2 = new ModelRenderer(this, 43, 6);
	      RFL2.addBox(-5F, 0F, -1F, 2, 6, 2);
	      RFL2.setRotationPoint(-2F, 17F, -7F);
	      RFL2.setTextureSize(64, 128);
	      RFL2.mirror = true;
	      setRotation(RFL2, 0F, 0F, 0.4363323F);
	      RFL3 = new ModelRenderer(this, 52, 12);
	      RFL3.addBox(-3.5F, 6.5F, -0.5F, 2, 2, 1);
	      RFL3.setRotationPoint(-2F, 17F, -7F);
	      RFL3.setTextureSize(64, 128);
	      RFL3.mirror = true;
	      setRotation(RFL3, 0F, 0F, 0.6981317F);
	      RFL4 = new ModelRenderer(this, 52, 18);
	      RFL4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
	      RFL4.setRotationPoint(-2F, 17F, -7F);
	      RFL4.setTextureSize(64, 128);
	      RFL4.mirror = true;
	      setRotation(RFL4, 0F, 0F, 0.8726646F);
	      LML1 = new ModelRenderer(this, 52, 0);
	      LML1.addBox(0F, -1F, -1F, 4, 2, 2);
	      LML1.setRotationPoint(2F, 17F, -2F);
	      LML1.setTextureSize(64, 128);
	      LML1.mirror = true;
	      setRotation(LML1, 0F, 0F, -0.3490659F);
	      LML2 = new ModelRenderer(this, 43, 6);
	      LML2.addBox(3F, 0F, -1F, 2, 6, 2);
	      LML2.setRotationPoint(2F, 17F, -2F);
	      LML2.setTextureSize(64, 128);
	      LML2.mirror = true;
	      setRotation(LML2, 0F, 0F, -0.4363323F);
	      LML3 = new ModelRenderer(this, 52, 12);
	      LML3.addBox(1.5F, 6.5F, -0.5F, 2, 2, 1);
	      LML3.setRotationPoint(2F, 17F, -2F);
	      LML3.setTextureSize(64, 128);
	      LML3.mirror = true;
	      setRotation(LML3, 0F, 0F, -0.6981317F);
	      LML4 = new ModelRenderer(this, 52, 18);
	      LML4.addBox(0.5F, 8F, -0.5F, 1, 4, 1);
	      LML4.setRotationPoint(2F, 17F, -2F);
	      LML4.setTextureSize(64, 128);
	      LML4.mirror = true;
	      setRotation(LML4, 0F, 0F, -0.8726646F);
	      RML1 = new ModelRenderer(this, 52, 0);
	      RML1.addBox(-4F, -1F, -1F, 4, 2, 2);
	      RML1.setRotationPoint(-2F, 17F, -2F);
	      RML1.setTextureSize(64, 128);
	      RML1.mirror = true;
	      setRotation(RML1, 0F, 0F, 0.3490659F);
	      RML2 = new ModelRenderer(this, 43, 6);
	      RML2.addBox(-5F, 0F, -1F, 2, 6, 2);
	      RML2.setRotationPoint(-2F, 17F, -2F);
	      RML2.setTextureSize(64, 128);
	      RML2.mirror = true;
	      setRotation(RML2, 0F, 0F, 0.4363323F);
	      RML3 = new ModelRenderer(this, 52, 12);
	      RML3.addBox(-3.5F, 6.5F, -0.5F, 2, 2, 1);
	      RML3.setRotationPoint(-2F, 17F, -2F);
	      RML3.setTextureSize(64, 128);
	      RML3.mirror = true;
	      setRotation(RML3, 0F, 0F, 0.6981317F);
	      RML4 = new ModelRenderer(this, 52, 18);
	      RML4.addBox(-1.5F, 8F, -0.5F, 1, 4, 1);
	      RML4.setRotationPoint(-2F, 17F, -2F);
	      RML4.setTextureSize(64, 128);
	      RML4.mirror = true;
	      setRotation(RML4, 0F, 0F, 0.8726646F);
	      LBL1 = new ModelRenderer(this, 0, 34);
	      LBL1.addBox(0.5F, -4F, -1.5F, 3, 6, 4);
	      LBL1.setRotationPoint(2F, 18F, 3F);
	      LBL1.setTextureSize(64, 128);
	      LBL1.mirror = true;
	      setRotation(LBL1, -0.5235988F, 0F, 0F);
	      LBL2 = new ModelRenderer(this, 0, 24);
	      LBL2.addBox(1F, -10F, -1F, 2, 6, 3);
	      LBL2.setRotationPoint(2F, 18F, 3F);
	      LBL2.setTextureSize(64, 128);
	      LBL2.mirror = true;
	      setRotation(LBL2, -0.5235988F, 0F, 0F);
	      LBL3 = new ModelRenderer(this, 9, 8);
	      LBL3.addBox(0.5F, -13F, -1.5F, 3, 3, 4);
	      LBL3.setRotationPoint(2F, 18F, 3F);
	      LBL3.setTextureSize(64, 128);
	      LBL3.mirror = true;
	      setRotation(LBL3, -0.5235988F, 0F, 0F);
	      LBL4 = new ModelRenderer(this, 0, 9);
	      LBL4.addBox(1F, -9F, 6F, 2, 12, 2);
	      LBL4.setRotationPoint(2F, 18F, 3F);
	      LBL4.setTextureSize(64, 128);
	      LBL4.mirror = true;
	      setRotation(LBL4, 0F, 0F, 0F);
	      LBL5 = new ModelRenderer(this, 52, 5);
	      LBL5.addBox(1.5F, -4F, 7F, 1, 2, 4);
	      LBL5.setRotationPoint(2F, 18F, 3F);
	      LBL5.setTextureSize(64, 128);
	      LBL5.mirror = true;
	      setRotation(LBL5, -0.6981317F, 0F, 0F);
	      LBL6 = new ModelRenderer(this, 41, 18);
	      LBL6.addBox(1.5F, -2F, 10.5F, 1, 1, 4);
	      LBL6.setRotationPoint(2F, 18F, 3F);
	      LBL6.setTextureSize(64, 128);
	      LBL6.mirror = true;
	      setRotation(LBL6, -0.5235988F, 0F, 0F);
	      RBL1 = new ModelRenderer(this, 0, 34);
	      RBL1.addBox(-3.5F, -4F, -1.5F, 3, 6, 4);
	      RBL1.setRotationPoint(-2F, 18F, 3F);
	      RBL1.setTextureSize(64, 128);
	      RBL1.mirror = true;
	      setRotation(RBL1, -0.5235988F, 0F, 0F);
	      RBL2 = new ModelRenderer(this, 0, 24);
	      RBL2.addBox(-3F, -10F, -1F, 2, 6, 3);
	      RBL2.setRotationPoint(-2F, 18F, 3F);
	      RBL2.setTextureSize(64, 128);
	      RBL2.mirror = true;
	      setRotation(RBL2, -0.5235988F, 0F, 0F);
	      RBL3 = new ModelRenderer(this, 9, 8);
	      RBL3.addBox(-3.5F, -13F, -1.5F, 3, 3, 4);
	      RBL3.setRotationPoint(-2F, 18F, 3F);
	      RBL3.setTextureSize(64, 128);
	      RBL3.mirror = true;
	      setRotation(RBL3, -0.5235988F, 0F, 0F);
	      RBL4 = new ModelRenderer(this, 0, 9);
	      RBL4.addBox(-3F, -9F, 6F, 2, 12, 2);
	      RBL4.setRotationPoint(-2F, 18F, 3F);
	      RBL4.setTextureSize(64, 128);
	      RBL4.mirror = true;
	      setRotation(RBL4, 0F, 0F, 0F);
	      RBL5 = new ModelRenderer(this, 52, 5);
	      RBL5.addBox(-2.5F, -4F, 7F, 1, 2, 4);
	      RBL5.setRotationPoint(-2F, 18F, 3F);
	      RBL5.setTextureSize(64, 128);
	      RBL5.mirror = true;
	      setRotation(RBL5, -0.6981317F, 0F, 0F);
	      RBL6 = new ModelRenderer(this, 41, 18);
	      RBL6.addBox(-2.5F, -2F, 10.5F, 1, 1, 4);
	      RBL6.setRotationPoint(-2F, 18F, 3F);
	      RBL6.setTextureSize(64, 128);
	      RBL6.mirror = true;
	      setRotation(RBL6, -0.5235988F, 0F, 0F);
	      LFWing = new ModelRenderer(this, 0, 62);
	      LFWing.addBox(-2.5F, 0F, 0F, 5, 1, 19);
	      LFWing.setRotationPoint(0F, 13F, -5F);
	      LFWing.setTextureSize(64, 128);
	      LFWing.mirror = true;
	      setRotation(LFWing, 0F, 0F, 0F);
	      RFWing = new ModelRenderer(this, 0, 62);
	      RFWing.addBox(-2.5F, 0F, 0F, 5, 1, 19);
	      RFWing.setRotationPoint(0F, 13F, -5F);
	      RFWing.setTextureSize(64, 128);
	      RFWing.mirror = true;
	      setRotation(RFWing, 0F, 0F, 0F);
	      LBWing = new ModelRenderer(this, 0, 62);
	      LBWing.addBox(-2.5F, 0F, 0F, 5, 1, 19);
	      LBWing.setRotationPoint(0F, 13F, -5F);
	      LBWing.setTextureSize(64, 128);
	      LBWing.mirror = true;
	      setRotation(LBWing, 0F, 0F, 0F);
	      RBWing = new ModelRenderer(this, 0, 62);
	      RBWing.addBox(-2.5F, 0F, 0F, 5, 1, 19);
	      RBWing.setRotationPoint(0F, 13F, -5F);
	      RBWing.setTextureSize(64, 128);
	      RBWing.mirror = true;
	      setRotation(RBWing, 0F, 0F, 0F);
	  }
  
  public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
	super.render(par1Entity, par2, par3, par4, par5, par6, par7);
	setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
	 	LAnt.render(par7);
	    RAnt.render(par7);
	    LEye.render(par7);
	    REye.render(par7);
	    Head1.render(par7);
	    Head2.render(par7);
	    Head3.render(par7);
	    HeadLBot.render(par7);
	    HeadCBot.render(par7);
	    HeadRBot.render(par7);
	    Thorax1.render(par7);;
	    Thorax2.render(par7);
	    Ab1.render(par7);
	    Ab2.render(par7);
	    LFL1.render(par7);
	    LFL2.render(par7);
	    LFL3.render(par7);
	    LFL4.render(par7);
	    RFL1.render(par7);
	    RFL2.render(par7);
	    RFL3.render(par7);
	    RFL4.render(par7);
	    LML1.render(par7);
	    LML2.render(par7);
	    LML3.render(par7);
	    LML4.render(par7);
	    RML1.render(par7);
	    RML2.render(par7);
	    RML3.render(par7);
	    RML4.render(par7);
	    LBL1.render(par7);
	    LBL2.render(par7);
	    LBL3.render(par7);
	    LBL4.render(par7);
	    LBL5.render(par7);
	    LBL6.render(par7);
	    RBL1.render(par7);
	    RBL2.render(par7);
	    RBL3.render(par7);
	    RBL4.render(par7);
	    RBL5.render(par7);
	    RBL6.render(par7);

	    GL11.glPushMatrix();
	    GL11.glEnable(3042);
	    float transparency = 0.6F;
	    GL11.glBlendFunc(770, 771);
	    GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);

	    LFWing.render(par7);
	    RFWing.render(par7);
	    LBWing.render(par7);
	    RBWing.render(par7);
	    
	    GL11.glDisable(3042);
	    GL11.glPopMatrix();  

  }

  
  private void setRotation(ModelRenderer model, float  x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
      this.Head1.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.Head2.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.Head3.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.HeadLBot.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.HeadRBot.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.HeadCBot.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.RAnt.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.LAnt.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.LEye.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.REye.rotateAngleY = par4 / (180F / (float)Math.PI);
      
      this.Head1.rotateAngleX = par5 / (180F / (float)Math.PI) - 0.1745329F;
      this.Head2.rotateAngleX = par5 / (180F / (float)Math.PI) - 0.1745329F;
      this.Head3.rotateAngleX = par5 / (180F / (float)Math.PI) - 0.1745329F;
      this.HeadLBot.rotateAngleX = par5 / (180F / (float)Math.PI) - 0.1745329F;
      this.HeadRBot.rotateAngleX = par5 / (180F / (float)Math.PI) - 0.1745329F;
      this.HeadCBot.rotateAngleX = par5 / (180F / (float)Math.PI) - 0.1745329F;
      this.RAnt.rotateAngleX = par5 / (180F / (float)Math.PI) + 0.1745329F;
      this.LAnt.rotateAngleX = par5 / (180F / (float)Math.PI) + 0.1745329F;
      this.LEye.rotateAngleX = par5 / (180F / (float)Math.PI) - 0.1745329F;
      this.REye.rotateAngleX = par5 / (180F / (float)Math.PI) - 0.1745329F;
      
      this.LML1.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      this.LML2.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      this.LML3.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      this.LML4.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      this.LFL1.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      this.LFL2.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      this.LFL3.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      this.LFL4.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      
      this.RML1.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      this.RML2.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      this.RML3.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      this.RML4.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      this.RFL1.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      this.RFL2.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      this.RFL3.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      this.RFL4.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      
      EntityLocust var8 = (EntityLocust)par7Entity;
      
      if(!var8.onGround)
      {	  
    	  LBL4.setRotationPoint(2F, 22.0F, 14F);
    	  LBL5.setRotationPoint(2F, 22.0F, 14F);
    	  LBL6.setRotationPoint(2F, 21.0F, 14F);
          RBL4.setRotationPoint(-2F, 22.5F, 14F);
          RBL5.setRotationPoint(-2F, 22.5F, 14F);
          RBL6.setRotationPoint(-2F, 21.0F, 14F);
    	 
          this.LBL1.rotateAngleX = -1.0F;
          this.LBL2.rotateAngleX = -1.0F;
          this.LBL3.rotateAngleX = -1.0F;
          this.LBL4.rotateAngleX = 0.8F;
          this.LBL5.rotateAngleX = 0.0F;
          this.LBL6.rotateAngleX = 0.0F;
          
          this.RBL1.rotateAngleX = -1.0F;
          this.RBL2.rotateAngleX = -1.0F;
          this.RBL3.rotateAngleX = -1.0F;
          this.RBL4.rotateAngleX = 0.8F;
          this.RBL5.rotateAngleX = 0.0F;
          this.RBL6.rotateAngleX = 0.0F;

          this.LFL1.rotateAngleX = -0.6F;
          this.LFL2.rotateAngleX = -0.6F;
          this.LFL3.rotateAngleX = -0.6F;
          this.LFL4.rotateAngleX = -0.6F;
          this.RFL1.rotateAngleX = -0.6F;
          this.RFL2.rotateAngleX = -0.6F;
          this.RFL3.rotateAngleX = -0.6F;
          this.RFL4.rotateAngleX = -0.6F;
          this.LML1.rotateAngleX = 0.6F;
          this.LML2.rotateAngleX = 0.6F;
          this.LML3.rotateAngleX = 0.6F;
          this.LML4.rotateAngleX = 0.6F;
          this.RML1.rotateAngleX = 0.6F;
          this.RML2.rotateAngleX = 0.6F;
          this.RML3.rotateAngleX = 0.6F;
          this.RML4.rotateAngleX = 0.6F;
          
          this.RFWing.rotateAngleY = -1.7F;
          this.RBWing.rotateAngleY = -0.9F;
          
          this.LFWing.rotateAngleY = 1.7F;
          this.LBWing.rotateAngleY = 0.9F;
          
          float x = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;       
          this.RFWing.rotateAngleX = x;
          this.RBWing.rotateAngleX = x;
          
          this.LFWing.rotateAngleX = x;
          this.LBWing.rotateAngleX = x;
  	
      }
      if(var8.onGround)
      {	
          this.RFWing.rotateAngleY = 0F;
          this.RBWing.rotateAngleY = 0F;
          
          this.LFWing.rotateAngleY = 0F;
          this.LBWing.rotateAngleY = 0F;
          
          this.RFWing.rotateAngleX = 0F;
          this.RBWing.rotateAngleX = 0F;
          
          this.LFWing.rotateAngleX = 0F;
          this.LBWing.rotateAngleX = 0F;
         
    	  LBL4.setRotationPoint(2F, 18F, 3F);
    	  LBL5.setRotationPoint(2F, 18F, 3F);
    	  LBL6.setRotationPoint(2F, 18F, 3F);
    	  
    	  RBL4.setRotationPoint(-2F, 18F, 3F);
    	  RBL5.setRotationPoint(-2F, 18F, 3F);
    	  RBL6.setRotationPoint(-2F, 18F, 3F);
    	  
	  this.LBL1.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2 -0.5235988F;
      this.LBL2.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2 -0.5235988F;
      this.LBL3.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2 -0.5235988F;
      this.LBL4.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2;
      this.LBL5.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2-0.6981317F;
      this.LBL6.rotateAngleX = MathHelper.cos(par1 * 2.0F + (float)Math.PI) * 0.7F * par2-0.5235988F;
    	
      this.RBL1.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2 -0.5235988F;
      this.RBL2.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2 -0.5235988F;
      this.RBL3.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2 -0.5235988F;
      this.RBL4.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2;
      this.RBL5.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2-0.6981317F;
      this.RBL6.rotateAngleX = MathHelper.cos(par1 * 2.0F) * 0.7F * par2-0.5235988F;
      }
 
  }
}

