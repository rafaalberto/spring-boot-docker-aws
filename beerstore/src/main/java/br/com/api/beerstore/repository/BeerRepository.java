package br.com.api.beerstore.repository;

import br.com.api.beerstore.model.Beer;
import br.com.api.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);
}
