package xyz.venividivivi.weirdequipment.fabric.mixin;

import net.minecraft.block.PumpkinBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.venividivivi.weirdequipment.registry.WeirdEquipmentModTags;

@Mixin(PumpkinBlock.class)
public class PumpkinBlockMixin {
    @Redirect(method = "onUse",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean isShears(ItemStack instance, Item item) {
        return instance.isIn(WeirdEquipmentModTags.SHEARS);
    }
}
