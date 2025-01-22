package com.gill.blade_app.routes;

import java.util.HashMap;
import java.util.Map;

import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.route.GET;

@Path(value = "/", description = "Home page application")
public class Home {

    @GET(value = "/")
    public response helloWorld() {
        return new response();
    }

    @GET(value = "/x")
    public Map<String, String> nada() {
        Map<String, String> foo = new HashMap<String, String>();
        foo.put("nombre", "nada");

        return foo;
    }

    private class response {
        public String name;
        public String id;

        public response() {
            this.name = "hola";
            this.id = "mundo";
        }
    }
}
