/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sistemagerenciamentopedidos.model.Cliente;
import sistemagerenciamentopedidos.util.ArquivoUtil;

public class ClienteRepository {

    private static final String ARQUIVO_CLIENTES = "clientes.csv";

    private String listaParaCsv(List<Cliente> lista) {
        StringBuilder sb = new StringBuilder();
        for (Cliente c : lista) {
            sb.append(c.getId_cliente())
              .append(";")
              .append(c.getNome())
              .append("\n");
        }
        return sb.toString();
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        try {
            String conteudo = ArquivoUtil.carregarDados(ARQUIVO_CLIENTES);
            if (conteudo.isEmpty()) {
                return lista;
            }

            String[] linhas = conteudo.split("\n");
            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;
                
                String[] colunas = linha.split(";");
                if (colunas.length >= 2) {
                    int id = Integer.parseInt(colunas[0].trim());
                    String nome = colunas[1].trim();
                    lista.add(new Cliente(id, nome));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler os clientes do arquivo: " + e.getMessage());
        }
        return lista;
    }

    public void incluir(Cliente cliente) throws IOException {
        List<Cliente> clientes = listar();
        for (Cliente c : clientes) {
            if (c.getId_cliente() == cliente.getId_cliente()) {
                throw new IOException("Erro: Ja existe um cliente com o ID " + cliente.getId_cliente());
            }
        }
        clientes.add(cliente);
        ArquivoUtil.salvarDados(listaParaCsv(clientes), ARQUIVO_CLIENTES);
    }

    public void alterar(Cliente cliente) throws IOException {
        List<Cliente> clientes = listar();
        boolean encontrado = false;

        for (Cliente c : clientes) {
            if (c.getId_cliente() == cliente.getId_cliente()) {
                c.setNome(cliente.getNome());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new IOException("Cliente com ID " + cliente.getId_cliente() + " nao encontrado.");
        }

        ArquivoUtil.salvarDados(listaParaCsv(clientes), ARQUIVO_CLIENTES);
    }

    public void excluir(int id) throws IOException {
        List<Cliente> clientes = listar();
        boolean removido = clientes.removeIf(c -> c.getId_cliente() == id);

        if (!removido) {
            throw new IOException("Cliente com ID " + id + " nao encontrado para exclusao.");
        }

        ArquivoUtil.salvarDados(listaParaCsv(clientes), ARQUIVO_CLIENTES);
    }

    public Cliente consultar(int id) {
        List<Cliente> clientes = listar();
        for (Cliente c : clientes) {
            if (c.getId_cliente() == id) {
                return c;
            }
        }
        return null;
    }
}