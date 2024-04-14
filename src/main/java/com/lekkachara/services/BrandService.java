package com.lekkachara.services;

import com.lekkachara.entities.Brand;
import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<Brand> getAllBrands();
    Optional<Brand> getBrandById(Long id);
    Brand createBrand(Brand brand);
    Brand updateBrand(Long id, Brand brandDetails);
    void deleteBrand(Long id);
}
