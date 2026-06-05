/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.controller;

import java.io.IOException;
import java.util.List;
import sistemagerenciamentopedidos.model.Cliente;
import sistemagerenciamentopedidos.repository.ClienteRepository;
import sistemagerenciamentopedidos.util.Validador;

/**
 *
 * @author ueg
 */
public class ClienteController {
   private final ClienteRepository clienteRepository;

    public ClienteController() {
        this.clienteRepository = new ClienteRepository();
    }

    public void incluirCliente(Cliente cliente) throws IOException {
        if (!Validador.campoObrigatorio(cliente.getNome())) {
            throw new IOException("Erro: O nome do cliente e obrigatorio e nao pode estar vazio.");
        }
        
        if (!Validador.idValido(cliente.getId_cliente())) {
            throw new IOException("Erro: O ID do cliente deve ser um valor maior que zero.");
        }

        clienteRepository.incluir(cliente);
    }

    public void alterarCliente(Cliente cliente) throws IOException {
        if (!Validador.campoObrigatorio(cliente.getNome())) {
            throw new IOException("Erro: O nome do cliente nao pode estar vazio para alteracao.");
        }
        
        clienteRepository.alterar(cliente);
    }

    public void excluirCliente(int id) throws IOException {
        if (!Validador.idValido(id)) {
            throw new IOException("Erro: ID invalido para exclusao.");
        }
        
        clienteRepository.excluir(id);
    }

    public Cliente consultarCliente(int id) {
        if (!Validador.idValido(id)) {
            return null;
        }
        return clienteRepository.consultar(id);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.listar();
    }
}