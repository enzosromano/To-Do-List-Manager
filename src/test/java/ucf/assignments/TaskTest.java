package ucf.assignments;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    TaskMethods taskMethods = new TaskMethods();

    @Test
    void setEditTaskTest(){

        TodoList.initializeList();
        TodoList.listTitle = "TaskList";
        Task toEdit = new Task("ToEdit", TodoList.listTitle,
                "Empty description", "No due-date assigned", false);
        TodoList.taskObserve.add(toEdit);

        taskMethods.setEditTask(toEdit);

        if(TodoList.taskObserve.contains(toEdit)){
            fail();
        }
        assert(true);

    }

    @Test
    void changeTaskNameTest(){

        String newName = "EditedName";
        TodoList.initializeList();
        TodoList.listTitle = "TaskList";
        Task toEdit = new Task("ToEdit", TodoList.listTitle,
                "Empty description", "No due-date assigned", false);

        taskMethods.setEditTask(toEdit);
        Task compareTo = taskMethods.changeTaskName(newName);
        if(!compareTo.getTaskTitle().equals(newName)){
            fail();
        }
        assert(true);

    }

    @Test
    void changeTaskDescriptionTest(){

        String newDescription = "EditedDescription";
        TodoList.initializeList();
        TodoList.listTitle = "TaskList";
        Task toEdit = new Task("ToEdit", TodoList.listTitle,
                "Empty description", "No due-date assigned", false);

        taskMethods.setEditTask(toEdit);
        Task compareTo = taskMethods.changeTaskDescription(newDescription);
        if(!compareTo.getTaskDescription().equals(newDescription)){
            fail();
        }
        assert(true);

    }

    @Test
    void changeTaskDateTest(){

        LocalDate date = LocalDate.now();
        String dateString = date.toString();
        TodoList.initializeList();
        TodoList.listTitle = "TaskList";
        Task toEdit = new Task("ToEdit", TodoList.listTitle,
                "Empty description", "No due-date assigned", false);

        taskMethods.setEditTask(toEdit);
        Task compareTo = taskMethods.changeTaskDate(date);
        System.out.println(date);
        System.out.println(compareTo.getDueDate());
        if(!compareTo.getDueDate().equals(dateString)){
            fail();
        }
        assert(true);

    }

    @Test
    void changeStatusTest(){

        TodoList.initializeList();
        TodoList.listTitle = "TaskList";
        Task toEdit = new Task("ToEdit", TodoList.listTitle,
                "Empty description", "No due-date assigned", false);

        taskMethods.setEditTask(toEdit);
        Task compareTo = taskMethods.toggleStatus();
        if(!compareTo.getIsDone()){
            fail();
        }
        assert(true);

    }


}
