package com.example.demo.util.converter;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ItemDTO;
import com.example.demo.model.Item;
import com.example.demo.model.Product;

@Component
public class ItemConverter {
	public ItemDTO toDTO(Item item) {
		ItemDTO itemDto = new ItemDTO();
		itemDto.setId(item.getIdItem());
		itemDto.setPrice(item.getPrice());
		itemDto.setName(item.getProduct().getNameP());
		itemDto.setType(item.getProduct().getType());
		itemDto.setDescription(item.getProduct().getDescription());
		return itemDto;
	}
	
	public Item toItem(ItemDTO itemDto) {
		Item item = new Item();
		Product product = new Product();
		item.setPrice(itemDto.getPrice());
		product.setNameP(itemDto.getName());
		product.setDescription(itemDto.getDescription());
		product.setType(itemDto.getType());
		item.setProduct(product);
		return item;
	}
	
	public Item toItem(ItemDTO itemDto, Item item) {
		Product product = new Product();
		item.setPrice(itemDto.getPrice());
		product.setNameP(itemDto.getName());
		product.setDescription(itemDto.getDescription());
		product.setType(itemDto.getType());
		item.setProduct(product);
		return item;
	}
}
