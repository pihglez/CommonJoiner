/*
 * Copyright (C) 2016 Pedro I. Hernández G. <pihglez@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.ph.commonjoiner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.ph.Timer.Timer;

/**
 *
 * @author Pedro I. Hernández G. <pihglez@gmail.com>
 */
public class CommonJoiner {
    public static final String VERSION = "v0.1.0.0";
    
    public static void main(String[] args) {
        
        try{
            Options options = new Options();
            options.addOption("f", true,    "archivo de entrada inicial" );
            options.addOption("g", true,    "archivo de entrada a comparar" );
            options.addOption("o", true,    "archivo de salida (omitir para salida por pantalla)" );
            options.addOption("h", false,   "muestra esta ayuda" );
            options.addOption("l", false,   "muestra la licencia GPL v.3 al completo" );
            options.addOption("s", false,   "muestra estadisticas al finalizar el proceso" );
            options.addOption("v", false,   "muestra la version del software" );
            
            
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            
            if((args.length == 1)) {
                if(cmd.hasOption("l")) System.out.println(GNULicense.FULLLICENSE);
                if(cmd.hasOption("v")) System.out.println(InfoCommonJoiner.INFO);
                if(cmd.hasOption("h")) printSmallHelp(options);
                
                
            } else {
                if(cmd.hasOption("f") && cmd.hasOption("g") && cmd.hasOption("o")){
                    String entrada1     = cmd.getOptionValue("f");
                    String entrada2     = cmd.getOptionValue("g");
                    String salida       = cmd.getOptionValue("o");

                    boolean okEntrada1  = new File(entrada1).exists();
                    boolean okEntrada2  = new File(entrada2).exists();
                    boolean okSalida    = !(new File(salida).exists());

                    if (okEntrada1 && okEntrada2) {
                        String thisLine     = null;
                        HashSet nums1       = new HashSet<Integer>();
                        ArrayList numComun  = new ArrayList<Integer>();
                        
                        System.out.println(InfoCommonJoiner.INTRO);

                        try{
                            Timer tF1 = new Timer("Inicio lectura archivo 1");
                            BufferedReader br1 = new BufferedReader(new FileReader(new File(entrada1)));
                            while ((thisLine = br1.readLine()) != null) {
                                nums1.add(Integer.parseInt(thisLine));
                            }
                            tF1.setT1();
                            br1.close();
                            System.out.println("Número de elementos únicos en '" + entrada1 + "': " + nums1.size());

                            Timer tF2 = new Timer("Inicio lectura archivo 2");
                            long readedLines = 0;
                            BufferedReader br2 = new BufferedReader(new FileReader(new File(entrada2)));
                            while ((thisLine = br2.readLine()) != null) {
                                readedLines++;
                                if (!nums1.add(Integer.parseInt(thisLine))){
                                    numComun.add(Integer.parseInt(thisLine));
                                }
                            }
                            tF2.setT1();
                            br2.close();
                            System.out.println("Número de elementos leídos en '" + entrada2 + "': " + readedLines);

                            Timer tF3 = new Timer("Inicio escritura de archivo");
                            if (!okSalida){
                                System.out.println("Número de elementos comunes: " + numComun.size());
                                System.out.println("" + Arrays.toString(numComun.toArray()));
                            } else {
                                // escritura del archivo
                                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(salida)));
                                // ordenamiento de los resultados
                                Collections.sort(numComun);

                                for (Object i : numComun) {
                                    bw.write(i.toString());
                                    bw.newLine();
                                }
                                bw.close();
                                tF3.setT1();
                                
                                System.out.println("Número de elementos comunes escritos: " + numComun.size());
                            }
                            
                            if(cmd.hasOption("s")) {
                                System.out.println("\nESTADISTICAS");
                                System.out.println("  Tiempo de operación de lectura de archivo " + entrada1 + ": " + tF1.getEllapsedTime() + "ms");
                                System.out.println("  Tiempo de operación de lectura de archivo " + entrada2 + ": " + tF2.getEllapsedTime() + "ms");
                                if (okSalida) System.out.println("  Tiempo de operación de escritura de archivo " + salida + ": " + tF3.getEllapsedTime() + "ms");
                                long tiempoTotal = tF1.getEllapsedTime() + tF2.getEllapsedTime() + ((okSalida) ? tF3.getEllapsedTime() : 0);
                                System.out.println("  Tiempo total de las operaciones " + ": " + tiempoTotal + "ms");
                            }

                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                } else {
                    printSmallHelp(options);
                }
            }
            
        } catch (ParseException ex){
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        
    }
    
    private static void printSmallHelp(Options options) {
        System.out.println(InfoCommonJoiner.INTRO);
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar CommonJoiner.jar", options);
    }
}