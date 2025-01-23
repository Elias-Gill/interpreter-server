package com.gill.blade_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.request.Body;
import com.hellokaton.blade.annotation.route.POST;
import com.hellokaton.blade.mvc.http.Request;
import com.hellokaton.blade.mvc.http.Response;
import com.hellokaton.blade.mvc.ui.ResponseType;
import com.hellokaton.blade.exception.BadRequestException;

@Path(value = "/interpreter", description = "Code execution request endpoint")
public class Interpreter {

    @POST(description = "Execute code remotely and get the output back", responseType = ResponseType.JSON)
    public void executeCodeEndpoint(Request request, Response response, @Body CodeRequest body) {
        String mode = body.mode;

        // Validate the mode
        if (!"eval".equals(mode) && !"lexer".equals(mode) && !"parser".equals(mode)) {
            throw new BadRequestException("Invalid request data. Invalid mode");
        }

        // Execute the code and return the result
        System.out.println("ejecutando codigo");
        CodeResponse result = this.executeCode(body.code, body.mode);
        System.out.println("ejecucion completada");
        response.json(result);
    }

    private CodeResponse executeCode(String input, String mode) {
        ProcessBuilder processBuilder = new ProcessBuilder("interpreter", "-mode=" + mode, "-max-time", "5000", "-quiet");
        processBuilder.redirectErrorStream(false); // Separate stdout and stderr
        Process process = null;

        try {
            // Start the process
            process = processBuilder.start();

            // Write to the process's stdin
            OutputStream outputStream = process.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();
            outputStream.close(); // signal EOF to the interpreter

            // Read stdout and stderr
            StringBuilder outputBuilder = new StringBuilder();
            StringBuilder errorBuilder = new StringBuilder();

            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Read stdout
            String line;
            while ((line = outputReader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }

            // Read stderr
            while ((line = errorReader.readLine()) != null) {
                errorBuilder.append(line).append("\n");
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

            // Return the result as a CodeResponse object
            return new CodeResponse(outputBuilder.toString().trim(), errorBuilder.toString().trim());

        } catch (IOException | InterruptedException e) {
            // Handle exceptions
            e.printStackTrace();
            return new CodeResponse(null, "Internal Server Error: " + e.getMessage());
        } finally {
            // Ensure the process is destroyed
            if (process != null) {
                process.destroy();
            }
        }
    }

    public class CodeRequest {
        public String code;
        public String mode;

        public CodeRequest(String mode, String code) {
            this.mode = mode;
            this.code = code;
        }
    }

    public class CodeResponse {
        public String errors;
        public String output;

        public CodeResponse(String output, String errors) {
            this.errors = errors;
            this.output = output;
        }
    }
}
