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
 * Contador de tiempo
 * T0
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class Timer {
    /**
     * <strong>T0</strong> Tiempo inicial sobre el que se calcula el final
     */
    private final long T0;
    
    /**
     * Tiempo <strong>T1</strong> final sobre el que se calcula la duración
     */
    private long t1;
    
    /**
     * Descripción asociada
     */
    private String description;
    
    /**
     * Constructor que establece el tiempo T0 y T1 al momento actual, medido en
     * nanosegundos (nanoTime())
     * @param description <strong>String</strong> Descripción asociada
     */
    public Timer(String description) {
        this.T0 = System.nanoTime();
        t1 = T0;
        this.description = description;
    }
    
    /**
     * Establece el tiempo T1 al momento actual: System.nanoTime()
     */
    public void setT1() {
        this.t1 = System.nanoTime();
    }
    
    /**
     * Devuelve el tiempo transcurrido entre el inicio y la última llamada al
     * establecimiento de T1
     * @return <strong>Long</strong> El tiempo en nanosegundos entre T0 y T1
     */
    public long getEllapsedTime(){
        return t1 - T0;
    }
}
