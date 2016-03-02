package br.edu.ifspsaocarlos.sdm2.tutorialrealm.data;

import android.content.Context;

import java.util.List;

import br.edu.ifspsaocarlos.sdm2.tutorialrealm.model.Cliente;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ClienteDAO {
    private final Realm realm;

    public ClienteDAO(Context context) {
        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        // Get a Realm instance for this thread
        this.realm = Realm.getInstance(realmConfig);
    }

    public void criar(Cliente cliente) {
        realm.beginTransaction();
        realm.copyToRealm(cliente);
        realm.commitTransaction();
    }

    public void excluir(Cliente cliente) {
        Cliente cli = buscaClientePeloTelefone(cliente.getTelefone());

        realm.beginTransaction();
        cliente.removeFromRealm();
        realm.commitTransaction();
    }

    private Cliente buscaClientePeloTelefone(String telefone) {
        return realm.where(Cliente.class).equalTo("telefone", telefone).findFirst();
    }

    public List<Cliente> buscaTodosClientes() {
        return realm.where(Cliente.class).findAll();
    }

    public List<Cliente> buscaClientePeloNome(String query) {
        return realm.where(Cliente.class).contains("nome", query).findAll();
    }

}
