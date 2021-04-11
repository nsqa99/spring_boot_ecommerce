package com.example.demo.service.serviceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ItemDTO;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import com.example.demo.util.converter.ItemConverter;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	ItemConverter converter;

	@Override
	public List<ItemDTO> findAll(Pageable pageable) {
		List<ItemDTO> results = 
				itemRepo.findAll(pageable)
					.getContent()
					.stream()
					.map(converter::toDTO)
					.collect(Collectors.toList());
		return results;
	}

	@Override
	public ItemDTO save(ItemDTO dto) throws Exception {
		Item item = converter.toItem(dto);
		item = itemRepo.save(item);
		if (item == null) throw new Exception("save_failed");
		return converter.toDTO(item);
	}

	@Override
	public ItemDTO update(ItemDTO dto, long id) throws Exception {
		Item oldItem = itemRepo.findById(id).orElse(null);
		if (oldItem == null) {
			throw new Exception("item_not_found");
		}
		Item newItem = converter.toItem(dto, oldItem);
		newItem = itemRepo.save(newItem);
		if (newItem == null) throw new Exception("update_failed");
		return converter.toDTO(newItem);
	}

	@Override
	public void delete(long id) throws Exception {
		Item itemToDelete = itemRepo.findById(id).orElse(null);
		if (itemToDelete == null) {
			throw new Exception("item_not_found");
		}
		itemRepo.delete(itemToDelete);
	}

	@Override
	public ItemDTO findById(long id) {
		Item item = itemRepo.findById(id).orElse(null);
		if (item == null) return null;
		return converter.toDTO(item);
	}

	@Override
	public int getTotalRecords() {
		return (int) itemRepo.count();
	}

}
