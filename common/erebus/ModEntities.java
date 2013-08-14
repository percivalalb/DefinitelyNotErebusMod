package erebus;

import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFly;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityVelvetWorm;
import erebus.entity.EntityWasp;

public class ModEntities {

	public static void init() {

		registerEntity(EntityBeetleLarva.class, "BeetleLarva", EntityRegistry.findGlobalUniqueEntityId(), -1251634, -13032944);
		registerEntity(EntityWasp.class, "Wasp", EntityRegistry.findGlobalUniqueEntityId(), -16382458, -256);
		registerEntity(EntityCentipede.class, "Centipede", EntityRegistry.findGlobalUniqueEntityId(), -13565952, -92160);
		registerEntity(EntityBeetle.class, "Beetle", EntityRegistry.findGlobalUniqueEntityId(), -12116973, -5938366);
		registerEntity(EntityFly.class, "Fly", EntityRegistry.findGlobalUniqueEntityId(), -13165534, -6750208);
		registerEntity(EntityMosquito.class, "Mosquito", EntityRegistry.findGlobalUniqueEntityId(), -13816034, -14803180);
		registerEntity(EntityTarantula.class, "Tarantula", EntityRegistry.findGlobalUniqueEntityId(), 894731, 512);	
		registerEntity(EntityVelvetWorm.class, "VelvetWorm", EntityRegistry.findGlobalUniqueEntityId(), 894731, 000000);
	
	}
	
	public static void registerEntity(Class entityClass, String saveName, int id, int backgroundEggColour, int foregroundEggColour) {
	    EntityRegistry.registerGlobalEntityID(entityClass, saveName + " - Erebus", id, backgroundEggColour, foregroundEggColour);
	    EntityRegistry.registerModEntity(entityClass, saveName + " - Erebus", id, ErebusMod.instance, 120, 1, true);
	   // CreativeTabCarboniferous.spawnersList.add(id);
	}
}
