package erebus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonErebusProxy// implements IGuiHandler
{
	public static int renderId;

/**
 * Client side only register stuff...
 */
	public void registerRenderInformation() 
	  {
	  //unused server side. -- see ClientProxy for implementation
	  }
		
	/*public void load()
	{
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	  {
	  // TODO Auto-generated method stub
	  return null;
	  }
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	  {
	  // TODO Auto-generated method stub
	  return null;
	  }*/

}// End class CommonProxy