package net.class101.server1.resolver;

import net.class101.server1.dto.Response;

public interface ViewResolver {
    void show(Response response);
    void show(Throwable throwable);
}
