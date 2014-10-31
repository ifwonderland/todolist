package mashape.shaochen.model;

/**
 * Defines search criterai for items, null or empty means all
 * <p>
 * 
 * @author shuang
 * 
 */
public class ItemSearchCriteria {

	private String title;

	private String body;

	private String done;

	public ItemSearchCriteria(String title, String body, String done) {
		super();
		this.title = title;
		this.body = body;
		this.done = done;
	}

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

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

}
