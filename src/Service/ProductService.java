package Service;

import Entity.ProductEntity;
import Entity.UserEntity;
import Exception.ConflictException;
import Exception.AuthException;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    List<ProductEntity> listaProdutos = new ArrayList<>();
    private AuthService permissao;
    private int gerarNumero;

    public ProductService(AuthService permissao) {
        this.permissao = permissao;
    }

    public ProductEntity buscarProduto(int numeroBusca) {
        for (ProductEntity buscaProduto : listaProdutos) {
            if (numeroBusca == buscaProduto.getNumeroProduto()) {
                return buscaProduto;
            }
        }
        throw new ConflictException("Produto inexistente!");
    }

    public void adicionarEstoque(int numeroBusca, int valorAdicionar) {
        ProductEntity produto = buscarProduto(numeroBusca);
        if (produto != null) {
            produto.setEstoqueInicial(produto.getEstoqueInicial() + valorAdicionar);
            System.out.println("Foi adicionado " + valorAdicionar + " Unidades em " + produto.getNomeProduto());
        }
    }

    public void listarProdutos() {
        permissao.verificarAutenticacao();
        for (ProductEntity listagemProdutos : listaProdutos) {
            System.out.println(listagemProdutos);
        }
    }

    public int gerarNumero() {
        gerarNumero++;
        return gerarNumero;
    }


    public void cadastroProduto(String nomeProduto, String descricaoProduto, double valorProduto, int estoqueInicial) {
        int numeroCadastro = gerarNumero();
        ProductEntity cadastroProduto = new ProductEntity(nomeProduto, descricaoProduto, valorProduto, numeroCadastro, estoqueInicial);
        listaProdutos.add(cadastroProduto);
    }

    public void removerProduto(int numeroProduto){
        ProductEntity produto = buscarProduto(numeroProduto);
        if (produto.getEstoqueInicial() == 0){
            listaProdutos.remove(produto);
        }
    }
}
