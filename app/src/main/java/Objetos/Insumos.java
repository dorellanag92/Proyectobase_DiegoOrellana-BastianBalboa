package Objetos;

import java.util.Arrays;
import java.util.Objects;

public class Insumos {

    private int id;
    private String[] insumos = {"Mancuernas Ajustables","Barras","Banca Press","Punching Bag","Cuerda","Trotadora","Balón de Pilates","Elíptica", "Alfombra de yoga"};
    private int[] precios = {50000,15000,100000,35000,8000,250000,10000,210000,5000};
    private int[] ratings = {5,4,2,5,4,5,5,2,3};
    private int stock;

    public Insumos(){
    }

    public Insumos(int id, String[] insumos, int[] precios,int[] ratings,  int stock) {
        this.id = id;
        this.insumos = insumos;
        this.precios = precios;
        this.ratings = ratings;
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

    public int[] getRatings() {
        return ratings;
    }

    public void setRatings(int[] ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insumos insumos1 = (Insumos) o;
        return id == insumos1.id && stock == insumos1.stock && Arrays.equals(insumos, insumos1.insumos) && Arrays.equals(precios, insumos1.precios) && Arrays.equals(ratings, insumos1.ratings);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Insumos{" +
                "id=" + id +
                ", insumos=" + Arrays.toString(insumos) +
                ", precios=" + Arrays.toString(precios) +
                ", ratings=" + Arrays.toString(ratings) +
                ", stock=" + stock +
                '}';
    }

    //Reglas de negocio
    public int anadirAdicional(int valor, int adicional){
        return valor + adicional;
    }
}
