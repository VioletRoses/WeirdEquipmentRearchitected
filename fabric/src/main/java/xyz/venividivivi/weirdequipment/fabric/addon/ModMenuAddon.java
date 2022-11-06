package xyz.venividivivi.weirdequipment.fabric.addon;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import xyz.venividivivi.weirdequipment.config.WeirdEquipmentConfigScreen;

public class ModMenuAddon implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) return WeirdEquipmentConfigScreen::init;
        else return parent -> null;
    }

}
