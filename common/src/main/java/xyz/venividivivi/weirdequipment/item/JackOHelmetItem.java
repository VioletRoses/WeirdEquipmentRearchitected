package xyz.venividivivi.weirdequipment.item;

import dev.architectury.platform.Platform;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JackOHelmetItem extends ArmorItem {
    public JackOHelmetItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Platform.isModLoaded("optifine") || Platform.isModLoaded("optifabric")) {
        } else if (Platform.isFabric() && !Platform.isModLoaded("lambdynlights")) {
            tooltip.add(Text.translatable("item.weird_equipment.jack_o_helmet.lambdynlights_warning.fabric"));
        } else if (Platform.isForge() && !Platform.isModLoaded("dynamiclights")) {
            tooltip.add(Text.translatable("item.weird_equipment.jack_o_helmet.lambdynlights_warning.forge"));
        }
    }
}
