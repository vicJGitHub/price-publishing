package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.ArtificialCollection;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtificialCollectionRep {

    String id;
    String productName;
    BigDecimal price;
    String unit;
    String market;
    String region;
    Date createTime;
    Date collectionTime;
    String createUser;
    Date updateTime;
    String specification;
    String dataOgrion;
    String priceType;

    public ArtificialCollectionRep(ArtificialCollection artificialCollection) {
        this.setId(artificialCollection.getId());
        this.setProductName(artificialCollection.getName());
        this.setPrice(artificialCollection.getPrice());
        this.setUnit(artificialCollection.getUnit());
        this.setMarket(artificialCollection.getMarket());
        this.setRegion(artificialCollection.getRegion());
        this.setCollectionTime(artificialCollection.getCollctionTime());
        this.setCreateUser(artificialCollection.getCreateUser());
        this.setSpecification(artificialCollection.getSpecification());
        this.setPriceType(artificialCollection.getPriceType());
        this.setCreateTime(artificialCollection.getCreateTime());
        this.setUpdateTime(artificialCollection.getUpdateTime());
    }
}
