package br.jus.jfpb.cadastro.dao;

import br.jus.jfpb.cadastro.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UsuarioDAO extends AbstractDAO<Usuario>{

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario validaLogin(Usuario usuario) {
        String s = "select u from Usuario u where u.nome = :nome and u.senha = :senha";
        Query query = manager.createQuery(s);
        query.setParameter("nome", usuario.getNome());
        query.setParameter("senha", usuario.getSenha());
        List<Usuario> usuarios = query.getResultList();

        if(!usuarios.isEmpty()){
            return usuarios.get(0);
        }
        return null;
    }

    public boolean validaLoginLdap(Usuario usuario1){

        ValidarLDAP validarLDAP = new ValidarLDAP();
        String nome;
        boolean usuarioLdap = validarLDAP.conexao(usuario1.getNome(),usuario1.getSenha());
        //boolean usuarioprocurado = validarLDAP.buscarUsuarioLdap(usuario.getNome());
        if(!usuarioLdap){
            return true;
        }
        else{
            return true;
        }



    }

}