package fiap.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fiap.sqlite.model.Produto;

/**
 * Created by logonpf on 26/09/2017.
 */

public class ProdutoDAO extends SQLiteOpenHelper {

    private static final String BANCO = "loja";
    private static final int VERSAO  = 1;

    public ProdutoDAO(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TB_PRODUTO (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, VALOR REAL, DESCRICAO TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS TB_PRODUTO";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Produto produto){
        ContentValues valores = getContentValues(produto);
        getWritableDatabase().insert("TB_PRODUTO",null,valores);
    }

    @NonNull
    private ContentValues getContentValues(Produto produto) {
        ContentValues valores = new ContentValues();
        valores.put("NOME",produto.getNome());
        valores.put("VALOR",produto.getValor());
        valores.put("DESCRICAO",produto.getDescricao());
        return valores;
    }

    public void update(Produto produto){
        ContentValues valores = getContentValues(produto);
        getWritableDatabase().update("TB_PRODUTO", valores,
                "CODIGO = ?",new String[]{
                        String.valueOf(produto.getCodigo())});
    }

    public void delete(int codigo){
        getWritableDatabase().delete("TB_PRODUTO",
                "CODIGO = ?", new String[]{String.valueOf(codigo)});
    }

    public List<Produto> list(){
        Cursor cursor = getReadableDatabase()
                .query("TB_PRODUTO",null,null,null,null,null,null);
        List<Produto> produtos = new ArrayList<>();
        while (cursor.moveToNext()){
            int codigo = cursor.getInt(0);
            String nome = cursor.getString(1);
            double valor = cursor.getDouble(2);
            String desc = cursor.getString(3);

            Produto produto = new Produto(codigo,nome, valor, desc);
            produtos.add(produto);
        }
        return produtos;
    }


}
