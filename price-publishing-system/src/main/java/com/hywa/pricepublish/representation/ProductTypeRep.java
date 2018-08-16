package com.hywa.pricepublish.representation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductTypeRep {

    private String id;
    private String code;
    private String name;
    private String selectedUrl;
    private String unSelectedUrl;

    public ProductTypeRep(String id, String code, String name, String selectedUrl,
            String unSelectedUrl) {
        this.setId(id);
        this.setCode(code);
        this.setName(name);
        this.setSelectedUrl(selectedUrl);
        this.setUnSelectedUrl(unSelectedUrl);
    }
}
