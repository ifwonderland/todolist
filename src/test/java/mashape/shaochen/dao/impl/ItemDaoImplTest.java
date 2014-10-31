package mashape.shaochen.dao.impl;

import mashape.shaochen.dao.ItemDao;
import mashape.shaochen.dao.ItemListDao;
import mashape.shaochen.model.Item;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemDaoImplTest {

	private static ItemDao itemDao = new ItemDaoImpl();

	private static ItemListDao listDao = new ItemListDaoImpl();

	private static Item testItem = null;

	@BeforeClass
	public static void setup() throws Exception {
		testItem = listDao.list().get(0);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Test
	public void testGetById() {
		Item item = null;

		try {
			item = itemDao.get(testItem.getId());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertEquals(testItem, item);
	}

	@Test
	public void testGetByTitle() {
		Item item = null;

		try {
			item = itemDao.get(testItem.getTitle());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertEquals(testItem, item);
	}

	@Test
	public void testCreateDuplicateItem() {
		try {
			itemDao.create(testItem);
		} catch (Exception e) {
			Assert.assertTrue(true);
			return;
		}

		Assert.fail("Creating duplicate item didn't throw exception. ");
	}

	@Test
	public void testCreateNewItem() {
		Item item = getNewItem();
		try {

			Item oldItem = itemDao.get(item.getTitle());
			if (oldItem != null)
				itemDao.delete(item);

			Item createdItem = itemDao.create(item);
			Assert.assertEquals(item.getTitle(), createdItem.getTitle());
			Assert.assertEquals(item.getBody(), createdItem.getBody());
			Assert.assertEquals(item.isDone(), createdItem.isDone());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteNonExistsItem() {
		Item item = getNewItem();
		try {
			while (itemDao.get(item.getTitle()) != null) {
				item.setTitle(item.getTitle() + "re");
			}
			item.setId(null);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		try {
			itemDao.delete(item);
		} catch (Exception e) {
			Assert.assertTrue(true);
			return;
		}

		Assert.fail("Deletion of nonexist item did not throw exception. ");

	}

	@Test
	public void testDeleteExistItem() {
		Item item = getNewItem();
		Item oldItem = null;
		try {
			oldItem = itemDao.get(item.getTitle());
			if (oldItem == null)
				oldItem = itemDao.create(item);

			itemDao.delete(oldItem);
			oldItem = itemDao.get(item.getTitle());
			if (oldItem != null)
				Assert.fail("Item " + oldItem + " not deleted. ");

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testUpdate() {
		Item item = getNewItem();
		try {
			Item oldItem = itemDao.get(item.getTitle());
			if (oldItem == null)
				oldItem = itemDao.create(item);

			String newTitle = oldItem.getTitle() + "updated";
			String newBody = oldItem.getBody() + "updated";
			boolean newDone = !oldItem.isDone();

			oldItem.setTitle(newTitle);
			oldItem.setBody(newBody);
			oldItem.setDone(newDone);

			Item updatedItem = itemDao.update(oldItem);
			Assert.assertEquals(newTitle, updatedItem.getTitle());
			Assert.assertEquals(newBody, updatedItem.getBody());
			Assert.assertEquals(newDone, updatedItem.isDone());

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	private Item getNewItem() {
		Item item = new Item();
		item.setId(null);
		item.setBody("test body");
		item.setTitle("test creation title");
		item.setDone(true);

		return item;
	}

}
