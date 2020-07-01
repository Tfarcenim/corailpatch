package tfar.corailpatch;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ovh.corail.tombstone.compatibility.SupportMods;
import tfar.corailpatch.mixin.SupportModAccessor;

import java.lang.reflect.Field;
import java.util.List;

@Mod(modid = CorailPatch.MODID, name = CorailPatch.NAME, version = CorailPatch.VERSION)
public class CorailPatch
{
    public static final String MODID = "corailpatch";
    public static final String NAME = "Corail Patch";
    public static final String VERSION = "1.0";

    @EventHandler
    public void postInit(FMLPreInitializationEvent e){
        for (SupportMods mod : SupportMods.values()){
            List<ModContainer> modList = Loader.instance().getModList();
            for (ModContainer container : modList) {
                String modid = container.getModId();
                String compatName = mod.getName();
                if (modid.equals(compatName)) {
                    boolean fake = container.getOwnedPackages().stream().anyMatch(s -> {
                        String[] parts = s.split("\\.");
                        for (String part : parts) {
                            if (part.equals("tfar") || part.equals("someguyssoftware"))
                            return true;
                        }
                        return false;
                    });
                    if (fake) {
                        ((SupportModAccessor)(Object)mod).setLoaded(false);
                    }
                }
            }
        }
    }
}
