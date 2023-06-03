package com.bobvarioa.kubejsprojecte.kubejs;

import com.bobvarioa.kubejsprojecte.projecte.KubeJSEMCMapperAfter;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.PECapabilities;
import moze_intel.projecte.utils.PlayerHelper;
import moze_intel.projecte.utils.WorldTransmutations;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.math.BigDecimal;
import java.math.BigInteger;

public class KubeJSProjectEPlugin extends KubeJSPlugin {
    public static EventGroup GROUP = EventGroup.of("ProjectEEvents");
    public static EventHandler SET_EMC = GROUP.server("setEMC", () -> SetEMCEventJS.class);
    public static EventHandler WORLD_TRANS = GROUP.startup("registerWorldTransmutations", () -> RegisterWorldTransmutationsEventJS.class);

    @Override
    public void registerEvents() {
        GROUP.register();
    }

    public void registerBindings(BindingsEvent event) {
        event.add("ProjectE", new ProjectEUtils());
    }

    public static class ProjectEUtils {
        public String getPlayerEMC(Player player) {
            if (player == null) return null;
            var cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY);
            if (cap.isPresent()) {
                return cap.resolve().get().getEmc().toString();
            }
            return "0";
        }

        public void setPlayerEMC(Player player, String num) {
            if (player == null) return;
            var cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY);
            if (cap.isPresent()) {
                var emc = cap.resolve().get();
                emc.setEmc(emc.getEmc().add(new BigDecimal(num).toBigInteger()));
            }
        }

        public void addPlayerEMC(Player player, String num) {
            if (player == null) return;
            var cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY);
            if (cap.isPresent()) {
                var emc = cap.resolve().get();
                emc.setEmc(emc.getEmc().add(new BigDecimal(num).toBigInteger()));
            }
        }
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
