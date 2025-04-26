package com.bobvarioa.kubejsprojecte.projecte;

import moze_intel.projecte.api.mapper.EMCMapper;
import moze_intel.projecte.api.mapper.IEMCMapper;
import moze_intel.projecte.api.mapper.collector.IMappingCollector;
import moze_intel.projecte.api.nss.NSSItem;
import moze_intel.projecte.api.nss.NormalizedSimpleStack;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashMap;
import java.util.Map;

@EMCMapper(
        priority = -1000
)
public class KubeJSEMCMapperAfter implements IEMCMapper<NormalizedSimpleStack, Long> {
    @EMCMapper.Instance
    public static final KubeJSEMCMapperAfter INSTANCE = new KubeJSEMCMapperAfter();

    @Override
    public String getName() {
        return "KubeJSProjectE";
    }

    @Override
    public String getTranslationKey() {
        return "mapping.mapper.kubejsprojecte";
    }

    @Override
    public String getDescription() {
        return "Allows KubeJS ProjectE to function";
    }

    public Map<ResourceLocation, Long> items = new HashMap<>();

    @Override
    public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, ReloadableServerResources reloadableServerResources, RegistryAccess registryAccess, ResourceManager resourceManager) {
        for (var entry : items.entrySet()) {
            mapper.setValueAfter(NSSItem.createItem(entry.getKey()), entry.getValue());
        }
    }
}
