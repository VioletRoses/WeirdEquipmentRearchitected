package xyz.venividivivi.registry;

import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import xyz.venividivivi.WeirdEquipment;
import xyz.venividivivi.entity.BlockCannonShotEntity;
import xyz.venividivivi.entity.RopeCoilEntity;
import xyz.venividivivi.entity.TorchArrowEntity;

public class WeirdEquipmentEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITYTYPES = DeferredRegister.create(WeirdEquipment.MOD_ID, Registry.ENTITY_TYPE_KEY);

    public static final RegistrySupplier<EntityType<BlockCannonShotEntity>> BLOCK_CANNON_SHOT = ENTITYTYPES.register("block_cannon_shot", () ->
            new EntityType<BlockCannonShotEntity>(BlockCannonShotEntity::new, SpawnGroup.MISC, false, false, true, false, ImmutableSet.<Block>builder().build(), EntityDimensions.fixed(0.6f, 0.6f), 1000000, 1));

    public static final RegistrySupplier<EntityType<TorchArrowEntity>> TORCH_ARROW = ENTITYTYPES.register("torch_arrow", () ->
            new EntityType<TorchArrowEntity>(TorchArrowEntity::new, SpawnGroup.MISC, false, false, true, false, ImmutableSet.<Block>builder().build(), EntityDimensions.fixed(0.3f, 0.3f), 1000000, 1));
    public static final RegistrySupplier<EntityType<RopeCoilEntity>> ROPE_COIL = ENTITYTYPES.register("rope_coil", () ->
            new EntityType<RopeCoilEntity>(RopeCoilEntity::new, SpawnGroup.MISC, false, false, false, false, ImmutableSet.<Block>builder().build(), EntityDimensions.fixed(0.3f, 0.3f), 1000000, 1));

    public static void register() {
        ENTITYTYPES.register();
    }
}
