package mashape.shaochen;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import mashape.shaochen.webresource.ToDoList;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class ToDoListTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(ToDoList.class);
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testListAllToDoListItems() {
		WebTarget target = target().path("todolist/list");
		Response response = target.request().get();

		System.out.println(response);
	}
}
