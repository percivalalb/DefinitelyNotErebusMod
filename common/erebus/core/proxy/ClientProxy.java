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
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.client.fx.EntityRepellentFX;
import erebus.client.model.entity.*;
import erebus.client.render.block.*;
import erebus.client.render.entity.*;
import erebus.client.render.item.*;
import erebus.client.render.tileentity.*;
import erebus.core.handler.ClientTickHandler;
import erebus.core.handler.KeyBindingHandler;
import erebus.core.handler.PortalOverlayHandler;
import erebus.entity.*;
import erebus.entity.effect.EntityErebusLightningBolt;
import erebus.network.packet.PacketParticle;
import erebus.tileentity.*;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerKeyHandlers() {
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}

	@Override
	public void registerRenderInformation() {
		MinecraftForge.EVENT_BUS.register(new PortalOverlayHandler());

		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);

		RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new RenderBeetle());
		RenderingRegistry.registerEntityRenderingHandler(EntityFly.class, new RenderFly());
		RenderingRegistry.registerEntityRenderingHandler(EntityTarantula.class, new RenderTarantula());
		RenderingRegistry.registerEntityRenderingHandler(EntityMosquito.class, new RenderMosquito());
		RenderingRegistry.registerEntityRenderingHandler(EntityVelvetWorm.class, new RenderVelvetWorm());
		RenderingRegistry.registerEntityRenderingHandler(EntityWasp.class, new RenderWasp());
		RenderingRegistry.registerEntityRenderingHandler(EntityCentipede.class, new RenderCentipede());
		RenderingRegistry.registerEntityRenderingHandler(EntityBeetleLarva.class, new RenderBeetleLarva());
		RenderingRegistry.registerEntityRenderingHandler(EntityBotFly.class, new RenderBotFly());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackWidow.class, new RenderBlackWidow());
		RenderingRegistry.registerEntityRenderingHandler(EntityScorpion.class, new RenderScorpion());
		RenderingRegistry.registerEntityRenderingHandler(EntityGrasshopper.class, new RenderGrasshopper());
		RenderingRegistry.registerEntityRenderingHandler(EntityLocust.class, new RenderLocust());
		RenderingRegistry.registerEntityRenderingHandler(EntitySolifuge.class, new RenderSolifuge());
		RenderingRegistry.registerEntityRenderingHandler(EntityMoth.class, new RenderMoth());
		RenderingRegistry.registerEntityRenderingHandler(EntityFirebrat.class, new RenderFirebrat(new ModelFirebrat(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAntlion.class, new RenderAntlion(new ModelAntlion(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWaspDagger.class, new WaspDaggerItemRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBlock.class, new RenderAnimatedBlock(new ModelAnimatedBlock(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedChest.class, new RenderAnimatedChest(new ModelAnimatedChest(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnimatedBambooCrate.class, new RenderAnimatedBlock(new ModelAnimatedBlock(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGlowWorm.class, new RenderGlowWorm(new ModelGlowWorm(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBombardierBeetle.class, new RenderBombardierBeetle(new ModelBombardierBeetle(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityScytodes.class, new RenderScytodes(new ModelScytodes(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMoneySpider.class, new RenderMoneySpider(new ModelScytodes(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityUmberGolem.class, new RenderUmberGolem(new ModelUmberGolem(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPrayingMantis.class, new RenderPrayingMantis(new ModelPrayingMantis(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJumpingSpider.class, new RenderJumpingSpider(new ModelJumpingSpider(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireAnt.class, new RenderFireAnt(new ModelFireAnt(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWebSling.class, new RenderWebSling());
		RenderingRegistry.registerEntityRenderingHandler(EntityErebusLightningBolt.class, new RenderErebusLightningBolt());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltar.class, new TileEntityErebusAltarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarLightning.class, new TileEntityErebusAltarLightningRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarHealing.class, new TileEntityErebusAltarHealingRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarXP.class, new TileEntityErebusAltarXPRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityErebusAltarRepair.class, new TileEntityErebusAltarRepairRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooCrate.class, new TileEntityRenderBambooCrate());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpawner.class, new TileEntitySpawnerRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlowingJar.class, new TileEntityGlowingJarRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLadder.class, new TileEntityLadderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooBridge.class, new TileEntityBambooBridgeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmberGolemStatue.class, new TileEntityUmberGolemStatueRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPetrifiedWoodChest.class, new TileEntityPetrifiedWoodChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBones.class, new TileEntityBoneBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBambooPole.class, new TileEntityBambooPoleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtenderThingy.class, new TileEntityExtenderThingyRenderer());

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
		MinecraftForgeClient.registerItemRenderer(ModItems.waspDagger.itemID, new WaspDaggerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooTorch.blockID, new BambooTorchItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.wandOfAnimation.itemID, new WandOfAnimationItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.glowingJar.blockID, new ItemGlowingJarRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.scorpionPincer.itemID, new ScorpionPincerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.umberGolemStatue.blockID, new ItemUmberGolemStatueRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.petrifiedWoodChest.blockID, new ItemPetrifiedWoodChestRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.blockBones.blockID, new ItemBoneBlockRenderer());
		MinecraftForgeClient.registerItemRenderer(ModItems.webSlinger.itemID, new WebSlingerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooBridge.blockID, new BambooBridgeItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.extenderThingy.blockID, new ExtenderThingyItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.bambooPole.blockID, new BambooPoleItemRenderer());

	}

	@Override
	public void handleParticlePacket(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		EffectRenderer eff = Minecraft.getMinecraft().effectRenderer;
		byte particleType = data.readByte();

		if (particleType == PacketParticle.BEETLE_LARVA_SQUISH) {
			EntityLivingBase e = (EntityLivingBase) player.worldObj.getEntityByID(data.readInt());

			for (int countparticles = 0; countparticles <= 200; ++countparticles)
				eff.addEffect(new EntityBreakingFX(player.worldObj, e.posX + (e.getRNG().nextDouble() - 0.5D) * e.width, e.posY + e.getRNG().nextDouble() * e.height - e.yOffset, e.posZ + (e.getRNG().nextDouble() - 0.5D) * e.width, Item.slimeBall));
		}
		else if (particleType == PacketParticle.BEETLE_LARVA_AND_GRASSHOPPER_EAT) { // x,y,z,blockID,meta
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