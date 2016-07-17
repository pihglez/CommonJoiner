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
package org.ph.Timer;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Timer {
    private final long T0;
    private long t1;
    private String description;
    
    public Timer(String description) {
        this.T0 = System.nanoTime();
        t1 = T0;
        this.description = description;
    }
    
    public void setT1() {
        this.t1 = System.nanoTime();
    }
    
    public long getEllapsedTime(){
        return t1 - T0;
    }
}
