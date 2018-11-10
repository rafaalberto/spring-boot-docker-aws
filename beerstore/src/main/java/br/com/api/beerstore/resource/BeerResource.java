package br.com.api.beerstore.resource;

import br.com.api.beerstore.model.Beer;
import br.com.api.beerstore.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired
    private BeerRepository beerRepository;

    @GetMapping
    public List<Beer> findAll(){
        return beerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer) {
        int result = 5 / 0;
        return beerRepository.save(beer);
    }

}
