package br.edu.ifspsaocarlos.sdm2.tutorialrealm.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Cliente extends RealmObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Required   // telefone não pode ser nulo
    private String telefone;
    private String nome;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
