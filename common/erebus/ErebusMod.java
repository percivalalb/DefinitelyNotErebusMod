package erebus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.sound.EntitySoundEvent;
import erebus.core.addon.AddonManager;
import erebus.core.handler.CommonTickHandler;
import erebus.core.handler.ConfigurationHandler;
import erebus.core.handler.ConnectionTeleportHandler;
import erebus.core.handler.VersionCheckTickHandler;
import erebus.core.helper.LogHelper;
import erebus.core.proxy.CommonProxy;
import erebus.creativetab.CreativeTabErebusBlock;
import erebus.creativetab.CreativeTabErebusGear;
import erebus.creativetab.CreativeTabErebusItem;
import erebus.lib.Reference;
import erebus.network.PacketHandler;
import erebus.recipes.RecipeHandler;
import erebus.utils.VersionHelper;
import erebus.world.WorldProviderErebus;

/**
 * @author ProPercivalalb, Dylan4Ever, (Others PUT NAMES HERE)
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES)
@NetworkMod(channels = { Reference.CHANNEL }, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
public class ErebusMod {

	@SidedProxy(clientSide = Reference.SP_CLIENT, serverSide = Reference.SP_SERVER)
	public static CommonProxy proxy;

	@Instance(Reference.MOD_ID)
	public static ErebusMod instance;

	public static EnumArmorMaterial armorEXOSKELETON = EnumHelper.addArmorMaterial("EXOSKELETON", 11, new int[] { 2, 4, 3, 2 }, 15);
	public static EnumArmorMaterial armorJADE = EnumHelper.addArmorMaterial("JADE", 24, new int[] { 3, 7, 5, 2 }, 15);
	public static EnumToolMaterial toolJADE = EnumHelper.addToolMaterial("JADE", 2, 863, 10.0F, 4.0F, 18);
	public static EnumToolMaterial toolJADEPAXEL = EnumHelper.addToolMaterial("JADEPAXEL", 2, 1079, 8.0F, 4.0F, 14);
	public static EnumToolMaterial toolCAVEMANCLUB = EnumHelper.addToolMaterial("CAVEMANCLUB", 0, 131, 4.0F, 2.0F, 12);

	public static CreativeTabs tabErebusBlock = new CreativeTabErebusBlock(CreativeTabs.getNextID(), "erebus.block");
	public static CreativeTabs tabErebusItem = new CreativeTabErebusItem(CreativeTabs.getNextID(), "erebus.item");
	public static CreativeTabs tabErebusGear = new CreativeTabErebusGear(CreativeTabs.getNextID(), "erebus.gear");

	public static int erebusDimensionID;
	public static boolean activateExtraOres = false;
	public static boolean shouldDoVersionCheck = true;
	public static byte beetleLarvaEating = 0;

	public static ConnectionTeleportHandler packeterebushandler = new ConnectionTeleportHandler();

	@EventHandler
	@SideOnly(Side.CLIENT)
	public void preInitClient(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EntitySoundEvent());
	}

	@EventHandler
	public void preInitServer(FMLPreInitializationEvent event) {
		LogHelper.init();

		ConfigurationHandler.loadConfig(event);

		if (shouldDoVersionCheck) {
			VersionHelper.execute();
			TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);
		}

		ModBlocks.init();
		ModItems.init();
		ModEntities.init();

		AddonManager.registerAddons();

		NetworkRegistry.instance().registerConnectionHandler(packeterebushandler);
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);

		DimensionManager.registerProviderType(erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(erebusDimensionID, erebusDimensionID);
		AddonManager.runFMLPre(ConfigurationHandler.configurationFile);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerTileEntities();
		ModBiomes.init();
		proxy.registerRenderInformation();
		RecipeHandler.init();

		MinecraftForge.EVENT_BUS.register(ModBlocks.erebusSapling);
		MinecraftForge.EVENT_BUS.register(ModBlocks.quickSand);

		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		AddonManager.runFMLInit(ConfigurationHandler.configurationFile);
	}

	@EventHandler
	public void postLoad(FMLPostInitializationEvent event) {
		AddonManager.runRegisteredAddons(ConfigurationHandler.configurationFile);
	}
}