package com.pirai.CriteriaBuilderr.Service;

import com.pirai.CriteriaBuilderr.Entity.CategoryDto;
import com.pirai.CriteriaBuilderr.Entity.Product;
import com.pirai.CriteriaBuilderr.Entity.ProductDto;
import com.pirai.CriteriaBuilderr.Repository.ProductRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ProductRepo pr;

    public List<Product> findAll(){
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> rq= cq.from(Product.class);
        cq.select(rq); //SELECT * FROM Product (Printing for all the products in database)

        //git purpose
        System.out.println("git test");
        System.out.println("hello good morning");
        return em.createQuery(cq).getResultList();
    }
    public List<Product> findFilterAll(){
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> rq= cq.from(Product.class);
        List<Predicate> pre=new ArrayList<>();
        pre.add(cb.ge(rq.get("price"),300));
        pre.add(cb.equal(rq.get("category"),"Bikes"));
        pre.add(cb.like(cb.lower(rq.get("name")),"%p%"));
        cq.where(cb.and(pre.toArray(new Predicate[0])));

        //git purpose
        System.out.println("Predevelop branch");

        return em.createQuery(cq).getResultList();
    }
    public List<Product> filterAndCondition(){
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> rq= cq.from(Product.class);
        Predicate pricepredi=cb.gt(rq.get("price"),1000);
        Predicate categorypredi=cb.equal(rq.get("category"),"Mobile");
        cq.where(cb.and(pricepredi,categorypredi));
        return em.createQuery(cq).getResultList();
    }
    public List<Product> filterOrCondition(){
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> rq= cq.from(Product.class);
        Predicate pricepredi=cb.lt(rq.get("price"),1500);
        Predicate categorypredi=cb.equal(rq.get("category"),"Accessories");
        cq.where(cb.or(pricepredi,categorypredi));
        return em.createQuery(cq).getResultList();
    }
    public List<Product> dynamicFilter(String name,String category,Double minprice){
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> rq= cq.from(Product.class);
        List<Predicate> pl=new ArrayList<>();
        if(name!=null && !name.isEmpty()){
            pl.add(cb.like(rq.get("name"),name));
        }
        if(category != null && !category.isEmpty() ){
        pl.add(cb.equal(rq.get("category"),category));
        }
        if(minprice!=null){
            pl.add(cb.gt(rq.get("price"),minprice));
        }

        //Git purpose

        System.out.println(category);
        System.out.println(name);
        System.out.println(minprice);
        System.out.println("git status");


        cq.where(cb.and(pl.toArray(new Predicate[0])));
        return em.createQuery(cq).getResultList();
    }
    public List<Product> pageiNation(String category,int page,int size,boolean Asc){
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
        Root<Product> rq= cq.from(Product.class);
        List<Predicate> li=new ArrayList<>();
        if(category!=null && !category.isEmpty()){
            li.add(cb.equal(rq.get("category"),category));
        }
        cq.where(cb.and(li.toArray(new Predicate[0])));
        if(Asc){
            cq.orderBy(cb.asc(rq.get("price")));
        }else cq.orderBy(cb.desc(rq.get("price")));
        TypedQuery<Product> tp= em.createQuery(cq);
        tp.setFirstResult(page*size);
        tp.setMaxResults(size);

        //git purpose
        System.out.println("hello");
        System.out.println("ok");


        return tp.getResultList();

    }
    public List<ProductDto> constructor(){
//        CriteriaBuilder cb=em.getCriteriaBuilder();
//        CriteriaQuery<Product> cq=cb.createQuery(Product.class);
//        Root<Product> rq=cq.from(Product.class);
//        cq.select(cb.construct(Product.class,rq.get("name"),rq.get("price")));
//        TypedQuery<Product> tp= em.createQuery(cq);
//        return tp.getResultList(); //It will print all give output like this category,name,id & price ("category":"Null","id":"Null","name": "RDR2","price": 1500.2)

        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<ProductDto> cq=cb.createQuery(ProductDto.class);
        Root<Product> rq=cq.from(Product.class);
        cq.select(cb.construct(ProductDto.class,rq.get("name"),rq.get("price")));
        TypedQuery<ProductDto> tp= em.createQuery(cq);
        return tp.getResultList();// It will give output like this only name & price ("name": "RDR2","price": 1500.2)
    }
    public List<CategoryDto> findCategoryy(List<String> categories){
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<CategoryDto> cq=cb.createQuery(CategoryDto.class);
        Root<Product> rq= cq.from(Product.class);
        Expression<Long> countt=cb.count(rq);
        Expression<Double> avgg=cb.avg(rq.get("price"));
        cq.select(cb.construct(CategoryDto.class,rq.get("category"),avgg,countt));
        if(categories!=null && !categories.isEmpty()){
            cq.where(rq.get("category").in(categories));
        }
        System.out.println("hello");
        System.out.println("ok");
        cq.groupBy(rq.get("category"));
        return em.createQuery(cq).getResultList();
    }
}
