package erebus.core.addon.buildcraft;

import java.lang.reflect.Method;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import buildcraft.transport.Pipe;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Loader;
import erebus.core.helper.ReflectionHelper;

/**
 * @author ProPercivalalb
 */
public class BuildcraftAPI {

	private Optional<Class>  facadeManagerClass = Optional.absent();
	private Optional<Method> registerFacadeMethod = Optional.absent();
	private Optional<Class>  buildcraftTransportClass = Optional.absent();
	private Optional<Class>  pipeClass = Optional.absent();
	private Optional<Method> registerItemPipeMethod = Optional.absent();
	
	public BuildcraftAPI(String modId) {
		if(!Loader.isModLoaded(modId))
			return;
		facadeManagerClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_FACADE_MANAGER));
		if(facadeManagerClass.isPresent())
			registerFacadeMethod = Optional.fromNullable(ReflectionHelper.getMethod(facadeManagerClass.get(), BuildcraftLib.METHOD_REGISTER_FACADE, ItemStack.class));
		buildcraftTransportClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_BUILDCRAFT_TRANSPORT));
		pipeClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_PIPE));
		if(buildcraftTransportClass.isPresent() && pipeClass.isPresent())
			registerItemPipeMethod = Optional.fromNullable(ReflectionHelper.getMethod(facadeManagerClass.get(), BuildcraftLib.METHOD_REGISTER_ITEM_PIPE, Integer.TYPE, pipeClass.get(), String.class, Object[].class));
	}
	
	public void registerFacade(int id, int meta) {
		try {
			if(registerFacadeMethod.isPresent()) {
				registerFacadeMethod.get().invoke(null, new ItemStack(id, 1, meta));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public Item registerPipe(int defaultID, Class<?> clas, String descr, Object... ingredients) {
		try {
			if(registerItemPipeMethod.isPresent() && pipeClass.isPresent()) {
				Item item = (Item)registerItemPipeMethod.get().invoke(null, defaultID, clas, descr, ingredients);
				return item;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
}
