/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.model;
import java.util.Date;
/**
 *
 * @author ueg
 */
public class Pedido {
    private int cod_pedido;
    private int id_cliente;
    private Date dt_pedido;
    private Date dt_entrega;
    private float vlr_total;

    public Pedido(int cod_pedido, int id_cliente, Date dt_pedido, Date dt_entrega, float vlr_total) {
        this.cod_pedido = cod_pedido;
        this.id_cliente = id_cliente;
        this.dt_pedido = dt_pedido;
        this.dt_entrega = dt_entrega;
        this.vlr_total = vlr_total;
    }

    public int getCod_pedido() {
        return cod_pedido;
    }

    public void setCod_pedido(int cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getDt_pedido() {
        return dt_pedido;
    }

    public void setDt_pedido(Date dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    public Date getDt_entrega() {
        return dt_entrega;
    }

    public void setDt_entrega(Date dt_entrega) {
        this.dt_entrega = dt_entrega;
    }

    public float getVlr_total() {
        return vlr_total;
    }

    public void setVlr_total(float vlr_total) {
        this.vlr_total = vlr_total;
    }
    
}
