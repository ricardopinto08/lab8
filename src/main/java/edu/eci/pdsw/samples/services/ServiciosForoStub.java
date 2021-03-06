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
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */

public class ServiciosForoStub extends ServiciosForo implements Serializable{


    private final Map<String,Usuario> usuarios;

    
    private final Map<Integer,EntradaForo> foros;
    private static int foroidcount=0;

    public ServiciosForoStub() {
        usuarios=new ConcurrentHashMap<>();
        foros=new ConcurrentHashMap<>();
        cargarDatosEstaticos(usuarios, foros);
    }

    @Override
    public List<EntradaForo> consultarEntradasForo() throws ExcepcionServiciosForos {
        return new LinkedList<>(foros.values());
    }

    @Override
    public EntradaForo consultarEntradaForo(int id) throws ExcepcionServiciosForos {
        if (!foros.containsKey(id)){
            throw new ExcepcionServiciosForos("Entrada a foro inexistente:"+id);
        }
        return foros.get(id);
    }

    @Override
    public void registrarNuevaEntradaForo(EntradaForo f) throws ExcepcionServiciosForos {
        synchronized(this){
            f.setIdentificador(foroidcount);            
            foroidcount++;
        }
        foros.put(f.getIdentificador(), f);        
    }
    
    @Override
    public Map<String, Usuario> getUsuarios1() {
        return usuarios;
    }
    
    @Override
    public void agregarRespuestaForo(int idforo, Comentario c) throws ExcepcionServiciosForos {
        try{
            EntradaForo f=foros.get(idforo);
            f.getRespuestas().add(c);
        }catch (Exception ex){
            throw new ExcepcionServiciosForos("Foro no encontrado, rectifique e intente de nuevo.");
        }
        
    }

    @Override
    public Usuario consultarUsuario(String email) throws ExcepcionServiciosForos {
        try {
            return usuarios.get(email);
        } catch (Exception e) {
            throw new ExcepcionServiciosForos("El correo "+email+" no se encuentra registrado.");
        }
        
        
    }
    
    @Override
    public void registrarUsuario(Usuario us) throws ExcepcionServiciosForos {
        if (!usuarios.containsKey(us.getEmail())){
            usuarios.put(us.getEmail(),us);
        }
        else{
            throw new ExcepcionServiciosForos("El correo "+us.getEmail()+" ya se encuentra registrado.");
        }
               
        
    }
    
    
    private void cargarDatosEstaticos(Map<String,Usuario> usuarios,Map<Integer,EntradaForo> foros){
        Usuario juan = new Usuario("juan.perez@gmail.com", "Juan Perez");
        usuarios.put("juan.perez@gmail.com", juan);
        usuarios.put("juan.rodriguez@gmail.com", new Usuario("juan.rodriguez@gmail.com", "Juan Rodriguez"));
        usuarios.put("luisa.perez@gmail.com", new Usuario("luisa.perez@gmail.com", "Luisa Perez"));
        
        EntradaForo ef=new EntradaForo( usuarios.get("juan.perez@gmail.com"),"Por que el agua moja?", "Super interrogante", java.sql.Date.valueOf("2000-01-01"));

        try {
            registrarNuevaEntradaForo(ef);
        } catch (ExcepcionServiciosForos ex) {
            Logger.getLogger(ServiciosForoStub.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        try {
            agregarRespuestaForo(0, new Comentario(juan, "Hola como estan", java.sql.Date.valueOf("2001-11-11"))); 
        } catch (ExcepcionServiciosForos ex) {
        }
        
    }

    @Override
    public List<Usuario> getUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
            
    
}