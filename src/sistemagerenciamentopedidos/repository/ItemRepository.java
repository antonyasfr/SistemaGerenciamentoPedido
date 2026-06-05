/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sistemagerenciamentopedidos.model.Item;
import sistemagerenciamentopedidos.util.ArquivoUtil;

public class ItemRepository {

    private static final String ARQUIVO_ITENS = "itens.csv";

   
    private String listaParaCsv(List<Item> lista) {
        StringBuilder sb = new StringBuilder();
        for (Item i : lista) {
            sb.append(i.getCod_pedido()).append(";")
              .append(i.getSeq_item()).append(";")
              .append(i.getCod_produto()).append(";")
              .append(i.getQtde_itens()).append(";")
              .append(i.getPreco_uni_item()).append(";")
              .append(i.getPreco_total()).append("\n");
        }
        return sb.toString();
    }

     
    public List<Item> listar() {
        List<Item> lista = new ArrayList<>();
        try {
            String conteudo = ArquivoUtil.carregarDados(ARQUIVO_ITENS);
            if (conteudo.isEmpty()) {
                return lista;
            }

            String[] linhas = conteudo.split("\n");
            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;
                
                String[] colunas = linha.split(";");
                if (colunas.length >= 6) {
                    int codPedido = Integer.parseInt(colunas[0].trim());
                    int seqItem = Integer.parseInt(colunas[1].trim());
                    int codProduto = Integer.parseInt(colunas[2].trim());
                    int qtdeItens = Integer.parseInt(colunas[3].trim());
                    float precoUni = Float.parseFloat(colunas[4].trim());
                    float precoTotal = Float.parseFloat(colunas[5].trim());
                    
                    
                    Item item = new Item(codPedido, seqItem, codProduto, qtdeItens, precoUni, precoTotal);
                    lista.add(item);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler os itens do arquivo: " + e.getMessage());
        }
        return lista;
    }

    public void incluir(Item item) throws IOException {
        List<Item> itens = listar();
        
       
        for (Item i : itens) {
            if (i.getCod_pedido() == item.getCod_pedido() && i.getSeq_item() == item.getSeq_item()) {
                throw new IOException("Erro: Ja existe um item com a sequencia " + item.getSeq_item() + " para o pedido " + item.getCod_pedido());
            }
        }
        itens.add(item);
        ArquivoUtil.salvarDados(listaParaCsv(itens), ARQUIVO_ITENS);
    }

    public void alterar(Item item) throws IOException {
        List<Item> itens = listar();
        boolean encontrado = false;

        for (Item i : itens) {
            if (i.getCod_pedido() == item.getCod_pedido() && i.getSeq_item() == item.getSeq_item()) {
               
                i.setQtde_itens(item.getQtde_itens());
                i.setPreco_total(item.getPreco_total());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new IOException("Item nao encontrado para alteracao.");
        }

        ArquivoUtil.salvarDados(listaParaCsv(itens), ARQUIVO_ITENS);
    }

 
    public void excluir(int codPedido, int seqItem) throws IOException {
        List<Item> itens = listar();
        boolean removido = itens.removeIf(i -> i.getCod_pedido() == codPedido && i.getSeq_item() == seqItem);

        if (!removido) {
            throw new IOException("Item não encontrado para exclusão.");
        }

        ArquivoUtil.salvarDados(listaParaCsv(itens), ARQUIVO_ITENS);
    }
 
    public final Item consultar(int codPedido, int seqItem) {
        List<Item> itens = listar();
        for (Item i : itens) {
            if (i.getCod_pedido() == codPedido && i.getSeq_item() == seqItem) {
                return i;
            }
        }
        return null;
    }
}