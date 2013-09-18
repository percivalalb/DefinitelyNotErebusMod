package erebus.core.addon.buildcraft;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Loader;
import erebus.core.helper.ReflectionHelper;

/**
 * @author ProPercivalalb
 */
public class BuildcraftAPI {

	private Optional<Class> facadeManagerClass = Optional.absent();
	private Optional<Method> registerFacadeMethod = Optional.absent();
	private Optional<Class> buildcraftTransportClass = Optional.absent();
	private Optional<Class> pipeClass = Optional.absent();
	private Optional<Method> registerItemPipeMethod = Optional.absent();
	private Optional<Class> transportProxyClass = Optional.absent();
	private Optional<Field> pipeRenderField = Optional.absent();
	private Optional<Class> pipeConnectionBansClass = Optional.absent();
	private Optional<Method> banPipeMethod = Optional.absent();
	private IItemRenderer pipeRender = null;

	public BuildcraftAPI(String modId) {
		if (!Loader.isModLoaded(modId))
			return;
		facadeManagerClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_FACADE_MANAGER));
		if (facadeManagerClass.isPresent())
			registerFacadeMethod = Optional.fromNullable(ReflectionHelper.getMethod(facadeManagerClass.get(), BuildcraftLib.METHOD_REGISTER_FACADE, ItemStack.class));
		buildcraftTransportClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_BUILDCRAFT_TRANSPORT));
		pipeClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_PIPE));
		if (buildcraftTransportClass.isPresent() && pipeClass.isPresent())
			registerItemPipeMethod = Optional.fromNullable(ReflectionHelper.getMethod(buildcraftTransportClass.get(), BuildcraftLib.METHOD_REGISTER_ITEM_PIPE, Integer.TYPE, Class.class, String.class, Object[].class));
		transportProxyClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_TRANSPORT_PROXY));
		if (transportProxyClass.isPresent()) {
			pipeRenderField = Optional.fromNullable(ReflectionHelper.getField(transportProxyClass.get(), null, BuildcraftLib.FIELD_PIPE_RENDER));
			if (pipeRenderField.isPresent())
				try {
					pipeRender = (IItemRenderer) pipeRenderField.get().get(null);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		}
		pipeConnectionBansClass = Optional.fromNullable(ReflectionHelper.getClass(BuildcraftLib.CLASS_PIPE_CONNECTION));
		if (pipeConnectionBansClass.isPresent())
			banPipeMethod = Optional.fromNullable(ReflectionHelper.getMethod(pipeConnectionBansClass.get(), BuildcraftLib.METHOD_BAN_PIPE, Class[].class));
	}

	public void registerFacade(int id, int meta) {
		try {
			if (registerFacadeMethod.isPresent()) {
				registerFacadeMethod.get().invoke(null, new ItemStack(id, 1, meta));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Item registerPipe(int defaultID, Class<?> clas, String descr, Object... ingredients) {
		try {
			if (registerItemPipeMethod.isPresent()) {
				Item item = (Item) registerItemPipeMethod.get().invoke(null, defaultID, clas, descr, ingredients);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void banConnection(Class... types) {
		try {
			buildcraft.transport.PipeConnectionBans.banConnection(types);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IItemRenderer getPipeRender() {
		return pipeRender;
	}
}
