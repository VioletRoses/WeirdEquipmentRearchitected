package xyz.venividivivi.weirdequipment.forge;

import dev.architectury.platform.forge.EventBuses;
import xyz.venividivivi.weirdequipment.WeirdEquipment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WeirdEquipment.MOD_ID)
public class WeirdEquipmentForge {
    public WeirdEquipmentForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(WeirdEquipment.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        WeirdEquipment.init();
    }
}
