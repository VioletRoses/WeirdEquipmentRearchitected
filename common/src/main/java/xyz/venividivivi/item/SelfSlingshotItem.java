package xyz.venividivivi.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
//import xyz.venividivivi.weirdequipment.config.WeirdEquipmentConfig;

public class SelfSlingshotItem extends BowItem {
    public SelfSlingshotItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            float f = getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks);
            float soundPitch = 0.05f;
            if (!((double) f < 0.1)) {
                if (user.raycast(5.0, 0, false).getType() == HitResult.Type.BLOCK) {
                    //f = f * WeirdEquipmentConfig.SELF_SLINGSHOT_VELOCITY;
                    float yaw = playerEntity.getYaw(), pitch = playerEntity.getPitch();
                    float g = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
                    float h = -MathHelper.sin((pitch) * 0.017453292F);
                    float j = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
                    playerEntity.setVelocity((new Vec3d(g, h, j)).normalize().multiply(-2.25f * f, -1.25f * f, -2.25f * f));

                    stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                    soundPitch = 1.0f;
                }
                world.playSound(playerEntity, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            }
        }
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
