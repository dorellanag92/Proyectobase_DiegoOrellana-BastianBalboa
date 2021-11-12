package Objetos;

import java.util.Arrays;
import java.util.Objects;

public class Insumos {

    private int id;
    private String[] insumos = {"Mancuernas","Barras","Banca Press","Punching Bag","Guantes","Corredora"};
    private int[] precios = {25000,12000,120000,35000,30000,250000};
    private int stock;

    public Insumos(){

    }

    public Insumos(int id, String[] insumos, int[] precios, int stock) {
        this.id = id;
        this.insumos = insumos;
        this.precios = precios;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getInsumos() {
        return insumos;
    }

    public void setInsumos(String[] insumos) {
        this.insumos = insumos;
    }

    public int[] getPrecios() {
        return precios;
    }

    public void setPrecios(int[] precios) {
        this.precios = precios;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insumos insumos1 = (Insumos) o;
        return id == insumos1.id && stock == insumos1.stock && Arrays.equals(insumos, insumos1.insumos) && Arrays.equals(precios, insumos1.precios);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, stock);
        result = 31 * result + Arrays.hashCode(insumos);
        result = 31 * result + Arrays.hashCode(precios);
        return result;
    }

    @Override
    public String toString() {
        return "Insumos{" +
                "id=" + id +
                ", insumos=" + Arrays.toString(insumos) +
                ", precios=" + Arrays.toString(precios) +
                ", stock=" + stock +
                '}';
    }

    //Reglas de negocio

    //Para adicional
    public int anadirAdicional(int valor, int adicional){
        return valor + adicional;
    }
}
