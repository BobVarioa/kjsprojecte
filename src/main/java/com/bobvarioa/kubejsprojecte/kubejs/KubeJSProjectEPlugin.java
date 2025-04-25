package com.bobvarioa.kubejsprojecte.kubejs;

import com.bobvarioa.kubejsprojecte.projecte.KubeJSEMCMapperAfter;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import moze_intel.projecte.api.capabilities.PECapabilities;
import moze_intel.projecte.api.world_transmutation.SimpleWorldTransmutation;
import moze_intel.projecte.api.world_transmutation.WorldTransmutation;
import moze_intel.projecte.world_transmutation.WorldTransmutationManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.math.BigDecimal;

public class KubeJSProjectEPlugin implements KubeJSPlugin {
    public static EventGroup GROUP = EventGroup.of("ProjectEEvents");
    public static EventHandler SET_EMC = GROUP.server("setEMC", () -> SetEMCEventJS.class);
    public static EventHandler WORLD_TRANS = GROUP.startup("registerWorldTransmutations", () -> RegisterWorldTransmutationsEventJS.class);

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(GROUP);
    }

    public void registerBindings(BindingRegistry event) {
        event.add("ProjectE", new ProjectEUtils());
    }

    public static class ProjectEUtils {
        public String getPlayerEMC(Player player) {
            if (player == null) return null;
            var cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY);
            if (cap != null) {
                return cap.getEmc().toString();
            }
            return "0";
        }

        public void setPlayerEMC(Player player, String num) {
            if (player == null) return;
            var cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY);
            if (cap != null) {
                cap.setEmc(new BigDecimal(num).toBigInteger());
            }
        }

        public void addPlayerEMC(Player player, String num) {
            if (player == null) return;
            var cap = player.getCapability(PECapabilities.KNOWLEDGE_CAPABILITY);
            if (cap != null) {
                cap.setEmc(cap.getEmc().add(new BigDecimal(num).toBigInteger()));
            }
        }
    }

    public static class SetEMCEventJS implements KubeEvent {
        public static SetEMCEventJS INSTANCE = new SetEMCEventJS();

        public void setEMC(Item item, long emc) {
            KubeJSEMCMapperAfter.INSTANCE.items.put(item, emc);
        }

        public void setEMCAfter(Item item, long emc) {
            KubeJSEMCMapperAfter.INSTANCE.items.put(item, emc);
        }

        public void setEMCBefore(Item item, long emc) {
            KubeJSEMCMapperAfter.INSTANCE.items.put(item, emc);
        }
    }

    public static class RegisterWorldTransmutationsEventJS implements KubeEvent {
        public static RegisterWorldTransmutationsEventJS INSTANCE = new RegisterWorldTransmutationsEventJS();
        public void transform(Block from, Block into, Block intoAlt) {
            WorldTransmutationManager.INSTANCE.register(new WorldTransmutation(from.defaultBlockState(), into.defaultBlockState(), intoAlt.defaultBlockState()));
        }

        public void transform(Block from, Block into) {
            WorldTransmutationManager.INSTANCE.register(new WorldTransmutation(from.defaultBlockState(), into.defaultBlockState(), into.defaultBlockState()));
        }
    }
}
