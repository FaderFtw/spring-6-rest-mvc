package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.service.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    @Autowired
    private BeerService beerService;


    @PatchMapping("{beerId}")
    public ResponseEntity<?> updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){
        log.debug("Patch Beer - in Controller");
        beerService.patchBeerById(beerId, beer);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{beerId}")
    public ResponseEntity<?> updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){
        log.debug("Update Beer by Id - in Controller");
        beerService.updateBeerById(beerId, beer);
        return ResponseEntity.noContent().build();

        // return ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@RequestBody Beer beer){
        log.debug("Save New Beer - in Controller");
        Beer savedBeer = beerService.saveNewBeer(beer);
        return ResponseEntity.created(URI.create("/api/v1/beer/" + savedBeer.getId().toString())).build();

        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
        // return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping({"", "/"})
    public List<Beer> listBeers(){
        log.debug("List Beers - in controller");
        return beerService.listBeers();
    }

    //@RequestMapping(method = RequestMethod.GET, value = "/{beerId}")
    @GetMapping("/{beerId}")
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(beerId);
    }

}
