package br.com.devjmcn.desafioguarani.model;

import java.util.List;

import br.com.devjmcn.desafioguarani.model.models.Clients;
import br.com.devjmcn.desafioguarani.model.models.Product;
import io.reactivex.rxjava3.core.Observable;


public interface Repository {

    Observable<List<String>> getProductStatus();

    Observable<List<Clients>> getAllClients(String status);

    Observable<List<Product>> getProductsByName(String selected, String name);
}
