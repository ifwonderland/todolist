package mashape.shaochen.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;

public class Item {

	// system generated id

	private ObjectId id;

	private String title;

	private String body;

	private boolean done;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item)
			return ((Item) obj).getId().equals(this.getId());
		return false;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public static final Item newInstance(String title, String body, boolean done) {
		Item item = new Item();
		item.setTitle(title);
		item.setBody(body);
		item.setDone(done);
		item.setId(null);
		return item;
	}

}
