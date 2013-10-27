package erebus;

import java.io.File;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.sound.AmbientMusicManager;
import erebus.client.sound.EntitySoundEvent;
import erebus.client.sound.LightningAltarNoises;
import erebus.core.handler.CommonTickHandler;
import erebus.core.handler.ConfigurationHandler;
import erebus.core.handler.KeyBindingHandler;
import erebus.core.handler.PlayerTeleportHandler;
import erebus.core.handler.VersionCheckTickHandler;
import erebus.core.helper.LogHelper;
import erebus.core.proxy.CommonProxy;
import erebus.creativetab.CreativeTabErebus;
import erebus.creativetab.CreativeTabErebusBlock;
import erebus.creativetab.CreativeTabErebusGear;
import erebus.creativetab.CreativeTabErebusItem;
import erebus.lib.Reference;
import erebus.network.PacketHandler;
import erebus.recipes.BCFacadeManager;
import erebus.recipes.RecipeHandler;
import erebus.utils.VersionHelper;
import erebus.world.WorldProviderErebus;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES)
@NetworkMod(channels = { Reference.CHANNEL }, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
public class ErebusMod {

	@SidedProxy(clientSide = Reference.SP_CLIENT, serverSide = Reference.SP_SERVER)
	public static CommonProxy proxy;

	@Instance(Reference.MOD_ID)
	public static ErebusMod instance;

	public static EnumArmorMaterial armorEXOSKELETON = EnumHelper.addArmorMaterial("EXOSKELETON", 11, new int[] { 2, 4, 3, 2 }, 15);
	public static EnumArmorMaterial armorJADE = EnumHelper.addArmorMaterial("JADE", 24, new int[] { 3, 7, 5, 2 }, 15);
	public static EnumToolMaterial toolJADE = EnumHelper.addToolMaterial("JADE", 2, 863, 10.0F, 2.0F, 18);
	public static EnumToolMaterial toolJADEPAXEL = EnumHelper.addToolMaterial("JADEPAXEL", 2, 1079, 8.0F, 4.0F, 14);
	public static EnumToolMaterial toolCAVEMANCLUB = EnumHelper.addToolMaterial("CAVEMANCLUB", 0, 131, 4.0F, 2.0F, 12);
	public static EnumToolMaterial weaponWaspDagger = EnumHelper.addToolMaterial("WASPDAGGER", 0, 16, 1.0F, 0.0F, 12);

	public static CreativeTabErebus tabErebusBlock = new CreativeTabErebusBlock("erebus.block");
	public static CreativeTabErebus tabErebusItem = new CreativeTabErebusItem("erebus.item");
	public static CreativeTabErebus tabErebusGear = new CreativeTabErebusGear("erebus.gear");
	
	public static PlayerTeleportHandler teleportHandler = new PlayerTeleportHandler();

	@EventHandler
	@SideOnly(Side.CLIENT)
	public void preInitClient(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EntitySoundEvent());
		AmbientMusicManager.register();
	}

	@EventHandler
	public void preInitServer(FMLPreInitializationEvent event) {
		LogHelper.init();

		ConfigurationHandler.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID + ".cfg"));

		if (ConfigurationHandler.shouldDoVersionCheck) {
			VersionHelper.execute();
			TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);
		}

		ModBlocks.init();
		ModItems.init();
		ModEntities.init();

		GameRegistry.registerPlayerTracker(teleportHandler);
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);

		DimensionManager.registerProviderType(ConfigurationHandler.erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(ConfigurationHandler.erebusDimensionID, ConfigurationHandler.erebusDimensionID);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerTileEntities();
		proxy.registerRenderInformation();

		ModBiomes.init();
		RecipeHandler.init();

		MinecraftForge.EVENT_BUS.register(ModBlocks.erebusSapling);
		MinecraftForge.EVENT_BUS.register(ModBlocks.quickSand);
		MinecraftForge.EVENT_BUS.register(ModBlocks.insectRepellent);
		MinecraftForge.EVENT_BUS.register(ModItems.armorGlider);
		MinecraftForge.EVENT_BUS.register(ModItems.jumpBoots);
		MinecraftForge.EVENT_BUS.register(new LightningAltarNoises());

		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		BCFacadeManager.registerFacades();
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());

	}
}