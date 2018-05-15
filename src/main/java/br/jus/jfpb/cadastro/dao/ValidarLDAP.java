package br.jus.jfpb.cadastro.dao;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import java.util.Hashtable;

public class ValidarLDAP {
    public ValidarLDAP() {
    }

    public boolean conexao(String usuario, String senha) {
        Hashtable<String, String> ht = new Hashtable(11);
        String url = "ldap://10.102.8.51";
        ht.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        ht.put("java.naming.provider.url", url);
        ht.put("java.naming.security.authentication", "simple");
        ht.put("java.naming.security.principal", usuario + "@jfpb.jus.br");
        ht.put("java.naming.security.credentials", senha);

        try {
            DirContext context = new InitialDirContext(ht);
            context.close();
            if(senha == ""){
                System.out.println(senha);
                return false;
            }
            return true;
        } catch (NamingException var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public boolean buscarUsuarioLdap(String usuarioProcurado) throws NamingException {
        String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";
        String MY_HOST = "ldap://adjpa00.jfpb.jus.br:389/dc=jfpb,dc=jus,dc=br";
        String MY_SEARCHBASE = "dc=jfpb,dc=jus,dc=br";
        String MY_FILTER = "(sAMAccountName=" + usuarioProcurado + ")";
        String[] var10000 = new String[]{"cn", "uid"};
        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial", INITCTX);
        env.put("java.naming.provider.url", "ldap://adjpa00.jfpb.jus.br:389");
        env.put("java.naming.security.authentication", "simple");
        env.put("java.naming.security.principal", "internet");
        env.put("java.naming.security.credentials", "nossainternetjfpb");

        try {
            DirContext ctx = new InitialDirContext(env);
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(2);
            NamingEnumeration results = ctx.search(MY_SEARCHBASE, MY_FILTER, constraints);
            return results != null && results.hasMore();
        } catch (NamingException var11) {
            var11.printStackTrace();
            return false;
        }
    }
}
