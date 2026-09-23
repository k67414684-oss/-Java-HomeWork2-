import java.util.Scanner;

public class Ex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ТАРИФІКАТОР СЛУЖБИ ДОСТАВКИ ===");

        System.out.print("Введіть вагу відправлення (кг): ");
        double weight = scanner.nextDouble();

        System.out.print("Введіть відстань транспортування (км): ");
        int distance = scanner.nextInt();

        if (weight <= 0 || weight > 50.0) {
            System.out.println("Помилка: вага відправлення повинна бути більше 0 та не перевищувати 50.0 кг!");
            scanner.close();
            return;
        }

        if (distance <= 0) {
            System.out.println("Помилка: відстань транспортування повинна бути більше 0 км!");
            scanner.close();
            return;
        }

        System.out.print("Оберіть пункт призначення (1 - Відділення, 2 - Поштомат, 3 - Кур'єр): ");
        int deliveryType = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Чи є у вас карта Premium? (так/ні): ");
        String isPremiumInput = scanner.nextLine().trim();

        double baseTariff = switch (deliveryType) {
            case 1 -> 50.0;
            case 2 -> {
                if (weight > 15.0) {
                    System.out.println("\nПомилка: поштомат не приймає габаритні посилки понад 15 кг!");
                    yield -1.0;
                }
                yield 60.0;
            }
            case 3 -> 100.0;
            default -> {
                System.out.println("\nПомилка: обрано некоректний тип доставки!");
                yield -1.0;
            }
        };

        if (baseTariff == -1.0) {
            scanner.close();
            return;
        }

        double distanceFee;
        if (distance <= 50) {
            distanceFee = 0.0;
        } else if (distance <= 200) {
            distanceFee = 35.0;
        } else {
            distanceFee = 80.0;
        }

        boolean isPremium = isPremiumInput.equalsIgnoreCase("так");

        double subtotal = baseTariff + distanceFee;

        double discountRate = isPremium ? 0.20 : 0.0;
        double finalTotal = subtotal * (1.0 - discountRate);

        String deliveryTypeName = switch (deliveryType) {
            case 1 -> "Відділення";
            case 2 -> "Поштомат";
            case 3 -> "Кур'єр";
            default -> "Невідомо";
        };

        System.out.println("\n------------- НАКЛАДНА ДОСТАВКИ -------------");
        System.out.printf("Тип доставки:              %s (базовий тариф: %.2f грн)%n", deliveryTypeName, baseTariff);
        System.out.printf("Доплата за відстань:       %.2f грн (%d км)%n", distanceFee, distance);
        System.out.printf("Сума до знижки:            %.2f грн%n", subtotal);
        System.out.printf("Статус клієнта:            %s%n", isPremium ? "Premium (-20%)" : "Ззвичайний (0%)");
        System.out.println("---------------------------------------------");
        System.out.printf("РАЗОМ ДО СПЛАТИ:           %.2f грн%n", finalTotal);
        System.out.println("=============================================");

        scanner.close();
    }
}