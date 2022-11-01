package xyz.venividivivi.weirdequipment.item;

import dev.architectury.platform.Platform;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JackOHelmetItem extends ArmorItem {
    public JackOHelmetItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Platform.isFabric() && !Platform.isModLoaded("lambdynlights") || Platform.isForge() && !Platform.isModLoaded("dynamiclights")) {
            tooltip.add(Text.translatable("item.weird_equipment.jack_o_helmet.lambdynlights_warning"));
        }
    }
}
