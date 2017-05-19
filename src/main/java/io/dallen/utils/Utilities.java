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
package io.dallen.utils;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Donovan Allen
 */
public class Utilities extends JavaPlugin{
    
    @Getter
    private static Utilities plugin;
    
    public Utilities(){
        plugin = this;
    }
}
