import java.util.Objects;
// abstract — клас не створюють напряму; його абстрактні методи реалізують підкласи.
public abstract class Function {
    // protected — доступ підкласам і пакету; final — значення присвоюється один раз.
    protected final double a;
    protected final double b;
    protected Function(double a, double b) { // Конструктор базової частини викликається з підкласу через super.
        // Double.isFinite відкидає NaN та ±Infinity; ! заперечує перевірку.
        // throw передає помилку в catch; new створює об’єкт винятку з повідомленням.
        // && — «і», || — «або»; праву умову перевіряють лише за потреби.
        if (!Double.isFinite(a) || !Double.isFinite(b) || a <= 0 || b <= 0 || a > 1e100 || b > 1e100) throw new IllegalArgumentException("Півосі мають бути в (0; 1e100]."); // Перевіряємо допустимі геометричні параметри.
        // this — поточний об’єкт; this.поле відрізняє поле від однойменного параметра.
        this.a = a;
        this.b = b;
    }
    public double getA() { return a; } // Getter потрібен також для сортування в лабораторній 5.
    public double getB() { return b; } // Другий параметр використовується як додаткова ознака порядку.
    // abstract-метод не має тіла; конкретний підклас мусить надати реалізацію.
    public abstract double value(double x); // Кожен підклас зобов'язаний надати власну формулу.
    public abstract void print(double x); // Кожен підклас реалізує спосіб виведення значення.
    // @Override — компілятор перевіряє, що метод перевизначає успадкований або реалізує інтерфейс.
    @Override public abstract String toString(); // Вимагаємо змістовного текстового опису підкласу.
    @Override public abstract boolean equals(Object other); // Підкласи явно визначають рівність параметрів.
    // hashCode узгоджується з equals: рівні об’єкти повинні мати однаковий хеш.
    @Override public int hashCode() { return Objects.hash(getClass(), a, b); } // Різні типи та параметри можуть мати різні хеші; рівні об'єкти мають однакові.
}
