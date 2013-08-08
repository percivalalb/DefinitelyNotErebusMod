package erebus.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import erebus.Entity.EntityBeetle;


@SideOnly(Side.CLIENT)
public class RenderBeetle extends RenderLiving
{
    public RenderBeetle(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderBeetle(EntityBeetle par1EntityBeetle, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityBeetle, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBeetle((EntityBeetle)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBeetle((EntityBeetle)par1Entity, par2, par4, par6, par8, par9);
    }

    private ResourceLocation resource1 = new ResourceLocation("erebus:textures/mob/Beetleblue.png");
    private ResourceLocation resource2 = new ResourceLocation("erebus:textures/mob/Beetlebrown.png");
    private ResourceLocation resource3 = new ResourceLocation("erebus:textures/mob/Beetlegreen.png");
    private ResourceLocation resource4 = new ResourceLocation("erebus:textures/mob/Beetlered.png");
    private ResourceLocation resource5 = new ResourceLocation("erebus:textures/mob/Beetletan.png");
    @Override
	protected ResourceLocation func_110775_a(Entity entity) {
    	EntityBeetle beetle = (EntityBeetle)entity;
    	switch(beetle.skin) {
		case 0:
			return resource1;
		case 1:
			return resource2;
		case 2:
			return resource3;
		case 3:
			return resource4;
		case 4:
			return resource5;     	
		}

		return resource1;
	}
}
