import java.util.Objects; // Потрібно для узгодженого hashCode.
public final class HyperbolaViaInterface implements FunctionLike { // Ця реалізація не успадковує абстрактний Function.
    private final double a; // Інтерфейс не має полів екземпляра, тому стан зберігає реалізатор.
    private final double b; // Друга піввісь кривої.
    public HyperbolaViaInterface(double a, double b) { // Конструктор класу, який реалізує контракт.
        if (!Double.isFinite(a) || !Double.isFinite(b) || a <= 0 || b <= 0 || a > 1e100 || b > 1e100) throw new IllegalArgumentException("Півосі мають бути в (0; 1e100]."); // Перевіряємо параметри.
        this.a = a; // Ініціалізуємо першу піввісь.
        this.b = b; // Ініціалізуємо другу піввісь.
    }
    @Override public double value(double x) { // Обчислюємо верхню гілку так само, як у задачі 2.
        if (!Double.isFinite(x) || Math.abs(x) < a) throw new IllegalArgumentException("x поза областю визначення."); // Перевіряємо аргумент.
        double result = b * Math.sqrt((Math.abs(x) / a - 1) * (Math.abs(x) / a + 1)); // Безпосередня формула без делегування абстрактному класу.
        if (!Double.isFinite(result)) throw new ArithmeticException("Переповнення."); // Перевіряємо скінченність результату.
        return result; // Виконуємо контракт value.
    }
    @Override public void print(double x) { System.out.println(this + "; x=" + x + "; y=" + value(x)); } // Виконуємо контракт print.
    @Override public String toString() { return "HyperbolaViaInterface(a=" + a + ", b=" + b + ")"; } // Описуємо тип і стан.
    @Override public boolean equals(Object other) { return other instanceof HyperbolaViaInterface f && Double.compare(a, f.a) == 0 && Double.compare(b, f.b) == 0; } // Порівнюємо за значенням.
    @Override public int hashCode() { return Objects.hash(a, b); } // Використовуємо ті самі поля, що й equals.
}
