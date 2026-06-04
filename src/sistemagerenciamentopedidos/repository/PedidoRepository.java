/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sistemagerenciamentopedidos.model.Pedido;
import sistemagerenciamentopedidos.util.ArquivoUtil;

public class PedidoRepository {

    private static final String ARQUIVO_PEDIDOS = "pedidos.csv"; // Conforme item 5.7
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Para salvar a data bonita no CSV

    // Converte a lista de pedidos para texto CSV (Item 6.3)
    private String listaParaCsv(List<Pedido> lista) {
        StringBuilder sb = new StringBuilder();
        for (Pedido p : lista) {
            String dtPedidoStr = p.getDt_pedido() != null ? sdf.format(p.getDt_pedido()) : "";
            String dtEntregaStr = p.getDt_entrega() != null ? sdf.format(p.getDt_entrega()) : "";
            
            sb.append(p.getCod_pedido()).append(";")
              .append(p.getId_cliente()).append(";")
              .append(dtPedidoStr).append(";")
              .append(dtEntregaStr).append(";")
              .append(p.getVlr_total()).append("\n");
        }
        return sb.toString();
    }

    // Lê o arquivo pedidos.csv e reconstrói os objetos em memória
    public List<Pedido> listar() {
        List<Pedido> lista = new ArrayList<>();
        try {
            String conteudo = ArquivoUtil.carregarDados(ARQUIVO_PEDIDOS);
            if (conteudo.isEmpty()) {
                return lista;
            }

            String[] linhas = conteudo.split("\n");
            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;
                
                String[] colunas = linha.split(";");
                if (colunas.length >= 5) {
                    int cod = Integer.parseInt(colunas[0].trim());
                    int idCliente = Integer.parseInt(colunas[1].trim());
                    
                    Date dtPedido = colunas[2].trim().isEmpty() ? null : sdf.parse(colunas[2].trim());
                    Date dtEntrega = colunas[3].trim().isEmpty() ? null : sdf.parse(colunas[3].trim());
                    float vlrTotal = Float.parseFloat(colunas[4].trim());
                    
                    Pedido p = new Pedido(cod, idCliente, dtPedido, dtEntrega, vlrTotal);
                    lista.add(p);
                }
            }
        } catch (IOException | ParseException | NumberFormatException e) {
            System.err.println("Erro ao ler os pedidos do arquivo: " + e.getMessage());
        }
        return lista;
    }

    public void incluir(Pedido pedido) throws IOException {
        List<Pedido> pedidos = listar();
        for (Pedido p : pedidos) {
            if (p.getCod_pedido() == pedido.getCod_pedido()) {
                throw new IOException("Erro: Já existe um pedido com o Código " + pedido.getCod_pedido());
            }
        }
        pedidos.add(pedido);
        ArquivoUtil.salvarDados(listaParaCsv(pedidos), ARQUIVO_PEDIDOS);
    }

    public void alterar(Pedido pedido) throws IOException {
        List<Pedido> pedidos = listar();
        boolean encontrado = false;

        for (Pedido p : pedidos) {
            if (p.getCod_pedido() == pedido.getCod_pedido()) {
                // RF13: Permite alterar o cliente associado e as datas. O valor total é recalculado.
                p.setId_cliente(pedido.getId_cliente());
                p.setDt_pedido(pedido.getDt_pedido());
                p.setDt_entrega(pedido.getDt_entrega());
                p.setVlr_total(pedido.getVlr_total());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new IOException("Pedido com Código " + pedido.getCod_pedido() + " não encontrado.");
        }

        ArquivoUtil.salvarDados(listaParaCsv(pedidos), ARQUIVO_PEDIDOS);
    }

    public void excluir(int cod) throws IOException {
        List<Pedido> pedidos = listar();
        boolean removido = pedidos.removeIf(p -> p.getCod_pedido() == cod);

        if (!removido) {
            throw new IOException("Pedido com Código " + cod + " não encontrado para exclusão.");
        }

        ArquivoUtil.salvarDados(listaParaCsv(pedidos), ARQUIVO_PEDIDOS);
    }

    public Pedido consultar(int cod) {
        List<Pedido> pedidos = listar();
        for (Pedido p : pedidos) {
            if (p.getCod_pedido() == cod) {
                return p; // RF15
            }
        }
        return null;
    }
}