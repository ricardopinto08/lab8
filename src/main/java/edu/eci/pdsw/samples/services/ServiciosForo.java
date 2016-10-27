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
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author hcadavid
 */

public abstract class ServiciosForo implements Serializable {
    
    private static final ServiciosForo instance= new ServiciosForoDAOStub();
    
    
    protected ServiciosForo() {        

    }
    
    public static ServiciosForo getInstance() throws RuntimeException
    {        
        return instance;
    }


    public abstract List<Usuario> getUsuarios();
    public abstract Map<String, Usuario> getUsuarios1();
    
    /**
     * Consulta todas las entradas al foro registradas
     * @return el conjunto de las entradas al foro disponibles
     * @throws ExcepcionServiciosForos si se presento un error de persistencia
     */
    public abstract List<EntradaForo> consultarEntradasForo() throws ExcepcionServiciosForos;


    /**
     * Dado un identificador, consulta una entrada a foro especifica
     * @param id identificador de la entrada al foro
     * @return la entrada al foro
     * @throws ExcepcionServiciosForos si el identificador no corresponde a 
     * un entrada a foro existente
     */
    public abstract EntradaForo consultarEntradaForo(int id) throws ExcepcionServiciosForos;
    

    /**
     * REGISTRA una nueva entrada al foro. El identificador de la EntradaForo sera
     * asignado por el sistema una vez se haya registrado
     * @param f la nueva entrada al foro
     * @throws ExcepcionServiciosForos si f no tiene asociado su  usuario
     */
    public abstract void registrarNuevaEntradaForo(EntradaForo f) throws ExcepcionServiciosForos;
    

    /**
     * Agrega una respuesta a un foro determinado
     * @param idforo identificador de la entredaForo a la cual se agregara
     * la respuesta
     * @param c el comentario a ser agregado
     * @throws ExcepcionServiciosForos si el comentario no tiene asociado un
     * usuario
     */
    public abstract void agregarRespuestaForo(int idforo,Comentario c) throws ExcepcionServiciosForos;
    
    
    /**
     * Consulta un usuario registrado
     * @param email el correo con el cual el usuario se registro
     * @return el Usuario
     * @throws ExcepcionServiciosForos si no hay usuarios asociados al correo
     * indicado
     */
    public abstract Usuario consultarUsuario(String email) throws ExcepcionServiciosForos;
    
    /**
     * Ingresar un usuario 
     * @param us el usuario que se registrara
     * @throws ExcepcionServiciosForos si ya hay usuario asociado al correo
     * ingresado
     */
    public abstract void registrarUsuario(Usuario us) throws ExcepcionServiciosForos;
       
}
