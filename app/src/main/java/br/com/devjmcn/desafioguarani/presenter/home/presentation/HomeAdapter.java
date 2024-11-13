package br.com.devjmcn.desafioguarani.presenter.home.presentation;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.devjmcn.desafioguarani.R;
import br.com.devjmcn.desafioguarani.databinding.LayoutItemProductBinding;
import br.com.devjmcn.desafioguarani.model.models.Product;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private final OnItemClick onItemClick;
    private List<Product> productList = new ArrayList<>();

    public HomeAdapter(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return HomeViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product, onItemClick);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void submitList(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        final Context context;
        final LayoutItemProductBinding binding;

        public HomeViewHolder(@NonNull LayoutItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.context = binding.getRoot().getContext();
        }

        public static HomeViewHolder inflate(ViewGroup parent) {
            LayoutItemProductBinding binding = LayoutItemProductBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new HomeViewHolder(binding);
        }

        public void bind(Product product, OnItemClick onItemClick) {
            binding.txvId.setText(context.getString(R.string.str_cod_value, product.getCod()));
            binding.txvName.setText(product.getDesc());

            if (!product.getPrices().isEmpty()){
                binding.txvPriceMin.setText(context.getString(R.string.str_price_min, product.getPrices().get(0)));
                int sizeList = product.getPrices().size();
                binding.txvPriceMax.setText(context.getString(R.string.str_price_max, product.getPrices().get(sizeList -1)));
            }

            binding.txvStock.setText(context.getString(R.string.str_stock_value, product.getStock().toString()));

            binding.getRoot().setOnClickListener(v -> {
                onItemClick.onItemClick(product);
            });
        }
    }

    interface OnItemClick{
        void onItemClick(Product product);
    }
}
