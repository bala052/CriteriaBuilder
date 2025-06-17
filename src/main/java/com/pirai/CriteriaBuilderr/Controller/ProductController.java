package com.pirai.CriteriaBuilderr.Controller;

import com.pirai.CriteriaBuilderr.Entity.CategoryDto;
import com.pirai.CriteriaBuilderr.Entity.Product;
import com.pirai.CriteriaBuilderr.Entity.ProductDto;
import com.pirai.CriteriaBuilderr.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/api")
@RestController
public class ProductController
{
    @Autowired
    private ProductService ps;
    @GetMapping("/Product")
    public List<Product> getAll(){
        return ps.findAll();
    }
    @GetMapping("/Filterproduct")
    public List<Product> getFilterAll(){
        return ps.findFilterAll();
    }
    @GetMapping("/Filterand")
    public List<Product> getFilterAnd()
    {
        return ps.filterAndCondition();
    }
    @GetMapping("/Filteror")
    public List<Product> getFilterOr()
    {
        return ps.filterOrCondition();
    }
    @GetMapping("/Dynamicfilter")
    public List<Product> getDynamic(@RequestParam(required = false)String name,
                                    @RequestParam(required = false)String category,
                                    @RequestParam(required = false)Double minprice){
        return ps.dynamicFilter(name,category,minprice);
    }
    @GetMapping("/PaginationSorting")
    public List<Product> getPagiNation(@RequestParam(required = false)String category,
                                       @RequestParam(defaultValue = "0")int page,
                                       @RequestParam(defaultValue = "5")int size,
                                       @RequestParam(defaultValue = "true")boolean Asc){
        return ps.pageiNation(category,page,size,Asc);
    }
    @GetMapping("/Construct")
    public List<ProductDto> getConstruct(){
        return ps.constructor();
    }
    @GetMapping("/Findcategory")
    public List<CategoryDto> getCategory(@RequestParam(required = false) List<String> categories){
        return ps.findCategoryy(categories);
    }

}
