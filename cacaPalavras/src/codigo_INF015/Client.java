package codigo_INF015;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("", 1234);
            Scanner in = new Scanner(client.getInputStream());
            Scanner inUser = new Scanner(System.in);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            //System.out.println(in.nextLine());

            while (true) {
                System.out.println(in.nextLine());

                System.out.println("Digite uma letra: ");
                out.println(inUser.nextLine());
                System.out.println(in.nextLine());
                Thread.sleep(1200);
                limparSaida();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public final static void limparSaida() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException ex) {
        }
    }
}
