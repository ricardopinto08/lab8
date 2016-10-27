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
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionServiciosForos;
import edu.eci.pdsw.samples.services.ServiciosForo;
import edu.eci.pdsw.samples.services.ServiciosForoDAOStub;
import java.sql.Date;
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
 * idForo
 * 
 * Tipo:
 * Comentario asociado a un idForo 
 * 
 * Clase de equivalencia valida:
 * Crear Comentario creando un Foro ahi mismo
 * 
 * Clase de equivalencia invalida:
 * Crear Comentario asociado un Foro inexistente
 * 
 * ----------------------------------------------------------
 * 
 * 2)
 * 
 * Condicion de entrada:
 * Comentario
 * 
 * Tipo:
 * Comentario Con formato de fecha correcto
 * 
 * Clase de equivalencia valida:
 * Utilizar un formato de fecha valido
 * 
 * Clase de equivalencia invalida:
 * Utilizar una fecha incoherente
 */
public class ComentariosTest {
    ServiciosForo principal;
    EntradaForo ef;
    Usuario us;
    
    public ComentariosTest() {
    }
    
    @Before
    public void setUp() {
        principal=ServiciosForo.getInstance();
        us = new Usuario("pepitoMEGA-LINKRESUBIDOPACKESPANIOL.com", "PepaPig");
        ef = new EntradaForo(us, "Nasfd", "Eerq", new Date(new java.util.Date().getTime()));
        try {
            principal.registrarUsuario(us);
            principal.registrarNuevaEntradaForo(ef);
        } catch (ExcepcionServiciosForos ex) {
        }
        
    }
    /*
    @Test
    public void registroComentarioTest() throws ExcepcionServiciosForos{
        Comentario c= new Comentario(us, "El rappi de hoy fue bueno : 6 Iphone 7 de 128GB y 3 Samsung S7 edge.", java.sql.Date.valueOf("2012-01-11"));
        principal.agregarRespuestaForo(ef.getIdentificador(),c);
    }
    */
    @Test
    public void registroNoComentarioTest() throws ExcepcionServiciosForos{
        Comentario c= new Comentario(us, "El rappi de hoy fue bueno : 6 Iphone 7 de 128GB y 3 Samsung S7 edge.", java.sql.Date.valueOf("2012-01-11"));
        try {
            principal.agregarRespuestaForo(23,c);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Foro no encontrado, rectifique e intente de nuevo." );
        }
         
    }
    
    @Test
    public void consultarUsuarioNoRegistradoTest() throws ExcepcionServiciosForos{
        try {
            principal.consultarUsuario("carlos.ramirez-ot@hotmail.com");
        } catch (Exception e) {
            assertEquals(e.getMessage(),"El correo carlos.ramirez-ot@hotmail.com no se encuentra registrado.");
        }
         
    }
    
    
}
