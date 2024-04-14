package com.lekkachara.services.servicesImpl;

import com.lekkachara.entities.Brand;
import com.lekkachara.reposiories.BrandRepository;
import com.lekkachara.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long id, Brand brandDetails) {
        Optional<Brand> optionalBrand=brandRepository.findById(id);
        if (optionalBrand.isPresent()){
            Brand existingBrand = optionalBrand.get();
            existingBrand.setName(brandDetails.getName());
            return brandRepository.save(existingBrand);
        }else {
            return null;
        }
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
