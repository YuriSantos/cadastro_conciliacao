package br.jus.jfpb.cadastro.service;

import br.jus.jfpb.cadastro.dao.UsuarioDAO;
import br.jus.jfpb.cadastro.dao.ValidarLDAP;
import br.jus.jfpb.cadastro.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;
    private ValidarLDAP validarLDAP;

    public Usuario validaLogin(Usuario usuario){
        return usuarioDAO.validaLogin(usuario);

    }
}