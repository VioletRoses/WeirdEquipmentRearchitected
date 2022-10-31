package xyz.venividivivi;

import com.google.common.base.Suppliers;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.Registries;
import net.fabricmc.api.EnvType;
import xyz.venividivivi.registry.WeirdEquipmentBlocks;
import xyz.venividivivi.registry.WeirdEquipmentEntityTypes;
import xyz.venividivivi.registry.WeirdEquipmentItems;
import xyz.venividivivi.registry.client.WeirdEquipmentEntityRenderers;
import xyz.venividivivi.registry.client.WeirdEquipmentModelProviders;

import java.util.function.Supplier;

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
