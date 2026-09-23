public class Main {
    // static — виклик без об’єкта; public — доступ ззовні, private — лише в класі; void — без результату.
    // main — точка входу; String[] args містить аргументи запуску без назви програми.
    public static void main(String[] args) { // Точка входу консольної програми.
        try { // Обробка некоректних параметрів об'єктів.
            int task = Input.integer("Завдання (1 - організації, 2 - абстрактний клас, 3 - інтерфейс): ", 1, 3);
            if (task == 1) { // Будуємо масив типу суперкласу з об'єктами різних підкласів.
                // new тип[n] — масив незмінної довжини; числові елементи спочатку 0, посилання — null.
                Organization[] list = new Organization[3];
                String[] labels = {"Страхова компанія", "Нафтогазова компанія", "Завод"};
                // .length — довжина масиву без дужок; для String — length(), для колекції — size().
                // for (початок; умова; крок); i++ збільшує лічильник після проходу.
                for (int i = 0; i < list.length; i++) { // Читаємо дані кожної організації з клавіатури.
                    System.out.println(labels[i]);
                    String name = Input.text("Назва: ");
                    String address = Input.text("Адреса: ");
                    int count = Input.integer("Кількість полісів / свердловин / працівників: ", 0, 1000000);
                    // switch зі стрілками -> виконує лише вибрану гілку; break не потрібен.
                    // switch-вираз повертає значення вибраної гілки для присвоєння.
                    list[i] = switch (i) {
                        // new Клас(...) створює об’єкт і викликає його конструктор.
                        case 0 -> new InsuranceCompany(name, address, count); // Перший елемент є страховою компанією.
                        case 1 -> new OilGasCompany(name, address, count); // Другий є нафтогазовою компанією.
                        default -> new Factory(name, address, count); // Третій є заводом.
                    };
                }
                // for (Тип елемент : колекція) — перебір елементів без індексу.
                for (Organization item : list) item.Show(); // Поліморфізм вибирає Show фактичного типу об'єкта.
            } else { // Обидві реалізації кривих мають однаковий сценарій вводу.
                double a = Input.real("Піввісь a > 0: ");
                double b = Input.real("Піввісь b > 0: ");
                double x = Input.real("Аргумент x: ");
                if (task == 2) { // Демонструємо абстрактний базовий клас.
                    Function[] list = {new Ellipse(a, b), new Hyperbola(a, b)};
                    for (Function f : list) { // Працюємо через загальний контракт Function.
                        // catch (Тип e) перехоплює виняток із try; e.getMessage() повертає його повідомлення.
                        try { f.print(x); } catch (IllegalArgumentException e) { System.out.println(f + ": " + e.getMessage()); } // Помилка однієї кривої не заважає другій.
                        // умова ? a : b — вибір значення: a, якщо true, інакше b.
                        Function copy = f instanceof Ellipse ? new Ellipse(a, b) : new Hyperbola(a, b);
                        // equals порівнює вміст; == для об’єктів Java перевіряє тотожність посилань.
                        System.out.println("a=" + f.getA() + "; b=" + f.getB() + "; equals=" + f.equals(copy) + "; hash=" + f.hashCode());
                    }
                    System.out.println("Порівняння еліпсів: " + new Ellipse(a, b).compareTo(new Ellipse(a, b)));
                    System.out.println("Порівняння гіпербол: " + new Hyperbola(a, b).compareTo(new Hyperbola(a, b)));
                } else { // Демонструємо окремі реалізації через інтерфейс.
                    FunctionLike[] list = {new EllipseViaInterface(a, b), new HyperbolaViaInterface(a, b)};
                    for (FunctionLike f : list) { // Динамічний виклик відбувається так само, як з абстрактним класом.
                        try { f.print(x); } catch (IllegalArgumentException e) { System.out.println(f + ": " + e.getMessage()); } // Повідомляємо про вихід за область визначення.
                        FunctionLike copy = f instanceof EllipseViaInterface ? new EllipseViaInterface(a, b) : new HyperbolaViaInterface(a, b);
                        System.out.println("equals=" + f.equals(copy) + "; hash=" + f.hashCode());
                    }
                }
            }
        } catch (RuntimeException e) { System.out.println("Помилка: " + e.getMessage()); } // Верхній рівень обробляє неправильні півосі й завершення вводу.
    }
}
