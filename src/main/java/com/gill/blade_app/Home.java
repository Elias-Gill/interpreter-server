package com.gill.blade_app;

import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.route.GET;
import com.hellokaton.blade.mvc.RouteContext;

@Path(value = "/", description = "Home page application")
public class Home {
    @GET(description = "Home which displays a simple text editor and the interpreter outputs")
    public void home(RouteContext ctx) {
        ctx.render("index.html");
    }
}
