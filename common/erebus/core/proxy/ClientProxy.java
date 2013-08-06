package erebus.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import erebus.ClientErebusTickHandler;
import erebus.PortalOverlayRenderer;
import erebus.mod_Erebus;
import erebus.Block.TileEntityHollowLog;
import erebus.Entity.EntityBeetle;
import erebus.Entity.EntityBeetleLarva;
import erebus.Entity.EntityCentipede;
import erebus.Entity.EntityFly;
import erebus.Entity.EntityGreenfly;
import erebus.Entity.EntityMosquito;
import erebus.Entity.EntityTarantula;
import erebus.Entity.EntityVelvetWorm;
import erebus.Entity.EntityWasp;
import erebus.Entity.ModelBeetle;
import erebus.Entity.ModelBeetleLarva;
import erebus.Entity.ModelCentipede;
import erebus.Entity.ModelGreenfly;
import erebus.Entity.ModelMosquito;
import erebus.Entity.ModelTarantula;
import erebus.Entity.ModelVelvetWorm;
import erebus.Entity.ModelWasp;
import erebus.Entity.RenderBeetle;
import erebus.Entity.RenderBeetleLarva;
import erebus.Entity.RenderCentipede;
import erebus.Entity.RenderFly;
import erebus.Entity.RenderGreenfly;
import erebus.Entity.RenderMosquito;
import erebus.Entity.RenderTarantula;
import erebus.Entity.RenderVelvetWorm;
import erebus.Entity.RenderWasp;
import erebus.client.render.item.HollowLogItemRenderer;
import erebus.client.render.tileentity.TileEntityRenderHollowLog;

public class ClientProxy extends CommonProxy {
	
	public static Minecraft mc = FMLClientHandler.instance().getClient();
	public Item item;

	@Override
	public void registerRenderInformation()  {  
    
		MinecraftForge.EVENT_BUS.register(new PortalOverlayRenderer());
		TickRegistry.registerTickHandler(new ClientErebusTickHandler(), Side.CLIENT);    
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetleLarva.class, new RenderBeetleLarva(new ModelBeetleLarva(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new RenderBeetle(new ModelBeetle(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFly.class, new RenderFly());
		//RenderingRegistry.registerEntityRenderingHandler(EntityGreenfly.class, new RenderGreenfly(new ModelGreenfly(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantula.class, new RenderTarantula(new ModelTarantula(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMosquito.class, new RenderMosquito(new ModelMosquito(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityVelvetWorm.class, new RenderVelvetWorm(new ModelVelvetWorm(), 0.6F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp(new ModelWasp(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCentipede.class, new RenderCentipede(new ModelCentipede(), 0.5F));

		//Special Renderer
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHollowLog.class, new TileEntityRenderHollowLog());

		MinecraftForgeClient.registerItemRenderer(mod_Erebus.hollowLogAcacia.blockID, new HollowLogItemRenderer(TileEntityRenderHollowLog.hollowLogResource));
	}
}