1. List all todo items

https://shuang-jersey.herokuapp.com/todolist/list


2. Search todo items
https://shuang-jersey.herokuapp.com/todolist/search?title=[param]&body=[param]&done=[param]

The search query parameters can be null or empty, and if not empty, it is a match all search

3. Get to do item by title (exact match)

https://shuang-jersey.herokuapp.com/todolist/name/[param]

4. Create item
POST with JSON data

https://shuang-jersey.herokuapp.com/todolist/create
wiith JSON data

5. Delete item

POST with JSON data
https://shuang-jersey.herokuapp.com/todolist/delete


6. Update item

PUT with JSON data
https://shuang-jersey.herokuapp.com/todolist/update


7. Mark done

PUT with JSON data
https://shuang-jersey.herokuapp.com/todolist/markdone



8. Mark undone

PUT with JSON data
https://shuang-jersey.herokuapp.com/todolist/markUndone



JSON test data examples

array
[{"body":"life","done":true,"id":{},"title":"apple"},{"body":"balance","done":false,"id":{},"title":"pear app"}]

item
{"body":"life","done":true,"id":{},"title":"apple"}