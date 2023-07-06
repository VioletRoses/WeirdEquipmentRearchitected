package xyz.venividivivi.weirdequipment.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class WeirdEquipmentModTags {
    public static final TagKey<Item> SHEARS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "shears"));
}
