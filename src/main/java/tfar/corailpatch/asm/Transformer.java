package tfar.corailpatch.asm;


import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class Transformer implements IFMLLoadingPlugin {

	private static final Logger logger = LogManager.getLogger();

	public Transformer() {
		// Corail Tombstone doesn't have a coremod so we can't apply mixins to it, lets make it one
		try {
			String name = "tombstone-4.0.0-1.12.2.jar";
			loadModJar(new File("./mods/".concat(name)));
			logger.info("It seems like corail has attempted to crash the game again, lets prevent that.  " +
							"If you have a dispute with another modder, don't take it out on the players");
		} catch (Exception e) {
			e.printStackTrace();
		}
		MixinBootstrap.init();
		Mixins.addConfiguration("mixins.corailpatch.json");
	}

	/**
	 * Return a list of classes that implements the IClassTransformer interface
	 *
	 * @return a list of classes that implements the IClassTransformer interface
	 */
	@Override
	public String[] getASMTransformerClass()
	{
		return new String[0];
	}

	/**
	 * Return a class name that implements "ModContainer" for injection into the mod list
	 * The "getName" function should return a name that other mods can, if need be,
	 * depend on.
	 * Trivially, this modcontainer will be loaded before all regular mod containers,
	 * which means it will be forced to be "immutable" - not susceptible to normal
	 * sorting behaviour.
	 * All other mod behaviours are available however- this container can receive and handle
	 * normal loading events
	 */
	@Override
	public String getModContainerClass()
	{
		return null;
	}

	/**
	 * Return the class name of an implementor of "IFMLCallHook", that will be run, in the
	 * main thread, to perform any additional setup this coremod may require. It will be
	 * run <strong>prior</strong> to Minecraft starting, so it CANNOT operate on minecraft
	 * itself. The game will deliberately crash if this code is detected to trigger a
	 * minecraft class loading
	 * TODO: implement crash ;)
	 */
	@Nullable
	@Override
	public String getSetupClass()
	{
		return null;
	}

	/**
	 * Inject coremod data into this coremod
	 * This data includes:
	 * "mcLocation" : the location of the minecraft directory,
	 * "coremodList" : the list of coremods
	 * "coremodLocation" : the file this coremod loaded from,
	 *
	 * @param data
	 */
	@Override
	public void injectData(Map<String, Object> data)
	{

	}

	private void loadModJar(File jar) throws Exception {
		((LaunchClassLoader) this.getClass().getClassLoader()).addURL(jar.toURI().toURL());
		CoreModManager.getReparseableCoremods().add(jar.getName());
	}

	/**
	 * Return an optional access transformer class for this coremod. It will be injected post-deobf
	 * so ensure your ATs conform to the new srgnames scheme.
	 *
	 * @return the name of an access transformer class or null if none is provided
	 */
	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}

}