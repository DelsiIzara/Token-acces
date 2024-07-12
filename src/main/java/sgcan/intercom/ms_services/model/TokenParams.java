package sgcan.intercom.ms_services.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenParams {
    private String client_id;
    private String client_secret;
    private String grant_type;
}