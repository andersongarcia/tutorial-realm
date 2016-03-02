package br.edu.ifspsaocarlos.sdm2.tutorialrealm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Pedido extends RealmObject {
    private Cliente cliente;
    private RealmList<ItemPedido> items;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public RealmList<ItemPedido> getItems() {
        return items;
    }

    public void setItems(RealmList<ItemPedido> items) {
        this.items = items;
    }
}
