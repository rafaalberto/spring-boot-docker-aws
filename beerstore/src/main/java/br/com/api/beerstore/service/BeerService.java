package br.com.api.beerstore.service;

import br.com.api.beerstore.exception.BeerAlreadyExistException;
import br.com.api.beerstore.exception.EntityNotFoundException;
import br.com.api.beerstore.model.Beer;
import br.com.api.beerstore.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    private BeerRepository beerRepository;

    public BeerService(@Autowired BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Beer save(final Beer beer) {
        verifyIfBeerExist(beer);
        return beerRepository.save(beer);
    }

    public void delete(Long id){
        Optional<Beer> beerDB = beerRepository.findById(id);
        if(!beerDB.isPresent()){
            throw new EntityNotFoundException();
        }
        beerRepository.delete(beerDB.get());
    }

    private void verifyIfBeerExist(final Beer beer) {
        Optional<Beer> beerDB = beerRepository.findByNameAndType(beer.getName(), beer.getType());
        if (beerDB.isPresent()
                && (beer.isNew() || isUpdatingToADifferentBeer(beer, beerDB))) {
            throw new BeerAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerDB) {
        return beer.alreadExist() && !beerDB.get().equals(beer);
    }

}
