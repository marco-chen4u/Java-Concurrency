package threadlocal;

public final class ActionContext {
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {//for the factory method to use
            return new Context();
        }
    };

    private static class ContextHolder {
        private final static ActionContext actionContext = new ActionContext();
    }

    // singleton
    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }
}

class Context {
    // fields
    private String name;
    private String cardId;

    // methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
