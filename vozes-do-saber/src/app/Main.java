package app;

import modelo.CatalogoMateriais;
import modelo.CentralPedidos;
import modelo.EquipeApoio;
import modelo.Instituicao;
import modelo.MaterialEstudo;
import modelo.PedidoApoio;
import modelo.StatusPedido;
import modelo.TipoApoio;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // cria os dados usados no exemplo
        Instituicao escola = new Instituicao("Escola Comunitária Exemplo", "Escola", "Comunidade de exemplo");
        Instituicao faculdade = new Instituicao("Faculdade Parceira Exemplo", "Faculdade", "Comunidade de exemplo");
        EquipeApoio equipe = new EquipeApoio("Equipe de apoio educacional", escola);
        CatalogoMateriais catalogo = new CatalogoMateriais();
        CentralPedidos central = new CentralPedidos();

        MaterialEstudo leitura = new MaterialEstudo("Leitura e interpretação", "Educação básica",
                "Ler um aviso e responder três perguntas.", escola);
        MaterialEstudo matematica = new MaterialEstudo("Contagem de materiais", "Educação básica",
                "Praticar adição e subtração com materiais de estudo.", escola);
        MaterialEstudo organizacao = new MaterialEstudo("Organização dos estudos", "Ensino superior",
                "Planejar a leitura de um texto da faculdade.", faculdade);
        catalogo.cadastrarMaterial(leitura);
        catalogo.cadastrarMaterial(matematica);
        catalogo.cadastrarMaterial(organizacao);

        // abre o menu ou mostra o fluxo completo
        if (args.length > 0 && args[0].equals("--menu")) {
            executarMenu(catalogo, central, equipe);
        } else {
            demonstrar(catalogo, central, equipe, escola, faculdade);
        }
    }

    private static void demonstrar(CatalogoMateriais catalogo, CentralPedidos central,
                                   EquipeApoio equipe, Instituicao escola, Instituicao faculdade) {
        System.out.println("VOZES DO SABER - Demonstração em Java");
        System.out.println("Instituições fictícias: " + escola.getNome() + " e " + faculdade.getNome());

        System.out.println("\n1. MATERIAIS CADASTRADOS E BUSCA POR ETAPA");
        mostrarMateriais(catalogo.buscarPorEtapa("Educação básica"));
        mostrarMateriais(catalogo.buscarPorEtapa("Ensino superior"));
        System.out.println("Materiais da escola: " + escola.getMateriais().size());
        System.out.println("Materiais da faculdade: " + faculdade.getMateriais().size());

        System.out.println("\n2. REGISTRO DE PEDIDOS");
        PedidoApoio pedido1 = central.registrarPedido(TipoApoio.MATERIAL_DE_ESTUDO, "Preciso de material de matemática.");
        PedidoApoio pedido2 = central.registrarPedido(TipoApoio.APOIO_NO_ENSINO_SUPERIOR, "Preciso de orientação para estudar.");
        System.out.println(pedido1.resumo());
        System.out.println(pedido2.resumo());

        System.out.println("\n3. CONSULTA PELO PROTOCOLO");
        System.out.println(central.consultarPedido("vds-0001").resumo());
        System.out.println("Protocolo inexistente: " + (central.consultarPedido("VDS-9999") == null ? "não encontrado" : "encontrado"));

        System.out.println("\n4. ATUALIZAÇÃO PELA EQUIPE");
        equipe.atualizarPedido(central, pedido1.getProtocolo(), StatusPedido.EM_ANALISE, "");
        System.out.println("Em análise: " + pedido1.resumo());
        equipe.atualizarPedido(central, pedido1.getProtocolo(), StatusPedido.RESPONDIDO,
                "O material de matemática está disponível no catálogo.");
        System.out.println("Respondido: " + pedido1.resumo());
        equipe.atualizarPedido(central, pedido1.getProtocolo(), StatusPedido.CONCLUIDO,
                "Material localizado e orientação enviada.");
        System.out.println("Concluído: " + central.consultarPedido(pedido1.getProtocolo()).resumo());
        System.out.println("Total de pedidos: " + central.getPedidos().size());

        try {
            central.registrarPedido(TipoApoio.OUTRO, " ");
        } catch (IllegalArgumentException erro) {
            System.out.println("Validação: " + erro.getMessage());
        }
    }

    private static void executarMenu(CatalogoMateriais catalogo, CentralPedidos central, EquipeApoio equipe) {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            System.out.println("\nVOZES DO SABER");
            System.out.println("1 - Ver materiais  |  2 - Buscar por etapa  |  3 - Registrar pedido");
            System.out.println("4 - Consultar pedido  |  5 - Ver pedidos da equipe  |  6 - Atualizar pedido  |  0 - Sair");
            System.out.print("Escolha: ");
            String opcao = entrada.nextLine().trim();
            try {
                switch (opcao) {
                    case "1" -> mostrarMateriais(catalogo.getMateriais());
                    case "2" -> {
                        System.out.print("Etapa (Educação básica ou Ensino superior): ");
                        mostrarMateriais(catalogo.buscarPorEtapa(entrada.nextLine()));
                    }
                    case "3" -> {
                        TipoApoio[] tipos = TipoApoio.values();
                        for (int i = 0; i < tipos.length; i++) System.out.println((i + 1) + " - " + tipos[i].getDescricao());
                        System.out.print("Tipo: ");
                        int indice = Integer.parseInt(entrada.nextLine()) - 1;
                        if (indice < 0 || indice >= tipos.length) throw new IllegalArgumentException("Tipo inválido.");
                        System.out.print("Descreva a necessidade, sem dados pessoais: ");
                        PedidoApoio pedido = central.registrarPedido(tipos[indice], entrada.nextLine());
                        System.out.println("Pedido registrado. Guarde o protocolo: " + pedido.getProtocolo());
                    }
                    case "4" -> {
                        System.out.print("Protocolo: ");
                        PedidoApoio pedido = central.consultarPedido(entrada.nextLine());
                        System.out.println(pedido == null ? "Pedido não encontrado." : pedido.resumo());
                    }
                    case "5" -> {
                        if (central.getPedidos().isEmpty()) System.out.println("Nenhum pedido registrado.");
                        for (PedidoApoio pedido : central.getPedidos()) System.out.println(pedido.resumo());
                    }
                    case "6" -> {
                        System.out.print("Protocolo: ");
                        String protocolo = entrada.nextLine();
                        System.out.println("1 - Em análise  |  2 - Respondido  |  3 - Concluído");
                        System.out.print("Novo status: ");
                        String numero = entrada.nextLine();
                        StatusPedido status = switch (numero) {
                            case "1" -> StatusPedido.EM_ANALISE;
                            case "2" -> StatusPedido.RESPONDIDO;
                            case "3" -> StatusPedido.CONCLUIDO;
                            default -> throw new IllegalArgumentException("Status inválido.");
                        };
                        System.out.print("Resposta da equipe: ");
                        equipe.atualizarPedido(central, protocolo, status, entrada.nextLine());
                        System.out.println("Pedido atualizado: " + central.consultarPedido(protocolo).resumo());
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException erro) {
                System.out.println("Não foi possível concluir: " + erro.getMessage());
            }
        }
    }

    private static void mostrarMateriais(List<MaterialEstudo> materiais) {
        if (materiais.isEmpty()) System.out.println("Nenhum material encontrado.");
        for (MaterialEstudo material : materiais) System.out.println("- " + material.resumo());
    }
}
