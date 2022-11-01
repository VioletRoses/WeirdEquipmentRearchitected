package xyz.venividivivi.weirdequipment.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import xyz.venividivivi.weirdequipment.entity.RopeCoilEntity;

public class RopeCoilItem extends Item {
    public final int count;
    public RopeCoilItem(Settings settings, int count) {
        super(settings);
        this.count = count;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            RopeCoilEntity ropeCoilEntity = new RopeCoilEntity(world, user, this);
            ropeCoilEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(ropeCoilEntity);
        }
        if (!user.getAbilities().creativeMode) itemStack.decrement(1);
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

}
