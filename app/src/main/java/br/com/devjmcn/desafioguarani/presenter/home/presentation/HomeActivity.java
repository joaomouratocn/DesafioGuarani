package br.com.devjmcn.desafioguarani.presenter.home.presentation;

import static br.com.devjmcn.desafioguarani.presenter.home.HomeContract.*;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import javax.inject.Inject;

import br.com.devjmcn.desafioguarani.R;
import br.com.devjmcn.desafioguarani.databinding.ActivityClientsBinding;
import br.com.devjmcn.desafioguarani.presenter.home.HomeContract;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity implements HomeViewContract {
    private ActivityClientsBinding binding;

    @Inject
    HomePresenterContract homePresenterContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityClientsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        homePresenterContract.attachView(this);

        initConfig();
    }

    private void initConfig() {
        homePresenterContract.getStatus();
    }

    @Override
    protected void onDestroy() {
        homePresenterContract.detachView();
        super.onDestroy();
    }

    @Override
    public void loadProdStatus(List<String> prodStatus) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                prodStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.spnStatus.setAdapter(adapter);
    }
}