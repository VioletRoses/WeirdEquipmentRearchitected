package xyz.venividivivi.weirdequipment.entity.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class BlockCannonShotEntityRenderer extends FlyingItemEntityRenderer {
    public BlockCannonShotEntityRenderer(EntityRendererFactory.Context context) {
        this(context, 2.25f, false);
    }

    public BlockCannonShotEntityRenderer(EntityRendererFactory.Context ctx, float scale, boolean lit) {
        super(ctx, scale, lit);
    }
}
