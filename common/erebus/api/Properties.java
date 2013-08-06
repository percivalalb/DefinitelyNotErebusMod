package erebus.api;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;

import net.minecraftforge.common.Configuration;

/**
 * @author ProPercivalalb
 **/
public class Properties {

	/** Reference to the mod id which is used by forge **/
	public static final String MOD_ID = "Erebus";
	/** The readable name used by forge **/
	public static final String MOD_NAME = "Erebus";
	/** Version string for the mod **/
	public static final String MOD_VERSION = "0.1 for MC 1.6.2";
	public static Logger logger = Logger.getLogger(MOD_ID);
	
	public static final String SP_CLIENT = "Erebus.ClientErebusProxy";
	public static final String SP_SERVER = "Erebus.CommonErebusProxy";
	
	//Texture Path
	public static final String PACKAGE = "/erebus";
	public static final String TEX_PACkAGE = "erebus:";
	
	//GUI ID
	
	//Block/Item ID's
	
	//Biome ID
	
	//ID's
	
	//NBT Data
	
	static {
		logger.setParent(FMLLog.getLogger());
	}
}