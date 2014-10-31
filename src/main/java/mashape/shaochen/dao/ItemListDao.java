package mashape.shaochen.dao;

import java.util.List;

import mashape.shaochen.model.Item;
import mashape.shaochen.model.ItemSearchCriteria;

/**
 * Read and update for collection of item daos.
 * 
 * @author shuang
 * 
 */
public interface ItemListDao {

	/**
	 * List all items
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Item> list() throws Exception;

	/**
	 * Search items by criteria
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Item> search(ItemSearchCriteria criteria) throws Exception;

}
