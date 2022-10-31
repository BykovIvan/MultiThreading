import manager.ManagerClient;
import model.ConfigFile;
import util.ManagerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String path = "http://localhost:8080";
    static String fileStr = "./clientshttp/src/resources/config.csv";
    static ManagerClient managerClient;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isTrue = true;

        while (isTrue) {
            showMenu();
            if (scanner.hasNext()){
                String command = scanner.nextLine();
                if (command.equals("start")) {
                    System.out.println("Запускаем клиентов");
                    managerClient = new ManagerClient(path, fileStr);
                    managerClient.startService();
                } else if (command.equals("set")){
                    System.out.println("");
                    System.out.println("Введите число rCount (Целое число)");
                    int rCount = scanner.nextInt();
                    System.out.println("Введите число wCount (Целое число)");
                    int wCount = scanner.nextInt();
                    System.out.println("Введите целые числа для idList через ','");
                    String ids = scanner.next();
                    String[] arrayOfId = ids.split(",");
                    List<Integer> listIds = new ArrayList<>();
                    for (int i = 0; i < arrayOfId.length; i++) {
                        listIds.add(Integer.valueOf(arrayOfId[i]));
                    }
                    ConfigFile configFile = new ConfigFile(rCount, wCount, listIds);
                    ManagerConfig.saveParam(configFile, fileStr);
                }
                if (command.equals("exit")){
                    isTrue = false;
                }
            }
        }
    }
    private static void showMenu(){
        System.out.println("Введите 'start' для старта создания клиентов");
        System.out.println("Введите 'set' установления параметров клиентов");
        System.out.println("Введите 'exit' выхода из программы");
    }
}
