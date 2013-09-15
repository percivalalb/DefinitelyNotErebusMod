package erebus.core.addon.buildcraft;

import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Loader;
import erebus.core.helper.ReflectionHelper;

/**
 * @author ProPercivalalb
 */
public class BuildcraftAPI {

	private Optional<Class>  facadeManagerClass = Optional.absent();
	private Optional<Method> registerFacadeMethod = Optional.absent();
	
	public BuildcraftAPI(String modId) {
		if(!Loader.isModLoaded(modId))
			return;
		facadeManagerClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_FACADE_MANAGER));
		if(facadeManagerClass.isPresent())
			registerFacadeMethod = Optional.fromNullable(ReflectionHelper.getMethod(facadeManagerClass.get(), BuildcraftLib.METHOD_REGISTER_FACADE, ItemStack.class));
	}
	
	public boolean registerFacade(int id, int meta) {
		try {
			if(registerFacadeMethod.isPresent()) {
				registerFacadeMethod.get().invoke(null, new ItemStack(id, 1, meta));
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
}
