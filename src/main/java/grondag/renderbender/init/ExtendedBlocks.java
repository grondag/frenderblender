/*
 * Copyright © Contributing Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional copyright and licensing notices may apply for content that was
 * included from other projects. For more information, see ATTRIBUTION.md.
 */

package grondag.renderbender.init;

import static grondag.renderbender.init.BasicBlocks.ITEM_FUNCTION_STANDARD;
import static grondag.renderbender.init.BasicBlocks.register;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ExtendedBlocks {
	private static final Block SHADER_BLOCK = new Block(Properties
			.of(Material.STONE).strength(1, 1)
			.color(MaterialColor.COLOR_CYAN)
			.isValidSpawn((s, v, p, o) -> false)
			.isRedstoneConductor((s, v, p) -> false)
			.isSuffocating((s, v, p) -> false));

	private static final Block CONDITIONAL_BLOCK = new Block(Properties
			.of(Material.GLASS).strength(1, 1)
			.color(MaterialColor.COLOR_CYAN)
			.noCollission()
			.isValidSpawn((s, v, p, o) -> false)
			.isRedstoneConductor((s, v, p) -> false)
			.isSuffocating((s, v, p) -> false)
	) {
		@Override
		public boolean skipRendering(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
			return blockState_2.getBlock() == this ? true : super.skipRendering(blockState_1, blockState_2, direction_1);
		}

		@Override
		public boolean propagatesSkylightDown(BlockState blockState_1, BlockGetter blockView_1, BlockPos blockPos_1) {
			return true;
		}

		@Override
		public int getLightBlock(BlockState blockState_1, BlockGetter blockView_1, BlockPos blockPos_1) {
			return 0;
		}
	};

	public static Item CONDITION_ITEM;

	public static void initialize() {
		register(SHADER_BLOCK, "shader", ITEM_FUNCTION_STANDARD);

		CONDITION_ITEM = register(CONDITIONAL_BLOCK, "conditional", ITEM_FUNCTION_STANDARD);
	}
}
