package mashape.shaochen.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jetty.util.StringUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import mashape.shaochen.dao.ItemDbMapper;
import mashape.shaochen.dao.ItemListDao;
import mashape.shaochen.dao.ItemDbMapper.ItemProperties;
import mashape.shaochen.db.MongoDbUtils;
import mashape.shaochen.model.Item;
import mashape.shaochen.model.ItemSearchCriteria;

public class ItemListDaoImpl implements ItemListDao {

	@Override
	public List<Item> list() throws Exception {
		List<Item> items = new ArrayList<>();

		DBCollection todoitemsCol = MongoDbUtils.getToDoItemsCollection();
		DBCursor cursor = todoitemsCol.find();

		while (cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			Item item = ItemDbMapper.mapTo(dbObj);
			items.add(item);
		}
		return items;
	}

	@Override
	public List<Item> search(ItemSearchCriteria criteria) throws Exception {
		List<Item> items = new ArrayList<>();
		DBCollection todoitemsCol = MongoDbUtils.getToDoItemsCollection();

		DBObject query = new BasicDBObject();
		if (criteria != null) {
			if (StringUtil.isNotBlank(criteria.getTitle())) {
				Pattern titleRegex = Pattern.compile(criteria.getTitle());
				query.put(ItemProperties.title.name(), titleRegex);

			}

			if (StringUtil.isNotBlank(criteria.getBody())) {
				Pattern bodyRegex = Pattern.compile(criteria.getBody());
				query.put(ItemProperties.body.name(), bodyRegex);
			}

			if (StringUtil.isNotBlank(criteria.getDone())) {
				boolean isDone = Boolean.getBoolean(criteria.getDone());
				query.put(ItemProperties.done.name(), isDone);
			}

		}

		DBCursor cursor = todoitemsCol.find(query);

		while (cursor.hasNext()) {
			DBObject dbObj = cursor.next();
			Item item = ItemDbMapper.mapTo(dbObj);
			items.add(item);
		}
		return items;
	}

}
