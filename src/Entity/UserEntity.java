package Entity;

import java.util.ArrayList;
import java.util.List;

public class UserEntity {
    private String nome;
    private String email;
    private String senha;
    private int numeroCadastro;
    private int permissao;

    List<PurchaseEntity> listaCompras = new ArrayList<>();

    public void listarCompras(){
        for (PurchaseEntity compras : listaCompras){
            System.out.println(compras);
        }
    }

    public UserEntity(String nome, String email, String senha, int numeroCadastro, int permissao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.numeroCadastro = numeroCadastro;
        this.permissao = permissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPermissao() {
        return permissao;
    }

    public List<PurchaseEntity> getListaCompras() {
        return listaCompras;
    }
}
