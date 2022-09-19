package main.sim;

import main.web.model.entity.SearchConstraints;

class RequestBot {
    private final long id;
    private boolean isAvailable;

    public RequestBot(long id) {
        this.id = id;
        this.isAvailable = true;
    }

    public long getId() {
        return id;
    }
}

public class RequestGenerator {
    private RequestBot[] botPool;
    private SearchConstraints constraints;

    public RequestGenerator(int userCount, SearchConstraints inputConstraints) {
        this.botPool = new RequestBot[userCount];
        this.constraints = inputConstraints;

        for (int i = 0; i < userCount; i++)
            botPool[i] = new RequestBot(i);
    }
}
