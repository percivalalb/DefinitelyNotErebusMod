package erebus;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityFirebrat;
import erebus.entity.EntityFly;
import erebus.entity.EntityGrasshopper;
import erebus.entity.EntityLocust;
import erebus.entity.EntityMosquito;
import erebus.entity.EntityMoth;
import erebus.entity.EntityScorpion;
import erebus.entity.EntitySolifuge;
import erebus.entity.EntityTarantula;
import erebus.entity.EntityWasp;
import erebus.entity.EntityWaspDagger;

public class ModEntities {

	public static void init() {

		// Mob Entities
		registerEntity(EntityBeetleLarva.class, "BeetleLarva", EntityRegistry.findGlobalUniqueEntityId(), -1251634, -13032944);
		registerEntity(EntityWasp.class, "Wasp", EntityRegistry.findGlobalUniqueEntityId(), -256, -16382458);
		registerEntity(EntityCentipede.class, "Centipede", EntityRegistry.findGlobalUniqueEntityId(), -13565952, -92160);
		registerEntity(EntityBeetle.class, "Beetle", EntityRegistry.findGlobalUniqueEntityId(), -12116973, -5938366);
		registerEntity(EntityFly.class, "Fly", EntityRegistry.findGlobalUniqueEntityId(), -13165534, -6750208);
		registerEntity(EntityMosquito.class, "Mosquito", EntityRegistry.findGlobalUniqueEntityId(), -13816034, -14803180);
		registerEntity(EntityTarantula.class, "Tarantula", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xE82066);
		// registerEntity(EntityVelvetWorm.class, "VelvetWorm",
		// EntityRegistry.findGlobalUniqueEntityId(), 894731, 000000);
		registerEntity(EntityBotFly.class, "BotFly", EntityRegistry.findGlobalUniqueEntityId(), -6750208, -13165534);
		registerEntity(EntityScorpion.class, "Scorpion", EntityRegistry.findGlobalUniqueEntityId(), 0xFFA200, 0xFFDB9C);
		registerEntity(EntitySolifuge.class, "Solifuge", EntityRegistry.findGlobalUniqueEntityId(), 0xFFDB9C, 0xFFA200);
		registerEntity(EntityGrasshopper.class, "Grasshopper", EntityRegistry.findGlobalUniqueEntityId(), 0x06B900, 0x5FFF5F);
		registerEntity(EntityLocust.class, "Locust", EntityRegistry.findGlobalUniqueEntityId(), 0x5FFF5F, 0x06B900);
		registerEntity(EntityMoth.class, "Moth", EntityRegistry.findGlobalUniqueEntityId(), 0x00FFDD, 0xFBFFA8);
		registerEntity(EntityFirebrat.class, "Firebrat", EntityRegistry.findGlobalUniqueEntityId(), 0xFF0000, 0xFF8800);
		registerEntity(EntityAntlion.class, "Antlion", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFFFFFF);
		registerEntity(EntityBlackWidow.class, "BlackWidow", EntityRegistry.findGlobalUniqueEntityId(), 0x000000, 0xFF0000);

		// Extra Mob spawn conditions
		EntityRegistry.addSpawn(EntityBlackWidow.class, 100, 5, 10, EnumCreatureType.monster, BiomeGenBase.hell);

		// Entity Items
		EntityRegistry.registerModEntity(EntityWaspDagger.class, "WaspDagger", EntityRegistry.findGlobalUniqueEntityId(), ErebusMod.instance, 64, 1, true);
	}

	public static void registerEntity(Class entityClass, String saveName, int id, int backgroundEggColour, int foregroundEggColour) {
		EntityRegistry.registerGlobalEntityID(entityClass, saveName + " - Erebus", id, backgroundEggColour, foregroundEggColour);
		EntityRegistry.registerModEntity(entityClass, saveName + " - Erebus", id, ErebusMod.instance, 120, 1, true);
	}
}
