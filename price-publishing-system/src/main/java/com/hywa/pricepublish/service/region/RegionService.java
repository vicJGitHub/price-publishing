package com.hywa.pricepublish.service.region;

import com.hywa.pricepublish.representation.CityReps;
import com.hywa.pricepublish.representation.CountyReps;
import com.hywa.pricepublish.representation.ProvinceReps;

public interface RegionService {

    ProvinceReps findProvince(int rows);

    CityReps findCity(String id, int rows);

    CountyReps findCounty(String id, int rows);
}
