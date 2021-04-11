package com.example.demo.resource;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ItemDTO;
import com.example.demo.service.serviceImpl.ItemServiceImpl;
import com.example.demo.util.JsonResponse;

@RestController
@RequestMapping("/api/v1")
public class ItemResources {
	@Autowired
	private ItemServiceImpl itemService;
	
	@GetMapping("/items")
	public ResponseEntity<JsonResponse<ItemDTO>> getItems(@PathVariable(name = "page", required = false) Integer page, @PathVariable(name = "limit", required = false) Integer limit) {
		if (page == null) page = 0;
		if (limit == null) limit = 10;
		Pageable pageable = PageRequest.of(page, limit);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("X-Total-Count", Integer.toString(itemService.getTotalRecords()));
		return new ResponseEntity<JsonResponse<ItemDTO>>(new JsonResponse<ItemDTO>("true", "ok", itemService.findAll(pageable)), responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/items/{iditem}")
	public ResponseEntity<JsonResponse<ItemDTO>> getItemById(@PathVariable(name = "iditem") long iditem) {
		List<ItemDTO> results = new ArrayList<ItemDTO>();
		ItemDTO item = itemService.findById(iditem);
		if(item != null) results.add(item);
		return ResponseEntity.ok(new JsonResponse<ItemDTO>("true", "ok", results));
	}
	
	@PostMapping("/items")
	public ResponseEntity<JsonResponse<ItemDTO>> insertItem(@RequestBody ItemDTO item) {
		try {
			item = itemService.save(item);
		} catch (Exception e) {
			return ResponseEntity.ok(new JsonResponse<ItemDTO>("false", e.getMessage()));
		}
		return ResponseEntity.ok(new JsonResponse<ItemDTO>("true", "success"));
	}
	
	@PutMapping("/items/{itemid}")
	public ResponseEntity<JsonResponse<ItemDTO>> updateItem(@PathVariable(name = "itemid") long iditem, @RequestBody ItemDTO newItem) {
		try {
			itemService.update(newItem, iditem);
		} catch (Exception e) {
			return ResponseEntity.ok(new JsonResponse<ItemDTO>("false", e.getMessage()));
		}
		return ResponseEntity.ok(new JsonResponse<ItemDTO>("true", "success"));
	}
	
	@DeleteMapping("/items/{itemid}")
	public ResponseEntity<JsonResponse<ItemDTO>> deleteItem(@PathVariable(name = "itemid") long iditem) {
		try {
			itemService.delete(iditem);
		} catch (Exception e) {
			return ResponseEntity.ok(new JsonResponse<ItemDTO>("false", e.getMessage()));
		}
		return ResponseEntity.ok(new JsonResponse<ItemDTO>("true", "success"));
	}
	
	
}
