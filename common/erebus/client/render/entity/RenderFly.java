package erebus.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityFly;
import erebus.client.model.entity.ModelFly;

@SideOnly(Side.CLIENT)
public class RenderFly extends RenderLiving
{
    /**
     * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
     * it seems a good match for a Flys size
     */
    private int renderedFlySize;

    public RenderFly()
    {
        super(new ModelFly(), 0.25F);
        this.renderedFlySize = ((ModelFly)this.mainModel).getFlySize();
    }

    public void func_82443_a(EntityFly par1EntityFly, double par2, double par4, double par6, float par8, float par9)
    {
        int var10 = ((ModelFly)this.mainModel).getFlySize();

        if (var10 != this.renderedFlySize)
        {
            this.renderedFlySize = var10;
            this.mainModel = new ModelFly();
        }

        super.doRenderLiving(par1EntityFly, par2, par4, par6, par8, par9);
    }

    protected void func_82442_a(EntityFly par1EntityFly, float par2)
    {
        GL11.glScalef(0.35F, 0.35F, 0.35F);
    }

    protected void func_82445_a(EntityFly par1EntityFly, double par2, double par4, double par6)
    {
        super.renderLivingAt(par1EntityFly, par2, par4, par6);
    }

    protected void func_82444_a(EntityFly par1EntityFly, float par2, float par3, float par4)
    {
        if (!par1EntityFly.getIsFlyHanging())
        {
            GL11.glTranslatef(0.0F, MathHelper.cos(par2 * 0.3F) * 0.1F, 0.0F);
        }
        else
        {
            GL11.glTranslatef(0.0F, -0.1F, 0.0F);
        }

        super.rotateCorpse(par1EntityFly, par2, par3, par4);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.func_82442_a((EntityFly)par1EntityLiving, par2);
    }

    protected void rotateCorpse(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
        this.func_82444_a((EntityFly)par1EntityLiving, par2, par3, par4);
    }

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        this.func_82445_a((EntityFly)par1EntityLiving, par2, par4, par6);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82443_a((EntityFly)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82443_a((EntityFly)par1Entity, par2, par4, par6, par8, par9);
    }
    
    private ResourceLocation resource = new ResourceLocation("erebus:textures/mob/Fly.png");
    @Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return resource;
	}
}
