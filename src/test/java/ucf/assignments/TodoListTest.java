package ucf.assignments;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        System.out.println(TodoList.taskObserve.size());
        if(!TodoList.taskObserve.isEmpty()){
            assert(true);
        }

    }

    @Test
    void deleteAllTasksTest() {

        TodoList.initializeList();

        menuMethods.makeTask();
        if(!TodoList.taskObserve.isEmpty()){
            fail();
        }
        TodoList.listTitle = "testingList";
        for(int i = 0; i < 5; i++){
            menuMethods.makeTask();
        }
        System.out.println(TodoList.taskObserve.size());
        if(TodoList.taskObserve.size() == 5){
            assert(true);
        }

    }



}


