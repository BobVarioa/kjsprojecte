package com.bobvarioa.kubejsprojecte;

import com.bobvarioa.kubejsprojecte.kubejs.KubeJSProjectEPlugin;
import com.bobvarioa.kubejsprojecte.projecte.KubeJSEMCMapperAfter;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(KubeJSProjectE.MODID)
public class KubeJSProjectE {
    public static final String MODID = "kubejsprojecte";

    public KubeJSProjectE(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, KubeJSProjectE::serverReload);
        NeoForge.EVENT_BUS.addListener(KubeJSProjectE::gameStart);
    }

    public static void serverReload(TagsUpdatedEvent event) {
        KubeJSEMCMapperAfter.INSTANCE.items.clear();
        KubeJSProjectEPlugin.SET_EMC.post(ScriptType.SERVER, KubeJSProjectEPlugin.SetEMCEventJS.INSTANCE);
    }

    public static void gameStart(ServerStartedEvent event) {
        KubeJSProjectEPlugin.WORLD_TRANS.post(ScriptType.SERVER, KubeJSProjectEPlugin.RegisterWorldTransmutationsEventJS.INSTANCE);
    }
}
