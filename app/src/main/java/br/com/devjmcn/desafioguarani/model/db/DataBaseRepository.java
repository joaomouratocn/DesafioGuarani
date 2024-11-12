package br.com.devjmcn.desafioguarani.model.db;

import javax.inject.Inject;

import br.com.devjmcn.desafioguarani.model.Repository;

public class DataBaseRepository implements Repository {

    private final DataBaseHelper dataBaseHelper;

    @Inject
    public DataBaseRepository(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }


}
