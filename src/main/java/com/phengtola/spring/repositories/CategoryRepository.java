package com.phengtola.spring.repositories;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.phengtola.spring.entities.Category;

/**
 * 
 * @author Tola Created Date: 2017/07/03
 *
 */
@Repository
public interface CategoryRepository {

	@Select("SELECT id, name, remark, status, created_date, index, uuid " +
			" FROM categories WHERE uuid = #{uuid}")
	@Results(value = {
				@Result(property = "createdDate", column = "created_date")
			})
	Category findByUUID(String uuid);
	
	@Select("SELECT id, name, remark, status, created_date, index, uuid " +
			" FROM categories WHERE status='1'")
	List<Category> findAll();


    @Update("UPDATE categories SET name=#{category.name}, remark=#{category.remark}, " +
            "status=#{category.status}, index=#{category.index} WHERE uuid=#{category.uuid}")
	boolean update(@Param("category") Category category);

    @Delete("DELETE FROM categories WHERE uuid=#{uuid}")
	boolean delete(@Param("uuid") String uuid);

	@Update("UPDATE categories SET status=#{status} WHERE uuid= #{uuid}")
	boolean updateStatusByUUID(@Param("status")String status, @Param("uuid")String uuid);
	

	@Insert("INSERT INTO categories (name, remark, status, uuid) " +
			"VALUES (#{category.name}, #{category.remark}, #{category.status}, #{category.uuid})")
	boolean save(@Param("category") Category category);
	
}
