package com.wbkjcom.order.repository;

import com.wbkjcom.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * com.wbkjcom.order.repository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
