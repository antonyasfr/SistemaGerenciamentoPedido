/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.model;

/**
 *
 * @author ueg
 */
public class Cliente {
    private int id_cliente;
    private String nome;

    public Cliente(int id_cliente, String nome) {
        this.id_cliente = id_cliente;
        this.nome = nome;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
