# To-Do List Maker Help
_______________________________________________________________________________________________________________________

##Main Menu

When first running the program you are brought to the main menu page,
This page has a table that will contain no content upon your first load.

On this page you will also see 8 buttons,

1) "Complete/Incomplete/All" - This button is used as a view toggle, clicking on it will change the tasks
    that will appear within the table on this page. If there are no tasks in the lists, the button will have
    no functionality
    
2) "Create New List" - This button will take you to a window that allows you to type in the name of a list
    you want to create that will be used to populate the table
   
3) "Import List" - This button will open a file chooser that allows you to navigate to the .json file that
    your list to be imported is stored in
   
4) "Export List" - This button will create a file in your current project directory under the name of jsonOfList.
    This file can be automatically re-imported
   
5) "Delete all tasks" - This button will delete all the current tasks in your list

6) "Make Task" - This button will add an empty placeholder task to your list that you can go in and change

7) "Edit Selected Task" - Select a task from the table and then click this button to edit it. This button
    will open the task editor window and navigate you away from the main menu
   
8) "Delete Selected Task" - Select a task from the table and then click this button to remove it from the list

### Restrictions
On the main menu, some buttons can only be used once certain actions have been taken

    - You can not import tasks until you have created a list using the "Create New List" button

    - You can not export a list using the "Export List" button if a list has not been initialized

    - You can not delete tasks using either delete task button on the page unless the list is populated 
    with at least one item

    - You can not make any tasks using the "Make Task" button until you have created a list 
    using the "Create New List" button

    - You can not sort any tasks using the "Complete/Incomplete/All" button until you have created a list
    using the "Create New List" button and populated it with at least one task
   
##List Menu
(Found by clicking on the "Create New List" button on the Main Menu)

The list menu contains nothing more than a text field, and two buttons ("Make a new List" and "Home")
1) Clicking the home button at any time will bring you back to the main screen and may not save your input
2) Clicking the Make a New List button will grab the input from the text field and initialize a list with that name
    1) If the text field is empty (has no input) at the time you click Make a New List nothing will happen,
       and a message will appear telling you the restrictions of list titling
       

##Task Menu
This menu can only be reached if...

    - You have initialized a list
    - There is at least one task within that list
    - You have selected a task within the table and then subsequently clicked on "Edit Selected Task"

Within the task menu, you will see 3 text-fields next to three buttons
    
    -Assign Task Name: Assigns user input to the tasks title from the text field directly to its left
        -If text field is empty upon hitting this button, nothing will happen and title restrictions will appear

    -Assign Description: Assigns user input to the tasks description from the text field directly to its left
        -If text field is empty upon hitting this button, nothing will happen and description restrictions will appear

    -Assign Due Date: This button will get the users input from the Date Picker directly to its left
        -The date picker requires the user to either input a date in "yyyy-mm-dd" format, or
        click on the calendar icon and pick the date through the date pickers interface
        -If no interaction or input has been recorded at the time of pressing the button, nothing will happen

The fourth button, "Toggle Status", will set the Status (completed or not) equal to the opposite of what it is currently

    - If the task is currently marked as Incomplete, clicking this button will mark it Complete
    - If the task is currently markes as Complete, clicking this button will mark it Incomplete

The home button on this page works differently than it does on the List Menu
Clicking this button at any point will not delete any saved data, if you have entered input and interacted
with any of the three buttons adjacent to text-fields above, the input will save.

##Import/Export

###Import

    - Imported files **MUST** be of the file type json
    - Json files you are trying to import **MUST** have tasks listed under a json object labeled "tasks"
        -For an example, create a list, add a task and export it. That will show you the correct format
    - Importing can only be done after a list has been initialized prior
        - This was done to narrow down the many different formats you can use when writing a json file. There is 
        obscurity in what exactly a user can try and use as an import file, so I chose to limit it accordingly
        - Importing a list will not intiialize a list or create a list name, it will only import its tasks

###Export

    - Lists will be exported to the directy that you are currently running the project from
    - Lists can be exported with 0 items in them, though im not sure why youd want that
    - The exported file will be in the JSON format and if you want to reimport it you are allowed to
    - The exported file will be named "jsonOfList"
