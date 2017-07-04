package com.phengtola.spring.controllers.rest;

import com.phengtola.spring.entities.Category;
import com.phengtola.spring.services.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Tola Created Date: 2017/07/03
 *
 */

@RestController
@RequestMapping("/v1/api/category")
public class CategoryRController {

    private HttpStatus httpStatus = HttpStatus.OK;
    private Map<String, Object> map = null;

    private CategoryService categoryService;

    @Autowired
    public CategoryRController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        List<Category> categories = null;
        map = new HashMap<String, Object>();
        try{
            categories = categoryService.findAll();
            if(!categories.isEmpty()) {
                map.put("data", categories);
                map.put("message", "Categoies have been found.");
                map.put("status", true);
                map.put("code", 200);
            } else {
                map.put("data", categories);
                map.put("message", "Categoies have not been found.");
                map.put("status", false);
                map.put("code", 404);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "Something is borken. Please contact to developer team!");
            map.put("status", false);
            map.put("code", 500);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(map, httpStatus);
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<Map<String, Object>> findByUUID(@PathVariable("uuid") String uuid) {
        map = new HashMap<String, Object>();
        try {
            Category category = categoryService.findByUUID(uuid);
            if(category != null) {
                map.put("data", category);
                map.put("message", "Category have been found.");
                map.put("status", true);
                map.put("code", 200);
            } else {
                map.put("data", category);
                map.put("message", "Category have not been found.");
                map.put("status", false);
                map.put("code", 404);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "Something is broken. Please contact to developer team!");
            map.put("status", false);
            map.put("code", 500);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(map, httpStatus);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody Category category) {
        map = new HashMap<String, Object>();
        try{
            if(categoryService.save(category)) {
                map.put("data", category);
                map.put("message", "Category have been inserted successfully.");
                map.put("status", true);
                map.put("code", 200);
            } else {
                map.put("data", category);
                map.put("message", "Category have been not inserted.");
                map.put("status", false);
                map.put("code", 404);
                httpStatus = HttpStatus.NOT_FOUND;
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "Something is broken. Please contact to developer team!");
            map.put("status", false);
            map.put("code", 500);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(map, httpStatus);
    }



    @PatchMapping("/update/status/{status}/{uuid}")
    public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("status") String status, @PathVariable("uuid") String uuid) {
        try {
            if(categoryService.updateStatusByUUID(status, uuid)) {
                map.put("message", "Category have been updated successfully.");
                map.put("status", true);
                map.put("code", 200);
            } else {
                map.put("message", "Category have been not updated.");
                map.put("status", false);
                map.put("code", 404);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "Something is broken. Please contact to developer team!");
            map.put("status", false);
            map.put("code", 500);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(map, httpStatus);
    }


    @PutMapping("/update/{uuid}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Category category) {
        try {
            if(categoryService.update(category)) {
                map.put("message", "Category have been updated successfully.");
                map.put("status", true);
                map.put("code", 200);
            } else {
                map.put("message", "Category have been not updated.");
                map.put("status", false);
                map.put("code", 404);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "Something is broken. Please contact to developer team!");
            map.put("status", false);
            map.put("code", 500);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(map, httpStatus);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") String uuid) {
        try {
            if(categoryService.delete(uuid)) {
                map.put("message", "Category have been updated successfully.");
                map.put("status", true);
                map.put("code", 200);
            } else {
                map.put("message", "Category have been not updated.");
                map.put("status", false);
                map.put("code", 404);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "Something is broken. Please contact to developer team!");
            map.put("status", false);
            map.put("code", 500);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(map, httpStatus);
    }








}
