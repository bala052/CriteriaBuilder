package com.pirai.CriteriaBuilderr.Repository;

import com.pirai.CriteriaBuilderr.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>
{
}
