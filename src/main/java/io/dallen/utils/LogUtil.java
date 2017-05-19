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

import io.dallen.utils.Storage.DatabaseInterfaces.JsonInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 *
 * @author Donovan
 */
public class LogUtil {

    private static boolean isDebug = true;

    public static void printDebug(Object msg) {
        if (isDebug) {
            Bukkit.getLogger().log(Level.INFO, "[{0}][DEBUG] {1}", new Object[]{getPluginName(), msg.toString()});
        }
    }

    public static void printDebugJson(Object obj) {
        try {
            printDebug(JsonInterface.getJSonParser().writeValueAsString(obj));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(LogUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printDebugStack(Exception ex) {
        if (isDebug) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            Bukkit.getLogger().log(Level.INFO, "[{0}][DEBUG][ERROR] {1}", new Object[]{getPluginName(), sw.toString()});
        }
    }

    public static void printInfo(Object msg) {
        Bukkit.getLogger().log(Level.INFO, "[{0}] {1}", new Object[]{getPluginName(), msg.toString()});
    }

    public static void printErr(Object msg) {
        Bukkit.getLogger().log(Level.SEVERE, "[{0}] [ERROR] {1}", new Object[]{getPluginName(), msg.toString()});
    }

    public static String getPluginName() {
        String[] pkgs = Thread.currentThread().getStackTrace()[3].getClassName().split("\\.");
        return cap(pkgs[2]) + cap(pkgs[3]);
    }

    private static String cap(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
