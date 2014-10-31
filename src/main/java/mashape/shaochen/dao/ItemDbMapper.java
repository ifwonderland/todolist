package mashape.shaochen.dao;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import mashape.shaochen.model.Item;

/**
 * Simple converter to convert mango objects to business object
 * <p>
 * 
 * @author shuang
 * 
 */
public class ItemDbMapper {

	public enum ItemProperties {
		_id, title, body, done
	}

	/**
	 * 
	 * Convert {@code DBObject} to POJO {@code Item} Not a super resilient
	 * converter method, throws exception whenever possible.
	 * <p>
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Item mapTo(DBObject obj) throws Exception {
		ObjectId id = (ObjectId) obj.get(ItemProperties._id.name());
		String title = (String) obj.get(ItemProperties.title.name());
		String body = (String) obj.get(ItemProperties.body.name());
		boolean done = (boolean) obj.get(ItemProperties.done.name());

		// returns a copy, not exposing the dbobject
		Item item = new Item();
		item.setId(id);
		item.setTitle(title);
		item.setBody(body);
		item.setDone(done);

		return item;

	}

	/**
	 * Convert from {@code Item} to {@code DBObject}.
	 * <p>
	 * 
	 * 
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public static DBObject mapTo(Item item) throws Exception {
		DBObject dbo = new BasicDBObject();
		dbo.put(ItemProperties.title.name(), item.getTitle());
		dbo.put(ItemProperties.body.name(), item.getBody());
		dbo.put(ItemProperties.done.name(), item.isDone());
		return dbo;
	}
}
