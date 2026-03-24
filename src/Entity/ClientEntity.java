package Entity;

public class ClientEntity extends UserEntity {
    public ClientEntity(String nome, String email, String senha, int numeroCadastro, int permissao) {
        super(nome, email, senha, numeroCadastro, permissao);
    }
}
