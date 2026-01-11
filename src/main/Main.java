package main;

import java.util.Locale;
import java.util.Scanner;

import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import repository.ClienteRepository;
import repository.ProdutoRepository;
import repository.VendaRepository;
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        ClienteRepository clienteRepository = new ClienteRepository();
        ProdutoRepository produtoRepository = new ProdutoRepository();
        VendaRepository vendaRepository = new VendaRepository();

        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar produto");
            System.out.println("3 - Criar venda");
            System.out.println("4 - Listar clientes");
            System.out.println("5 - Listar produtos");
            System.out.println("6 - Listar vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {

            case 1: {
                System.out.print("ID do cliente: ");
                int idCliente = scanner.nextInt();
                scanner.nextLine(); // limpar buffer

                System.out.print("Nome do cliente: ");
                String nomeCliente = scanner.nextLine();

                Cliente novoCliente = new Cliente(idCliente, nomeCliente);

                if (!clienteRepository.adicionar(novoCliente)) {
                    System.out.println("Cliente com esse ID ja existe.");
                } else {
                    System.out.println("Cliente cadastrado com sucesso!");
                }
                break;
            }


            case 2: {
                System.out.print("ID do produto: ");
                int idProduto = scanner.nextInt();
                scanner.nextLine(); // limpar buffer

                System.out.print("Nome do produto: ");
                String nomeProduto = scanner.nextLine();

                System.out.print("Preco do produto: ");
                double precoProduto = scanner.nextDouble();

                Produto novoProduto = new Produto(idProduto, nomeProduto, precoProduto);

                if (!produtoRepository.adicionar(novoProduto)) {
                    System.out.println("Produto com esse ID ja existe.");
                } else {
                    System.out.println("Produto cadastrado com sucesso!");
                }
                break;
            }

            case 3: {
                System.out.print("ID da venda: ");
                int idVenda = scanner.nextInt();

                System.out.print("ID do cliente: ");
                int idCliente = scanner.nextInt();

                Cliente cliente = clienteRepository.buscarPorId(idCliente);
                System.out.println("DEBUG cliente: " + cliente);

                if (cliente == null) {
                    System.out.println("Cliente nao encontrado.");
                    break;
                }

                Venda venda = new Venda(idVenda, cliente);

                System.out.print("ID do produto: ");
                int idProduto = scanner.nextInt();

                Produto produto = produtoRepository.buscarPorId(idProduto);
                if (produto == null) {
                    System.out.println("Produto nao encontrado.");
                    break;
                }

                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();

                ItemVenda item = new ItemVenda(produto, quantidade);
                venda.adicionarItem(item);

                vendaRepository.adicionar(venda);

                System.out.println("Venda criada com sucesso!");
                System.out.println("Total da venda: " + venda.getTotal());
                break;
            }

            case 4:
                System.out.println("\n=== LISTA DE CLIENTES ===");

                if (clienteRepository.listarTodos().isEmpty()) {
                    System.out.println("Nenhum cliente cadastrado.");
                    break;
                }

                for (Cliente c : clienteRepository.listarTodos()) {
                    System.out.println(c);
                }
                break;

            case 5:
                System.out.println("\n=== LISTA DE PRODUTOS ===");

                if (produtoRepository.listarTodos().isEmpty()) {
                    System.out.println("Nenhum produto cadastrado.");
                    break;
                }

                for (Produto p : produtoRepository.listarTodos()) {
                    System.out.println(p);
                }
                break;

            case 6:
                System.out.println("\n=== LISTA DE VENDAS ===");

                if (vendaRepository.listar().isEmpty()) {
                    System.out.println("Nenhuma venda cadastrada.");
                    break;
                }

                for (Venda v : vendaRepository.listar()) {
                    System.out.println("ID da venda: " + v.getId());
                    System.out.println("Cliente: " + v.getCliente().getNome());

                    System.out.println("Itens:");
                    for (ItemVenda iv : v.getItens()) {
                        System.out.println("  " + iv);
                    }

                    System.out.println("Total: " + String.format(java.util.Locale.US, "%.2f", v.getTotal()));
                    System.out.println("----------------------");
                }
                break;

            case 0:
                System.out.println("Saindo...");
                break;

            default:
                System.out.println("Opcao invalida.");
        }

    } while (opcao != 0);

    scanner.close();
}

}

        
            

