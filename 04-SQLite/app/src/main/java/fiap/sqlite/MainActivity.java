package fiap.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fiap.sqlite.dao.ProdutoDAO;
import fiap.sqlite.model.Produto;

public class MainActivity extends AppCompatActivity {

    private EditText editNome, editValor, editDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editDescricao = (EditText) findViewById(R.id.edit_descricao);
        editNome = (EditText) findViewById(R.id.edit_nome);
        editValor = (EditText) findViewById(R.id.edit_valor);
    }

    public void cadastrar(View view){
        Produto produto = new Produto();
        produto.setDescricao(editDescricao.getText().toString());
        produto.setNome(editNome.getText().toString());
        produto.setValor(Double.parseDouble(
                editValor.getText().toString()));

        ProdutoDAO dao = new ProdutoDAO(this);
        dao.insert(produto);

        Toast.makeText(this,"Cadastrado!",Toast.LENGTH_SHORT).show();
    }
}
