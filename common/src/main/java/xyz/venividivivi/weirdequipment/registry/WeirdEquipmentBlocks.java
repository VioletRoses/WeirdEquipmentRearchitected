package xyz.venividivivi.weirdequipment.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import xyz.venividivivi.weirdequipment.WeirdEquipment;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import xyz.venividivivi.weirdequipment.block.RopeBlock;
import xyz.venividivivi.weirdequipment.block.WallRopeBlock;

public class WeirdEquipmentBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(WeirdEquipment.MOD_ID, Registry.BLOCK_KEY);

    public static final RegistrySupplier<Block> ROPE = BLOCKS.register("rope", () ->
            new RopeBlock(AbstractBlock.Settings.of(Material.WOOL, MapColor.BROWN)));
    public static final RegistrySupplier<Block> WALL_ROPE = BLOCKS.register("wall_rope", () ->
            new WallRopeBlock(AbstractBlock.Settings.of(Material.WOOL, MapColor.BROWN)));

    public static void register() {
        BLOCKS.register();
    }
}
