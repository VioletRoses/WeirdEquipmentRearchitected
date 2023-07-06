package xyz.venividivivi.weirdequipment.forge;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import xyz.venividivivi.weirdequipment.WeirdEquipment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.venividivivi.weirdequipment.config.WeirdEquipmentConfigScreen;


@Mod(WeirdEquipment.MOD_ID)
public class WeirdEquipmentForge {
    public WeirdEquipmentForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(WeirdEquipment.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        WeirdEquipment.init();
        if(Platform.getEnv().equals(Dist.CLIENT) && Platform.isModLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory((minecraftClient, screen) -> WeirdEquipmentConfigScreen.init(screen)));
        }
    }
}
