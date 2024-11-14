package br.com.devjmcn.desafioguarani.di;

import br.com.devjmcn.desafioguarani.model.Repository;
import br.com.devjmcn.desafioguarani.model.db.dao;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {
    @Binds
    public abstract Repository provideRepository(dao dao);
}
