package erebus.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityTarantula;

@SideOnly(Side.CLIENT)
public class RenderTarantula extends RenderLiving {

	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/mob/Tarantula.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/mob/Exaturqoise.png");

	public RenderTarantula(ModelBase model, float par2) {
		super(model, par2);
	}

	public void renderTarantula(EntityTarantula entityTarantula, double par2, double par4, double par6, float par8, float par9) {
		super.doRenderLiving(entityTarantula, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRenderLiving(EntityLiving entityLiving, double par2, double par4, double par6, float par8, float par9) {
		renderTarantula((EntityTarantula) entityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9) {
		renderTarantula((EntityTarantula) entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityTarantula tarantula = (EntityTarantula) entity;
		if (tarantula.skin <= 4)
			return resource2;
		else
			return resource1;
	}
}
