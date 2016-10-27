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
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.EntradaForo;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.persistence.DaoEntradaForo;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.DaoUsuario;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */

public class ServiciosForoDAOStub extends ServiciosForo implements Serializable{


    private  DaoUsuario usuarios;

    private  DaoFactory daof;
    
    private  DaoEntradaForo foros;
    private static int foroidcount=0;

    public ServiciosForoDAOStub()  {
        Properties properties=new PropertiesLoader().readProperties("applicationconfig.properties");
        
        daof=DaoFactory.getInstance(properties);
        
    }

    @Override
    public List<EntradaForo> consultarEntradasForo() {
        List<EntradaForo> lista = null;
        try {
            daof.beginSession();
            foros=daof.getDaoEntradaForo();
            lista= foros.loadAll();
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosForoDAOStub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public EntradaForo consultarEntradaForo(int id) throws ExcepcionServiciosForos {
        try {
            daof.beginSession();
            foros=daof.getDaoEntradaForo();     
            EntradaForo f = foros.load(id);
            daof.commitTransaction();
            daof.endSession();
            return f;
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosForoDAOStub.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionServiciosForos("Entrada a foro inexistente:"+id);
        }
        
    }

    @Override
    public void registrarNuevaEntradaForo(EntradaForo f) throws ExcepcionServiciosForos {
        try {
            daof.beginSession();
            foros=daof.getDaoEntradaForo();     
            foros.save(f);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosForoDAOStub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Usuario> getUsuarios()  {
        LinkedList<Usuario> lista = null;
        try {
            daof.beginSession();
            usuarios=daof.getDaoUsuario();
            lista = new LinkedList<>(usuarios.loadAll());
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosForoDAOStub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    @Override
    public void agregarRespuestaForo(int idforo, Comentario c) throws ExcepcionServiciosForos {
        try{
            daof.beginSession();
            foros=daof.getDaoEntradaForo();
            foros.addToForo(idforo, c);
            daof.commitTransaction();
            daof.endSession();
        }catch (Exception ex){
            throw new ExcepcionServiciosForos("Foro no encontrado, rectifique e intente de nuevo.");
        }
        
    }

    @Override
    public Usuario consultarUsuario(String email) throws ExcepcionServiciosForos {
        try {
            daof.beginSession();
            usuarios=daof.getDaoUsuario();
            Usuario us= usuarios.load(email);
            daof.commitTransaction();
            daof.endSession();
            return us;
            
        } catch (Exception e) {
            throw new ExcepcionServiciosForos("El correo "+email+" no se encuentra registrado.");
        }
        
        
    }
    
    @Override
    public void registrarUsuario(Usuario us) throws ExcepcionServiciosForos {
        try {   
            daof.beginSession();
            usuarios=daof.getDaoUsuario();
            usuarios.save(us);
            daof.commitTransaction();
            daof.endSession();
        } catch(Exception e) {
            Logger.getLogger(ServiciosForoDAOStub.class.getName()).log(Level.SEVERE, null, e);
            throw new ExcepcionServiciosForos("El correo "+us.getEmail()+" ya se encuentra registrado.");
   
        }
               
        
    }

    @Override
    public Map<String, Usuario> getUsuarios1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
            
    
}

class PropertiesLoader {

    public Properties readProperties(String fileName)  {
        InputStream input = null;
        Properties properties = new Properties();
        input = this.getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }
}