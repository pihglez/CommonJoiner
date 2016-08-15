/*
 * Copyright (C) 2016 Pedro I. Hernández G. <pihglez@gmail.com>
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
package org.ph.commonjoiner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Archives {
    public static void copy (File source, File folder) {
        try {
            //  LinkOption.NOFOLLOW_LINKS
            Files.copy(source.toPath(), Paths.get(folder.getAbsolutePath(), source.getName()), StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException ex) {
            System.out.println("-> Error en la copia... Continuando el proceso si es posible...");
        }
    }
}
