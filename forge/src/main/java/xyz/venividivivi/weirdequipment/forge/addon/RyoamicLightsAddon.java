package xyz.venividivivi.weirdequipment.forge.addon;

import dev.lambdaurora.lambdynlights.api.DynamicLightHandlers;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentEntityTypes;

public class RyoamicLightsAddon implements DynamicLightsInitializer {
    @Override
    public void onInitializeDynamicLights() {
        DynamicLightHandlers.registerDynamicLightHandler(WeirdEquipmentEntityTypes.TORCH_ARROW.get(), lightSource -> 12);
    }
}
