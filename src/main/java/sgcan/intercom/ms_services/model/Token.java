package sgcan.intercom.ms_services.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String access_token;
    private String expires_in;
    private String refresh_expires_in;
    private String token_type;
    private String not_before_policy;
    private String scope;
}
