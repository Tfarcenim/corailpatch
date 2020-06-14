package com.tfar.corailpatch;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import ovh.corail.tombstone.compatibility.SupportMods;

import java.lang.reflect.Field;

@Mod(modid = CorailPatch.MODID, name = CorailPatch.NAME, version = CorailPatch.VERSION)
public class CorailPatch
{
    public static final String MODID = "corailpatch";
    public static final String NAME = "Corail Patch";
    public static final String VERSION = "1.0";

    @EventHandler
    public void postInit(FMLInitializationEvent e){
        Field field = null;
        try {
            field = SupportMods.class.getDeclaredField("loaded");
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        }
        field.setAccessible(true);
        try {
            field.setBoolean(SupportMods.TREASURE2,false);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
        try {
            field.setBoolean(SupportMods.GOTTSCHCORE,false);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }


    }
}
