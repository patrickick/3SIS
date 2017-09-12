package fiap.persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ArquivoActivity extends AppCompatActivity {

    private EditText edtFrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arquivo);

        edtFrase = (EditText) findViewById(R.id.edt_frase);

        //Exibir os dados arquivo
        try {
            FileInputStream stream = openFileInput("arquivo.txt");
            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(stream));
            String linha = reader.readLine();
            Toast.makeText(this,linha,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //grava a frase em um arquivo
    public void gravar(View view){
        String frase = edtFrase.getText().toString();
        try {
            FileOutputStream stream = openFileOutput("arquivo.txt",MODE_PRIVATE);
            stream.write(frase.getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
