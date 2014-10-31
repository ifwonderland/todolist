package mashape.shaochen.dao.impl;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import mashape.shaochen.dao.ItemDbMapper;
import mashape.shaochen.dao.ItemDao;
import mashape.shaochen.dao.ItemDbMapper.ItemProperties;
import mashape.shaochen.db.MongoDbUtils;
import mashape.shaochen.model.Item;

public class ItemDaoImpl implements ItemDao {

	@Override
	public Item get(ObjectId id) throws Exception {
		DBObject query = new BasicDBObject("_id", id);
		DBObject dbItem = MongoDbUtils.getToDoItemsCollection().findOne(query);

		if (dbItem != null)
			return ItemDbMapper.mapTo(dbItem);

		return null;
	}

	@Override
	public Item get(String title) throws Exception {
		DBObject query = new BasicDBObject("title", title);
		DBObject dbItem = MongoDbUtils.getToDoItemsCollection().findOne(query);

		if (dbItem != null)
			return ItemDbMapper.mapTo(dbItem);

		return null;
	}

	@Override
	public Item create(Item item) throws Exception {
		if (get(item.getId()) != null)
			throw new Exception("Item " + item
					+ " already exists. Creation failed. ");

		DBCollection todoCol = MongoDbUtils.getToDoItemsCollection();
		DBObject itemDbObj = ItemDbMapper.mapTo(item);
		todoCol.insert(itemDbObj);
		Item newItem = get(item.getTitle());
		if (newItem == null)
			throw new Exception("Creation to db failed for item: " + item);
		return newItem;
	}

	@Override
	public void delete(Item item) throws Exception {
		if (get(item.getId()) == null)
			throw new Exception("Item " + item
					+ " to be deleted does not exist in db. ");

		DBCollection todoCol = MongoDbUtils.getToDoItemsCollection();
		DBObject itemDbObj = ItemDbMapper.mapTo(item);
		todoCol.remove(itemDbObj);

		if (get(item.getId()) != null)
			throw new Exception("Item " + item + " deletion failed");

	}

	@Override
	public Item update(Item item) throws Exception {
		if (get(item.getId()) == null)
			throw new Exception("Item " + item
					+ " to be deleted does not exist in db. ");

		DBCollection todoCol = MongoDbUtils.getToDoItemsCollection();
		DBObject itemDbObj = ItemDbMapper.mapTo(item);

		DBObject queryObj = new BasicDBObject(ItemProperties._id.name(),
				item.getId());

		todoCol.update(queryObj, itemDbObj);

		return get(item.getId());
	}

}
