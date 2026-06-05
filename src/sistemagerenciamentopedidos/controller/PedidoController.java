/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.controller;

import java.io.IOException;
import java.util.List;
import sistemagerenciamentopedidos.model.Pedido;
import sistemagerenciamentopedidos.repository.PedidoRepository;
import sistemagerenciamentopedidos.util.Validador;
/**
 *
 * @author ueg
 */
public class PedidoController {
    
    private final PedidoRepository pedidoRepository;

    public PedidoController() {
        this.pedidoRepository = new PedidoRepository();
    }

    public void incluirPedido(Pedido pedido) throws IOException {
        
        if (!Validador.idValido(pedido.getCod_pedido())) {
            throw new IOException("Erro: O codigo do pedido deve ser um valor maior que zero.");
        }

        if (!Validador.idValido(pedido.getId_cliente())) {
            throw new IOException("Erro: O pedido deve estar associado a um cliente valido.");
        }

        if (pedido.getDt_entrega() != null && pedido.getDt_pedido() != null) {
            if (pedido.getDt_entrega().before(pedido.getDt_pedido())) {
                throw new IOException("Erro: A data de entrega nao pode ser anterior a data do pedido.");
            }
        }

        pedidoRepository.incluir(pedido);
    }

    public void alterarPedido(Pedido pedido) throws IOException {
        if (!Validador.idValido(pedido.getId_cliente())) {
            throw new IOException("Erro: O cliente associado deve ser valido.");
        }

        if (pedido.getDt_entrega() != null && pedido.getDt_pedido() != null) {
            if (pedido.getDt_entrega().before(pedido.getDt_pedido())) {
                throw new IOException("Erro: A data de entrega nao pode ser anterior a data do pedido.");
            }
        }
        
        pedidoRepository.alterar(pedido);
    }

    public void excluirPedido(int id) throws IOException {
        if (!Validador.idValido(id)) {
            throw new IOException("Erro: Codigo invalido para exclusao.");
        }
        
        pedidoRepository.excluir(id);
    }

    public Pedido consultarPedido(int id) {
        if (!Validador.idValido(id)) {
            return null;
        }
        return pedidoRepository.consultar(id);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.listar();
    }
}