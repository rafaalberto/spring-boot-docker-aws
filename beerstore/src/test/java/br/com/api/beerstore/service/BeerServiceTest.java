package br.com.api.beerstore.service;

import br.com.api.beerstore.exception.BeerAlreadyExistException;
import br.com.api.beerstore.exception.EntityNotFoundException;
import br.com.api.beerstore.model.Beer;
import br.com.api.beerstore.model.BeerType;
import br.com.api.beerstore.repository.BeerRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private BeerRepository beerRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        beerService = new BeerService(beerRepository);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists(){
        Beer beerInDB = new Beer();
        beerInDB.setId(10L);
        beerInDB.setName("Heineken");
        beerInDB.setType(BeerType.LAGER);
        beerInDB.setVolume(new BigDecimal("355"));

        when(beerRepository.findByNameAndType("Heineken", BeerType.LAGER))
                .thenReturn(Optional.of(beerInDB));

        Beer newBeer = new Beer();
        newBeer.setId(10L);
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        beerService.save(newBeer);
    }

    @Test
    public void should_create_new_beer(){
        Beer beer = new Beer();
        beer.setName("Heineken");
        beer.setType(BeerType.LAGER);
        beer.setVolume(new BigDecimal("355"));

        Beer beerInDB = new Beer();
        beerInDB.setId(10L);
        beerInDB.setName("Heineken");
        beerInDB.setType(BeerType.LAGER);

        when(beerRepository.save(beer)).thenReturn(beerInDB);
        Beer beerSaved = beerService.save(beer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }
}
