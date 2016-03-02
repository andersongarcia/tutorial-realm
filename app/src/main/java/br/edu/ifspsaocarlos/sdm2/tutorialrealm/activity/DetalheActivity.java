package br.edu.ifspsaocarlos.sdm2.tutorialrealm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm2.tutorialrealm.R;
import br.edu.ifspsaocarlos.sdm2.tutorialrealm.data.ClienteDAO;
import br.edu.ifspsaocarlos.sdm2.tutorialrealm.model.Cliente;

public class DetalheActivity extends AppCompatActivity {
    private Cliente cli;
    private ClienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("contact"))
        {

            this.cli = (Cliente) getIntent().getSerializableExtra("contact");
            EditText nameText = (EditText)findViewById(R.id.editTextNome);
            nameText.setText(cli.getNome());

            EditText foneText = (EditText)findViewById(R.id.editTextTelefone);
            foneText.setText(cli.getTelefone());

            int pos = cli.getNome().indexOf(" ");
            if (pos==-1)
                pos= cli.getNome().length();

            setTitle(cli.getNome().substring(0, pos));
        }

        dao = new ClienteDAO(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        if (!getIntent().hasExtra("contact"))
        {
            MenuItem item = menu.findItem(R.id.delContato);
            item.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvarContato:
                salvar();
                return true;
            /*case R.id.delContato:
                dao.deleteContact(cli);
                Toast.makeText(getApplicationContext(), R.string.msg_removido, Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);
                finish();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void salvar()
    {
        String name = ((EditText) findViewById(R.id.editTextNome)).getText().toString();
        String telefone = ((EditText) findViewById(R.id.editTextTelefone)).getText().toString();

        if (cli == null)
        {
            cli = new Cliente();
            cli.setNome(name);
            cli.setTelefone(telefone);

            dao.criar(cli);
            Toast.makeText(this, R.string.msg_inserido, Toast.LENGTH_SHORT).show();
        }
        else
        {
            cli.setNome(name);
            cli.setTelefone(telefone);

            //dao.atualizar(cli);
            Toast.makeText(this, R.string.msg_alterado, Toast.LENGTH_SHORT).show();
        }

        Intent resultIntent = new Intent();
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}