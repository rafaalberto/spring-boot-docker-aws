package br.com.api.beerstore.repository;

import br.com.api.beerstore.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}
