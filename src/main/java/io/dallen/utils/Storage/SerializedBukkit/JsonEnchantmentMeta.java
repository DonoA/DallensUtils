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
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Donovan Allen
 */
@NoArgsConstructor
public class JsonEnchantmentMeta implements SaveType.NativeType {

    @Getter
    @Setter
    private HashMap<String, Integer> enchants = new HashMap();

    public JsonEnchantmentMeta(ItemMeta meta) {
        for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
            enchants.put(entry.getKey().getName(), entry.getValue());
        }
    }

    @Override
    public Object toJavaObject() {
        throw new UnsupportedOperationException("Not supported for EnchantmentMeta");
    }
}
