package br.edu.ifspsaocarlos.sdm2.tutorialrealm.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import br.edu.ifspsaocarlos.sdm2.tutorialrealm.R;
import br.edu.ifspsaocarlos.sdm2.tutorialrealm.adapter.ClienteArrayAdapter;
import br.edu.ifspsaocarlos.sdm2.tutorialrealm.data.ClienteDAO;
import br.edu.ifspsaocarlos.sdm2.tutorialrealm.model.Cliente;

public class BaseActivity extends AppCompatActivity {

    protected ClienteDAO dao;
    public ListView list;
    public ClienteArrayAdapter adapter;
    protected SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new ClienteDAO(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
                Cliente cliente = (Cliente) adapterView.getAdapter().getItem(arg2);
                Intent intent = new Intent(getApplicationContext(), DetalheActivity.class);
                intent.putExtra("cliente", cliente);
                startActivityForResult(intent, 0);
            }

        });

        registerForContextMenu(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.pesqContato).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
        ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ClienteArrayAdapter adapter = (ClienteArrayAdapter) list.getAdapter();
        Cliente cliente = adapter.getItem(info.position);
            switch(item.getItemId()) {
                case R.id.delete_item:
                    dao.excluir(cliente);
                    Toast.makeText(getApplicationContext(), R.string.msg_removido, Toast.LENGTH_SHORT).show();
                    buildListView();
                    return true;
            }
        return super.onContextItemSelected(item);
    }


    protected void buildListView() {
        List<Cliente> values = dao.buscaTodosClientes();
        adapter = new ClienteArrayAdapter(this, values);
        list.setAdapter(adapter);
    }

    protected void buildSearchListView(String query) {
        List<Cliente> values = dao.buscaClientePeloNome(query);
        adapter= new ClienteArrayAdapter(this, values);
        list.setAdapter(adapter);

    }

}
