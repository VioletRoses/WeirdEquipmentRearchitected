package xyz.venividivivi.weirdequipment.forge;

import xyz.venividivivi.weirdequipment.WeirdEquipmentExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class WeirdEquipmentExpectPlatformImpl {
    /**
     * This is our actual method to {@link WeirdEquipmentExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
