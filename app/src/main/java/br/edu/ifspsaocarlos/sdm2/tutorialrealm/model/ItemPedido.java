package br.edu.ifspsaocarlos.sdm2.tutorialrealm.model;

import java.math.BigDecimal;

import io.realm.RealmObject;

public class ItemPedido extends RealmObject {
    private String produto;
    private int quantidade;
    private double valor;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
