package br.com.api.beerstore.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class Beer {

    public static final String SEQ_BEERS = "seq_beers";

    @Id
    @SequenceGenerator(name = SEQ_BEERS, sequenceName = SEQ_BEERS, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_BEERS)
    private Long id;

    @NotBlank(message = "beers-1")
    private String name;

    @NotNull(message = "beers-2")
    private BeerType type;

    @NotNull(message = "beers-3")
    @DecimalMin(value = "0", message = "beers-4")
    private BigDecimal volume;

}
