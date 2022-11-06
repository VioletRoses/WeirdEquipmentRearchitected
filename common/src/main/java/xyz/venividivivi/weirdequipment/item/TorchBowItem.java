package xyz.venividivivi.weirdequipment.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import xyz.venividivivi.weirdequipment.config.WeirdEquipmentConfig;
import xyz.venividivivi.weirdequipment.entity.TorchArrowEntity;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentItems;

public class TorchBowItem extends BowItem {
    public TorchBowItem(Settings settings) {
        super(settings);
    }

    public ItemStack ammoStack = null;

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean isCreativeOrInfinity = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = ammoStack;
            if (!itemStack.isEmpty() || isCreativeOrInfinity) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.TORCH);
                }

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!((double) f < 0.1)) {
                    boolean isCreativeAndHasAmmo = isCreativeOrInfinity && itemStack.isOf(Items.TORCH);
                    if (!world.isClient) {
                        TorchArrowEntity torchArrowEntity = new TorchArrowEntity(world, playerEntity);
                        torchArrowEntity.setDamage(1.0f);
                        torchArrowEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.0F, 1.0F);
                        if(f == 1.0f) torchArrowEntity.setCritical(true);

                        int powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (powerLevel > 0) torchArrowEntity.setDamage(torchArrowEntity.getDamage() + (double)powerLevel * 0.5 + 0.5);

                        int punchLevel = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (punchLevel > 0) torchArrowEntity.setPunch(punchLevel);

                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) torchArrowEntity.fireTime = 6;

                        world.spawnEntity(torchArrowEntity);
                        if (!isCreativeAndHasAmmo && !playerEntity.getAbilities().creativeMode && itemStack.isOf(Items.TORCH)) {
                            itemStack.decrement(1);
                            if (itemStack.isEmpty()) {
                                playerEntity.getInventory().removeOne(itemStack);
                                playerEntity.getInventory().removeOne(itemStack);
                            }
                        } else if (!isCreativeAndHasAmmo && itemStack.getItem().equals(WeirdEquipmentItems.NETHERITE_TORCH_PICKAXE.get())) {
                            user.getMainHandStack().damage(WeirdEquipmentConfig.NETHERITE_TORCH_PICKAXE_DURABILITY_LOSS, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.preferredHand));
                        }
                            stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                    }
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                }
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Inventory inventory = user.getInventory();
        for(int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack = inventory.getStack(i);
            if (inventory.getStack(i).getItem().equals(Items.TORCH) || user.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, user.getStackInHand(hand)) > 0) {
                ammoStack = itemStack;
                user.setCurrentHand(hand);
                return TypedActionResult.consume(user.getStackInHand(hand));
            }
        }
        if (user.getMainHandStack().getItem().equals(WeirdEquipmentItems.NETHERITE_TORCH_PICKAXE.get())) {
            user.setCurrentHand(hand);
            ammoStack = user.getMainHandStack();
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
        return TypedActionResult.fail(user.getStackInHand(hand));
    }
}
