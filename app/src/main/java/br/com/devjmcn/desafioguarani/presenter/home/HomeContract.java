package br.com.devjmcn.desafioguarani.presenter.home;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface HomeContract {
    public interface HomePresenterContract {
        void attachView(HomeViewContract homeViewContract);

        void detachView();

        void getStatus();
    }

    public interface HomeViewContract {
        void loadProdStatus(List<String> prodStatus);
    }
}