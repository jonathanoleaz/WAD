/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectAndDao;

/**
 *
 * @author jonat
 */
public class Datos {
    private int valor;
    private String atributo;

    public Datos(int valor, String atributo) {
        this.valor = valor;
        this.atributo = atributo;
    }

    Datos() {

    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    @Override
    public String toString() {
        return "Datos{" + "valor=" + valor + ", atributo=" + atributo + '}';
    }
    
    
    
    
    
}
