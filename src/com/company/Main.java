package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String senha = "123456";
        String nomes[] = {"vendedor", "comprador"};
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto(0, "produto 1", (float)100.00,10));
        listaProdutos.add(new Produto(1, "produto 2", (float)80.00, 15));
        listaProdutos.add(new Produto(2, "produto 3", (float)50.00, 5));
        listaProdutos.add(new Produto(3, "produto 4", (float)125.99, 8));

        boolean exe = true;
        while(exe) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escreva seu nome");
            String nomeDigitado = scanner.nextLine();
            System.out.println("Escreva a senha");
            String senhaDigitada = scanner.nextLine();

            for (int i = 0; i < nomes.length; i++) {
                if (nomes[i].equals(nomeDigitado) && senha.equals(senhaDigitada)) {
                    if (nomeDigitado.equals("comprador")) {
                        ArrayList<Produto> listaCompras = new ArrayList<Produto>();
                        while (exe){
                            System.out.println("Digite \n-1 Adicionar ao carrinho\n-2 Deletar item\n-3 Listar\n-4 Gerar txt\n 0 - Sair");
                            int dig = Integer.parseInt(scanner.nextLine());
                            switch (dig){
                                case 1:
                                    System.out.println("Selecione um produto para adicionar");
                                    for (int j = 0; j<listaProdutos.size(); j++){
                                        System.out.println(j + " " +listaProdutos.get(j).name);
                                    }
                                    int select = Integer.parseInt(scanner.nextLine());
                                    listaCompras.add(listaProdutos.get(select));
                                    break;

                                case 2:
                                    System.out.println("Selecione um produto para excluir");
                                    if(listaCompras.size()<0) {
                                        System.out.println("Nenhum item no carrinho");
                                    }else {
                                        for (int j = 0; j < listaCompras.size(); j++) {
                                            System.out.println(j + " " + listaCompras.get(j).name);
                                        }
                                        select = Integer.parseInt(scanner.nextLine());
                                        listaCompras.remove(listaCompras.get(select));
                                    }
                                    break;
                                case 3:
                                    float total = 0;
                                    System.out.println("Lista de compras");
                                    for (int j = 0; j<listaCompras.size(); j++){
                                        System.out.println(listaCompras.get(j).name);
                                        total += listaCompras.get(j).valor;
                                    }
                                    if(total>100.0){
                                        total = total * (float)0.95;
                                    }
                                    System.out.println("Valor total do carrinho: " + total);
                                    break;

                                case 4:
                                    FileWriter arq = new FileWriter("ArquivoProva.txt");
                                    PrintWriter gravarArq = new PrintWriter(arq);
                                    gravarArq.println("Lista de compras");
                                    for (int j=0; j<listaCompras.size(); j++) {
                                        gravarArq.println(listaCompras.get(j).name);
                                    }
                                    gravarArq.println("+-------------+%n");
                                    arq.close();
                                    break;
                                case 0:
                                    exe = false;
                                    break;
                                default:
                                    System.out.println("Insira um valor válido");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("logado como vendedor");
                        while (exe) {
                            System.out.println("Digite \n-1 Adicionar produtos ao estoque\n-2 Alterar valor \n-3 Listar produtos cadastrados\n-4 Gerar txt\n-5 Deletar produto\n-0 Sair");
                            int dig = Integer.parseInt(scanner.nextLine());
                            switch (dig) {
                                case 1:
                                    System.out.println("Digite um nome para o produto");
                                    String productName = scanner.nextLine();

                                    System.out.println("Digite o valor do produto");
                                    float productValue = Float.parseFloat(scanner.nextLine());

                                    System.out.println("Digite a quantidade em estoque");
                                    int productQtd = Integer.parseInt(scanner.nextLine());

                                    listaProdutos.add(new Produto(listaProdutos.size(), productName, productValue, productQtd));
                                    break;

                                case 2:
                                    System.out.println("Selecione um produto para alterar o valor");
                                    if (listaProdutos.size() < 0) {
                                        System.out.println("Nenhum produto cadastrado");
                                    } else {
                                        for (int j = 0; j < listaProdutos.size(); j++) {
                                            System.out.println(j + " " + listaProdutos.get(j).name);
                                        }
                                        int select = Integer.parseInt(scanner.nextLine());
                                        System.out.println("Digite o novo valor do produto");
                                        listaProdutos.get(select).valor = Float.parseFloat(scanner.nextLine());
                                    }
                                    break;
                                case 3:
                                    System.out.println("Lista de produtos");
                                    for (int j = 0; j < listaProdutos.size(); j++) {
                                        System.out.println(listaProdutos.get(j).name);
                                    }
                                    System.out.println("Valor total de produtos cadastrados: ");
                                    break;
                                case 4:
                                    FileWriter arq = new FileWriter("ArquivoProva.txt");
                                    PrintWriter gravarArq = new PrintWriter(arq);
                                    gravarArq.printf(" ");
                                    gravarArq.printf("Lista de compras");
                                    for (int j = 0; j < listaProdutos.size(); j++) {
                                        gravarArq.printf(listaProdutos.get(j).name);
                                    }
                                    gravarArq.printf("+-------------+%n");

                                    arq.close();
                                    break;
                                case 5:
                                    System.out.println("Selecione um produto para deletar");
                                    if (listaProdutos.size() < 0) {
                                        System.out.println("Nenhum produto cadastrado");
                                    } else {
                                        for (int j = 0; j < listaProdutos.size(); j++) {
                                            System.out.println(j + " " + listaProdutos.get(j).name);
                                        }
                                        listaProdutos.remove(Integer.parseInt(scanner.nextLine()));
                                    }
                                    break;
                                case 0:
                                    exe = false;
                                    break;
                                default:
                                    System.out.println("Insira um valor válido");
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }
}
