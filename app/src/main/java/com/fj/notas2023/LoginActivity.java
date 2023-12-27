package com.fj.notas2023;
// LoginActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificar las credenciales
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (username.equals("admin") && password.equals("1234")) {
                    // Credenciales correctas, iniciar activity_menu
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad actual para que no pueda volver atrás con el botón de retroceso
                } else {
                    // Credenciales incorrectas, muestra un mensaje de error o realiza alguna acción adicional
                    // (por ejemplo, mostrar un Toast)
                     Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
