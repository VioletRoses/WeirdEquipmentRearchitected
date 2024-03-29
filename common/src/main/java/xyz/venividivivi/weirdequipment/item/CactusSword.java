package xyz.venividivivi.weirdequipment.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class CactusSword extends SwordItem {
    public CactusSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.damage(attacker.getWorld().getDamageSources().cactus(), 1);
        return super.postHit(stack, target, attacker);
    }
}
