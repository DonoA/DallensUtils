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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Donovan Allen
 */
@NoArgsConstructor
public class JsonArmor implements SaveType.NativeType {

    @Getter
    @Setter
    private JsonItemStack Helm;
    @Getter
    @Setter
    private JsonItemStack Chest;
    @Getter
    @Setter
    private JsonItemStack Leg;
    @Getter
    @Setter
    private JsonItemStack Boot;

    public JsonArmor(ItemStack[] is) {
        Helm = new JsonItemStack(is[3]);
        Chest = new JsonItemStack(is[2]);
        Leg = new JsonItemStack(is[1]);
        Boot = new JsonItemStack(is[0]);
    }

    /**
     * sets Player armor to this save
     *
     * @param p The player who should be set to the save
     */
    public void GiveTo(Player p) {
        p.getInventory().setHelmet(Helm.toJavaObject());
        p.getInventory().setChestplate(Chest.toJavaObject());
        p.getInventory().setLeggings(Leg.toJavaObject());
        p.getInventory().setBoots(Boot.toJavaObject());
        p.updateInventory();
    }

    @Override
    public Object toJavaObject() {
        throw new UnsupportedOperationException("Not supported for EnchantmentMeta");
    }

}
