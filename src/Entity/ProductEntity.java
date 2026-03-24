package Entity;

public class ProductEntity {
    private int numeroProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private double valorProduto;
    private int estoqueInicial;

    public ProductEntity(String nomeProduto, String descricaoProduto, double valorProduto, int numeroProduto, int estoqueInicial) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.valorProduto = valorProduto;
        this.numeroProduto = numeroProduto;
        this.estoqueInicial = estoqueInicial;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public int getNumeroProduto() {
        return numeroProduto;
    }

    public int getEstoqueInicial() {
        return estoqueInicial;
    }

    public void setEstoqueInicial(int estoqueInicial) {
        this.estoqueInicial = estoqueInicial;
    }

    @Override
    public String toString() {
        return "\n [" + getNumeroProduto() + "] " + getNomeProduto() +
                "\n Descrição: " + getDescricaoProduto() +
                "\n Quantidade em estoque: " + getEstoqueInicial() +
                "\n Preço: " + getValorProduto() + "R$";
    }
}
