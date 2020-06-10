import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Todo {
    public static void main(String[] args) {
        List<String> todos = new ArrayList<>();
        Path path = Paths.get("/Users/bence/documents/github/bencevagod-todo-app/assets/todolist.txt");

        if (args.length == 0) {
            System.out.println();
            System.out.println("Command Line Todo application");
            System.out.println("=============================");
            System.out.println();
            System.out.println("Command line arguments: \n    -l    Lists all the tasks\n    -a    Adds a new task\n" +
                "    -r    Removes a task\n    -c    Completes a task");
            System.out.println();
        } else if (args[0].equals("-l")) {
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
        } else if(args[0].equals("-a")) {
            if (args.length < 2) {
                System.out.println("Unable to add: no task provided");
                System.exit(2);
            } else {
                try {
                    todos = Files.readAllLines(path);
                    todos.add(args[1]);
                    Files.write(path, todos);
                } catch (IOException e) {
                    System.out.println("Cannot write file");
                    System.exit(2);
                }
            }
        } else if(args[0].equals("-r")) {
            try {
                todos = Files.readAllLines(path);
                int indexToRemove = Integer.parseInt(args[1]) - 1;
                todos.remove(indexToRemove);
                Files.write(path, todos);
            } catch (IOException e) {
                System.out.println("Cannot delete from file");
                System.exit(2);
            }
        }
    }
}
