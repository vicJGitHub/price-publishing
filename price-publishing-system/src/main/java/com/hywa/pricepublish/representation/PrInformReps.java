package com.hywa.pricepublish.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrInformReps {

    private Long total;

    private List<PrInformRep> list;
}
