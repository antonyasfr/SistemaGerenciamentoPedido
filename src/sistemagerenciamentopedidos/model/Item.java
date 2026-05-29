/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.model;

/**
 *
 * @author ueg
 */
public class Item {
    private int cod_pedido;
    private int seq_item;
    private int cod_produto;
    private int qtde_itens;
    private float preco_uni_item;
    private float preco_total;

    public Item(int cod_pedido, int seq_item, int cod_produto, int qtde_itens, float preco_uni_item, float preco_total) {
        this.cod_pedido = cod_pedido;
        this.seq_item = seq_item;
        this.cod_produto = cod_produto;
        this.qtde_itens = qtde_itens;
        this.preco_uni_item = preco_uni_item;
        this.preco_total = preco_total;
    }

    public int getCod_pedido() {
        return cod_pedido;
    }

    public void setCod_pedido(int cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    public int getSeq_item() {
        return seq_item;
    }

    public void setSeq_item(int seq_item) {
        this.seq_item = seq_item;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public int getQtde_itens() {
        return qtde_itens;
    }

    public void setQtde_itens(int qtde_itens) {
        this.qtde_itens = qtde_itens;
    }

    public float getPreco_uni_item() {
        return preco_uni_item;
    }

    public void setPreco_uni_item(float preco_uni_item) {
        this.preco_uni_item = preco_uni_item;
    }

    public float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
    }
    
}
