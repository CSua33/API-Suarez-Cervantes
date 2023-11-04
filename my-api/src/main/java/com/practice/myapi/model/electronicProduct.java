package com.practice.myapi.model;

public class electronicProduct {
    //Este es el objeto de negocio
    //Este es un objeto que vamos a usar como estructura por tanto
    //no es necesario crear sets, gets y atributos privados, simplemente usamos el final
    public final long id;
    public final String name;
    public final int price;


    public electronicProduct(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
