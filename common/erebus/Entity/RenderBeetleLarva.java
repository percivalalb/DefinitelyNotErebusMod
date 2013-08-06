package erebus.Entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderBeetleLarva extends RenderLiving
{
	protected ModelBeetleLarva model;
	private static final ResourceLocation Texture = new ResourceLocation("erebus:textures/entities/larva.png");

	public RenderBeetleLarva(ModelBeetleLarva par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
		model =((ModelBeetleLarva)mainModel);
		
	}
    public void renderBeetleLarva(EntityBeetleLarva par1EntityBeetleLarva, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityBeetleLarva, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBeetleLarva((EntityBeetleLarva)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBeetleLarva((EntityBeetleLarva)par1Entity, par2, par4, par6, par8, par9);
    }

    protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		scaleBeetleLarva((EntityBeetleLarva) entityliving, f);
		EntityBeetleLarva entityBeetleLarva = (EntityBeetleLarva) entityliving;
	}

	protected void scaleBeetleLarva(EntityBeetleLarva entityBeetleLarva, float f)
	{
		float f1 = 1.0F;
		this.shadowSize = 0.3F;
		GL11.glScalef(f1, f1, f1);	
	}
	
	@Override
	protected ResourceLocation func_110775_a(Entity entity)
	{
		return Texture;
	}
	}

