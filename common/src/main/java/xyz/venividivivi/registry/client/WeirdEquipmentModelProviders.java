package xyz.venividivivi.registry.client;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.util.Identifier;
import xyz.venividivivi.registry.WeirdEquipmentItems;

public class WeirdEquipmentModelProviders {
    public static void register() {
        WeirdEquipmentItems.TORCH_BOW.listen((item -> {
            ItemPropertiesRegistry.register(WeirdEquipmentItems.TORCH_BOW.get(), new Identifier("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                }
                return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
            });

            ItemPropertiesRegistry.register(WeirdEquipmentItems.TORCH_BOW.get(), new Identifier("pulling"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                }
                return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
            });
        }));
    }



}