package erebus.core.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import erebus.lib.Reference;

/**
 * @author ProPercivalalb
 */
public class LogHelper {

    private static Logger erebusLogger = Logger.getLogger(Reference.MOD_ID);

    public static void init() {
    	erebusLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, String message) {
    	erebusLogger.log(logLevel, message);
    }
    
    public static void logWarning(String message) {
    	erebusLogger.log(Level.WARNING, message);
    }
    
    public static void logInfo(String message) {
    	erebusLogger.log(Level.INFO, message);
    }

    public static void logSevere(String message) {
    	erebusLogger.log(Level.SEVERE, message);
    }
}
