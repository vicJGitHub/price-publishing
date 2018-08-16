package com.hywa.pricepublish.representation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DictRep {

    private String id;
    private String code;
    private String name;
    private String dictTypeId;
    private String selectedUrl;
    private String unSelectedUrl;

    public DictRep(String id, String code, String name, String dictTypeId, String selectedUrl,
            String unSelectedUrl) {
        this.setId(id);
        this.setCode(code);
        this.setName(name);
        this.setSelectedUrl(selectedUrl);
        this.setUnSelectedUrl(unSelectedUrl);
        this.setDictTypeId(dictTypeId);
    }
}
