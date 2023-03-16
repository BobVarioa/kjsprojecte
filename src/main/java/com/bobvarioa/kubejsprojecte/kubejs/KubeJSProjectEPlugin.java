package com.bobvarioa.kubejsprojecte.kubejs;

import com.bobvarioa.kubejsprojecte.projecte.KubeJSEMCMapperAfter;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.EventJS;
import moze_intel.projecte.utils.WorldTransmutations;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class KubeJSProjectEPlugin extends KubeJSPlugin {
    public static EventGroup GROUP = EventGroup.of("ProjectEEvents");
    public static EventHandler SET_EMC = GROUP.server("setEMC", () -> SetEMCEventJS.class);
    public static EventHandler WORLD_TRANS = GROUP.startup("registerWorldTransmutations", () -> RegisterWorldTransmutationsEventJS.class);

    @Override
    public void registerEvents() {
        GROUP.register();
    }

    public static class SetEMCEventJS extends EventJS {
        public static SetEMCEventJS INSTANCE = new SetEMCEventJS();

        public void setEMC(String str, long emc) {
            KubeJSEMCMapperAfter.INSTANCE.items.put(str, emc);
        }

        public void setEMCAfter(String str, long emc) {
            KubeJSEMCMapperAfter.INSTANCE.items.put(str, emc);
        }

        public void setEMCBefore(String str, long emc) {
            KubeJSEMCMapperAfter.INSTANCE.items.put(str, emc);
        }
    }

    public static class RegisterWorldTransmutationsEventJS extends EventJS {
        public static RegisterWorldTransmutationsEventJS INSTANCE = new RegisterWorldTransmutationsEventJS();
        public void transform(Block from, Block into) {
            WorldTransmutations.register(from.defaultBlockState(), into.defaultBlockState(), null);
        }
    }
}
