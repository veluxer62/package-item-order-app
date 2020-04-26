package net.class101.server1.resolver;

import net.class101.server1.dto.Response;

public class ConsoleViewResolver implements ViewResolver {
    @Override
    public void show(Response response) {
        System.out.println(response.getBody());
    }
}
