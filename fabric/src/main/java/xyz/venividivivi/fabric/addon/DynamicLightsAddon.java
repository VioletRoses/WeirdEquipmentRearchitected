package xyz.venividivivi.fabric.addon;

import dev.lambdaurora.lambdynlights.api.DynamicLightHandlers;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import xyz.venividivivi.registry.WeirdEquipmentEntityTypes;

public class DynamicLightsAddon implements DynamicLightsInitializer {
    @Override
    public void onInitializeDynamicLights() {
        DynamicLightHandlers.registerDynamicLightHandler(WeirdEquipmentEntityTypes.TORCH_ARROW.get(), lightSource -> 12);
    }
}
