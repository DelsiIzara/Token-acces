package sgcan.intercom.ms_services.bussines;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FitoBussines {

    public Mono<String> getFito(String token){
        String url="https://azure-intercom.ddns.net:8443/DAV/DAV/documento/consultaestado";
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri(url)
                .headers(h->h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class);

    }

}
