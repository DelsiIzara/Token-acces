package sgcan.intercom.ms_services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    private Integer id;

    private String  uid;

    private String  brand;

    private String  name;

    private String  style;

    private String  hop;

    private String  yeast;

    private String  malts;

    private String  ibu;

    private String  alcohol;

    private String  blg;
}
