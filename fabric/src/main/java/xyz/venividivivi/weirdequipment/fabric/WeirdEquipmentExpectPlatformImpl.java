package xyz.venividivivi.weirdequipment.fabric;

import xyz.venividivivi.weirdequipment.WeirdEquipmentExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class WeirdEquipmentExpectPlatformImpl {
    /**
     * This is our actual method to {@link WeirdEquipmentExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
