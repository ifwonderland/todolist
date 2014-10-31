package mashape.shaochen.webresource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import mashape.shaochen.dao.ItemDao;
import mashape.shaochen.dao.ItemListDao;
import mashape.shaochen.dao.impl.ItemDaoImpl;
import mashape.shaochen.dao.impl.ItemListDaoImpl;
import mashape.shaochen.model.Item;
import mashape.shaochen.model.ItemSearchCriteria;

/**
 * Root resource (exposed at "todolist" path), this manages collection of to do
 * list items.
 * <p>
 * 
 * @author shuang
 * 
 */
@Path("todolist")
public class ToDoList {
	private ItemListDao listDao = new ItemListDaoImpl();

	private ItemDao itemDao = new ItemDaoImpl();

	/**
	 * List all to do items.
	 * 
	 * @return
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> listAllToDoItems() throws Exception {
		return listDao.list();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> listAllToDoItems(@QueryParam("title") String title,
			@QueryParam("body") String body, @QueryParam("done") String done)
			throws Exception {
		return listDao.search(new ItemSearchCriteria(title, body, done));
	}

	/**
	 * Get item by title.
	 * 
	 * @param title
	 * @return
	 */
	@GET
	@Path("/name/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Item getToDoItemByName(@PathParam("title") String title)
			throws Exception {
		return itemDao.get(title);

	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Item createToDoItem(Item item) throws Exception {
		return itemDao.create(item);
	}

	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteToDoItem(Item item) throws Exception {
		itemDao.delete(item);
		return "Item " + item.getTitle() + " has been deleted";
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Item updateToDoItem(Item item) throws Exception {
		return itemDao.update(item);
	}

	@PUT
	@Path("/markdone")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Item markDoneToDoItem(Item item) throws Exception {
		item.setDone(true);
		return updateToDoItem(item);
	}

	@PUT
	@Path("/markUndone")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Item markUndoneToDoItem(Item item) throws Exception {
		item.setDone(false);
		return updateToDoItem(item);
	}

}
