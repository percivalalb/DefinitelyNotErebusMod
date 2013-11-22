package erebus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.entity.EntityAntlion;
import erebus.entity.EntityBeetle;
import erebus.entity.EntityBeetleLarva;
import erebus.entity.EntityBlackWidow;
import erebus.entity.EntityBombardierBeetle;
import erebus.entity.EntityBotFly;
import erebus.entity.EntityCentipede;
import erebus.entity.EntityErebusSpider;
import erebus.entity.EntityErebusSpiderMoney;
import erebus.entity.EntityFirebrat;
import erebus.entity.EntityFly;
import erebus.entity.EntityGlowWorm;
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

	private static final int WASP_DAGGER_ID = 0;

	public static void init() {

		// Mob Entities
		registerEntity(EntityBeetleLarva.class, "BeetleLarva", -1251634, -13032944);
		registerEntity(EntityWasp.class, "Wasp", -256, -16382458);
		registerEntity(EntityCentipede.class, "Centipede", -13565952, -92160);
		registerEntity(EntityBeetle.class, "Beetle", -12116973, -5938366);
		registerEntity(EntityFly.class, "Fly", -13165534, -6750208);
		registerEntity(EntityMosquito.class, "Mosquito", -13816034, -14803180);
		registerEntity(EntityTarantula.class, "Tarantula", 0x000000, 0xE82066);
		// registerEntity(EntityVelvetWorm.class, "VelvetWorm",
		// EntityRegistry.findGlobalUniqueEntityId(), 894731, 000000);
		registerEntity(EntityBotFly.class, "BotFly", -6750208, -13165534);
		registerEntity(EntityScorpion.class, "Scorpion", 0xFFA200, 0xFFDB9C);
		registerEntity(EntitySolifuge.class, "Solifuge", 0xFFDB9C, 0xFFA200);
		registerEntity(EntityGrasshopper.class, "Grasshopper", 0x06B900, 0x5FFF5F);
		registerEntity(EntityLocust.class, "Locust", 0x5FFF5F, 0x06B900);
		registerEntity(EntityMoth.class, "Moth", 0x00FFDD, 0xFBFFA8);
		registerEntity(EntityFirebrat.class, "Firebrat", 0xFF0000, 0xFF8800);
		registerEntity(EntityAntlion.class, "Antlion", 0x000000, 0xFFFFFF);
		registerEntity(EntityBlackWidow.class, "BlackWidow", 0x000000, 0xFF0000);
		registerEntity(EntityGlowWorm.class, "GlowWorm", 0xFFFF00, 0xFFFFFF);
		registerEntity(EntityBombardierBeetle.class, "BombardierBeetle", 0xFFEEFF, 0x9E0E0E);
		registerEntity(EntityErebusSpider.class, "ErebusSpider", 0x0B4D49, 0xFFFFFF);
		registerEntity(EntityErebusSpiderMoney.class, "MoneySpider", 0xF5C400, 0x0B4D49);

		registerEntityWithoutEgg(EntityAnimatedBlock.class, "AnimatedBlock");
		registerEntityWithoutEgg(EntityAnimatedChest.class, "AnimatedChest");


		// Extra Mob spawn conditions
		EntityRegistry.addSpawn(EntityBlackWidow.class, 100, 5, 10, EnumCreatureType.monster, BiomeGenBase.hell);

		// Entity Items
		registerEntityWithoutEgg(EntityWaspDagger.class, "WaspDagger");
	}

	private static void registerEntity(Class<? extends Entity> entityClass, String saveName, int backgroundEggColour, int foregroundEggColour) {
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, saveName + " - Erebus", id, backgroundEggColour, foregroundEggColour);
		EntityRegistry.registerModEntity(entityClass, saveName + " - Erebus", id, ErebusMod.instance, 120, 1, true);
	}

	private static void registerEntityWithoutEgg(Class<? extends Entity> entityClass, String saveName) {
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, saveName, id);
		EntityRegistry.registerModEntity(entityClass, saveName, id, ErebusMod.instance, 120, 1, true);
	}
}