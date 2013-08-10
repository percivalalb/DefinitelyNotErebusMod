package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityMosquito;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMosquito extends RenderLiving
{
    public RenderMosquito(ModelBase par1ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par1ModelBase);
    }

    public void renderLivingMosquito(EntityMosquito par1EntityMosquito, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityMosquito, par2, par4, par6, par8, par9);
    }


    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingMosquito((EntityMosquito)par1EntityLiving, par2, par4, par6, par8, par9);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingMosquito((EntityMosquito)par1Entity, par2, par4, par6, par8, par9);
    }
    
    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
    	EntityMosquito mosquito = (EntityMosquito)entityliving;
    	GL11.glScalef(0.5F, 0.5F, 0.5F);
    	GL11.glTranslatef(0.0F, -1.4F, -0.5F);
    	if(mosquito.ridingEntity != null)
    	{
    		GL11.glTranslatef(0.0F, 0.0F, 0.5F);
    	}
    }
    
    private ResourceLocation resource = new ResourceLocation("erebus:textures/mob/Mosquito.png");
    @Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return resource;
	}
}
