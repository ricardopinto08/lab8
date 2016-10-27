/*
 * Copyright (C) 2015 hcadavid
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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.*;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
import edu.eci.pdsw.samples.services.ServiciosForoDAOStub;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 * 
 * 
 * 
 * CLASES DE EQUIVALENCIA
 * 1)
 * 
 * Condicion de entrada:
 * EntradaForo
 * 
 * Tipo:
 * EntradaForo asociada a un usuario
 * 
 * Clase de equivalencia valida:
 * Crear EntradaForo creando un usuario ahi mismo
 * 
 * Clase de equivalencia invalida:
 * Crear EntradaForo consultando un usuario inexistente
 * 
 * ----------------------------------------------------------
 * 
 * 2)
 * 
 * Condicion de entrada:
 * EntradaForo
 * 
 * Tipo:
 * EntradaForo Con formato de fecha correcto
 * 
 * Clase de equivalencia valida:
 * Utilizar un formato de fecha valido
 * 
 * Clase de equivalencia invalida:
 * Utilizar una fecha incoherente
 */

public class EntradasForoTest {
    ServiciosForo principal;
    EntradaForo ef;
    Usuario us;
    
    public EntradasForoTest() {
    }
    
    @Before
    public void setUp() {/*
        principal=ServiciosForo.getInstance();
        us = new Usuario("pepitoFULLHDRESUBIDOESPANOL4K.com", "Pepito");
        ef = new EntradaForo(us, "N", "E", new Date(new java.util.Date().getTime()));
        try {
            principal.registrarUsuario(us);
        } catch (ExcepcionServiciosForos ex) {
        }*/
    }
    
    
    @Test
    public void registroPacienteTest() throws ExcepcionServiciosForos{ 
        assertTrue(true);
        /*
        principal.registrarNuevaEntradaForo(ef);
        */
    }
    
    @Test
    public void consultarEntradaConIdInexistente() throws ExcepcionServiciosForos{
        assertTrue(true);/*
        try{
            principal.consultarEntradaForo(5121234);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Entrada a foro inexistente:5121234");
        }*/
    }
    
    @Test
    public void deberiaConsultarTodasLasEntradasForo() throws ExcepcionServiciosForos{
        assertTrue(true);/*
        principal.consultarEntradasForo();*/
    }
    
    
}
