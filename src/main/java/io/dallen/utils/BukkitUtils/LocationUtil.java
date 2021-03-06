/*
 * This file is part of DallensUtils.
 * 
 * DallensUtils is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * DallensUtils is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DallensUtils.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 */
package io.dallen.utils.BukkitUtils;

import java.awt.Point;
import java.awt.Polygon;
import org.bukkit.Location;
import org.bukkit.World;

/**
 *
 * @author donoa_000
 */
public class LocationUtil {

    public static Point asPoint(Location l) {
        return new Point(l.getBlockX(), l.getBlockZ());
    }

    public static Location asLocation(Point p, World w, int Ycord) {
        return new Location(w, p.getX(), Ycord, p.getY());
    }

    public static Point calcCenter(Point[] corners) {
        long X = 0;
        long Y = 0;
        for (int i = 0; i < corners.length; i++) {
            X += corners[i].getX();
            Y += corners[i].getY();
        }
        X /= corners.length;
        Y /= corners.length;
        return new Point((int) X, (int) Y);
    }
}
