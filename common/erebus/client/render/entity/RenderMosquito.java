package erebus.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelMosquito;
import erebus.entity.EntityMosquito;

@SideOnly(Side.CLIENT)
public class RenderMosquito extends RenderLiving {
	protected ModelMosquito model;
	private final ResourceLocation Texture = new ResourceLocation("erebus:textures/mob/Mosquito.png");

	public RenderMosquito(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelMosquito) mainModel;
	}

	public void renderMosquito(EntityMosquito par1EntityMosquito, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityMosquito, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderMosquito((EntityMosquito) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderMosquito((EntityMosquito) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(0.0F, -1.4F, -0.5F);
		if (entityliving.ridingEntity != null)
			GL11.glTranslatef(0.0F, 0.0F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Texture;
	}
}
