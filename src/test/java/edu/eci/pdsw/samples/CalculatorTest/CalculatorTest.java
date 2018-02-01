/*
 * Copyright (C) 2016 hcadavid
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
package edu.eci.pdsw.samples.CalculatorTest;

import edu.eci.pdsw.util.Pair;
import edu.eci.pdsw.commandpattern_testing.CalculadoraTarifas;
import edu.eci.pdsw.commandpattern_testing.ExcepcionParametrosInvalidos;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

public class CalculatorTest {

    /**
     * Problemas identificados a la hora de ejecutar las pruebas
     * Se registraron errores tipo org.quicktheories.core.ExceptionReporter.falsify y no se pudo cargar ni calcular las tarifas con calculador 
     * de tarifas. La solucion de la incompatibilidad de esa clase debe resolver el problema.
     */
    private DateTime now;
    private float tarifa;

    @Before
    public void init() {
        now = new DateTime();
        tarifa = 1000f;
    }
    /**
     * Especificacion de la clase de equivalencia Uno
     * Edad: Edad menor a 18.
     * Dias: Dias menor a 20
     * Descuento: Solo hay un 5% de descuento.
     */

    @Test
    public void testClaseEquivalenciaUno() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(0,17).describedAs(e -> "Edad = " + e)
                   ,range(0,20).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minus(days),edad) == tarifa * (1 - 0.05));        
    }
    
    /**
     * Especificacion clase Dos
     * Edad mayor a 18 y menor e igual que 65 años.
     * Dias de antelacion menor de 20.
     * Descuento 0%.
     */
    @Test
    public void testClaseEquivalenciaDos() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(18,65).describedAs(e -> "Edad = " + e)
                   ,range(0,20).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minus(days),edad) == tarifa * (1 - 0.05));
        
    }
    
        
    /**
     * Especificacion clase Tres
     * Edad: mayor a 65 años .
     * Dias: menor a 20 dias.
     * Descuento: 8%.
     */
    
    @Test
    public void testClaseEquivalenciaTres() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(66,120).describedAs(e -> "Edad = " + e)
                   ,range(0,20).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minus(days),edad) == tarifa * (1 - 0.05));      
    }
    /**
     * Especificacion clase cuarta
     * Edad: Mayor a 65 años.
     * Dias: Mayor a 20 dias.
     * Descuento: 8% O 15% O 23%
     */
    
      @Test
    public void testClaseEquivalenciaCuatro() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(65,125).describedAs(e -> "Edad = " + e)
                   ,range(21,60).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minus(days),edad) == tarifa * (1 - 0.05));
        
    }
    
    /**
     * Especificacion Clase Quinta
     * Edad : Mayor a 18 y menor o igual a 65 años.
     * Dias: Mayor a 20 dias.
     * Descuento:15%.
     */
    
      @Test
    public void testClaseEquivalenciaCinco() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(18,65).describedAs(e -> "Edad = " + e)
                   ,range(21,60).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minus(days),edad) == tarifa * (1 - 0.05));
        
    }
    
    
     /**
     * Especificacion Clase Sexta
     * Edad : Mayor a 18 años.
     * Dias: Mayor a 20 dias.
     * Descuento:15%.
     */
    
          @Test
    public void testClaseEquivalenciaSeis() {
        CalculadoraTarifas ct=new CalculadoraTarifas();

        qt().forAll(range(18,100).describedAs(e -> "Edad = " + e)
                   ,range(21,60).describedAs(d -> "DiasAntelacion = " + d))
            .check((edad,days) -> ct.calculoTarifa(tarifa,now, now.minus(days),edad) == tarifa * (1 - 0.05));
        
    }
    
}
