package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.entity.EntityScorpion;
import erebus.client.model.entity.ModelScorpion;


public class RenderScorpion extends RenderLiving 
{
	protected ModelScorpion model;
	
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/ModelScorpion.png");

	public RenderScorpion(ModelScorpion par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
		model =((ModelScorpion)mainModel);
		
	}
    public void renderScorpion(EntityScorpion par1EntityScorpion, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityScorpion, par2, par4, par6, par8, par9);
    }

    @Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderScorpion((EntityScorpion)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderScorpion((EntityScorpion)par1Entity, par2, par4, par6, par8, par9);
    }
    
	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		scaleScorpion((EntityScorpion) entityliving, f);
	}
	
	protected void scaleScorpion(EntityScorpion entityScorpion, float f) {
		float f1 = 1.0F;
		this.shadowSize = 0.5F;
		GL11.glScalef(f1, f1, f1);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return Texture;
	}
	}


