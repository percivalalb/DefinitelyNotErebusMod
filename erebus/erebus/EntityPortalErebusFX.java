package erebus;

import java.util.Random;

import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityPortalErebusFX extends EntityFX
{

 public EntityPortalErebusFX(World world, double d, double d1, double d2, 
         double d3, double d4, double d5)
 {
     super(world, d, d1, d2, d3, d4, d5);
     motionX = d3;
     motionY = d4;
     motionZ = d5;
     field_4086_p = posX = d;
     field_4085_q = posY = d1;
     field_4084_r = posZ = d2;
     float f = rand.nextFloat() * 0.6F + 0.4F;
     field_4083_a = particleScale = rand.nextFloat() * 0.2F + 0.5F;
     particleRed = 1.0F;
     particleBlue = 1.0F;
     particleRed = 1.0F;
     particleMaxAge = (int)(Math.random() * 10D) + 40;
     noClip = true;
     setParticleTextureIndex((int)(Math.random() * 8D));
 }

 public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
 {
     float f6 = ((float)particleAge + f) / (float)particleMaxAge;
     f6 = 1.0F - f6;
     f6 *= f6;
     f6 = 1.0F - f6;
     particleScale = field_4083_a * f6;
     super.renderParticle(tessellator, f, f1, f2, f3, f4, f5);
 }

 public float getBrightness(float par1)
 {
     float var2 = super.getBrightness(par1);
     float var3 = (float)this.particleAge / (float)this.particleMaxAge;
     var3 = var3 * var3 * var3 * var3;
     return var2 * (1.0F - var3) + var3;
 }

 public void onUpdate()
 {
     prevPosX = posX;
     prevPosY = posY;
     prevPosZ = posZ;
     float f = (float)particleAge / (float)particleMaxAge;
     float f1 = f;
     f = -f + f * f * 2.0F;
     f = 1.0F - f;
     posX = field_4086_p + motionX * (double)f;
     posY = field_4085_q + motionY * (double)f + (double)(1.0F - f1);
     posZ = field_4084_r + motionZ * (double)f;
     if(particleAge++ >= particleMaxAge)
     {
         setDead();
     }
 }

 private float field_4083_a;
 private double field_4086_p;
 private double field_4085_q;
 private double field_4084_r;
}
