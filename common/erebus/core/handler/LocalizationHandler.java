package erebus.core.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cpw.mods.fml.common.registry.LanguageRegistry;
import erebus.ErebusMod;
import erebus.core.helper.LocalizationHelper;

/**
 * @author ProPercivalalb
 */
public class LocalizationHandler {

	public static enum Localizations {

		EN_US("en_US.txt");
	    
		String fileName;
		
		Localizations(String file) {
			this.fileName = file;
		}
		
		public String getFile() {
			return this.fileName;
		}
		
	    public static final String LANG_RESOURCE_LOCATION = "/assets/erebus/lang/";
	}

	
	/***
     * Loads in all the Localization files.
     */
    public static void loadLanguages() {
        for (Localizations localization : Localizations.values()) {
        	
        	String localizationFile = Localizations.LANG_RESOURCE_LOCATION + localization.getFile();
        	
            try {
	            BufferedReader paramReader = new BufferedReader(new InputStreamReader(ErebusMod.class.getResourceAsStream(localizationFile))); 
				
	            String line = "";
				
	            while((line = paramReader.readLine()) != null) {
					if(!line.isEmpty() && !line.startsWith("#")) {
						
						String[] split = line.split("=");
						String inGameName = split[1];
						int count = 2;
						while(split.length > count) {
							inGameName += split[count] + (split.length - 1 != count ? "=" : "");
							++count;
						}
						
						LanguageRegistry.instance().addStringLocalization(split[0], LocalizationHelper.getLocaleFromFileName(localizationFile), inGameName);
					}
				}
            }
            catch(Exception e) {
            	e.printStackTrace();
            }
        }
    }

}
