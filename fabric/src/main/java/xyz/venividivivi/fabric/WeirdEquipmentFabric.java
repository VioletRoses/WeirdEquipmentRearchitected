package xyz.venividivivi.fabric;

import xyz.venividivivi.WeirdEquipment;
import net.fabricmc.api.ModInitializer;

public class WeirdEquipmentFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        WeirdEquipment.init();
    }
}
