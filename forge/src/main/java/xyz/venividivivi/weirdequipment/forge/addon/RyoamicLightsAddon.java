package xyz.venividivivi.weirdequipment.forge.addon;

import org.thinkingstudio.ryoamiclights.api.DynamicLightHandlers;
import org.thinkingstudio.ryoamiclights.forge.api.DynamicLightsInitializerEvent;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentEntityTypes;

public class RyoamicLightsAddon {
    public static void onInitializeDynamicLights(DynamicLightsInitializerEvent event) {
        DynamicLightHandlers.registerDynamicLightHandler(WeirdEquipmentEntityTypes.TORCH_ARROW.get(), lightSource -> 12);
    }
}
