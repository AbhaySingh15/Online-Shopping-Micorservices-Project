package com.abhay.itemservice;

import com.abhay.itemservice.entity.Item;
import com.abhay.itemservice.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
class ItemServiceApplicationTests {

	@Mock
	private ItemRepository itemRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllItems(){
		assertNotNull(itemRepository.getAllItems());
	}

	@Test
	void createItem(){
		Item item1 = new Item();
		item1.setId(7L);
		item1.setName("test item");
		item1.setDescription("this is a test item");
		item1.setPrice(999);

		Item item2 = new Item();
		item2.setId(7L);
		item2.setName("test item 2");
		item2.setDescription("this is a 2nd test item");
		item2.setPrice(999);
		List<Item> itemList = List.of(item1, item2);
		

	}
}
