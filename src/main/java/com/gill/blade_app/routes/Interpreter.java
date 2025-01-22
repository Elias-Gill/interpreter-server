package com.gill.blade_app.routes;

import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.request.Body;
import com.hellokaton.blade.annotation.route.POST;
import com.hellokaton.blade.mvc.ui.ResponseType;

@Path(value = "/interpreter", description = "Code execution request endpoint")
public class Interpreter {

    @POST(description = "Execute code remotely and get the output back", responseType = ResponseType.JSON)
    public CodeResponse executeCode(@Body String body) {
        return new CodeResponse(body, "");
    }

    public class CodeResponse {
        public String errors;
        public String output;

        public CodeResponse(String errors, String output) {
            this.errors = errors;
            this.output = output;
        }
    }
}
