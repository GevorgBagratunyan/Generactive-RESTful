//package com.generactive.repository;
//
//import com.generactive.model.GenerativeItem;
//import com.generactive.model.Group;
//import com.generactive.model.Item;
//import com.generactive.model.enums.Complexity;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ItemRepositoryTest {
//
//    private static final ItemRepository ITEM_REPOSITORY = new ItemRepository();
//    private static final GroupRepository GROUP_REPOSITORY = new GroupRepository();
//
//    @BeforeAll
//    public static void initDatabase() {
//        Group parent = new Group();
//        parent.setName("Parent");
//        GROUP_REPOSITORY.create(parent);
//
//        Group child = new Group();
//        child.setName("Child");
//        child.setParent(parent);
//        GROUP_REPOSITORY.create(child);
//
//        Item item = new GenerativeItem(Complexity.TWO);
//        item.setName("Start_Item");
//        item.setUrl("Start_URL");
//        item.setBasePrice(25.0);
//        ITEM_REPOSITORY.create(item);
//    }
//
//    @Test
//    @DisplayName("Create Item")
//    public void create() {
//        Item item = new GenerativeItem(Complexity.TWO);
//        item.setName("Test_Item");
//        item.setUrl("Test_URL");
//        item.setBasePrice(22.5);
//
//        Item fetched = ITEM_REPOSITORY.create(item);
//        assertNotNull(fetched);
//        assertEquals(2, fetched.getId());
//        assertTrue(fetched instanceof GenerativeItem);
//    }
//
//    @Test
//    @DisplayName("Get Item By ID")
//    public void getById() {
//        Optional<Item> optionalItem = ITEM_REPOSITORY.read(1);
//        assertTrue(optionalItem.isPresent());
//        assertEquals(1, optionalItem.get().getId());
//        assertEquals("Start_Item", optionalItem.get().getName());
//    }
//
//    @Test
//    @DisplayName("Delete Item By ID")
//    public void deleteById() {
//        Optional<Item> optionalItem = ITEM_REPOSITORY.delete(1);
//        assertTrue(optionalItem.isPresent());
//    }
//
//    @Test
//    @DisplayName("Update Item")
//    public void update() {
//        Item item = new GenerativeItem(Complexity.ONE);
//        item.setName("Updated");
//        Optional<Item> updatedOptionalItem = ITEM_REPOSITORY.update(item);
//        assertTrue(updatedOptionalItem.isPresent());
//        Item updatedItem = updatedOptionalItem.get();
//        assertEquals("ONE", ((GenerativeItem) updatedItem).getComplexity().toString());
//        assertEquals("Updated", updatedItem.getName());
//    }
//
//    @Test
//    @DisplayName("Get All Items")
//    public void getAll() {
//        List<Item> list = ITEM_REPOSITORY.getAll();
//        assertTrue(list.size() > 0);
//        assertEquals("Start_Item", list.get(0).getName());
//        assertTrue(list.get(0) instanceof GenerativeItem);
//    }
//
//    @Test
//    @DisplayName("Get Items in Given Price Range")
//    public void getItemsListByGivenPriceRange() {
//        List<Item> list = ITEM_REPOSITORY.allByPriceRange(0.0, 1200.0);
//        assertTrue(list.size() > 0);
//        assertEquals("Start_Item", list.get(0).getName());
//        assertTrue(list.get(0) instanceof GenerativeItem);
//        assertTrue(list.get(0).getBasePrice() < 1200.0 && list.get(0).getBasePrice() > 0.0);
//    }
//
//}
