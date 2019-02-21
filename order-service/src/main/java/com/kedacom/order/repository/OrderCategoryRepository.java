package com.kedacom.order.repository;

import com.kedacom.order.model.OrderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * com.kedacom.order.repository
 * 2018-01-03-13:47
 * 2018
 *  on 2018-01-03.
 */
@Repository
public interface OrderCategoryRepository extends JpaRepository<OrderCategory,Long> {

}
