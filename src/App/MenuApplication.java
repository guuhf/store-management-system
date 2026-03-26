package App;

import Service.AuthService;
import Exception.AuthException;
import Exception.ConflictException;
import Service.BuyService;
import Service.ProductService;

import java.util.Scanner;

public class MenuApplication {
    AuthService controleLogins = new AuthService();
    ProductService controleProdutos = new ProductService(controleLogins);
    BuyService controleCompras = new BuyService(controleLogins, controleProdutos);

    public void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean menu = true;
        while (menu) {
            System.out.println("1 - Cadastro de Usuário");
            System.out.println("2 - Login");
            System.out.println("3 - Cadastrar Produto");
            System.out.println("4 - Adicionar Estoque");
            System.out.println("5 - Listar Produtos");
            System.out.println("6 - Comprar Produtos");
            System.out.println("7 - Ver minhas compras");
            System.out.println("8 - Logout");
            System.out.println("0 - Sair");
            int escolhaMenu = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaMenu) {
                case 1:
                    try {
                        controleLogins.verificarUsuarioLogado();
                        System.out.println("Qual cargo o usuário tem?");
                        System.out.println("1 - Cliente");
                        System.out.println("2 - Administrador");
                        int escolhaCargo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Qual seu nome?");
                        String nome = scanner.nextLine();
                        System.out.println("Qual seu email?");
                        String email = scanner.nextLine();
                        System.out.println("Digite a sua senha:");
                        String senha = scanner.nextLine();
                        controleLogins.cadastrarUsuario(escolhaCargo, nome, email, senha);
                    }catch (ConflictException | AuthException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        controleLogins.verificarUsuarioLogado();
                        System.out.println("Digite o Email:");
                        String emailLogin = scanner.nextLine();
                        System.out.println("Digite a senha:");
                        String senhaLogin = scanner.nextLine();
                        controleLogins.logar(emailLogin, senhaLogin);
                    } catch (AuthException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        controleLogins.verificarPermissaoAdm();
                        System.out.println("Qual o nome do produto?");
                        String nomeProduto = scanner.nextLine();
                        System.out.println("Qual a descrição do produto?");
                        String descricaoProduto = scanner.nextLine();
                        System.out.println("Digite o estoque inicial:");
                        int estoqueInicial = scanner.nextInt();
                        System.out.println("Digite o preço do produto:");
                        double precoProduto = scanner.nextDouble();
                        controleProdutos.cadastroProduto(nomeProduto, descricaoProduto, precoProduto, estoqueInicial);
                    } catch (AuthException | ConflictException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        controleLogins.verificarPermissaoAdm();
                        System.out.println("Qual o número do produto que deseja adicionar estoque?");
                        int escolhaProduto = scanner.nextInt();
                        System.out.println("Qiantos produtos deseja adicionar?");
                        int valorAdicionar = scanner.nextInt();
                        controleProdutos.adicionarEstoque(escolhaProduto, valorAdicionar);
                    } catch (ConflictException | AuthException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        controleProdutos.listarProdutos();
                    }catch (AuthException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        controleLogins.verificarPermissaoClient();
                        System.out.println("Qual o número do produto que deseja comprar?");
                        int numeroCompra = scanner.nextInt();

                        System.out.println("Quantos produtos deseja comprar?");
                        int quantidadeCompra = scanner.nextInt();

                        controleCompras.comprarProduto(numeroCompra, controleLogins.getUsuarioLogado(), quantidadeCompra);
                    } catch (ConflictException | AuthException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        controleCompras.percorrerListaCompras(controleLogins.getUsuarioLogado());
                    }catch (AuthException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        controleLogins.deslogar();
                    }catch (AuthException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    menu = false;


            }
        }
    }
}
