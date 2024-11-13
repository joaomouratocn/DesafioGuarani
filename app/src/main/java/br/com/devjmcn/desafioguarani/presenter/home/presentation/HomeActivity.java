package br.com.devjmcn.desafioguarani.presenter.home.presentation;

import static br.com.devjmcn.desafioguarani.presenter.home.HomeContract.HomePresenterContract;
import static br.com.devjmcn.desafioguarani.presenter.home.HomeContract.HomeViewContract;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.devjmcn.desafioguarani.R;
import br.com.devjmcn.desafioguarani.databinding.ActivityHomeBinding;
import br.com.devjmcn.desafioguarani.databinding.DialogPriceBinding;
import br.com.devjmcn.desafioguarani.model.models.Product;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity implements HomeViewContract {
    private ActivityHomeBinding binding;
    private HomeAdapter adapter;

    @Inject
    HomePresenterContract homePresenterContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
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
        adapter = new HomeAdapter(product -> {
            showPriceDialog(product.getPrices());
        });

        binding.rcvClients.setAdapter(adapter);
        binding.rcvClients.setLayoutManager(new LinearLayoutManager(this));
        homePresenterContract.attachView(this);
        homePresenterContract.getProdStatus();
    }

    @Override
    protected void onDestroy() {
        homePresenterContract.detachView();
        super.onDestroy();
    }

    @Override
    public void loadProdStatus(List<String> prodStatus) {
        List<String> listRes = getStringRes(prodStatus);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                listRes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.spnStatus.setAdapter(adapter);
    }

    @NonNull
    private List<String> getStringRes(List<String> prodStatus) {
        List<String> listRes = new ArrayList<>();

        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("E", getString(R.string.str_stock));
        statusMap.put("L", getString(R.string.str_release));
        statusMap.put("N", getString(R.string.str_normal));
        statusMap.put("P", getString(R.string.str_promotion));

        for (String status : prodStatus) {
            String result = statusMap.get(status);
            listRes.add(result);
        }
        return listRes;
    }

    private String getStringBd(String prodStatus) {

        Map<String, String> statusMap = new HashMap<>();
        statusMap.put(getString(R.string.str_stock), "E");
        statusMap.put(getString(R.string.str_release), "L");
        statusMap.put(getString(R.string.str_normal), "N");
        statusMap.put(getString(R.string.str_promotion), "P");

        return statusMap.get(prodStatus);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getStatusSelected() {
        String result = (String) binding.spnStatus.getSelectedItem();
        return getStringBd(result);
    }

    @Override
    public void setProducts(List<Product> products) {
        adapter.submitList(products);
    }

    private void showPriceDialog(List<String> prices) {
        DialogPriceBinding diaBinding = DialogPriceBinding.inflate(getLayoutInflater());

        Dialog dialog = new Dialog(this);
        dialog.setContentView(diaBinding.getRoot());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                prices
        );

        diaBinding.ltvPrices.setAdapter(adapter);

        diaBinding.btnClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}