package br.com.devjmcn.desafioguarani.presenter.home;

import java.util.List;

import br.com.devjmcn.desafioguarani.model.models.Product;

public interface HomeContract {
    interface HomePresenterContract {
        void attachView(HomeViewContract homeViewContract);

        void detachView();

        void loadProductStatus();

        void searchProduct(String search, String selectedStatus);
    }

    interface HomeViewContract {

        void showLoad(Boolean show);

        void loadProdStatus(List<String> prodStatus);

        void showToast(String message);

        String getStatusSelected();

        void setProducts(List<Product> products);
    }
}