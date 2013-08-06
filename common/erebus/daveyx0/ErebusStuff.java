/*package erebus.daveyx0;

import java.util.HashMap;
import java.util.logging.Logger;

import erebus.Entity.EntityMosquito;

import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "Erebus Stuff", name = "Erebus Stuff mod", version = "1.0")
@NetworkMod
(
        clientSideRequired = true, serverSideRequired = false
)
public class ErebusStuff
{
	
	@PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
    	
    }
    
    @SideOnly(Side.CLIENT)
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event){
    try{
    event.manager.soundPoolSounds.addSound("Erebus/mosquito_flying.ogg", ErebusStuff.class.getResource("/sound/mosquito_flying.ogg"));
    } catch(Exception exception){
    System.err.println("Failed To Register Sound: " + exception.getMessage());
    }
    try{
    event.manager.soundPoolSounds.addSound("Erebus/mosquito_hit.ogg", ErebusStuff.class.getResource("/sound/mosquito_hit.ogg"));
    } catch(Exception exception){
    System.err.println("Failed To Register Sound: " + exception.getMessage());
    }
    try{
    event.manager.soundPoolSounds.addSound("Erebus/mosquito_death.ogg", ErebusStuff.class.getResource("/sound/mosquito_death.ogg"));
    } catch(Exception exception){
    System.err.println("Failed To Register Sound: " + exception.getMessage());
    }
    try{
    event.manager.soundPoolSounds.addSound("Erebus/mosquito_sucking.ogg", ErebusStuff.class.getResource("/sound/mosquito_sucking.ogg"));
    } catch(Exception exception){
    System.err.println("Failed To Register Sound: " + exception.getMessage());
    }
    }

    public ErebusStuff()
    {
    	
    }

    public String getVersion()
    {
        return "v1.0";
    }
    
    @Mod.Init
    public void load(FMLInitializationEvent event)
    {
        LanguageRegistry.instance().addStringLocalization("entity.Erebus Stuff.Mosquito.name", "en_US", "Mosquito");	    	
	    EntityRegistry.registerModEntity(EntityMosquito.class, "Mosquito", 17,  ErebusStuff.instance, 80, 3, true);
        EntityRegistry.addSpawn(EntityMosquito.class, 10, 1, 1, EnumCreatureType.monster, new BiomeGenBase[] 
				{ocean, plains, desert, extremeHills, forest, taiga, swampland, river, frozenOcean, frozenRiver, icePlains, iceMountains, beach, desertHills, forestHills, taigaHills, extremeHillsEdge, jungle, jungleHills}); 
        AddEggs();    
        proxy.registerRenderInformation();
        //  TickRegistry.registerTickHandler(new atmosmobs.CommonTickHandler(), Side.SERVER);      
    }

    private void AddEggs()
    {
        EntityList.IDtoClassMapping.put(815, daveyx0.mobs.EntityMosquito.class);
        EntityList.entityEggs.put(Integer.valueOf(815), new EntityEggInfo(815, 0xB77A35, 0xD0D0D0));
    }
   

    @SidedProxy(clientSide = "daveyx0.ClientProxy", serverSide = "daveyx0.CommonProxy")
    public static daveyx0.CommonProxy proxy; //This object will be populated with the class that you choose for the environment
    @Instance("Erebus Stuff")
    public static ErebusStuff instance; //The instance of the mod that will be defined, populated, and callable
    public static long lastTickRun = 0L;
	public BiomeGenBase ocean = BiomeGenBase.ocean;
	public BiomeGenBase plains = BiomeGenBase.plains;
	public BiomeGenBase desert = BiomeGenBase.desert;
	public BiomeGenBase extremeHills = BiomeGenBase.extremeHills;
	public BiomeGenBase forest = BiomeGenBase.forest;
	public BiomeGenBase taiga = BiomeGenBase.taiga;
	public BiomeGenBase swampland = BiomeGenBase.swampland;
	public BiomeGenBase river = BiomeGenBase.river;
	public BiomeGenBase hell = BiomeGenBase.hell;
	public BiomeGenBase sky = BiomeGenBase.sky;
	public BiomeGenBase frozenOcean = BiomeGenBase.frozenOcean;
	public BiomeGenBase frozenRiver = BiomeGenBase.frozenRiver;
	public BiomeGenBase icePlains = BiomeGenBase.icePlains;
	public BiomeGenBase iceMountains = BiomeGenBase.iceMountains;
	public BiomeGenBase mushroomIsland = BiomeGenBase.mushroomIsland;
	public BiomeGenBase mushroomIslandShore = BiomeGenBase.mushroomIslandShore;
	public BiomeGenBase beach = BiomeGenBase.beach;
	public BiomeGenBase desertHills = BiomeGenBase.desertHills;
	public BiomeGenBase forestHills = BiomeGenBase.forestHills;
	public BiomeGenBase taigaHills = BiomeGenBase.taigaHills;
	public BiomeGenBase extremeHillsEdge = BiomeGenBase.extremeHillsEdge;
	public BiomeGenBase jungle = BiomeGenBase.jungle;
	public BiomeGenBase jungleHills = BiomeGenBase.jungleHills;
    
}
*/