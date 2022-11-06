package xyz.venividivivi.weirdequipment.config;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.venividivivi.weirdequipment.WeirdEquipmentExpectPlatform;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WeirdEquipmentConfig {
    protected static final Logger LOGGER = LoggerFactory.getLogger("Weird Equipment Config");
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    //private static final File FILE = FabricLoader.getInstance().getConfigDir().resolve("weird_equipment.json").toFile();
    private static final File FILE = WeirdEquipmentExpectPlatform.getConfigDirectory().resolve("weird_equipment.json").toFile();
    public static boolean TORCH_BOW_CAN_DAMAGE_MOBS = true;
    public static int NETHERITE_TORCH_PICKAXE_DURABILITY_LOSS = 5;
    public static float SELF_SLINGSHOT_VELOCITY = 1;
    public static void init() {
        load();
    }

    public static void load() {
        if (FILE.exists()) {
            try (FileReader reader = new FileReader(FILE)) {
                fromJson((JsonObject) JsonParser.parseReader(reader));
            } catch (Exception e) {
                LOGGER.error("Could not load config from: '" + FILE.getPath() + "', " + e);
            }
        } else {
            LOGGER.info("Could not find config, creating one with defaults");
        }
        save();
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write(GSON.toJson(toJson()));
        } catch (IOException e) {
            LOGGER.error("Failed to save config to: '" + FILE.getPath() + "', " + e);
        }
    }

    public static void fromJson(JsonObject json) {
        try {
            TORCH_BOW_CAN_DAMAGE_MOBS = JsonHelper.getBoolean(json, "torch_bow_can_damage_mobs");
            NETHERITE_TORCH_PICKAXE_DURABILITY_LOSS = JsonHelper.getInt(json, "netherite_torch_pickaxe_durability_loss");
            SELF_SLINGSHOT_VELOCITY = JsonHelper.getFloat(json, "self_slingshot_velocity");
        } catch (JsonSyntaxException e) {
            LOGGER.error("Failed to parse member in property file, setting defaults for unparsed values.");
        }
    }

    public static JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("torch_bow_can_damage_mobs", TORCH_BOW_CAN_DAMAGE_MOBS);
        json.addProperty("netherite_torch_pickaxe_durability_loss", NETHERITE_TORCH_PICKAXE_DURABILITY_LOSS);
        json.addProperty("self_slingshot_velocity", SELF_SLINGSHOT_VELOCITY);
        return json;
    }

    public static boolean hasElement(JsonArray jsonArray, String element) {
        for (JsonElement jsonElement : jsonArray) {
            if (jsonElement.getAsString().equals(element)) return true;
        }
        return false;
    }
}
