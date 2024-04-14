package com.lekkachara.controller;

import com.lekkachara.entities.Brand;
import com.lekkachara.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/allBrands")
    public ResponseEntity<List<Brand>> getAllBrands(){
        List<Brand> brands = brandService.getAllBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @PostMapping("/saveBrand")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand){
        Brand createBrand = brandService.createBrand(brand);
        return new ResponseEntity<>(createBrand, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("id") Long id){
        Optional<Brand> brand = brandService.getBrandById(id);
        return brand.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable("id") Long id, @RequestBody Brand brand){
        Optional<Brand> brandById = brandService.getBrandById(id);
        if (brandById.isPresent()){
            Brand updatedBrand = brandService.updateBrand(id, brand);
            return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Brand with Id "+id+ " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable("id") Long id){
        Optional<Brand> brandOptional=brandService.getBrandById(id);
        if (brandOptional.isPresent()){
            brandService.deleteBrand(id);
            return new ResponseEntity<>("Brand with Id "+id+ " deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Brand with Id "+id+ " not found", HttpStatus.NOT_FOUND);
        }
    }
}
