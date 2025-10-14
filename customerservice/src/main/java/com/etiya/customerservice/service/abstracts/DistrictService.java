package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.service.requests.district.CreateDistrictRequest;
import com.etiya.customerservice.service.requests.district.UpdateDistrictRequest;
import com.etiya.customerservice.service.responses.district.CreatedDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetListDistrictResponse;
import com.etiya.customerservice.service.responses.district.UpdatedDistrictResponse;

import java.util.List;

public interface DistrictService {
    CreatedDistrictResponse add(CreateDistrictRequest request);
    List<GetListDistrictResponse> getAll();
    UpdatedDistrictResponse update(UpdateDistrictRequest request);
    GetDistrictResponse getById(int id);
    void deleteById(int id);
    List<GetListDistrictResponse> getByName(String name);
    List<GetListDistrictResponse> getByNameStartingWith(String name);
    List<GetListDistrictResponse> getByCityId(int cityId);
}