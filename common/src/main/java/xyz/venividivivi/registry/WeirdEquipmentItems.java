package xyz.venividivivi.registry;

import dev.architectury.platform.Platform;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import xyz.venividivivi.WeirdEquipment;
import xyz.venividivivi.item.*;
import xyz.venividivivi.item.material.CactusMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;


public class WeirdEquipmentItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(WeirdEquipment.MOD_ID, Registry.ITEM_KEY);


    public static final RegistrySupplier<Item> CACTUS_SWORD = ITEMS.register("cactus_sword", () ->
            new CactusSword(new CactusMaterial(), 1, -2f, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final RegistrySupplier<Item> SELF_SLINGSHOT = ITEMS.register("self_slingshot", () ->
            new SelfSlingshotItem(new Item.Settings().group(ItemGroup.TOOLS).maxDamage(350)));

    public static final RegistrySupplier<Item> BLOCK_CANNON = ITEMS.register("block_cannon", () ->
            new BlockCannonItem(new Item.Settings().group(ItemGroup.TOOLS).maxDamage(640)));

    public static final RegistrySupplier<Item> NETHERITE_TORCH_PICKAXE = ITEMS.register("netherite_torch_pickaxe", () ->
            new NetheriteTorchPickaxeItem(ToolMaterials.NETHERITE, 1, -2.8f, new Item.Settings().group(ItemGroup.TOOLS)));

    public static final RegistrySupplier<Item> TORCH_BOW = ITEMS.register("torch_bow", () ->
            new TorchBowItem(new Item.Settings().group(ItemGroup.TOOLS).maxDamage(384)));

    public static final RegistrySupplier<Item> ROPE = ITEMS.register("rope", () ->
            new WallHangingBlockItem(WeirdEquipmentBlocks.ROPE.get(), WeirdEquipmentBlocks.WALL_ROPE.get(), new Item.Settings().group(ItemGroup.MISC)));
    public static final RegistrySupplier<Item> SMALL_ROPE_COIL = ITEMS.register("small_rope_coil", () ->
            new RopeCoilItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(32), 9));
    public static final RegistrySupplier<Item> MEDIUM_ROPE_COIL = ITEMS.register("medium_rope_coil", () ->
            new RopeCoilItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(32), 18));
    public static final RegistrySupplier<Item> LARGE_ROPE_COIL = ITEMS.register("large_rope_coil", () ->
            new RopeCoilItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(16), 36));

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