import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TarefaManager {
    private List<Tarefa> tasks = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

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
    }
}
