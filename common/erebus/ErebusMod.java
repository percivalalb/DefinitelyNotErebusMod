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
import erebus.client.sound.EntityBeetleLarvaNoises;
import erebus.client.sound.EntityBlackWidowNoises;
import erebus.client.sound.EntityCentipedeNoises;
import erebus.client.sound.EntityFlyNoises;
import erebus.client.sound.EntityMosquitoNoises;
import erebus.client.sound.EntityWaspNoises;
import erebus.core.addon.AddonManager;
import erebus.core.handler.CommonTickHandler;
import erebus.core.handler.ConfigurationHandler;
import erebus.core.handler.ConnectionTeleportHandler;
import erebus.core.handler.LocalizationHandler;
import erebus.core.helper.LogHelper;
import erebus.core.proxy.CommonProxy;
import erebus.creativetab.CreativeTabErebusBlock;
import erebus.creativetab.CreativeTabErebusGear;
import erebus.creativetab.CreativeTabErebusItem;
import erebus.lib.Reference;
import erebus.network.PacketHandler;
import erebus.recipes.RecipeHandler;
import erebus.world.WorldProviderErebus;

/**
 * @author ProPercivalalb, Dylan4Ever, (Others PUT NAMES HERE)
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES)
@NetworkMod(channels = {Reference.CHANNEL}, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
public class ErebusMod
{	
	@SidedProxy(clientSide = Reference.SP_CLIENT, serverSide = Reference.SP_SERVER)
	public static CommonProxy proxy; //This object will be populated with the class that you choose for the environment
	@Instance(Reference.MOD_ID)
	public static ErebusMod instance; //The instance of the mod that will be defined, populated, and callable

	public static EnumArmorMaterial armorEXOSKELETON = EnumHelper.addArmorMaterial("EXOSKELETON", 11, new int[] {2, 4, 3, 2}, 15);
	public static EnumArmorMaterial armorJADE = EnumHelper.addArmorMaterial("JADE", 24, new int[] {3, 7, 5, 2}, 15);
	public static EnumToolMaterial toolJADE = EnumHelper.addToolMaterial("JADE", 2, 863, 10.0F, 4.0F, 18);
	public static EnumToolMaterial toolJADEPAXEL = EnumHelper.addToolMaterial("JADEPAXEL", 2, 1079, 8.0F, 4.0F, 14);
    
	public static CreativeTabs tabErebusBlock = new CreativeTabErebusBlock(CreativeTabs.getNextID(), "erebus.block");
	public static CreativeTabs tabErebusItem = new CreativeTabErebusItem(CreativeTabs.getNextID(), "erebus.item");
	public static CreativeTabs tabErebusGear = new CreativeTabErebusGear(CreativeTabs.getNextID(), "erebus.gear");

	public static int erebusDimensionID;

	public static ConnectionTeleportHandler packeterebushandler = new ConnectionTeleportHandler();
	
	// This method is used to add sounds
	@EventHandler
	@SideOnly(Side.CLIENT)
	public void preInitClient(FMLPreInitializationEvent event) {
		// Create an Entity 'sound' class as below and this will add sounds to the pool
		MinecraftForge.EVENT_BUS.register(new EntityBeetleLarvaNoises());
		MinecraftForge.EVENT_BUS.register(new EntityWaspNoises());
		MinecraftForge.EVENT_BUS.register(new EntityFlyNoises());
		MinecraftForge.EVENT_BUS.register(new EntityCentipedeNoises());
		MinecraftForge.EVENT_BUS.register(new EntityMosquitoNoises());
		MinecraftForge.EVENT_BUS.register(new EntityBlackWidowNoises());
	}
	
	@EventHandler
	public void preInitServer(FMLPreInitializationEvent event)  {
		
		/** Loads the configuration file before anything else **/
		ConfigurationHandler.loadConfig(event);
		
		//Loads the Languages into the game
		LocalizationHandler.loadLanguages();
		
		//Sets up the log helper
		LogHelper.init();
		
		//Adds Erebus blocks & items
		ModBlocks.init();
		ModItems.init();
		ModEntities.init();
		
		AddonManager.registerAddons();
		
		NetworkRegistry.instance().registerConnectionHandler(packeterebushandler);
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		
		DimensionManager.registerProviderType(erebusDimensionID, WorldProviderErebus.class, true);
		DimensionManager.registerDimension(erebusDimensionID, erebusDimensionID);
		//VillagerRegistry.addExtraVillageComponents(ComponentVillageWatchtower.class, 20, MathHelper.getRandomIntegerInRange(par0Random, 0 + par1, 1 + par1));
		AddonManager.runFMLPre(ConfigurationHandler.configurationFile);
	}

	@EventHandler  
	public void load(FMLInitializationEvent event) {
		ModBiomes.init();
		proxy.registerRenderInformation();
		RecipeHandler.inti();
		
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		AddonManager.runFMLInit(ConfigurationHandler.configurationFile);
	}
	
	//Don't try to use other mod's tools without making sure those mods are loaded in.
	@EventHandler  
	public void postLoad(FMLPostInitializationEvent event) {
		AddonManager.runRegisteredAddons(ConfigurationHandler.configurationFile);
	}
}

/*
@ForgeSubscribe
public void onUseBonemeal(BonemealEvent event)
{
	  MinecraftForge.EVENT_BUS.post(event);
	  if (event.ID == mod_Erebus.erebusSapling.blockID)
    {
        if (!event.world.isRemote)
        {
            if ((double)event.world.rand.nextFloat() < 0.45D)
            {
                ((BlockSaplingErebus)mod_Erebus.erebusSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
            }
        }
    }
}*/