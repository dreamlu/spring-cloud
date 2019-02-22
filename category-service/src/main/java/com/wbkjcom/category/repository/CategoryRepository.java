package com.wbkjcom.category.repository;

import com.wbkjcom.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * com.wbkjcom.goods.repository
 * 2017-12-28-14:43
 * 2017
 *  on 2017-12-28.
 */

/**
 * @Author:suxiongwei
 * @Description:分类 仓库
 * @Date:14:44 2017-12-28
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);

    //    List<Category> findByParentId(Long parentId);
    //
    //    List<Category> findByLevel(Integer level);

    List<Category> findByLevelAndName(Integer level, String name);
}
