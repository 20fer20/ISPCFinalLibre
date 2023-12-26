package com.fj.notas2023;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar el usuario y la contraseña
                if (isValidCredentials()) {
                    // Si las credenciales son válidas, iniciar la actividad del menú
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad actual para que el usuario no pueda regresar al login con el botón "Atrás"
                } else {
                    // Si las credenciales no son válidas, puedes mostrar un mensaje de error
                    // o realizar alguna otra acción
                    // Por ejemplo:
                    // Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        // Verificar el usuario y la contraseña (en este caso, usuario=admin, contraseña=admin)
        return username.equals("admin") && password.equals("admin");
    }
}
