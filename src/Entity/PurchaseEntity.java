package Entity;

public class PurchaseEntity {
    private UserEntity comprador;
    private ProductEntity produto;
    private int quantidade;
    private double valorTotal;

    public PurchaseEntity(UserEntity comprador, ProductEntity produto, int quantidade){
        this.comprador = comprador;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = produto.getValorProduto() * quantidade;
    }

    public UserEntity getComprador() {
        return comprador;
    }

    public void setComprador(UserEntity comprador) {
        this.comprador = comprador;
    }

    public ProductEntity getProduto() {
        return produto;
    }

    public void setProduto(ProductEntity produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "\n Comprador: " + comprador.getNome() +
                "\n Produto: " + produto.getNomeProduto() +
                "\n Quantidade da compra: " + getQuantidade() +
                "\n Valor da compra: " + getValorTotal() + "R$";
    }
}
