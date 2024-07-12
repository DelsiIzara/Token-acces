package sgcan.intercom.ms_services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sgcan.intercom.ms_services.business.FitoBusiness;
import sgcan.intercom.ms_services.business.TokenBusiness;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @Autowired
    public FitoBusiness fitoBusiness;
    @Autowired
    private TokenBusiness business;

    @GetMapping("/random")
    public Mono<String> getRandom(@RequestParam String token){
       return fitoBusiness.getFito(token);
    }

    @GetMapping("/status")
    public Mono<String> getFit(){
        return business.token()
                .flatMap(x->fitoBusiness.getFito(x.getAccess_token()));
    }
}
