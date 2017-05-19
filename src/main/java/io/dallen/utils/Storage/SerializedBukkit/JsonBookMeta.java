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
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.inventory.meta.BookMeta;

/**
 *
 * @author Donovan Allen
 */
@NoArgsConstructor
public class JsonBookMeta implements SaveType.NativeType {

    @Getter
    @Setter
    private String author;
    @Getter
    @Setter
    private List<String> pages;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private List<String> lore;

    public JsonBookMeta(BookMeta meta) {
        this.author = meta.getAuthor();
        this.pages = meta.getPages();
        this.title = meta.getTitle();
        if (meta.hasLore()) {
            this.lore = meta.getLore();
        }
    }

    @Override
    public Object toJavaObject() {
        throw new UnsupportedOperationException("Not supported for EnchantmentMeta");
    }

}
