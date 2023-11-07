package xyz.venividivivi.weirdequipment.forge;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import xyz.venividivivi.weirdequipment.WeirdEquipment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.venividivivi.weirdequipment.config.WeirdEquipmentConfigScreen;
import xyz.venividivivi.weirdequipment.forge.addon.RyoamicLightsAddon;


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
        if(Platform.getEnv().equals(Dist.CLIENT) && Platform.isModLoaded("ryoamiclights")) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupComplete);
        }

    }

    private void setupComplete(FMLLoadCompleteEvent evt) {
        new RyoamicLightsAddon().onInitializeDynamicLights();
    }
}
