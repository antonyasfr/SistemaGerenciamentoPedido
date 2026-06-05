/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.controller;

import java.io.IOException;
import java.util.List;
import sistemagerenciamentopedidos.model.Item;
import sistemagerenciamentopedidos.model.Produto;
import sistemagerenciamentopedidos.repository.ItemRepository;
import sistemagerenciamentopedidos.repository.ProdutoRepository;
import sistemagerenciamentopedidos.util.Validador;

/**
 *
 * @author ueg
 */
public class ItemController {
    
    private final ItemRepository itemRepository;
    private final ProdutoRepository produtoRepository;

    public ItemController() {
        this.itemRepository = new ItemRepository();
        this.produtoRepository = new ProdutoRepository();
    }

    public void incluirItem(Item item) throws IOException {

        if (!Validador.idValido(item.getCod_pedido()) || !Validador.idValido(item.getSeq_item())) {
            throw new IOException("Erro: Codigo do pedido e sequencia do item devem ser validos.");
        }
        if (!Validador.quantidadeItensValida(item.getQtde_itens())) {
            throw new IOException("Erro: A quantidade de itens deve ser maior que zero.");
        }

        Produto produto = produtoRepository.consultar(item.getCod_produto());
        if (produto == null) {
            throw new IOException("Erro: Produto inexistente.");
        }

        if (item.getQtde_itens() > produto.getQtde_produto()) {
            throw new IOException("Erro: Quantidade maior que o estoque disponivel (" + produto.getQtde_produto() + ").");
        }

        produto.setQtde_produto(produto.getQtde_produto() - item.getQtde_itens());
        produtoRepository.alterar(produto);

        item.setPreco_total(item.getQtde_itens() * item.getPreco_uni_item());

        itemRepository.incluir(item);
    }

    public void alterarItem(Item item) throws IOException {
        if (!Validador.quantidadeItensValida(item.getQtde_itens())) {
            throw new IOException("Erro: A quantidade de itens deve ser maior que zero.");
        }

        Item itemAntigo = itemRepository.consultar(item.getCod_pedido(), item.getSeq_item());
        if (itemAntigo == null) {
            throw new IOException("Erro: Item de pedido nao encontrado.");
        }

        Produto produto = produtoRepository.consultar(item.getCod_produto());
        if (produto == null) {
            throw new IOException("Erro: Produto nao encontrado.");
        }

        int diferencaQtde = item.getQtde_itens() - itemAntigo.getQtde_itens();

        if (diferencaQtde > produto.getQtde_produto()) {
            throw new IOException("Erro: Estoque insuficiente para o ajuste solicitado.");
        }

        produto.setQtde_produto(produto.getQtde_produto() - diferencaQtde);
        produtoRepository.alterar(produto);

        item.setPreco_total(item.getQtde_itens() * itemAntigo.getPreco_uni_item());

        itemRepository.alterar(item);
    }

    public void excluirItem(int codPedido, int seq) throws IOException {
        Item item = itemRepository.consultar(codPedido, seq);
        if (item == null) {
            throw new IOException("Erro: Item nao encontrado para exclusao.");
        }

        Produto produto = produtoRepository.consultar(item.getCod_produto());
        if (produto != null) {
            produto.setQtde_produto(produto.getQtde_produto() + item.getQtde_itens());
            produtoRepository.alterar(produto);
        }

        itemRepository.excluir(codPedido, seq);
    }

    public Item consultarItem(int codPedido, int seq) {
        return itemRepository.consultar(codPedido, seq);
    }

    public List<Item> listarItens() {
        return itemRepository.listar();
    }
}