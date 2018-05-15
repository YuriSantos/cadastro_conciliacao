package br.jus.jfpb.cadastro.controller;

import br.jus.jfpb.cadastro.dao.ValidarLDAP;
import br.jus.jfpb.cadastro.model.Usuario;
import org.hibernate.jpa.boot.spi.Bootstrap;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.rmi.CORBA.Util;
import javax.xml.transform.Result;
import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@Resource
public class UsuarioController {
    /*
    private final Result result;
    private final UsuarioRepository repository;
    private final PerfilRepository perfilRepository;
    private final Validator validator;
    private final UsuarioSession usuarioSession;
    private final HttpServletRequest req;
    private final PainelController painelController;
    private final MenuRepository menuRepository;
    private final MenufuncaoRepository menuFuncaoRepository;
    private final FuncaoRepository funcaoRepository;
    private final HttpServletRequest request;
    private Util util = new Util();
    private final ServletContext context;

    public UsuarioController(Result result, MenufuncaoRepository menuFuncaoRepository, UsuarioRepository repository, Validator validator, MenuRepository menuRepository, UsuarioSession usuarioSession, HttpServletRequest req, FuncaoRepository funcaoRepository, PainelController painelController, PerfilRepository perfilRepository, HttpServletRequest request, ServletContext context) {
        this.result = result;
        this.menuRepository = menuRepository;
        this.repository = repository;
        this.validator = validator;
        this.usuarioSession = usuarioSession;
        this.req = req;
        this.menuFuncaoRepository = menuFuncaoRepository;
        this.funcaoRepository = funcaoRepository;
        this.perfilRepository = perfilRepository;
        this.painelController = painelController;
        this.request = request;
        this.context = context;
    }

    @Public
    @Get({"/usuarios/login"})
    public Usuario login() {
        Bootstrap bootstrap = new Bootstrap(this.repository, this.menuFuncaoRepository, this.perfilRepository, this.menuRepository, this.validator, this.funcaoRepository, this.req, this.painelController);
        if (this.repository.findAll().size() == 0) {
            bootstrap.setup();
        }

        Integer tentativas = (Integer)this.req.getSession().getAttribute("tentativas");
        if (tentativas == null) {
            this.req.getSession().setAttribute("tentativas", Integer.valueOf(0));
        }

        this.result.include("tentativas", tentativas);
        return new Usuario();
    }

    @Public
    @Post
    @Path({"/usuarios/login"})
    public void login(String nome, String senha, String captchaHash, String captcha) throws NamingException {
        Bootstrap bootstrap = new Bootstrap(this.repository, this.menuFuncaoRepository, this.perfilRepository, this.menuRepository, this.validator, this.funcaoRepository, this.req, this.painelController);
        if (this.repository.findAll().size() == 0) {
            bootstrap.setup();
        }

        ValidarLDAP validarLdap = new ValidarLDAP();
        boolean usuarioLdap = validarLdap.buscarUsuarioLdap(nome);
        if (!usuarioLdap) {
            this.validator.add(new I18nMessage("ERROR", "usuario.semCadastroNoLDAP", new Object[0]));
            ((UsuarioController)this.validator.onErrorForwardTo(this)).login();
        } else {
            ValidaCaptcha validaCaptcha = new ValidaCaptcha();
            boolean usaCaptcha = true;
            Usuario carregado = this.repository.carrega(nome);
            if (carregado == null) {
                carregado = new Usuario();
                carregado.setLogin(nome);
                carregado.setNome(nome);
                carregado.setContaAtiva(true);
                Perfil p = this.perfilRepository.buscaPerfilPorNome("Usuario");
                carregado.setPerfil(p);
            }

            Integer tentativas = (Integer)this.req.getSession().getAttribute("tentativas");
            if (tentativas != null && tentativas.intValue() > 2) {
                usaCaptcha = false;
                if (validaCaptcha.valida(captcha, captchaHash)) {
                    usaCaptcha = true;
                }
            }

            if (carregado != null && validarLdap.conexao(nome, senha) && usaCaptcha && carregado.isContaAtiva()) {
                this.usuarioSession.login(carregado);
                this.usuarioSession.setIp(Util.getClientIpAddr(this.request));
                this.usuarioSession.setPainelUsuario(this.painelController.gerarMenuBase());
                this.req.getSession().setAttribute("tentativas", Integer.valueOf(1));
                String url = (String)this.req.getSession().getAttribute("urlchamada");
                if (url != null && !url.equals("")) {
                    this.req.getSession().removeAttribute("urlchamada");
                    this.result.redirectTo(url);
                } else {
                    ((PainelController)this.result.redirectTo(PainelController.class)).painelControle();
                }
            } else {
                if (tentativas.intValue() == 0 || tentativas.intValue() > 0) {
                    this.req.getSession().setAttribute("tentativas", tentativas.intValue() + 1);
                    this.result.include("tentativas", tentativas);
                }

                if (!validarLdap.conexao(nome, senha)) {
                    this.validator.add(new I18nMessage("ERROR", "usuario.senhaLoginInvalido", new Object[0]));
                    ((UsuarioController)this.validator.onErrorForwardTo(this)).login();
                }

                if (!usaCaptcha) {
                    this.validator.add(new I18nMessage("ERROR", "usuario.captcha", new Object[0]));
                    ((UsuarioController)this.validator.onErrorForwardTo(this)).login();
                }

                if (carregado != null && !carregado.isContaAtiva()) {
                    this.validator.add(new I18nMessage("ERROR", "usuario.situacao", new Object[0]));
                    ((UsuarioController)this.validator.onErrorForwardTo(this)).login();
                }

                if (tentativas.intValue() == 0 || tentativas.intValue() > 0) {
                    this.req.getSession().setAttribute("tentativas", tentativas.intValue() + 1);
                    this.result.include("tentativas", tentativas);
                }

                ((UsuarioController)this.validator.onErrorUsePageOf(UsuarioController.class)).login();
            }
        }

    }

    @Transformar
    @Path({"usuarios/{nome}/transformausuario"})
    public void login(String nome) throws NamingException {
        Bootstrap bootstrap = new Bootstrap(this.repository, this.menuFuncaoRepository, this.perfilRepository, this.menuRepository, this.validator, this.funcaoRepository, this.req, this.painelController);
        if (this.repository.findAll().size() == 0) {
            bootstrap.setup();
        }

        ValidarLDAP validarLdap = new ValidarLDAP();
        boolean usuarioLdap = validarLdap.buscarUsuarioLdap(nome);
        if (!usuarioLdap) {
            this.validator.add(new I18nMessage("ERROR", "usuario.semCadastroNoLDAP", new Object[0]));
            ((UsuarioController)this.validator.onErrorForwardTo(this)).login();
        } else {
            new ValidaCaptcha();
            boolean usaCaptcha = true;
            Usuario carregado = this.repository.carrega(nome);
            if (carregado == null) {
                carregado = new Usuario();
                carregado.setLogin(nome);
                carregado.setNome(nome);
                carregado.setContaAtiva(true);
                Perfil p = this.perfilRepository.find(2L);
                carregado.setPerfil(p);
            }

            if (carregado != null && carregado.isContaAtiva()) {
                this.usuarioSession.login(carregado);
                this.usuarioSession.setPainelUsuario(this.painelController.gerarMenuBase());
                this.req.getSession().setAttribute("tentativas", Integer.valueOf(1));
                String url = (String)this.req.getSession().getAttribute("urlchamada");
                if (url != null && !url.equals("")) {
                    this.req.getSession().removeAttribute("urlchamada");
                    this.result.redirectTo(url);
                } else {
                    ((PainelController)this.result.redirectTo(PainelController.class)).painelControle();
                }
            }

            if (!carregado.isContaAtiva()) {
                this.validator.add(new I18nMessage("ERROR", "usuario.situacao", new Object[0]));
                ((UsuarioController)this.validator.onErrorForwardTo(this)).login();
            }

            ((UsuarioController)this.validator.onErrorUsePageOf(UsuarioController.class)).login();
        }

    }

    @Public
    @Path({"/usuarios/logout"})
    public void logout() {
        if (this.usuarioSession != null) {
            this.usuarioSession.logout();
            ((UsuarioController)this.result.redirectTo(UsuarioController.class)).login();
        } else {
            ((UsuarioController)this.result.redirectTo(UsuarioController.class)).login();
        }
    }

    @Lista
    @Get({"/usuarios"})
    public List<Usuario> index(int pagina) {
        if (pagina == 0) {
            pagina = 1;
        }

        this.result.include("listaPerfil", this.perfilRepository.findAll());
        this.result.include("logado", this.usuarioSession.isLogdo());
        this.result.include("usuarioSessao", this.usuarioSession.getLogado());
        this.result.include("paginaAtual", pagina);
        return this.repository.findAll();
    }

    @Public
    @Get({"/usuario/hideMsgIe"})
    public void ocultarMsgIe() {
        this.usuarioSession.setHideIeMsg(true);
    }

    @Lista
    @Post({"/usuarios"})
    public List<Usuario> index(String login, Boolean ativo, String nome, String email, Long perfil, List<Usuario> listaUsuarios) {
        if (nome != null) {
            nome = nome.trim();
        }

        if (email != null) {
            email = email.trim();
        }

        if (login != null) {
            login = login.trim();
        }

        this.result.include("listaPerfil", this.perfilRepository.findAll());
        this.result.include("logado", this.usuarioSession.isLogdo());
        this.result.include("usuarioSessao", this.usuarioSession.getLogado());
        if (login != null || ativo != null || nome != null || email != null || perfil != null) {
            this.result.include("login", login);
            this.result.include("ativo", ativo);
            this.result.include("nome", nome);
            this.result.include("email", email);
            this.result.include("perfil", perfil);
            List<Usuario> usuarios = this.repository.buscarUsuario(login, ativo, nome, email, perfil);
            if (usuarios.size() != 0) {
                return usuarios;
            }

            this.validator.add(new ValidationMessage("Registro não encontrado!", "ERROR"));
            ((UsuarioController)this.validator.onErrorForwardTo(this)).index((String)null, (Boolean)null, (String)null, (String)null, (Long)null, new ArrayList());
        }

        return listaUsuarios != null ? listaUsuarios : this.repository.findAll();
    }

    public boolean verificaFormatoEmail(String email) {
        String formatoEmail = "@jfpb.jus.br";
        return email.contains(formatoEmail);
    }

    @Cria
    @Lista
    @Edita
    @Post({"verificarUsuario"})
    public void verificaUsuarioLdap(String usuarioProcurado) throws NamingException {
        ValidarLDAP validarLDAP = new ValidarLDAP();
        boolean ok = validarLDAP.buscarUsuarioLdap(usuarioProcurado);
        if (!ok) {
            this.validator.add(new ValidationMessage("Não existe usuário para este login no LDAP", "ERROR"));
            ((UsuarioController)this.validator.onErrorForwardTo(this)).newUsuario((Usuario)null, (String)null);
        }

    }

    @Cria
    @Post({"/usuarios/criar"})
    public void create(Usuario usuario) throws NamingException {
        ValidarLDAP vLdap = new ValidarLDAP();
        boolean validausuarioLdap = vLdap.buscarUsuarioLdap(usuario.getLogin());
        if (validausuarioLdap) {
            if (this.repository.existeUsuarioCria(usuario)) {
                this.validator.add(new I18nMessage("ERROR", "usuario.jaExiste", new Object[0]));
                ((UsuarioController)this.validator.onErrorRedirectTo(this)).newUsuario(usuario, "retorno");
            }

            if (this.repository.existeEmailCria(usuario)) {
                this.validator.add(new I18nMessage("ERROR", "usuario.emailExiste", new Object[0]));
                ((UsuarioController)this.validator.onErrorRedirectTo(this)).newUsuario(usuario, "retorno");
            }

            if (!this.verificaFormatoEmail(usuario.getEmail())) {
                this.validator.add(new I18nMessage("ERROR", "usuario.emaiInvalido", new Object[0]));
                ((UsuarioController)this.validator.onErrorRedirectTo(this)).newUsuario(usuario, "retorno");
            }

            Perfil perfil = this.perfilRepository.find(usuario.getPerfil().getId());
            usuario.setPerfil(perfil);
            this.validator.validate(usuario);
            usuario.setNome(usuario.getNome().toUpperCase());
            this.repository.create(usuario);
            this.validator.add(new I18nMessage("SUCCESS", "usuario.cadastrado", new Object[0]));
            ((UsuarioController)this.validator.onSuccessUsePageOf(this)).index(0);
        } else {
            this.validator.add(new I18nMessage("ERROR", "usuario.semCadastroNoLDAP", new Object[0]));
            ((UsuarioController)this.validator.onErrorRedirectTo(this)).newUsuario(usuario, "retorno");
        }

    }

    @Cria
    @Get({"/usuarios/new"})
    public Usuario newUsuario(Usuario usuario, String acao) {
        List<Perfil> perfis = this.perfilRepository.findAll();
        this.result.include("listaPerfil", perfis);
        this.result.include("usuarioSessao", this.usuarioSession.getLogado());
        return acao == "retorno" ? usuario : new Usuario();
    }

    @Edita
    @Put({"/usuarios"})
    public void update(Usuario usuario) throws NamingException {
        ValidarLDAP vLdap = new ValidarLDAP();
        boolean validausuarioLdap = vLdap.buscarUsuarioLdap(usuario.getLogin());
        if (validausuarioLdap) {
            if (this.repository.existeUsuarioEdita(usuario)) {
                this.validator.add(new I18nMessage("ERROR", "usuario.jaExiste", new Object[0]));
                ((UsuarioController)this.validator.onErrorRedirectTo(this)).edit(usuario);
            }

            if (this.repository.existeEmailEdita(usuario)) {
                this.validator.add(new I18nMessage("ERROR", "usuario.emailExiste", new Object[0]));
                ((UsuarioController)this.validator.onErrorRedirectTo(this)).edit(usuario);
            }

            if (!this.verificaFormatoEmail(usuario.getEmail())) {
                this.validator.add(new I18nMessage("ERROR", "usuario.emaiInvalido", new Object[0]));
                ((UsuarioController)this.validator.onErrorRedirectTo(this)).edit(usuario);
            }

            this.validator.validate(usuario);
            usuario.setNome(usuario.getNome().toUpperCase());
            this.repository.update(usuario);
            this.validator.add(new I18nMessage("SUCCESS", "usuario.atualizado", new Object[0]));
            ((UsuarioController)this.validator.onSuccessUsePageOf(this)).index(0);
        } else {
            this.validator.add(new I18nMessage("ERROR", "usuario.semCadastroNoLDAP", new Object[0]));
            ((UsuarioController)this.validator.onErrorRedirectTo(this)).edit(usuario);
        }

    }

    private boolean buscarRepetido(Usuario usuario) {
        Usuario uLogin = this.repository.buscaUsuarioPorLogin(usuario);
        if (uLogin != null && usuario.getLogin().equals(uLogin.getLogin()) && !usuario.getId().equals(uLogin.getId())) {
            this.validator.add(new ValidationMessage("Login já está cadastrado para outro usuário", "ERROR"));
            return false;
        } else {
            Usuario uEmail = this.repository.buscaUsuarioPorEmail(usuario);
            if (uEmail != null && usuario.getEmail().equals(uEmail.getEmail()) && !usuario.getId().equals(uEmail.getId())) {
                this.validator.add(new ValidationMessage("Email já está cadastrado para outro usuário", "ERROR"));
                return false;
            } else {
                return true;
            }
        }
    }

    @Edita
    @Get({"/usuarios/{usuario.id}/edit"})
    public Usuario edit(Usuario usuario) {
        List<Perfil> perfis = this.perfilRepository.findAll();
        this.result.include("listaPerfil", perfis);
        this.result.include("usuarioSessao", this.usuarioSession.getLogado());
        return this.repository.find(usuario.getId());
    }

    @Edita
    @Get({"/usuarios/{usuario.id}/ativar/{pagina}"})
    public void active(Usuario usuario, int pagina) {
        Usuario user = this.repository.find(usuario.getId());
        user.setContaAtiva(!user.isContaAtiva());
        this.validator.validate(user);
        ((UsuarioController)this.validator.onErrorUsePageOf(this)).index(pagina);
        this.repository.update(user);
        ((UsuarioController)this.result.redirectTo(this)).index(pagina);
    }

    @Exibe
    @Get({"/usuarios/{usuario.id}"})
    public Usuario show(Usuario usuario) {
        return this.repository.find(usuario.getId());
    }

    @Deleta
    @Delete({"/usuarios/{usuario.id}"})
    public void destroy(Usuario usuario) {
        this.repository.destroy(this.repository.find(usuario.getId()));
        ((UsuarioController)this.result.redirectTo(this)).index(0);
    }

    @Public
    @Path({"/erro"})
    public void erroGenerico() {

    }
    */
}
