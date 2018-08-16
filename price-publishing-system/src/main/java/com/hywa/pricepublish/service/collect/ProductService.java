package com.hywa.pricepublish.service.collect;

import com.hywa.pricepublish.representation.ProductRep;
import com.hywa.pricepublish.representation.ProductReps;

public interface ProductService {

    ProductReps findByType(String typeId, String bigTypeId, String productName,
            Integer pageNum, Integer pageSize);

    void save(ProductRep productRep, String userId);

    ProductReps findRecentUse(String userId);

    void update(ProductRep productRep, String userId);

    void delete(String productId);
}
