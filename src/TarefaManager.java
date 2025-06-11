import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TarefaManager {
    private List<Tarefa> tasks = new ArrayList<>();
    private final String FILE_NAME = "tarefas.txt";
    Scanner scanner = new Scanner(System.in);

    public TarefaManager() {
        loadFromFile();
    }

    private void savetoFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Tarefa t : tasks) {
                writer.write(t.isDone() + ";" + t.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) !=null){
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    Tarefa t = new Tarefa(parts[1]);
                    if (Boolean.parseBoolean(parts[0])){
                        t.markAsDone();
                    }
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefa: " + e.getMessage());
        }
    }

    public void addNewTask() {
        boolean isAdd = true;

        while (isAdd) {
            System.out.println("Digite o nome da tarefa ou digite 'sair' para voltar ao inicio");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("sair")) {
                isAdd = false;
            } else {
                tasks.add(new Tarefa(input));
                System.out.println("Tarefa adicionada com sucesso");
                savetoFile();
            }

        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada");
            return;
        }

        System.out.println("\n--- Lista de tarefas ---");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void markTaksAsDone() {
        listTasks();
        System.out.println("Digite o número da tarefa que deseja marcar como concluída");
        try {
            int number = Integer.parseInt(scanner.nextLine()) - 1;
            if (number >= 0 && number < tasks.size()) {
                tasks.get(number).markAsDone();
                System.out.println("Tarefa marcada como concluída");
                savetoFile();
            } else {
                System.out.println("Número inválido");
            }
        } catch (NumberFormatException e) {
            System.out.println("Estrada inválida");
        }
    }

    public void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa para excluir");
            return;
        }

        System.out.println("Tarefas atuais");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "-" + tasks.get(i));
        }

        System.out.print("Digite o número da tarefa que deseja excluir: ");
        try {
            int number1 = Integer.parseInt(scanner.nextLine());
            if (number1 < 1 || number1 > tasks.size()) {
                System.out.println("Número inválido");
            } else {
                Tarefa removed = tasks.remove(number1 - 1);
                System.out.println("Tarefa removida: " + removed);
            }
        } catch (NumberFormatException e){
            System.out.println("Entrada inválida.  Digite apenas o número da tarefa");
        }
        savetoFile();
    }

    private void sortByName() {
        tasks.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));;
        savetoFile();
        System.out.println("Tarefas ordenadas por nome");
        listTasks();;
    }

    private void sortByStatus() {
        tasks.sort((a, b) -> Boolean.compare(a.isDone(), b.isDone()));
        savetoFile();
        System.out.println("Tarefas ordenadas por status (pendentes primeiro)");
        listTasks();
    }

    public void sortMenu() {
        System.out.println("\nComo desseja ordenar as tarefas?");
        System.out.println("1 - Por nome (A-Z)");
        System.out.println("2 - Por status (pendentes primeiro)");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                sortByName();
                break;
            case "2":
                sortByStatus();
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}

