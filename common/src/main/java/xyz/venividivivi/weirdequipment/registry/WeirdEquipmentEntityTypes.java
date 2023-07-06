package xyz.venividivivi.weirdequipment.registry;

import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureSet;
import xyz.venividivivi.weirdequipment.WeirdEquipment;
import xyz.venividivivi.weirdequipment.entity.RopeCoilEntity;
import xyz.venividivivi.weirdequipment.entity.TorchArrowEntity;

public class WeirdEquipmentEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITYTYPES = DeferredRegister.create(WeirdEquipment.MOD_ID, RegistryKeys.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<TorchArrowEntity>> TORCH_ARROW = ENTITYTYPES.register("torch_arrow", () ->
            new EntityType<TorchArrowEntity>(TorchArrowEntity::new, SpawnGroup.MISC, false, false, true, false, ImmutableSet.<Block>builder().build(), EntityDimensions.fixed(0.3f, 0.3f), 1000000, 1, FeatureSet.empty()));
    public static final RegistrySupplier<EntityType<RopeCoilEntity>> ROPE_COIL = ENTITYTYPES.register("rope_coil", () ->
            new EntityType<RopeCoilEntity>(RopeCoilEntity::new, SpawnGroup.MISC, false, false, false, false, ImmutableSet.<Block>builder().build(), EntityDimensions.fixed(0.3f, 0.3f), 1000000, 1, FeatureSet.empty()));

    public static void register() {
        ENTITYTYPES.register();
    }
}
