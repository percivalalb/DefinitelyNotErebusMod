package erebus.core.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.client.fx.EntityRepellentFX;
import erebus.client.model.entity.ModelAnimatedBlock;
import erebus.client.model.entity.ModelAnimatedChest;
import erebus.client.model.entity.ModelAntlion;
import erebus.client.model.entity.ModelBeetle;
import erebus.client.model.entity.ModelBeetleLarva;
import erebus.client.model.entity.ModelBlackWidow;
import erebus.client.model.entity.ModelBombardierBeetle;
import erebus.client.model.entity.ModelBotFly;
import erebus.client.model.entity.ModelCentipede;
import erebus.client.model.entity.ModelFirebrat;
import erebus.client.model.entity.ModelGlowWorm;
import erebus.client.model.entity.ModelGrasshopper;
import erebus.client.model.entity.ModelLocust;
import erebus.client.model.entity.ModelMosquito;
import erebus.client.model.entity.ModelMoth;
import erebus.client.model.entity.ModelScorpion;
import erebus.client.model.entity.ModelSolifuge;
import erebus.client.model.entity.ModelTarantula;
import erebus.client.model.entity.ModelVelvetWorm;
import erebus.client.model.entity.ModelWasp;
import erebus.client.model.item.ModelWaspDagger;
import erebus.client.render.block.BlockBambooCropRender;
import erebus.client.render.block.BlockHollowLogRender;
import erebus.client.render.entity.RenderAnimatedBlock;
import erebus.client.render.entity.RenderAnimatedChest;
import erebus.client.render.entity.RenderAntlion;
import erebus.client.render.entity.RenderBeetle;
import erebus.client.render.entity.RenderBeetleLarva;
import erebus.client.render.entity.RenderBlackWidow;
import erebus.client.render.entity.RenderBombardierBeetle;
import erebus.client.render.entity.RenderBotFly;
import erebus.client.render.entity.RenderCentipede;
import erebus.client.render.entity.RenderFirebrat;
import erebus.client.render.entity.RenderFly;
import erebus.client.render.entity.RenderGlowWorm;
import erebus.client.render.entity.RenderGrasshopper;
import erebus.client.render.entity.RenderLocust;
import erebus.client.render.entity.RenderMosquito;
import erebus.client.render.entity.RenderMoth;
import erebus.client.render.entity.RenderScorpion;
import erebus.client.render.entity.RenderSolifuge;
import erebus.client.render.entity.RenderTarantula;
import erebus.client.render.entity.RenderVelvetWorm;
import erebus.client.render.entity.RenderWasp;
import erebus.client.render.item.BambooCrateItemRenderer;
import erebus.client.render.item.BambooTorchItemRenderer;
import erebus.client.render.item.ItemErebusAltarRenderer;
import erebus.client.render.item.ItemGlowingJarRenderer;
import erebus.client.render.item.ItemUmberFurnaceRenderer;
import erebus.client.render.item.WandOfAnimationItemRenderer;
import erebus.client.render.item.WaspDaggerItemRenderer;
import erebus.client.render.item.WaspSwordItemRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarHealingRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarLightningRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarRepairRenderer;
import erebus.client.render.tileentity.TileEntityErebusAltarXPRenderer;
import erebus.client.render.tileentity.TileEntityGlowingJarRenderer;
import erebus.client.render.tileentity.TileEntityRenderBambooCrate;
import erebus.client.render.tileentity.TileEntitySpawnerRender;
import erebus.core.handler.ClientTickHandler;
import erebus.core.handler.PortalOverlayHandler;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFirebrat;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityMoth;
import erebus.entity.EntityScorpion;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;
import erebus.entity.EntityWaspDagger;
import erebus.network.packet.PacketParticle;
import erebus.tileentity.TileEntityBambooCrate;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarHealing;
import erebus.tileentity.TileEntityErebusAltarLightning;
import erebus.tileentity.TileEntityErebusAltarRepair;
import erebus.tileentity.TileEntityErebusAltarXP;
import erebus.tileentity.TileEntityGlowingJar;
import erebus.tileentity.TileEntitySpawner;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		MinecraftForge.EVENT_BUS.register(new PortalOverlayHandler());

		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);

		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new RenderBeetle(new ModelBeetle(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFly.class, new RenderFly());
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantula.class, new RenderTarantula(new ModelTarantula(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMosquito.class, new RenderMosquito(new ModelMosquito(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityVelvetWorm.class, new RenderVelvetWorm(new ModelVelvetWorm(), 0.6F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp(new ModelWasp(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCentipede.class, new RenderCentipede(new ModelCentipede(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetleLarva.class, new RenderBeetleLarva(new ModelBeetleLarva(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBotFly.class, new RenderBotFly(new ModelBotFly(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackWidow.class, new RenderBlackWidow(new ModelBlackWidow(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityScorpion.class, new RenderScorpion(new ModelScorpion(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrasshopper.class, new RenderGrasshopper(new ModelGrasshopper(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLocust.class, new RenderLocust(new ModelLocust(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySolifuge.class, new RenderSolifuge(new ModelSolifuge(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMoth.class, new RenderMoth(new ModelMoth(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFirebrat.class, new RenderFirebrat(new ModelFirebrat(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlion.class, new RenderAntlion(new ModelAntlion(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWaspDagger.class, new WaspDaggerItemRenderer(new ModelWaspDagger(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBlock.class, new RenderAnimatedBlock(new ModelAnimatedBlock(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedChest.class, new RenderAnimatedChest(new ModelAnimatedChest(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGlowWorm.class, new RenderGlowWorm(new ModelGlowWorm(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBombardierBeetle.class, new RenderBombardierBeetle(new ModelBombardierBeetle(), 0.3F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltar.class, new TileEntityErebusAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarLightning.class, new TileEntityErebusAltarLightningRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarHealing.class, new TileEntityErebusAltarHealingRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarXP.class, new TileEntityErebusAltarXPRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarRepair.class, new TileEntityErebusAltarRepairRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooCrate.class, new TileEntityRenderBambooCrate());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawner.class, new TileEntitySpawnerRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlowingJar.class, new TileEntityGlowingJarRenderer());
		RenderingRegistry.registerBlockHandler(bambooCropRenderID, new BlockBambooCropRender());
		RenderingRegistry.registerBlockHandler(hollowLogRenderID, new BlockHollowLogRender());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooCrate.blockID, new BambooCrateItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltar.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltarLightning.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltarHealing.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltarXP.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.erebusAltarRepair.blockID, new ItemErebusAltarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.waspSword.itemID, new WaspSwordItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.umberFurnace.blockID, new ItemUmberFurnaceRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.waspDagger.itemID, new WaspDaggerItemRenderer(new ModelWaspDagger(), 0.3F));
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooTorch.blockID, new BambooTorchItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.wandOfAnimation.itemID, new WandOfAnimationItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.glowingJar.blockID, new ItemGlowingJarRenderer());
	}

	@Override
	public void handleParticlePacket(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		EffectRenderer eff = Minecraft.getMinecraft().effectRenderer;
		byte particleType = data.readByte();

		if (particleType == PacketParticle.BEETLE_LARVA_SQUISH) {
			EntityLivingBase e = (EntityLivingBase) player.worldObj.getEntityByID(data.readInt());

			for (int countparticles = 0; countparticles <= 200; ++countparticles)
				eff.addEffect(new EntityBreakingFX(player.worldObj, e.posX + (e.getRNG().nextDouble() - 0.5D) * e.width, e.posY + e.getRNG().nextDouble() * e.height - e.yOffset, e.posZ + (e.getRNG().nextDouble() - 0.5D) * e.width, Item.slimeBall));
		} else if (particleType == PacketParticle.BEETLE_LARVA_EAT || particleType == PacketParticle.GRASSHOPPER_EAT) { // x,y,z,blockID,meta
			EntityLivingBase e = (EntityLivingBase) player.worldObj.getEntityByID(data.readInt());
			int woodX = data.readInt(), woodY = data.readInt(), woodZ = data.readInt();
			Block block = Block.blocksList[data.readInt()];
			int blockMeta = data.readByte();

			if (e == null || block == null)
				return;

			for (int countparticles = 0; countparticles <= 50; ++countparticles)
				eff.addEffect(new EntityDiggingFX(player.worldObj, woodX + 0.5D + (e.getRNG().nextDouble() - 0.5D) * e.width, woodY + 0.2D + e.getRNG().nextDouble() * e.height - e.yOffset, woodZ + 0.5D + (e.getRNG().nextDouble() - 0.5D) * e.width, e.getRNG().nextGaussian() * 0.5D, e.getRNG()
				.nextGaussian() * 0.01D, e.getRNG().nextGaussian() * 0.5D, block, blockMeta));
		}
	}

	@Override
	public void spawnCustomParticle(String particleName, World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		EntityFX fx = null;

		if (particleName.equals("repellent"))
			fx = new EntityRepellentFX(world, x, y, z, 0.0F, 0.0F, 0.0F);

		if (fx != null)
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}
}