package xyz.venividivivi.weirdequipment.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentItems;

@Mixin (ZombieEntity.class)
public class ZombieEntityMixin extends MobEntity {

    protected ZombieEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        System.out.println(player.getStackInHand(hand).getTranslationKey());
        if (getStackInHand(this.getActiveHand()).isEmpty() && player.getStackInHand(hand).getTranslationKey().equalsIgnoreCase("item.weird_equipment.dirt_sword")) {
            setStackInHand(this.getActiveHand(), new ItemStack(WeirdEquipmentItems.DIRT_SWORD.get()));
            player.setStackInHand(hand, new ItemStack(Items.AIR));
            return ActionResult.PASS;
        }
        return super.interactMob(player, hand);
    }
}
