/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.controller;

import java.io.IOException;
import java.util.List;
import sistemagerenciamentopedidos.model.Produto;
import sistemagerenciamentopedidos.repository.ProdutoRepository;
import sistemagerenciamentopedidos.util.Validador;

/**
 *
 * @author ueg
 */
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    public ProdutoController() {
        this.produtoRepository = new ProdutoRepository();
    }

    public void incluirProduto(Produto produto) throws IOException {
        if (!Validador.campoObrigatorio(produto.getNome())) {
            throw new IOException("Erro: O nome do produto e obrigatorio e nao pode estar vazio.");
        }

        if (!Validador.idValido(produto.getCod_produto())) {
            throw new IOException("Erro: O codigo do produto deve ser um valor maior que zero.");
        }

        if (!Validador.valorNaoNegativo(produto.getPreco())) {
            throw new IOException("Erro: O preço do produto nao pode ser negativo.");
        }

        if (!Validador.valorNaoNegativo(produto.getQtde_produto())) {
            throw new IOException("Erro: A quantidade em estoque nao pode ser negativa.");
        }

        produtoRepository.incluir(produto);
    }

    public void alterarProduto(Produto produto) throws IOException {
        if (!Validador.campoObrigatorio(produto.getNome())) {
            throw new IOException("Erro: O nome do produto nao pode estar vazio para alteracao.");
        }

        if (!Validador.valorNaoNegativo(produto.getPreco())) {
            throw new IOException("Erro: O preco do produto nao pode ser negativo.");
        }

        if (!Validador.valorNaoNegativo(produto.getQtde_produto())) {
            throw new IOException("Erro: A quantidade em estoque nao pode ser negativa.");
        }
        
        produtoRepository.alterar(produto);
    }

    public void excluirProduto(int id) throws IOException {
        if (!Validador.idValido(id)) {
            throw new IOException("Erro: Codigo invalido para exclusao.");
        }
        
        produtoRepository.excluir(id);
    }

    public Produto consultarProduto(int id) {
        if (!Validador.idValido(id)) {
            return null;
        }
        return produtoRepository.consultar(id);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listar();
    }
}