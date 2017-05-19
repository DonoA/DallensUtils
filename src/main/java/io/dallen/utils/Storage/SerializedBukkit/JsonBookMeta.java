/*
 * Copyright 2016 Donovan Allen.
 * 
 * This file is part of Kingdoms for the Morphics Network.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 */
package io.dallen.utils.Storage.SerializedBukkit;

import io.dallen.kingdoms.utilities.Storage.SaveType;
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
