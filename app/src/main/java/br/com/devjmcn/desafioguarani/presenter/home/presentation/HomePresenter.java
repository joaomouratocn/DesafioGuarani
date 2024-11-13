package br.com.devjmcn.desafioguarani.presenter.home.presentation;

import static br.com.devjmcn.desafioguarani.presenter.home.HomeContract.HomeViewContract;

import javax.inject.Inject;

import br.com.devjmcn.desafioguarani.model.Repository;
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
    public void getProdStatus() {
        disposable = repository.getProductStatus().subscribe(
                prodStatus -> {
                    homeViewContract.loadProdStatus(prodStatus);
                },
                throwable -> {
                    homeViewContract.showToast(throwable.getMessage());
                },
                () -> {
                    String selectedStatus = homeViewContract.getStatusSelected();
                    disposable = repository.getProductsByName(selectedStatus, "").subscribe(
                        products -> {
                            homeViewContract.setProducts(products);
                        },
                        throwable -> {
                            homeViewContract.showToast(throwable.getMessage());
                        }
                    );
                }
        );
    }
}
