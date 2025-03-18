package handler;

import util.Container;
import util.request.Request;
import util.request.RequestHandler;

import java.util.Scanner;

public class CommandProcessor {
    private Container container;
    private RequestHandler requestHandler;
    private Scanner scanner;
    private InitTest initTest;

    public CommandProcessor(Container container) {
        this.container = container;
        this.requestHandler = new RequestHandler(container);
        this.scanner = new Scanner(System.in);
        this.initTest = new InitTest();
    }

    public void start() {
        initTest.init();
        while (true) {
            System.out.print("https://example.com");
            String input = scanner.nextLine();
            if(input.equals("exit") || input.equals("종료")) break;

            Request request = new Request(input);
            requestHandler.handle(request);
        }
    }


}
