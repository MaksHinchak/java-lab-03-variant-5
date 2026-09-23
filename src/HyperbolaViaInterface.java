import java.util.Objects;
// final class — від цього класу не можна успадковуватися.
// implements — клас реалізує методи зазначеного інтерфейсу.
public final class HyperbolaViaInterface implements FunctionLike {
    // private — поле закрите ззовні; final забороняє переприсвоєння, але не зміну вмісту об’єкта.
    private final double a;
    private final double b;
    public HyperbolaViaInterface(double a, double b) { // Конструктор класу, який реалізує контракт.
        // Double.isFinite відкидає NaN та ±Infinity; ! заперечує перевірку.
        // throw передає помилку в catch; new створює об’єкт винятку з повідомленням.
        // && — «і», || — «або»; праву умову перевіряють лише за потреби.
        if (!Double.isFinite(a) || !Double.isFinite(b) || a <= 0 || b <= 0 || a > 1e100 || b > 1e100) throw new IllegalArgumentException("Півосі мають бути в (0; 1e100]."); // Перевіряємо параметри.
        // this — поточний об’єкт; this.поле відрізняє поле від однойменного параметра.
        this.a = a;
        this.b = b;
    }
    // @Override — компілятор перевіряє, що метод перевизначає успадкований або реалізує інтерфейс.
    @Override public double value(double x) { // Обчислюємо верхню гілку так само, як у задачі 2.
        if (!Double.isFinite(x) || Math.abs(x) < a) throw new IllegalArgumentException("x поза областю визначення."); // Перевіряємо аргумент.
        double result = b * Math.sqrt((Math.abs(x) / a - 1) * (Math.abs(x) / a + 1));
        if (!Double.isFinite(result)) throw new ArithmeticException("Переповнення."); // Перевіряємо скінченність результату.
        return result;
    }
    @Override public void print(double x) { System.out.println(this + "; x=" + x + "; y=" + value(x)); } // Виконуємо контракт print.
    @Override public String toString() { return "HyperbolaViaInterface(a=" + a + ", b=" + b + ")"; } // Описуємо тип і стан.
    // instanceof Тип змінна — перевірка типу й отримання типізованого посилання; null дає false.
    // compare повертає знак порядку: від’ємне / 0 / додатне; його використовує сортування.
    @Override public boolean equals(Object other) { return other instanceof HyperbolaViaInterface f && Double.compare(a, f.a) == 0 && Double.compare(b, f.b) == 0; } // Порівнюємо за значенням.
    // hashCode узгоджується з equals: рівні об’єкти повинні мати однаковий хеш.
    @Override public int hashCode() { return Objects.hash(a, b); } // Використовуємо ті самі поля, що й equals.
}
