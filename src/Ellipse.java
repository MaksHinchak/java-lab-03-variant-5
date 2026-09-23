// extends — успадкування класу; final, якщо вказано, забороняє подальше успадкування.
// implements — клас реалізує методи зазначеного інтерфейсу.
public final class Ellipse extends Function implements Comparable<Ellipse> {
    // super(...) — виклик конструктора батьківського класу.
    public Ellipse(double a, double b) { super(a, b); }
    // @Override — компілятор перевіряє, що метод перевизначає успадкований або реалізує інтерфейс.
    @Override public double value(double x) {
        // Double.isFinite відкидає NaN та ±Infinity; ! заперечує перевірку.
        if (!Double.isFinite(x) || Math.abs(x) > a) throw new IllegalArgumentException("x поза областю визначення Ellipse.");
        double result = b * Math.sqrt(Math.max(0, 1 - (x / a) * (x / a)));
        if (!Double.isFinite(result)) throw new ArithmeticException("Переповнення обчислення функції.");
        return result;
    }
    @Override public void print(double x) { System.out.println(this + "; x=" + x + "; y=" + value(x)); }
    @Override public String toString() { return "Ellipse(a=" + a + ", b=" + b + ")"; }
    // instanceof Тип змінна — перевірка типу й отримання типізованого посилання; null дає false.
    // compare повертає знак порядку: від’ємне / 0 / додатне; його використовує сортування.
    @Override public boolean equals(Object other) { return other instanceof Ellipse f && Double.compare(a, f.a) == 0 && Double.compare(b, f.b) == 0; }
    @Override public int compareTo(Ellipse other) {
        int first = Double.compare(a, other.a);
        // умова ? a : b — вибір значення: a, якщо true, інакше b.
        return first != 0 ? first : Double.compare(b, other.b);
    }
}
