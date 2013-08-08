package erebus.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHollowLog extends ModelBase
{
  //fields
    ModelRenderer BottomLog;
    ModelRenderer SideL;
    ModelRenderer SideR;
    ModelRenderer TopLog;
    ModelRenderer MossyStuff;
    ModelRenderer MossyStuff2;
  
  public ModelHollowLog()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      BottomLog = new ModelRenderer(this, 0, 0);
      BottomLog.addBox(0F, 0F, 0F, 16, 1, 16);
      BottomLog.setRotationPoint(-8F, 23F, -8F);
      BottomLog.setTextureSize(128, 64);
      BottomLog.mirror = true;
      setRotation(BottomLog, 0F, 0F, 0F);
      SideL = new ModelRenderer(this, 64, 0);
      SideL.addBox(0F, 0F, 0F, 1, 14, 16);
      SideL.setRotationPoint(7F, 9F, -8F);
      SideL.setTextureSize(128, 64);
      SideL.mirror = true;
      setRotation(SideL, 0F, 0F, 0F);
      SideR = new ModelRenderer(this, 64, 0);
      SideR.addBox(0F, 0F, 0F, 1, 14, 16);
      SideR.setRotationPoint(-8F, 9F, -8F);
      SideR.setTextureSize(128, 64);
      SideR.mirror = true;
      setRotation(SideR, 0F, 0F, 0F);
      TopLog = new ModelRenderer(this, 0, 17);
      TopLog.addBox(0F, 0F, 0F, 16, 1, 16);
      TopLog.setRotationPoint(-8F, 8F, -8F);
      TopLog.setTextureSize(128, 64);
      TopLog.mirror = true;
      setRotation(TopLog, 0F, 0F, 0F);
      MossyStuff = new ModelRenderer(this, 0, 40);
      MossyStuff.addBox(0F, 0F, -1F, 14, 14, 1);
      MossyStuff.setRotationPoint(-7F, 9F, -7F);
      MossyStuff.setTextureSize(128, 64);
      MossyStuff.mirror = true;
      setRotation(MossyStuff, 0F, 0F, 0F);
      MossyStuff2 = new ModelRenderer(this, 0, 40);
      MossyStuff2.addBox(0F, 0F, 0F, 14, 14, 1);
      MossyStuff2.setRotationPoint(-7F, 9F, 7F);
      MossyStuff2.setTextureSize(128, 64);
      MossyStuff2.mirror = true;
      setRotation(MossyStuff2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    BottomLog.render(f5);
    SideL.render(f5);
    SideR.render(f5);
    TopLog.render(f5);
    MossyStuff.render(f5);
    MossyStuff2.render(f5);
  }
  
  /**
   * This method renders out all parts of the chest model.
   */
  public void renderModel(float f5)
  {
    BottomLog.render(f5);
    SideL.render(f5);
    SideR.render(f5);
    TopLog.render(f5);
    MossyStuff.render(f5);
    MossyStuff2.render(f5);
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
  }

}
