package br.com.fiap.webservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONStringer;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CadastroActivity extends AppCompatActivity {

    private EditText editDescricao;
    private EditText editQuantidade;
    private EditText editPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editDescricao = (EditText) findViewById(R.id.edt_descricao);
        editQuantidade = (EditText) findViewById(R.id.edt_quantidade);
        editPreco = (EditText) findViewById(R.id.edt_preco);
    }
    //Método para o clique do botão
    public void cadastrar(View view){
        CadastroTask task = new CadastroTask();
        task.execute(editDescricao.getText().toString(),
                editQuantidade.getText().toString(),
                editPreco.getText().toString());
    }

    private class CadastroTask extends AsyncTask<String,Void,Integer>{

        @Override
        protected void onPostExecute(Integer integer) {
            //201 - HTTP code CREATED
            if (integer == 201){
                Toast.makeText(CadastroActivity.this,"Sucesso!",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(CadastroActivity.this,"Erro",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Integer doInBackground(String... params) {
            try {
                URL url = new URL("http://10.20.63.61:8080/MercadoFiap/rest/mercado/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json");

                //JSON que será enviado para o sevidor - API
                JSONStringer json = new JSONStringer();
                json.object();
                json.key("descricao").value(params[0]);
                json.key("quantidade").value(params[1]);
                json.key("preco").value(params[2]);
                json.endObject();

                //Envia para o servidor
                OutputStreamWriter stream = new OutputStreamWriter(connection.getOutputStream());
                stream.write(json.toString());
                stream.close();

                return connection.getResponseCode();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
