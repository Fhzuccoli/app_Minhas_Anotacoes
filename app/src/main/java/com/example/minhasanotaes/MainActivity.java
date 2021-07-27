package com.example.minhasanotaes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.minhasanotaes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText editAnotacao;
    private AnotacoesPreferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editAnotacao = findViewById(R.id.editAnotacao);

        preferencias = new AnotacoesPreferencias(getApplicationContext());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validar se algo foi digitado
                String textoRecuperado = editAnotacao.getText().toString();
                if (textoRecuperado.equals("")) {
                    Snackbar.make(view, "Preecha a anotação!", Snackbar.LENGTH_LONG)
                            .show();
                }else{
                    preferencias.salvarAnotacao(textoRecuperado);
                    Snackbar.make(view, "Anotação salva com sucesso!", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}