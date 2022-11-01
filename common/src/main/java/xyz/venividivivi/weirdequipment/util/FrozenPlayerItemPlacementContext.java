package xyz.venividivivi.weirdequipment.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/*
This class requires some explanation:
For the block cannon, the method getPlacementState() is used to determine the rotation and other non-standard
properties of the block being placed. This method requires an ItemPlacementContext, which in turn needs the player's
PlayerEntity. The ItemPlacementContext provides info including where player is looking, to determine the block rotation.
This info should be determined when the cannon fires, but due to the ItemPlacementContext also requiring the position
of where the block is being placed, has to be calculated when the block lands. This means that if the player looks
away after firing the cannon, the block will be placed with the incorrect rotation.
That's where this class comes into play. It overrides the methods that determine where the PlayerEntity is looking,
and replaces the return values with values that were calculated when the cannon was fired. It's a bit hacky, but is
(to my knowledge) the only way of going about this.
 */
public class FrozenPlayerItemPlacementContext extends ItemPlacementContext {

    public final Direction horizontalFacing, verticalFacing;
    public final Direction[] facing;

    public FrozenPlayerItemPlacementContext(World world, PlayerEntity playerEntity, Hand hand, ItemStack itemStack, BlockHitResult blockHitResult, Direction[] facing) {
        super(world, playerEntity, hand, itemStack, blockHitResult);
        this.facing = facing;
        horizontalFacing = playerEntity.getHorizontalFacing();
        verticalFacing = Direction.getLookDirectionForAxis(playerEntity, Direction.Axis.Y);
    }

    @Override
    public Direction getPlayerFacing() {
        return horizontalFacing;
    }

    @Override
    public Direction[] getPlacementDirections() {
        Direction[] directions = facing;
        if (!this.canReplaceExisting) {
            Direction direction = this.getSide();

            int i = 0;
            while (i < directions.length && directions[i] != direction.getOpposite()) {
                ++i;
            }

            if (i > 0) {
                System.arraycopy(directions, 0, directions, 1, i);
                directions[0] = direction.getOpposite();
            }

        }
        return directions;
    }

    @Override
    public Direction getVerticalPlayerLookDirection() {
        return verticalFacing;
    }

    @Override
    public Direction getPlayerLookDirection() {
        return facing[0];
    }
}
