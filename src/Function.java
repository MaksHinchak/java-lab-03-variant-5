import java.util.Objects;
// abstract — клас не створюють напряму; його абстрактні методи реалізують підкласи.
public abstract class Function {
    // protected — доступ підкласам і пакету; final — значення присвоюється один раз.
    protected final double a;
    protected final double b;
    protected Function(double a, double b) {
        // Double.isFinite відкидає NaN та ±Infinity; ! заперечує перевірку.
        if (!Double.isFinite(a) || !Double.isFinite(b) || a <= 0 || b <= 0 || a > 1e100 || b > 1e100) throw new IllegalArgumentException("Півосі мають бути в (0; 1e100].");
        // this — поточний об’єкт; this.поле відрізняє поле від однойменного параметра.
        this.a = a;
        this.b = b;
    }
    public double getA() { return a; }
    public double getB() { return b; }
    // abstract-метод не має тіла; конкретний підклас мусить надати реалізацію.
    public abstract double value(double x);
    public abstract void print(double x);
    // @Override — компілятор перевіряє, що метод перевизначає успадкований або реалізує інтерфейс.
    @Override public abstract String toString();
    @Override public abstract boolean equals(Object other);
    // hashCode узгоджується з equals: рівні об’єкти повинні мати однаковий хеш.
    @Override public int hashCode() { return Objects.hash(getClass(), a, b); }
}
