/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sistemagerenciamentopedidos.model.Produto;
import sistemagerenciamentopedidos.util.ArquivoUtil;

public class ProdutoRepository {

    private static final String ARQUIVO_PRODUTOS = "produtos.csv";

    // Converte a lista de produtos para uma String gigante formatada em CSV
    private String listaParaCsv(List<Produto> lista) {
        StringBuilder sb = new StringBuilder();
        for (Produto p : lista) {
            sb.append(p.getCod_produto()).append(";")
              .append(p.getNome()).append(";")
              .append(p.getPreco()).append(";")
              .append(p.getQtde_produto()).append("\n");
        }
        return sb.toString();
    }

    // Converte o texto bruto do CSV de volta para uma lista de objetos Produto em memória
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        try {
            String conteudo = ArquivoUtil.carregarDados(ARQUIVO_PRODUTOS);
            if (conteudo.isEmpty()) {
                return lista;
            }

            String[] linhas = conteudo.split("\n");
            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;
                
                String[] colunas = linha.split(";");
                if (colunas.length >= 4) {
                    int cod = Integer.parseInt(colunas[0].trim());
                    String nome = colunas[1].trim();
                    // O Documento pede float/double para preço e int para quantidade
                    float preco = Float.parseFloat(colunas[2].trim()); 
                    int qtde = Integer.parseInt(colunas[3].trim());
                    
                    // Se o seu construtor no Model tiver uma ordem diferente, ajuste aqui!
                    Produto p = new Produto(cod, nome, preco, qtde); 
                    lista.add(p);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler os produtos do arquivo: " + e.getMessage());
        }
        return lista;
    }

    public void incluir(Produto produto) throws IOException {
        List<Produto> produtos = listar();
        // Regra de negócio: impede Código duplicado (RF06)
        for (Produto p : produtos) {
            if (p.getCod_produto() == produto.getCod_produto()) {
                throw new IOException("Erro: Ja existe um produto com o Codigo " + produto.getCod_produto());
            }
        }
        produtos.add(produto);
        ArquivoUtil.salvarDados(listaParaCsv(produtos), ARQUIVO_PRODUTOS);
    }

    public void alterar(Produto produto) throws IOException {
        List<Produto> produtos = listar();
        boolean encontrado = false;

        for (Produto p : produtos) {
            if (p.getCod_produto() == produto.getCod_produto()) {
                // RF06: O código do produto não pode ser alterado. Altera apenas o resto.
                p.setNome(produto.getNome());
                p.setPreco(produto.getPreco());
                p.setQtde_produto(produto.getQtde_produto());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new IOException("Produto com Codigo " + produto.getCod_produto() + " nao encontrado.");
        }

        ArquivoUtil.salvarDados(listaParaCsv(produtos), ARQUIVO_PRODUTOS);
    }

    public void excluir(int cod) throws IOException {
        List<Produto> produtos = listar();
        // Remove o produto que tiver o Código correspondente (RF07)
        boolean removido = produtos.removeIf(p -> p.getCod_produto() == cod);

        if (!removido) {
            throw new IOException("Produto com Codigo " + cod + " nao encontrado para exclusao.");
        }

        ArquivoUtil.salvarDados(listaParaCsv(produtos), ARQUIVO_PRODUTOS);
    }

    public Produto consultar(int cod) {
        List<Produto> produtos = listar();
        for (Produto p : produtos) {
            if (p.getCod_produto() == cod) {
                return p; // Retorna o produto específico (RF08)
            }
        }
        return null; // Retorna null se não achar
    }
}