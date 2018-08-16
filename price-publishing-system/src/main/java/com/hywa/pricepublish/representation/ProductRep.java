package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.Product;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRep {

    private String productId;
    @NotNull(message = "商品名称不能为空")
    private String productName;
    @NotNull(message = "请绑定单位")
    private String unit;
    private BigDecimal price;
    private String type;
    @NotNull(message = "请绑定类型")
    private String typeId;
    private String bigType;
    @NotNull(message = "请绑定详细类型")
    private String bigTypeId;
    private String specification;

    public ProductRep(Product product) {
        this.setProductId(product.getId());
        this.setProductName(product.getName());
        this.setUnit(product.getUnit());
        this.setType(product.getProductType());
        this.setBigType(product.getProductBigType());
        this.setSpecification(product.getSpecification());

    }
}
