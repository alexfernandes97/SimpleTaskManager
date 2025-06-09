public class Tarefa {
    private String name;
    private boolean isDone;

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public Tarefa(String name){
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[✔] " : "[ ] ") + name;
    }


}
