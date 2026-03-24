package Service;

import Entity.AdmEntity;
import Entity.ClientEntity;
import Entity.UserEntity;
import Exception.ConflictException;
import Exception.AuthException;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    List<UserEntity> listaUsuarios = new ArrayList<>();
    private int gerarNumero;
    private UserEntity usuarioLogado;

    public void cadastrarUsuario(int escolhaCargo, String nome, String email, String senha) {
            UserEntity cadastro = tipoUsuario(escolhaCargo, nome, email, senha);
            verificarEmail(cadastro.getEmail());
            listaUsuarios.add(cadastro);
    }

    public void verificarEmail(String email) {
        for (UserEntity existeEmail : listaUsuarios) {
            if (email.equalsIgnoreCase(existeEmail.getEmail())) {
                throw new ConflictException("Usuário ja existente!");
            }
        }

    }

    public UserEntity tipoUsuario(int escolhaCargo, String nome, String email, String senha) {
        int numeroCadastro = gerarNumero();
        if (escolhaCargo == 1) {
            return new ClientEntity(nome, email, senha, numeroCadastro, 1);
        } else if (escolhaCargo == 2) {
            return new AdmEntity(nome, email, senha, numeroCadastro, 2);
        } else throw new ConflictException("Escolha inválida");

    }

    public int gerarNumero() {
        gerarNumero++;
        return gerarNumero;
    }

    public UserEntity verificarEmailLogin(String email) {
        for (UserEntity buscaLogin : listaUsuarios) {
            if (email.equalsIgnoreCase(buscaLogin.getEmail())) {
                return buscaLogin;
            }
        }
        throw new AuthException("O email está incorreto!");
    }

    public void verificarLogin(String email, String senha) {
        UserEntity login = verificarEmailLogin(email);
        if (senha.equals(login.getSenha())) {
            usuarioLogado = login;
        }else throw new AuthException("Senha Incorreta!");
    }
    //Verificação para fazer o login!
    public void verificarUsuarioLogado() {
        if (usuarioLogado != null) {
            throw new AuthException("Ja existe um usuario logado!");
        }
    }
    //Verificação para acessar as funções do sistema!
    public void verificarAutenticacao(){
        if (usuarioLogado == null){
            throw new AuthException("Você precisa logar para acessar as funções!");
        }
    }

    public void logar(String email, String senha) {
        verificarLogin(email, senha);
        System.out.println(email + " Está logado!");
    }

    public UserEntity getUsuarioLogado() {
        return usuarioLogado;
    }

    public void verificarPermissaoAdm(){
        verificarAutenticacao();
         if (usuarioLogado.getPermissao() == 1){
            throw new AuthException("Você não tem permissão para acessar isso!");
        }
    }

    public void verificarPermissaoClient(){
        verificarAutenticacao();
        if (usuarioLogado.getPermissao() == 2){
            throw new AuthException("Você não tem permissão para acessar isso!");
        }
    }

    public void deslogar(){
        if (usuarioLogado != null){
            usuarioLogado = null;
            System.out.println("Usuário deslogado!");
        }else throw new AuthException("Você precisa estar logado!");
    }

}
