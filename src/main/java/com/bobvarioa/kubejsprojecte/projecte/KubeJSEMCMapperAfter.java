package com.bobvarioa.kubejsprojecte.projecte;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import moze_intel.projecte.api.mapper.EMCMapper;
import moze_intel.projecte.api.mapper.IEMCMapper;
import moze_intel.projecte.api.mapper.collector.IMappingCollector;
import moze_intel.projecte.api.nss.NormalizedSimpleStack;
import moze_intel.projecte.emc.json.NSSSerializer;
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
    public String getDescription() {
        return "Allows KubeJS ProjectE to function";
    }

    public Map<String, Long> items = new HashMap<>();

    @Override
    public void addMappings(IMappingCollector<NormalizedSimpleStack, Long> mapper, CommentedFileConfig commentedFileConfig, ReloadableServerResources reloadableServerResources, ResourceManager resourceManager) {
        for (var entry : items.entrySet()) {
            mapper.setValueBefore(NSSSerializer.INSTANCE.deserialize(entry.getKey()), entry.getValue());
        }
    }
}
