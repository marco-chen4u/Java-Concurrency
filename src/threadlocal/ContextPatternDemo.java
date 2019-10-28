package threadlocal;

import java.util.stream.IntStream;

public class ContextPatternDemo {
    public static void main(String[] args) {
        IntStream.range(1, 5)
                .forEach(i -> new Thread(new ExecutionTask()).start()
                );
    }
}


class ExecutionTask implements Runnable {
    // fields
    private ContextPatternClientAction client;
    private ContextPatternServerAction server;

    public ExecutionTask() {
        this.client = new ContextPatternClientAction();
        this.server = new ContextPatternServerAction();
    }

    @Override
    public void run() {

        server.execute();
        System.out.println("The name query successful");

        client.execute();
        System.out.println("The card id query successful");

        // by doing so, we eliminate the parameter passing instead of using threadlocal data types
        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The Name is " + context.getName() + " and CardID " + context.getCardId());

    }
}

class ContextPatternServerAction {

    public void execute() {
        try {
            Thread.sleep(1000);
            String name = Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class ContextPatternClientAction {
    private final String DEFAULT_ID = "43590221";

    private String getCardID(String name) {
        try {
            Thread.sleep(1000);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return DEFAULT_ID + Thread.currentThread().getId();
    }

    public void execute() {
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardID = getCardID(name);
        context.setCardId(cardID);
    }

}
