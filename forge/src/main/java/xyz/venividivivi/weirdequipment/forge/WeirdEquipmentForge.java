package xyz.venividivivi.weirdequipment.forge;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import xyz.venividivivi.weirdequipment.WeirdEquipment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.venividivivi.weirdequipment.config.WeirdEquipmentConfigScreen;

import java.util.function.BiFunction;

@Mod(WeirdEquipment.MOD_ID)
public class WeirdEquipmentForge {
    public WeirdEquipmentForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(WeirdEquipment.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        WeirdEquipment.init();
        if(Platform.getEnv().equals(Dist.CLIENT) && Platform.isModLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory(new BiFunction<MinecraftClient, Screen, Screen>() {
                        @Override
                        public Screen apply(MinecraftClient minecraftClient, Screen screen) {
                            return WeirdEquipmentConfigScreen.init(screen);
                        }
                    }));
        }
    }
}
