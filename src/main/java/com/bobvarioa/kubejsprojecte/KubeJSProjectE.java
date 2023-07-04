package com.bobvarioa.kubejsprojecte;

import com.bobvarioa.kubejsprojecte.kubejs.KubeJSProjectEPlugin;
import com.bobvarioa.kubejsprojecte.projecte.KubeJSEMCMapperAfter;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(KubeJSProjectE.MODID)
public class KubeJSProjectE {
    public static final String MODID = "kubejsprojecte";

    public KubeJSProjectE() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, KubeJSProjectE::serverReload);
        MinecraftForge.EVENT_BUS.addListener(KubeJSProjectE::gameStart);
    }

    public static void serverReload(TagsUpdatedEvent event) {
        KubeJSEMCMapperAfter.INSTANCE.items.clear();
        KubeJSProjectEPlugin.SET_EMC.post(ScriptType.SERVER, KubeJSProjectEPlugin.SetEMCEventJS.INSTANCE);
    }

    public static void gameStart(ServerStartedEvent event) {
        KubeJSProjectEPlugin.WORLD_TRANS.post(ScriptType.SERVER, KubeJSProjectEPlugin.RegisterWorldTransmutationsEventJS.INSTANCE);
    }
}
