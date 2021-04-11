package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.dto.ItemDTO;

public interface ItemService {
	List<ItemDTO> findAll(Pageable pageable);
	ItemDTO save(ItemDTO item) throws Exception;
	ItemDTO update(ItemDTO item, long id) throws Exception;
	ItemDTO findById(long id);
	void delete(long id) throws Exception;
	int getTotalRecords();
}
