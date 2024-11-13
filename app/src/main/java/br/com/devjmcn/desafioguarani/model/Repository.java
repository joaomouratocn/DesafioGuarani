package br.com.devjmcn.desafioguarani.model;

import java.util.List;

import br.com.devjmcn.desafioguarani.model.models.Clients;
import io.reactivex.rxjava3.core.Observable;


public interface Repository {

    Observable<List<String>> getProductStatus();

    Observable<List<Clients>> getAllClients(String status);
}
