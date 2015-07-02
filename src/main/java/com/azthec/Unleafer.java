/*
 * Copyright (c) 2015 Sean Porter <glitchkey@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.azthec;

//* IMPORTS: JDK/JRE
	//* NOT NEEDED
//* IMPORTS: SPONGE
	import org.spongepowered.api.event.block.BlockBreakEvent;
	import org.spongepowered.api.event.block.BlockBurnEvent;
	import org.spongepowered.api.event.block.LeafDecayEvent;
	import org.spongepowered.api.event.Subscribe;
	import org.spongepowered.api.plugin.Plugin;
	import org.spongepowered.api.world.extent.Extent;
	import org.spongepowered.api.world.Location;
//* IMPORTS: OTHER
	//* NOT NEEDED

@Plugin(id = "Unleafer", name = "Unleafer", version = "1.0")

public class Unleafer
{
	@Subscribe
	public void onBlockBreak(BlockBreakEvent event)
	{
		checkLog(event.getBlock());
	}

	@Subscribe
	public void onBlockBurn(BlockBurnEvent event)
	{
		checkLog(event.getBlock());
	}

	@Subscribe
	public void onLeafDecay(LeafDecayEvent event)
	{
		checkBlocks(event.getBlock());
	}

	public void checkLog(Location location)
	{
		String name = location.getBlockType().getName();
		name = name.toUpperCase().toLowerCase();

		if (!name.contains("log") && !name.contains("leaves"))
			return;

		checkBlocks(location);
	}

	public void checkBlocks(Location location)
	{
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		Extent e = location.getExtent();
		Location loc;
		String name;

		for (int cx = x - 1; cx < x + 2; cx++) {
			for (int cy = y - 1; cy < y + 2; cy++) {
				for (int cz = z - 1; cz < z + 2; cz++) {
					loc = new Location(e, cx, cy, cz);
					name = loc.getBlockType().getName();
					name = name.toUpperCase().toLowerCase();

					if (!name.contains("leaves"))
						continue;

					loc.addScheduledUpdate(0, 1);
				}
			}
		}
	}
}
