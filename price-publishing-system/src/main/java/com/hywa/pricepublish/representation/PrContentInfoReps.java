package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.PrContentInfo;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrContentInfoReps {
   private Long total;
   private List<PrContentInfo> list;

}
