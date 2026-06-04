/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.util;

public class Validador {

    public static boolean campoObrigatorio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean idValido(int id) {
        return id > 0;
    }

    public static boolean valorNaoNegativo(double valor) {
        return valor >= 0;
    }

    public static boolean quantidadeItensValida(int quantidade) {
        return quantidade > 0;
    }
}