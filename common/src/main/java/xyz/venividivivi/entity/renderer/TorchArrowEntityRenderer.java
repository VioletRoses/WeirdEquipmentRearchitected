package xyz.venividivivi.entity.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import xyz.venividivivi.WeirdEquipment;
import xyz.venividivivi.entity.TorchArrowEntity;

public class TorchArrowEntityRenderer extends ProjectileEntityRenderer<TorchArrowEntity> {
    public TorchArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(TorchArrowEntity entity) {
        return new Identifier(WeirdEquipment.MOD_ID, "textures/entity/projectiles/torch_arrow.png");
    }
}
