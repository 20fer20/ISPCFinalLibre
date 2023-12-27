package com.fj.notas2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5,et6;
    private Cursor fila;
    ListView listaView;

    BDHelper admin;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1= (EditText) findViewById(R.id.edtcodigo);
        et2= (EditText) findViewById(R.id.edtcurso);
        et3= (EditText) findViewById(R.id.edtnota1);
        et4= (EditText) findViewById(R.id.edtnota2);
        et5= (EditText) findViewById(R.id.edtnota3);
        et6= (EditText) findViewById(R.id.edtpromedio);
        listaView = (ListView) findViewById(R.id.lvtablanotas);

        et6.setEnabled(false);

        admin = new BDHelper(this, "instituto", null, 1);

        ArrayList array_list = admin.getAllRegistros();

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, array_list);

        listaView.setAdapter(arrayAdapter);
    }


    public void Alta(View view) {

        admin = new BDHelper(this, "instituto",
                null, 1);

        db = admin.getWritableDatabase();

        String codigo = et1.getText().toString();
        String curso = et2.getText().toString();
        String nota1 = et3.getText().toString();
        String nota2 = et4.getText().toString();
        String nota3 = et5.getText().toString();

        int promedioope=0;

        promedioope=Integer.parseInt(et3.getText().toString());
        promedioope=promedioope+Integer.parseInt(et4.getText().toString());
        promedioope=promedioope+Integer.parseInt(et5.getText().toString());

        promedioope=promedioope/3;

        String promedio=String.valueOf(promedioope);

        ContentValues registro = new ContentValues();
        registro.put("codigo", codigo);
        registro.put("curso", curso);
        registro.put("nota1", nota1);
        registro.put("nota2", nota2);
        registro.put("nota3", nota3);
        registro.put("promedio", promedio);

        db.insert("registro", null, registro);

        db.close();

        ArrayList array_list = admin.getAllRegistros();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, array_list);
        listaView.setAdapter(arrayAdapter);

        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");

        Toast.makeText(this, "se cargaron los datos ", Toast.LENGTH_SHORT).show();
    }


    public void consulta(View v) {

        admin = new BDHelper(this, "instituto", null, 1);

        db = admin.getWritableDatabase();

        String codigo = et1.getText().toString();

        fila = db.rawQuery("select curso,nota1,nota2,nota3, promedio  from registro where codigo=" + codigo, null);


        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
            et5.setText(fila.getString(3));
            et6.setText(fila.getString(4));
        }
        else

            Toast.makeText(this, "no existe un registro con dicho codigo",
                    Toast.LENGTH_SHORT).show();

        db.close();
    }

    public void baja(View v) {

        admin = new BDHelper(this, "instituto", null, 1);

        db = admin.getWritableDatabase();

        String codigo = et1.getText().toString();

        int cant = db.delete("registro", "codigo=" + codigo, null);

        db.close();

        ArrayList array_list = admin.getAllRegistros();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, array_list);

        listaView.setAdapter(arrayAdapter);

        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");

        if (cant == 1)

            Toast.makeText(this, "se borró el registro con dicho documento",
                    Toast.LENGTH_SHORT).show();
        else

            Toast.makeText(this, "no existe un registro con dicho documento",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {

        admin = new BDHelper(this, "instituto", null, 1);

        db = admin.getWritableDatabase();

        String codigo = et1.getText().toString();
        String curso = et2.getText().toString();
        String nota1 = et3.getText().toString();
        String nota2 = et4.getText().toString();
        String nota3 = et5.getText().toString();

        int promedioope=0;
        promedioope=Integer.parseInt(et3.getText().toString());
        promedioope=promedioope+Integer.parseInt(et4.getText().toString());
        promedioope=promedioope+Integer.parseInt(et5.getText().toString());
        promedioope=promedioope/3;

        String promedio=String.valueOf(promedioope);
        ContentValues registro = new ContentValues();
        registro.put("codigo", codigo);
        registro.put("curso", curso);
        registro.put("nota1", nota1);
        registro.put("nota2", nota2);
        registro.put("nota3", nota3);
        registro.put("promedio", promedio);

        int cant = db.update("registro", registro, "codigo=" + codigo, null);

        db.close();

        ArrayList array_list = admin.getAllRegistros();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, array_list);

        listaView.setAdapter(arrayAdapter);

        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");

        if (cant == 1)

            Toast.makeText(this,"se modificaron los datos",Toast.LENGTH_SHORT)
                    .show();
        else

            Toast.makeText(this,"no existe un registro con dicho documento",
                    Toast.LENGTH_SHORT).show();
    }
}