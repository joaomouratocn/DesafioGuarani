package br.com.devjmcn.desafioguarani.presenter.home.presentation;

import static br.com.devjmcn.desafioguarani.presenter.home.HomeContract.HomeViewContract;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.devjmcn.desafioguarani.R;
import br.com.devjmcn.desafioguarani.model.Repository;
import br.com.devjmcn.desafioguarani.model.models.Product;
import br.com.devjmcn.desafioguarani.presenter.home.HomeContract;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomePresenter implements HomeContract.HomePresenterContract {
    Disposable disposable;
    Repository repository;

    private HomeViewContract homeViewContract = null;


    @Inject
    public HomePresenter(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void attachView(HomeViewContract homeViewContract) {
        this.homeViewContract = homeViewContract;
    }

    @Override
    public void detachView() {
        homeViewContract = null;
    }

    @Override
    public void loadProductStatus() {
        disposable = repository.getProductStatus().subscribe(
                prodStatus -> {
                    homeViewContract.loadProdStatus(prodStatus);
                },
                throwable -> {
                    homeViewContract.showToast(throwable.getMessage());
                }
        );
    }

    @Override
    public void searchProduct(String search, String selectedStatus) {
        homeViewContract.showLoad(true);
        disposable = repository.getProductsByName(selectedStatus, search).subscribe(
                products -> {
                    homeViewContract.setProducts(products);
                },
                throwable -> {
                    homeViewContract.showToast(throwable.getMessage());
                },
                () -> {
                    homeViewContract.showLoad(false);
                }
        );
    }
}
