package br.com.api.beerstore.service;

import br.com.api.beerstore.model.Beer;

import java.util.List;

public interface BeerService {

    List<Beer> findAll();

    Beer save(final Beer beer);

    void delete(Long id);

}
