package mashape.shaochen.dao.impl;

import java.util.List;

import mashape.shaochen.dao.ItemDao;
import mashape.shaochen.dao.ItemListDao;
import mashape.shaochen.model.Item;
import mashape.shaochen.model.ItemSearchCriteria;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemListDaoImplTest {

	private static ItemDao itemDao = new ItemDaoImpl();

	private static ItemListDao listDao = new ItemListDaoImpl();

	private static Item[] testItems = null;

	private static String[] titles = new String[] { "test title1",
			"abstract title", "tit", "apple", "pear app" };

	private static String[] bodies = new String[] { "test bodies 1", "body",
			"work", "life", "balance" };

	private static boolean[] dones = new boolean[] { false, false, true, true,
			false };

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setupItems();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		removeItems();
	}

	@Test
	public void testList() throws Exception {
		List<Item> allItems = listDao.list();
		for (Item testItem : testItems) {
			Assert.assertTrue(allItems.contains(testItem));
		}
	}

	@Test
	public void testSearchEmpty() throws Exception {
		List<Item> searchItems = listDao.search(null);
		List<Item> allItems = listDao.list();

		for (Item allItem : allItems)
			searchItems.contains(allItem);

		searchItems = listDao.search(new ItemSearchCriteria(null, null, null));

		for (Item allItem : allItems)
			searchItems.contains(allItem);

		searchItems = listDao.search(new ItemSearchCriteria("", "", ""));

		for (Item allItem : allItems)
			searchItems.contains(allItem);

	}

	// "test title1", "abstract title", "tit", "apple", "pear app"
	@Test
	public void testSearchTitle() throws Exception {
		ItemSearchCriteria search = new ItemSearchCriteria("title1", "", "");
		List<Item> result = listDao.search(search);
		Assert.assertTrue(result.contains(testItems[0]));

		search = new ItemSearchCriteria("tit", "", "");
		result = listDao.search(search);
		boolean contains = result.contains(testItems[0])
				&& result.contains(testItems[1])
				&& result.contains(testItems[2]);
		Assert.assertTrue(contains);

		search = new ItemSearchCriteria("app", "", "");
		result = listDao.search(search);
		contains = result.contains(testItems[3])
				&& result.contains(testItems[4]);
		Assert.assertTrue(contains);

		search = new ItemSearchCriteria("sdafadsfasdfasdf", "", "");
		result = listDao.search(search);
		Assert.assertEquals(0, result.size());

	}

	private static void setupItems() throws Exception {
		testItems = new Item[5];

		for (int i = 0; i < 5; i++) {
			Item newItem = Item.newInstance(titles[i], bodies[i], dones[i]);
			Item oldItem = itemDao.get(newItem.getTitle());
			if (oldItem == null)
				oldItem = itemDao.create(newItem);
			testItems[i] = oldItem;
		}

	}

	private static void removeItems() throws Exception {
		for (int i = 0; i < 5; i++) {
			itemDao.delete(testItems[i]);
		}

		testItems = null;
	}

}
