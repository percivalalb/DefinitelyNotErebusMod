package erebus.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.client.model.entity.ModelBeetle;
import erebus.client.model.entity.ModelBeetleLarva;
import erebus.client.model.entity.ModelBlackWidow;
import erebus.client.model.entity.ModelBotFly;
import erebus.client.model.entity.ModelCentipede;
import erebus.client.model.entity.ModelMosquito;
import erebus.client.model.entity.ModelTarantula;
import erebus.client.model.entity.ModelVelvetWorm;
import erebus.client.model.entity.ModelWasp;
import erebus.client.render.entity.RenderBeetle;
import erebus.client.render.entity.RenderBeetleLarva;
import erebus.client.render.entity.RenderBlackWidow;
import erebus.client.render.entity.RenderBotFly;
import erebus.client.render.entity.RenderCentipede;
import erebus.client.render.entity.RenderFly;
import erebus.client.render.entity.RenderMosquito;
import erebus.client.render.entity.RenderTarantula;
import erebus.client.render.entity.RenderVelvetWorm;
import erebus.client.render.entity.RenderWasp;
import erebus.client.render.item.BambooItemRenderer;
import erebus.client.render.item.HollowLogItemRenderer;
import erebus.client.render.item.WaspSwordItemRenderer;
import erebus.client.render.tileentity.TileEntityRenderBamboo;
import erebus.client.render.tileentity.TileEntityRenderHollowLog;
import erebus.core.handler.ClientTickHandler;
import erebus.core.handler.PortalOverlayHandler;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFly;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;
import erebus.tileentity.TileEntityBamboo;
import erebus.tileentity.TileEntityHollowLog;

public class ClientProxy extends CommonProxy {
	
	public static Minecraft mc = FMLClientHandler.instance().getClient();
	public Item item;

	@Override
	public void registerRenderInformation()  {  
    
		MinecraftForge.EVENT_BUS.register(new PortalOverlayHandler());
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);    
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new RenderBeetle(new ModelBeetle(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFly.class, new RenderFly());
		//RenderingRegistry.registerEntityRenderingHandler(EntityGreenfly.class, new RenderGreenfly(new ModelGreenfly(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantula.class, new RenderTarantula(new ModelTarantula(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMosquito.class, new RenderMosquito(new ModelMosquito(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityVelvetWorm.class, new RenderVelvetWorm(new ModelVelvetWorm(), 0.6F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp(new ModelWasp(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCentipede.class, new RenderCentipede(new ModelCentipede(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetleLarva.class, new RenderBeetleLarva(new ModelBeetleLarva(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBotFly.class, new RenderBotFly(new ModelBotFly(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackWidow.class, new RenderBlackWidow(new ModelBlackWidow(), 0.3F));
		
		//Special Renderer
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHollowLog.class, new TileEntityRenderHollowLog());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBamboo.class, new TileEntityRenderBamboo());
		
		MinecraftForgeClient.registerItemRenderer(ModBlocks.hollowLogAcacia.blockID, new HollowLogItemRenderer(TileEntityRenderHollowLog.hollowLogResource));
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooCrate.blockID, new BambooItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.waspSword.itemID, new WaspSwordItemRenderer(WaspSwordItemRenderer.texture));
	}
}