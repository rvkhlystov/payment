//уточнить почему scanner.close отправляет приложение в исключения
//Добавить обработку ошибок при неверно введенных данных
import java.util.Scanner;

public interface Interaction {


    default long userInput(String descriptionOfInputData, long n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите " + descriptionOfInputData);
        long scan = scanner.nextLong();
        //scanner.close();
        return scan;
    }

    default String userInput(String descriptionOfInputData, String n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите " + descriptionOfInputData);
        String scan = scanner.nextLine();
        //scanner.close();
        return scan;
    }

    default float userInput(String descriptionOfInputData, float n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите " + descriptionOfInputData);
        float scan = scanner.nextFloat();
        //scanner.close();
        return scan;
    }

    default String paymentOutput(long descriptionOutputData) {
        return ("Перевод на номер телефона " + descriptionOutputData + " завершен успешно");
    }





}
