package xyz.venividivivi.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import xyz.venividivivi.entity.BlockCannonShotEntity;

public class BlockCannonItem extends Item {

    public BlockCannonItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.raycast(5.0, 0, false).getType() != HitResult.Type.BLOCK) {
            Item offHandItem = user.getOffHandStack().getItem();
            if (offHandItem instanceof BlockItem) {
                user.setCurrentHand(hand);
                ItemStack ammoStack = user.getOffHandStack();
                if (!world.isClient) {
                    BlockCannonShotEntity blockCannonShotEntity = new BlockCannonShotEntity(world, user, offHandItem);
                    blockCannonShotEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0, 3F, 0);
                    ammoStack.damage(1, user, (p) -> p.sendToolBreakStatus(user.getActiveHand()));

                    world.spawnEntity(blockCannonShotEntity);
                    if (!user.getAbilities().creativeMode) {
                        ammoStack.decrement(1);
                        if (ammoStack.isEmpty()) {
                            user.getInventory().removeOne(ammoStack);
                        }
                    }
                }
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.2F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);
                return TypedActionResult.consume(user.getStackInHand(hand));
            }
        }
        return TypedActionResult.fail(user.getStackInHand(hand));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
