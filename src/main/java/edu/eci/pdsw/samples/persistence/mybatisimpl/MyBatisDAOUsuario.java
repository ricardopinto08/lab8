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
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.persistence.DaoUsuario;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EntradaForoMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.UsuarioMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author hcadavid
 */
public class MyBatisDAOUsuario implements DaoUsuario{

    
    private SqlSession currentSession=null;

    public MyBatisDAOUsuario(SqlSession session) {
        this.currentSession=session;
    }
    
    
    
    @Override
    public Usuario load(String email) throws PersistenceException {
        UsuarioMapper pedmp=currentSession.getMapper(UsuarioMapper.class);
        return pedmp.getUsuario(email);
    }

    @Override
    public void save(Usuario p) throws PersistenceException {
        UsuarioMapper pedmp=currentSession.getMapper(UsuarioMapper.class);
        pedmp.insertarUsuario(p.getEmail(), p.getNombre());
    }

    @Override
    public void update(Usuario p) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> loadAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
