package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.service.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    @Autowired
    private BeerService beerService;

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping({"", "/"})
    public List<Beer> listBeers(){
        log.debug("List Beers - in controller");
        return beerService.listBeers();
    }

    //@RequestMapping(method = RequestMethod.GET, value = "/{beerId}")
    @GetMapping("/{beerId}")
    public Beer getBeerById(@PathVariable("beerId") String beerId){
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(UUID.fromString(beerId));
    }

}
