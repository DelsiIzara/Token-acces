package sgcan.intercom.ms_services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sgcan.intercom.ms_services.model.Token;

@Component
public class TokenBusiness {
    @Autowired
    private  WebClient webClient;

    @Autowired
    public FitoBusiness fitoBusiness;

    public Mono<Token> postToken(){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "vuce-colombia");
        formData.add("client_secret", "Kl36jA67LNI3fTgjCtp8qElCYZEI3wzT");
        formData.add("grant_type", "client_credentials");
        String URL = "https://azure-intercom.ddns.net:28443/realms/intercom/protocol/openid-connect/token";

        return webClient.post()
                .uri(URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(Token.class);
    }
   // @Scheduled(fixedDelay = 5000)
    public Mono<Token> token(){
        return this.postToken().map(x->{
            Token a = new Token();
            a.setAccess_token(x.getAccess_token());
            a.setExpires_in(x.getExpires_in());
            a.setRefresh_expires_in(x.getRefresh_expires_in());
            a.setToken_type(x.getToken_type());
            a.setNot_before_policy(x.getNot_before_policy());
            a.setScope(x.getScope());
            System.out.println("Token : "+ a.getAccess_token());
            return a;
        });

    }

    @Scheduled(fixedDelay = 5000)
    public Mono<String> statusFito(){
        return token()
            .flatMap(x->fitoBusiness.getFito(x.getAccess_token()));
    }

}
