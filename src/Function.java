import java.util.Objects; // Допоміжний метод формує хеш-код за класом і параметрами.
public abstract class Function { // Абстрактна функція не створюється без конкретного підкласу.
    protected final double a; // Додатний масштаб уздовж осі x доступний підкласам.
    protected final double b; // Додатний масштаб уздовж осі y.
    protected Function(double a, double b) { // Конструктор базової частини викликається з підкласу через super.
        if (!Double.isFinite(a) || !Double.isFinite(b) || a <= 0 || b <= 0 || a > 1e100 || b > 1e100) throw new IllegalArgumentException("Півосі мають бути в (0; 1e100]."); // Перевіряємо допустимі геометричні параметри.
        this.a = a; // Зберігаємо масштаб x.
        this.b = b; // Зберігаємо масштаб y.
    }
    public double getA() { return a; } // Getter потрібен також для сортування в лабораторній 5.
    public double getB() { return b; } // Другий параметр використовується як додаткова ознака порядку.
    public abstract double value(double x); // Кожен підклас зобов'язаний надати власну формулу.
    public abstract void print(double x); // Кожен підклас реалізує спосіб виведення значення.
    @Override public abstract String toString(); // Вимагаємо змістовного текстового опису підкласу.
    @Override public abstract boolean equals(Object other); // Підкласи явно визначають рівність параметрів.
    @Override public int hashCode() { return Objects.hash(getClass(), a, b); } // Різні типи та параметри можуть мати різні хеші; рівні об'єкти мають однакові.
}
