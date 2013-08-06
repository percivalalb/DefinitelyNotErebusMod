package erebus;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import erebus.World.Gen.WorldGenPonds;

public class ClientErebusTickHandler implements ITickHandler
{
	public static Minecraft mc = FMLClientHandler.instance().getClient();
	public boolean keyStates[] = new boolean[256];
	private static int ticks = 0;

	public boolean checkKey(int i) {
		if(Minecraft.getMinecraft().currentScreen != null) {
			return false;
		}
		if(Keyboard.isKeyDown(i) != keyStates[i]) {
			return keyStates[i] = !keyStates[i];
		} else {
			return false;
		}
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if (type.equals(EnumSet.of(TickType.RENDER)))
		{
			onRenderTick();
			float ticks1 = ((Float)tickData[0]).floatValue();
			onRenderTickEnd(ticks1);
		}
		else if (type.equals(EnumSet.of(TickType.CLIENT)))
		{
			GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
			if (guiscreen != null)
			{
				onTickInGUI(guiscreen);
			} else {
				onTickInGame();
			}
		}
		else if (type.equals(EnumSet.of(TickType.PLAYER)))
		{
			EntityPlayer player = (EntityPlayer)tickData[0];
			teleportTick(player);
		}
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.RENDER, TickType.CLIENT, TickType.PLAYER, TickType.WORLD);
		// In my testing only RENDER, CLIENT, & PLAYER did anything on the client side.
		// Read 'cpw.mods.fml.common.TickType.java' for a full list and description of available types
	}

	@Override
	public String getLabel() { return null; }


	public void onRenderTick()
	{
		//System.out.println("onRenderTick");
		//TODO: Your Code Here
	}


	public void onRenderTickEnd(float ticks)
	{
		ScaledResolution var5 = new ScaledResolution(TeleportErebusClient.mc.gameSettings, TeleportErebusClient.mc.displayWidth, TeleportErebusClient.mc.displayHeight);
		int width = var5.getScaledWidth();
		int height = var5.getScaledHeight();
		//TODO	mod_Erebus.renderBossHealth();
		float time = TeleportErebusClient.prevTimeInPortal + (TeleportErebusClient.timeInPortal - TeleportErebusClient.prevTimeInPortal) * ticks;
		if (time > 0.0F && TeleportErebusClient.mc.currentScreen == null)
		{
		}
		else
		{
			if (TeleportErebusClient.mc.thePlayer != null) 
			{
				TeleportErebusClient.mc.thePlayer.removePotionEffect(Potion.confusion.id);
			}  
		}

		if(TeleportErebusClient.mc.thePlayer != null)
		{
			//TODO	TeleportErebusClient.renderGameOverlay(1);
		}
	}    

	public void onTickInGUI(GuiScreen guiscreen)
	{
	}

	public void onTickInGame()
	{
		onTick(mc);
	}

	public void onTick(Minecraft minecraft)
	{   

	}



	public void teleportTick(EntityPlayer player)
	{
		TeleportErebusClient.onTick(player);
	} 

}