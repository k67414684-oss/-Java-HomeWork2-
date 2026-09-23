import java.util.Scanner;

public class Ex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== КАЛЬКУЛЯТОР ПОЇЗДКИ ТА ПАЛЬНОГО ===");

        System.out.print("Введіть відстань поїздки (км): ");
        double distance = scanner.nextDouble();

        System.out.print("Введіть середню витрату на 100 км (л): ");
        double fuelConsumption = scanner.nextDouble();

        if (distance <= 0 || fuelConsumption <= 0) {
            System.out.println("Помилка: відстань та витрата пального повинні бути більше 0!");
            scanner.close();
            return;
        }

        System.out.print("Оберіть тип пального (1 - А-95, 2 - Дизель, 3 - Газ): ");
        int fuelType = scanner.nextInt();

        double pricePerLiter;
        String fuelName;

        switch (fuelType) {
            case 1 -> {
                pricePerLiter = 56.50;
                fuelName = "Бензин А-95";
            }
            case 2 -> {
                pricePerLiter = 52.80;
                fuelName = "Дизель";
            }
            case 3 -> {
                pricePerLiter = 29.40;
                fuelName = "Газ";
            }
            default -> {
                System.out.println("Помилка: обрано невідомий тип пального!");
                scanner.close();
                return;
            }
        }

        double neededFuel = (distance * fuelConsumption) / 100.0;
        double baseCost = neededFuel * pricePerLiter;

        double finalCost = (baseCost >= 2000.0) ? baseCost * 0.95 : baseCost;
        double discountAmount = baseCost - finalCost;

        System.out.println("\n---------------- ЗВІТ ПОЇЗДКИ ----------------");
        System.out.printf("Обране пальне:               %s (%.2f грн/л)%n", fuelName, pricePerLiter);
        System.out.printf("Необхідно пального:          %.2f л%n", neededFuel);
        System.out.printf("Базова вартість:             %.2f грн%n", baseCost);
        System.out.printf("Знижка АЗС (%s):             %.2f грн%n", (baseCost >= 2000.0) ? "5%" : "0%", discountAmount);
        System.out.println("----------------------------------------------");
        System.out.printf("Підсумкова сума до сплати:   %.2f грн%n", finalCost);
        System.out.println("==============================================");

        scanner.close();
    }
}