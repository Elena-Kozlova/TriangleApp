package com.dct.run;

import com.dct.service.request.TriangleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        MainHelper mainHelper = new MainHelper();
        TriangleRequest triangleRequest;

        try {
            if (args.length < 3) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter side A:");
                String a = br.readLine();

                System.out.println("Enter side B:");
                String b = br.readLine();

                System.out.println("Enter side C:");
                String c = br.readLine();

                triangleRequest = mainHelper.composeTriangleRequest(a, b, c);
            } else {
                triangleRequest = mainHelper.composeTriangleRequest(args[0], args[1], args[2]);
            }

            mainHelper.checkTriangle(triangleRequest);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("The side(s) is in incorrect format");
            LOGGER.error("Triangle side was specified in incorrect format, e");
        }
    }
}
