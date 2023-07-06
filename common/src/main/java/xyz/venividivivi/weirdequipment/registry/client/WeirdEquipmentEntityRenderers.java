package xyz.venividivivi.weirdequipment.registry.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import xyz.venividivivi.weirdequipment.entity.renderer.TorchArrowEntityRenderer;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentEntityTypes;

@Environment(EnvType.CLIENT)
public class WeirdEquipmentEntityRenderers {

    public static void register() {
        EntityRendererRegistry.register(WeirdEquipmentEntityTypes.ROPE_COIL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(WeirdEquipmentEntityTypes.TORCH_ARROW, TorchArrowEntityRenderer::new);
    }
}
