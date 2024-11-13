package br.com.devjmcn.desafioguarani.presenter.home;

import java.util.List;

import br.com.devjmcn.desafioguarani.model.models.Product;

public interface HomeContract {
    public interface HomePresenterContract {
        void attachView(HomeViewContract homeViewContract);

        void detachView();

        void getProdStatus();
    }

    public interface HomeViewContract {
        void loadProdStatus(List<String> prodStatus);

        void showToast(String message);

        String getStatusSelected();

        void setProducts(List<Product> products);
    }
}