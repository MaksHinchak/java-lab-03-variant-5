public class Main { // Три задачі: ієрархія організацій, абстрактні функції та інтерфейс.
    public static void main(String[] args) { // Точка входу консольної програми.
        try { // Обробка некоректних параметрів об'єктів.
            int task = Input.integer("Завдання (1 - організації, 2 - абстрактний клас, 3 - інтерфейс): ", 1, 3); // Вибір демонстрації.
            if (task == 1) { // Будуємо масив типу суперкласу з об'єктами різних підкласів.
                Organization[] list = new Organization[3]; // Місткість для страхової, нафтогазової компанії та заводу.
                String[] labels = {"Страхова компанія", "Нафтогазова компанія", "Завод"}; // Підказки до введення кожного типу.
                for (int i = 0; i < list.length; i++) { // Читаємо дані кожної організації з клавіатури.
                    System.out.println(labels[i]); // Повідомляємо, який об'єкт зараз вводиться.
                    String name = Input.text("Назва: "); // Спільна властивість.
                    String address = Input.text("Адреса: "); // Друга спільна властивість.
                    int count = Input.integer("Кількість полісів / свердловин / працівників: ", 0, 1000000); // Власне поле поточного типу.
                    list[i] = switch (i) { // Створюємо конкретний тип, зберігаючи посилання як Organization.
                        case 0 -> new InsuranceCompany(name, address, count); // Перший елемент є страховою компанією.
                        case 1 -> new OilGasCompany(name, address, count); // Другий є нафтогазовою компанією.
                        default -> new Factory(name, address, count); // Третій є заводом.
                    };
                }
                for (Organization item : list) item.Show(); // Поліморфізм вибирає Show фактичного типу об'єкта.
            } else { // Обидві реалізації кривих мають однаковий сценарій вводу.
                double a = Input.real("Піввісь a > 0: "); // Масштаб уздовж осі x.
                double b = Input.real("Піввісь b > 0: "); // Масштаб уздовж осі y.
                double x = Input.real("Аргумент x: "); // Точка, у якій обчислюється функція.
                if (task == 2) { // Демонструємо абстрактний базовий клас.
                    Function[] list = {new Ellipse(a, b), new Hyperbola(a, b)}; // Масив базового типу містить різні реалізації.
                    for (Function f : list) { // Працюємо через загальний контракт Function.
                        try { f.print(x); } catch (IllegalArgumentException e) { System.out.println(f + ": " + e.getMessage()); } // Помилка однієї кривої не заважає другій.
                        Function copy = f instanceof Ellipse ? new Ellipse(a, b) : new Hyperbola(a, b); // Створюємо рівний за параметрами об'єкт того самого типу.
                        System.out.println("a=" + f.getA() + "; b=" + f.getB() + "; equals=" + f.equals(copy) + "; hash=" + f.hashCode()); // Показуємо стан, рівність та хеш.
                    }
                    System.out.println("Порівняння еліпсів: " + new Ellipse(a, b).compareTo(new Ellipse(a, b))); // Однакові параметри дають нуль у природному порядку.
                    System.out.println("Порівняння гіпербол: " + new Hyperbola(a, b).compareTo(new Hyperbola(a, b))); // Демонструємо Comparable другого підкласу.
                } else { // Демонструємо окремі реалізації через інтерфейс.
                    FunctionLike[] list = {new EllipseViaInterface(a, b), new HyperbolaViaInterface(a, b)}; // Масив посилань на інтерфейс.
                    for (FunctionLike f : list) { // Динамічний виклик відбувається так само, як з абстрактним класом.
                        try { f.print(x); } catch (IllegalArgumentException e) { System.out.println(f + ": " + e.getMessage()); } // Повідомляємо про вихід за область визначення.
                        FunctionLike copy = f instanceof EllipseViaInterface ? new EllipseViaInterface(a, b) : new HyperbolaViaInterface(a, b); // Створюємо копію відповідного типу.
                        System.out.println("equals=" + f.equals(copy) + "; hash=" + f.hashCode()); // Перевіряємо методи Object у реалізаторів інтерфейсу.
                    }
                }
            }
        } catch (RuntimeException e) { System.out.println("Помилка: " + e.getMessage()); } // Верхній рівень обробляє неправильні півосі й завершення вводу.
    }
}
