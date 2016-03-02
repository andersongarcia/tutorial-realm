package br.edu.ifspsaocarlos.sdm2.tutorialrealm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ifspsaocarlos.sdm2.tutorialrealm.R;
import br.edu.ifspsaocarlos.sdm2.tutorialrealm.model.Cliente;

public class ClienteArrayAdapter extends ArrayAdapter<Cliente> {
    private LayoutInflater inflater;

    public ClienteArrayAdapter(Activity activity, List<Cliente> objects) {
        super(activity, R.layout.cliente_celula, objects);

        this.inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cliente_celula, null);
            holder = new ViewHolder();
            holder.nome = (TextView) convertView.findViewById(R.id.nome);
            holder.telefone = (TextView) convertView.findViewById(R.id.fone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cliente c = getItem(position);
        holder.nome.setText(c.getNome());
        holder.telefone.setText(c.getTelefone());
        return convertView;
    }

    static class ViewHolder {
        public TextView nome;
        public TextView telefone;
    }}
