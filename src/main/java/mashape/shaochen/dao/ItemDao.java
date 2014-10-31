package mashape.shaochen.dao;

import org.bson.types.ObjectId;

import mashape.shaochen.model.Item;

/**
 * Responsible for getting and updating individual {@code Item}
 * 
 * @author shuang
 * 
 */
public interface ItemDao {

	/**
	 * Get one item by id. (_id)
	 * 
	 * @param id
	 * @return
	 */
	public Item get(ObjectId id) throws Exception;

	/**
	 * Get one item by title.
	 * 
	 * @param title
	 * @return
	 */
	public Item get(String title) throws Exception;

	/**
	 * Create new item.
	 * 
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public Item create(Item item) throws Exception;

	/**
	 * Delete an item
	 * 
	 * @param item
	 * @throws Exception
	 */
	public void delete(Item item) throws Exception;

	/**
	 * Update an item
	 * 
	 * @param item
	 * 
	 * @return {@code Item}
	 * 
	 * @throws Exception
	 */
	public Item update(Item item) throws Exception;

}
