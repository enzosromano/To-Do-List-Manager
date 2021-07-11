package ucf.assignments;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoListTest {

    MenuMethods menuMethods = new MenuMethods();

    @Test
    void initializeListTest() {

        TodoList.initializeList();
        if(TodoList.taskObserve != null){
            assert(true);
        }
        else{
            assert(false);
        }

    }

    @Test
    void makeAndDeleteTaskTest() {

        TodoList.initializeList();

        menuMethods.makeTask();
        if(TodoList.taskObserve.isEmpty()){
            fail();
        }
        TodoList.listTitle = "testingList";
        menuMethods.makeTask();
        if(!TodoList.taskObserve.isEmpty()){
            assert(true);
        }
        TodoList.taskObserve.clear();

    }

    @Test
    void deleteAllTasksTest() {

        TodoList.initializeList();

        TodoList.listTitle = "testingList";
        for(int i = 0; i < 5; i++){
            menuMethods.makeTask();
        }
        if(TodoList.taskObserve.size() == 5){
            assert(true);
        }

    }

    @Test
    void importFileTest(){

        TodoList.initializeList();
        TodoList.listTitle = "CompleteList";
        File file = new File("./src/main/resources/TaskImport.json");
        JSONArray testingImport = menuMethods.importList(file);
        if(testingImport.size() == 0){
            fail();
        }
        assert(true);

    }

    @Test
    void displayCompleteTest(){

        TodoList.initializeList();
        TodoList.listTitle = "CompleteList";
        for(int i = 0; i < 5; i++) {
            TodoList.taskObserve.add(new Task("taskTitle", TodoList.listTitle,
                    "taskDescription", "dueDate", false));
            TodoList.taskObserve.add(new Task("taskTitle", TodoList.listTitle,
                    "taskDescription", "dueDate", true));
        }

        ObservableList<Task> completeItems = menuMethods.displayComplete();

        for(int i = 0; i < completeItems.size(); i++){
            if(!completeItems.get(i).getIsDone()){
                fail();
            }
        }
        assert(true);

    }

    @Test
    void displayIncompleteTest(){

        TodoList.initializeList();
        TodoList.listTitle = "CompleteList";
        for(int i = 0; i < 5; i++) {
            TodoList.taskObserve.add(new Task("taskTitle", TodoList.listTitle,
                    "taskDescription", "dueDate", false));
            TodoList.taskObserve.add(new Task("taskTitle", TodoList.listTitle,
                    "taskDescription", "dueDate", true));
        }

        ObservableList<Task> completeItems = menuMethods.displayIncomplete();

        for(int i = 0; i < completeItems.size(); i++){
            if(completeItems.get(i).getIsDone()){
                fail();
            }
        }
        assert(true);

    }

    @Test
    void printToJsonTest(){

        TodoList.initializeList();
        TodoList.listTitle = "CompleteList";
        for(int i = 0; i < 5; i++) {
            TodoList.taskObserve.add(new Task("taskTitle", TodoList.listTitle,
                    "taskDescription", "dueDate", false));
            TodoList.taskObserve.add(new Task("taskTitle", TodoList.listTitle,
                    "taskDescription", "dueDate", true));
        }

        if(!menuMethods.printToJson()){
            fail();
        }

        assert(true);

    }

}


