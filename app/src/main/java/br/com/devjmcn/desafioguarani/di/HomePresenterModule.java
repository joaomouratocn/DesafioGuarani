package br.com.devjmcn.desafioguarani.di;

import static br.com.devjmcn.desafioguarani.presenter.home.HomeContract.HomePresenterContract;

import br.com.devjmcn.desafioguarani.presenter.home.presentation.HomePresenter;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class HomePresenterModule {
    @Binds
    public abstract HomePresenterContract homePresenter(HomePresenter homePresenter);
}
