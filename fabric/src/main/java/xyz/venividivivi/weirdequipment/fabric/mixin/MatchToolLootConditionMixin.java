package xyz.venividivivi.weirdequipment.fabric.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.JsonHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MatchToolLootCondition.class)
public class MatchToolLootConditionMixin {
    @Final
    @Shadow
    ItemPredicate predicate;
    @Inject(at = @At("HEAD"), method = "test*", cancellable = true)
    public void test(LootContext lootContext, CallbackInfoReturnable<Boolean> info) {
        ItemStack itemStack = lootContext.get(LootContextParameters.TOOL);
        JsonObject jObjectPredicate = JsonHelper.asObject(predicate.toJson(), "predicate");
        if (itemStack != null && itemStack.getItem() instanceof ShearsItem && JsonHelper.hasElement(jObjectPredicate, "items")) {
            for (JsonElement i : JsonHelper.getArray(jObjectPredicate, "items")) {
                if (i.getAsString().equalsIgnoreCase("minecraft:shears")) {
                    info.setReturnValue(true);
                }
            }
        }
    }
}
