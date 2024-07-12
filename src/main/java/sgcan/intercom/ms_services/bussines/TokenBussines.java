package sgcan.intercom.ms_services.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import sgcan.intercom.ms_services.model.Token;

@Component
public class TokenBussines {
    @Autowired
    public FitoBussines fitoBussines;

    public Mono<Token> getToken(){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "vuce-peru");
        formData.add("client_secret", "GwUiKQCxvMAQYHMDDrrhh7PTT2EY7ipz");
        formData.add("grant_type", "client_credentials");
        //https://azure-intercom.ddns.net:28443
        ///realms/intercom/protocol/openid-connect/token
        String URL="https://azure-intercom.ddns.net:28443";
        String ENDPOINT = "realms/intercom/protocol/openid-connect/token";
        WebClient webClient= WebClient.builder().baseUrl(URL).build();
        Mono<Token> response= webClient.post().uri(ENDPOINT).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData).retrieve().bodyToMono(Token.class);

        return response;
    }

    public Mono<Token> taskToken(){
        return this.getToken().map(x->{
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
    public Mono<String> getConsulta(){
       return taskToken().flatMap(x->fitoBussines.getFito(x.getAccess_token())
               .doOnNext(p->System.out.println(p)));
    }
}
