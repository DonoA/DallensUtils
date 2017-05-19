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
package io.dallen.utils.Storage.SerializedBukkit;

import io.dallen.utils.Storage.SaveType;
import java.awt.geom.Ellipse2D;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Donovan Allen
 */
@NoArgsConstructor
public class JsonEllipse implements SaveType.NativeType {

    @Getter
    private double length;
    @Getter
    private double width;
    @Getter
    private double x;
    @Getter
    private double y;

    public JsonEllipse(Ellipse2D base) {
        this.length = base.getHeight();
        this.width = base.getWidth();
        this.x = base.getX();
        this.y = base.getY();
    }

    @Override
    public Ellipse2D.Double toJavaObject() {
        return new Ellipse2D.Double(x, y, length, width);
    }

}
