package br.com.devjmcn.desafioguarani.presenter.clients.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayoutMediator;

import br.com.devjmcn.desafioguarani.R;
import br.com.devjmcn.desafioguarani.databinding.ActivityClientBinding;
import br.com.devjmcn.desafioguarani.presenter.home.presentation.HomeActivity;

public class ClientsActivity extends AppCompatActivity {
    private ActivityClientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityClientBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initConfig();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initConfig() {
        binding = ActivityClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.tblClients);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_line);
            getSupportActionBar().setTitle(R.string.str_register_client);
        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        binding.vpClients.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(binding.tbClients, binding.vpClients,
            (tab, position) -> {
                switch (position) {
                    case 0:
                        tab.setText(getString(R.string.str_cnpj));
                        break;
                    case 1:
                        tab.setText(getString(R.string.str_cpf));
                        break;
                }
            }
        ).attach();

        binding.naviClientRegister.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_home) {
                Intent intent = new Intent(ClientsActivity.this, HomeActivity.class);
                startActivity(intent);
            } else if (menuItem.getItemId() == R.id.nav_clients_consult) {
                Intent intent = new Intent(ClientsActivity.this, ConsultClientsActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ClientsActivity.this, ConsultClientsActivity.class);
                startActivity(intent);
            }
            binding.drawerLayout.closeDrawers();
            return true;
        });
    }
}