import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Todo {
    public static void main(String[] args) {

        if (args.length == 0) {
            printUsage();
        } else if(!(args[0].equals("-l") || args[0].equals("-a") || args[0].equals("-r") || args[0].equals("-c"))) {
            System.out.println("Unsupported argument");
            printUsage();
        } else if (args[0].equals("-l")) {
            listItems(args);
        } else if(args[0].equals("-a")) {
            addTask(args);
        } else if(args[0].equals("-r")) {
            removeTask(args);
        } else if (args[0].equals("-c")) {
            completeTask(args);
        }
    }
    public static void printUsage() {
        System.out.println();
        System.out.println("Command Line Todo application");
        System.out.println("=============================");
        System.out.println();
        System.out.println("Command line arguments: \n    -l    Lists all the tasks\n    -a    Adds a new task\n" +
                "    -r    Removes a task\n    -c    Completes a task");
        System.out.println();
    }

    public static void listItems(String[] args) {
        List<String> todos = new ArrayList<>();
        Path path = Paths.get("../assets/todolist.txt");

        try {
            todos = Files.readAllLines(path);

        }catch (IOException e) {
            System.out.println("No such file");
            System.exit(2);
        }
        System.out.println();
        if (todos.size() == 0){
            System.out.println("No todos for today! :)");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println((i+1)+" - "+todos.get(i));
            }
        }
    }

    public static void addTask(String[] args) {
        List<String> todos = new ArrayList<>();
        Path path = Paths.get("../assets/todolist.txt");

        if (args.length < 2) {
            System.out.println("Unable to add: no task provided");
            System.exit(2);
        } else {
            try {
                todos = Files.readAllLines(path);
                todos.add("[ ] "+args[1]);
                Files.write(path, todos);
            } catch (IOException e) {
                System.out.println("Cannot write file");
                System.exit(2);
            }
        }
    }

    public static void removeTask(String[] args) {
        List<String> todos = new ArrayList<>();
        Path path = Paths.get("../assets/todolist.txt");

        try {
            todos = Files.readAllLines(path);
            int indexToRemove = Integer.parseInt(args[1]) - 1;
            todos.remove(indexToRemove);
            Files.write(path, todos);
        } catch (IOException e) {
            System.out.println("Cannot delete from file");
            System.exit(2);
        } catch (IndexOutOfBoundsException e) {
            if (args.length < 2) {
                System.out.println("Unable to remove: index is not provided");
                System.exit(2);
            } else {
                System.out.println("Unable to remove: index is out of bound");
                System.exit(2);
            }
        } catch (NumberFormatException e) {
            System.out.println("Unable to remove: index is not a number");
        }
    }

    public static void completeTask(String[] args) {
        List<String> todos = new ArrayList<>();
        Path path = Paths.get("../assets/todolist.txt");

        try {
            todos = Files.readAllLines(path);
            int indexToOverWrite = Integer.parseInt(args[1]) - 1;
//                char[] placingX = todos.get(indexToOverWrite).toCharArray();
//                placingX[1] = 'x';
//                String completedTask = String.valueOf(placingX);
            todos.set(indexToOverWrite, "[x"+todos.get(indexToOverWrite).substring(2));
            Files.write(path, todos);
        } catch (IOException e) {
            System.out.println("Cannot write to file");
            System.exit(2);
        } catch (IndexOutOfBoundsException e) {
            if (args.length < 2) {
                System.out.println("Unable to check: index is not provided");
                System.exit(2);
            } else {
                System.out.println("Unable to check: index is out of bound");
                System.exit(2);
            }
        } catch (NumberFormatException e) {
            System.out.println("Unable to check: index is not a number");
        }
    }
}
