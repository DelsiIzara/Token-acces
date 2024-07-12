package sgcan.intercom.ms_services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FitoBusiness {
    @Autowired
    private WebClient webClient;

    public Mono<String> getFito(String token){
        String url="https://azure-intercom.ddns.net:8443/DAV/DAV/documento/consultaestado";
        return webClient.get()
                .uri(url)
                .headers(h->h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class);

    }

}
