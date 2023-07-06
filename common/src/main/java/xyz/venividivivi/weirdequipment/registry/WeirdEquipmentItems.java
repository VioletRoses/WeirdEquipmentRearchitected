package xyz.venividivivi.weirdequipment.registry;

import dev.architectury.platform.Platform;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xyz.venividivivi.weirdequipment.WeirdEquipment;
import xyz.venividivivi.weirdequipment.item.material.CactusMaterial;
import xyz.venividivivi.weirdequipment.item.material.PumpkinArmorMaterial;
import xyz.venividivivi.weirdequipment.item.*;



public class WeirdEquipmentItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(WeirdEquipment.MOD_ID, RegistryKeys.ITEM);


    public static final RegistrySupplier<Item> JACK_O_HELMET = ITEMS.register("jack_o_helmet", () ->
            new JackOHelmetItem(new PumpkinArmorMaterial(), ArmorItem.Type.HELMET, new Item.Settings().arch$tab(ItemGroups.COMBAT)));

    public static final RegistrySupplier<Item> CACTUS_SWORD = ITEMS.register("cactus_sword", () ->
            new CactusSword(new CactusMaterial(), 1, -2f, new Item.Settings().arch$tab(ItemGroups.COMBAT)));
    public static final RegistrySupplier<Item> DIRT_SWORD = ITEMS.register("dirt_sword", () ->
            new SwordItem(ToolMaterials.WOOD, -3, -2.6f, new Item.Settings().arch$tab(ItemGroups.COMBAT)));

    public static final RegistrySupplier<Item> FLINT_AND_SHEARS = ITEMS.register("flint_and_shears", () ->
            new FlintAndShearsItem(new Item.Settings().maxDamage(300).arch$tab(ItemGroups.TOOLS)));
    public static final RegistrySupplier<Item> SELF_SLINGSHOT = ITEMS.register("self_slingshot", () ->
            new SelfSlingshotItem(new Item.Settings().arch$tab(ItemGroups.TOOLS).maxDamage(350)));

    public static final RegistrySupplier<Item> DIAMOND_SHEARS = ITEMS.register("diamond_shears", () ->
            new ShearsItem(new Item.Settings().maxDamage(1000).arch$tab(ItemGroups.TOOLS)));
    public static final RegistrySupplier<Item> NETHERITE_TORCH_PICKAXE = ITEMS.register("netherite_torch_pickaxe", () ->
            new NetheriteTorchPickaxeItem(ToolMaterials.NETHERITE, 1, -2.8f, new Item.Settings().arch$tab(ItemGroups.TOOLS)));

    public static final RegistrySupplier<Item> BOTTOMLESS_WATER_BUCKET = ITEMS.register("bottomless_water_bucket", () ->
            new BucketItem(Fluids.WATER, new Item.Settings().arch$tab(ItemGroups.TOOLS).maxCount(1)));

    public static final RegistrySupplier<Item> LARGE_LAVA_BUCKET = ITEMS.register("large_lava_bucket", () ->
            new BucketItem(Fluids.LAVA, new Item.Settings().arch$tab(ItemGroups.TOOLS).maxDamage(9)));

    public static final RegistrySupplier<Item> TORCH_BOW = ITEMS.register("torch_bow", () ->
            new TorchBowItem(new Item.Settings().arch$tab(ItemGroups.TOOLS).maxDamage(384)));

    public static final RegistrySupplier<Item> TORCH_CORE = ITEMS.register("torch_core", () ->
            new Item(new Item.Settings().arch$tab(ItemGroups.INGREDIENTS)));

    public static final RegistrySupplier<Item> ROPE = ITEMS.register("rope", () ->
            new WallHangingBlockItem(WeirdEquipmentBlocks.ROPE.get(), WeirdEquipmentBlocks.WALL_ROPE.get(), new Item.Settings().arch$tab(ItemGroups.BUILDING_BLOCKS)));

    public static final RegistrySupplier<Item> SMALL_ROPE_COIL = ITEMS.register("small_rope_coil", () ->
            new RopeCoilItem(new Item.Settings().arch$tab(ItemGroups.TOOLS).maxCount(32), 9));
    public static final RegistrySupplier<Item> LARGE_ROPE_COIL = ITEMS.register("large_rope_coil", () ->
            new RopeCoilItem(new Item.Settings().arch$tab(ItemGroups.TOOLS).maxCount(32), 18));
    public static final RegistrySupplier<Item> XL_ROPE_COIL = ITEMS.register("xl_rope_coil", () ->
            new RopeCoilItem(new Item.Settings().arch$tab(ItemGroups.TOOLS).maxCount(16), 36));

    public static void register() {
        ITEMS.register();
        if (Platform.getEnv().equals(EnvType.CLIENT)) {
            TORCH_BOW.listen((item) -> {
                ItemPropertiesRegistry.register(item, new Identifier("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    }
                    return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
                });

                ItemPropertiesRegistry.register(item, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, seed) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    }
                    return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
                });
            });
        }
    }
}
