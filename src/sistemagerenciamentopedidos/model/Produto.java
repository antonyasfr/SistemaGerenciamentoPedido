/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.model;

/**
 *
 * @author ueg
 */
public class Produto {
    private int cod_produto;
    private String nome;
    private float preco;
    private int qtde_produto;

    public Produto(int cod_produto, String nome, float preco, int qtde_produto) {
        this.cod_produto = cod_produto;
        this.nome = nome;
        this.preco = preco;
        this.qtde_produto = qtde_produto;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQtde_produto() {
        return qtde_produto;
    }

    public void setQtde_produto(int qtde_produto) {
        this.qtde_produto = qtde_produto;
    }
    
}
