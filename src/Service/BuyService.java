package Service;

import Entity.ProductEntity;
import Entity.PurchaseEntity;
import Entity.UserEntity;
import Exception.AuthException;
import Exception.ConflictException;

public class BuyService {
    private AuthService permissao;
    private ProductService productService;

    public BuyService(AuthService permissao, ProductService productService) {
        this.permissao = permissao;
        this.productService = productService;
    }

    public void comprarProduto(int numeroBusca, UserEntity usuario, int quantidade) {
        ProductEntity produtoEscolhido = productService.buscarProduto(numeroBusca);
        removerEstoque(produtoEscolhido, quantidade);
        correcaoCompra(produtoEscolhido, usuario, quantidade);
    }

    public void correcaoCompra(ProductEntity produto, UserEntity usuario, int quantidade) {
        for (PurchaseEntity corrigirCompra : usuario.getListaCompras()) {
            if (produto.getNumeroProduto() == corrigirCompra.getProduto().getNumeroProduto()) {
                int novaQtd = corrigirCompra.getQuantidade() + quantidade;
                corrigirCompra.setQuantidade(novaQtd);
                corrigirCompra.setValorTotal(novaQtd * produto.getValorProduto());
                System.out.println("Compra de " + produto.getNomeProduto() + "Realizada!");
                return;
        }
        }
        PurchaseEntity compra = new PurchaseEntity(usuario, produto, quantidade);
        System.out.println("Compra de " + produto.getNomeProduto() + "Realizada!");
        usuario.getListaCompras().add(compra);
    }


    public void removerEstoque(ProductEntity produto, int quantidade) {
        if (quantidade <= produto.getEstoqueInicial()) {
            produto.setEstoqueInicial(produto.getEstoqueInicial() - quantidade);
        } else
            throw new ConflictException("Quantidade indisponível. Estoque atual: " + produto.getEstoqueInicial() + " unidades.");
    }

    public void percorrerListaCompras(UserEntity usuario) {
        permissao.verificarAutenticacao();
        usuario.listarCompras();
    }
}
