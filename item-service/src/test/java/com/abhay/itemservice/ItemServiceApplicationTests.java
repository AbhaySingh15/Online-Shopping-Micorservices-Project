package com.abhay.itemservice;

import com.abhay.itemservice.entity.Item;
import com.abhay.itemservice.repository.ItemRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemServiceApplicationTests {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	void contextLoads() {

	}

	@Test
	@BeforeAll
	void createItem(){
		Item item1 = new Item();
		item1.setName("test item");
		item1.setDescription("this is a test item");
		item1.setPrice(999);

		Item item2 = new Item();
		item2.setName("test item 2");
		item2.setDescription("this is a 2nd test item");
		item2.setPrice(999);
		List<Item> itemList	=itemRepository.saveAll(List.of(item1, item2));

		assertTrue(itemList.size()>0);
	}
	@Test
	public void getAllItems(){
		List<Item> itemList = itemRepository.findAll();
		assertTrue(itemList.size()>0);
	}

	@Test
	public void getItemByName(){
		Item item = new Item();
		item.setName("laptop");
		item.setDescription("this is a test item");
		item.setPrice(999);
		itemRepository.save(item);
		Item item1 = itemRepository.findByName("laptop");
		assertEquals("laptop",item1.getName());
	}
}
