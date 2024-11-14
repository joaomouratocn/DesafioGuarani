package br.com.devjmcn.desafioguarani.presenter.clients.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.devjmcn.desafioguarani.R;
import br.com.devjmcn.desafioguarani.databinding.ActivityConsultClientsBinding;
import br.com.devjmcn.desafioguarani.presenter.home.presentation.HomeActivity;

public class ConsultClientsActivity extends AppCompatActivity {
    private ActivityConsultClientsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityConsultClientsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initConfig();
    }

    private void initConfig() {
        binding.naviConsult.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_home) {
                Intent intent = new Intent(ConsultClientsActivity.this, HomeActivity.class);
                startActivity(intent);
            } else if (menuItem.getItemId() == R.id.nav_clients) {
                Intent intent = new Intent(ConsultClientsActivity.this, ClientsActivity.class);
                startActivity(intent);
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });


        setSupportActionBar(binding.tlbConsult);
        binding.tlbConsult.setNavigationIcon(R.drawable.menu_line);
        binding.tlbConsult.setNavigationOnClickListener(v -> binding.drawerLayout.openDrawer(GravityCompat.START));
    }
}