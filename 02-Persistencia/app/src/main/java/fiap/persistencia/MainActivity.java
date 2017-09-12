package fiap.persistencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editUsuario;
    private EditText editSenha;
    private CheckBox checkConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsuario = (EditText) findViewById(R.id.edt_usuario);
        editSenha = (EditText) findViewById(R.id.edt_senha);
        checkConectado = (CheckBox) findViewById(R.id.chk_conectado);

        //Mostrar as preferencias do usuário...
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        boolean conectado = sp.getBoolean("conectado",false);
        if (conectado) {
            String usuario = sp.getString("user", "");
            String senha = sp.getString("password", "");
            editUsuario.setText(usuario);
            editSenha.setText(senha);
            checkConectado.setChecked(true);
        }
    }

    //Gravar o nome do usuario no SharedPreferences
    public void gravar(View view){
        String usuario = editUsuario.getText().toString();
        String senha = editSenha.getText().toString();
        boolean conectado = checkConectado.isChecked();

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (conectado) {
            editor.putString("user", usuario);
            editor.putString("password",senha);
            editor.putBoolean("conectado",conectado);
        }else{
            editor.remove("user");
            editor.remove("password");
            editor.remove("conectado");
        }
        editor.commit();

        //Navegar para a tela de arquivo
        Intent intent = new Intent(this,ArquivoActivity.class);
        startActivity(intent);

    }
}
