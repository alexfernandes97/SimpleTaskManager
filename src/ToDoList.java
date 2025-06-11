import java.util.Scanner;

public class ToDoList {
    public static void main(String[] args) {
        TarefaManager manager = new TarefaManager();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;

      while (isContinue) {
          System.out.println("\n Gerenciador de Tarefas");
          System.out.println();
          System.out.println("1 - Adicionar nova tarefa");
          System.out.println("2 - Listar tarefas");
          System.out.println("3 - Ordenar tarefas");
          System.out.println("4 - Marcar tarefa como concluída");
          System.out.println("5 - Remover tarefa");
          System.out.println("6 - Sair");

          String answer = scanner.nextLine();

         switch (answer) {
             case "1":
                 manager.addNewTask();
                 break;
             case "2":
                 manager.listTasks();
                 break;
             case "3":
                 manager.sortMenu();
                 break;
             case "4":
                 manager.markTaksAsDone();
                 break;
             case "5":
                 manager.removeTask();
                 break;
             case "6":
                 isContinue = false;
                 System.out.println("Encerrando programa");
                 break;
             default:
                 System.out.println("Opção inválida");
         }
      }
        scanner.close();
    }

}

