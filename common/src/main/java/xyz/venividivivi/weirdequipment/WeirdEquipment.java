package xyz.venividivivi.weirdequipment;

import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentBlocks;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentEntityTypes;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentItems;
import xyz.venividivivi.weirdequipment.registry.client.WeirdEquipmentEntityRenderers;
import xyz.venividivivi.weirdequipment.registry.client.WeirdEquipmentModelProviders;

public class WeirdEquipment {
    public static final String MOD_ID = "weird_equipment";

    public static void init() {
        WeirdEquipmentBlocks.register();
        WeirdEquipmentItems.register();
        WeirdEquipmentEntityTypes.register();
        if(Platform.getEnv().equals(EnvType.CLIENT)) {
            WeirdEquipmentEntityRenderers.register();
            WeirdEquipmentModelProviders.register();
        }
        System.out.println(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
