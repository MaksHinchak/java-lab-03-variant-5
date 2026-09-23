// extends — успадкування класу; final, якщо вказано, забороняє подальше успадкування.
// implements — клас реалізує методи зазначеного інтерфейсу.
public final class Hyperbola extends Function implements Comparable<Hyperbola> {
    // super(...) — виклик конструктора батьківського класу.
    public Hyperbola(double a, double b) { super(a, b); }
    // @Override — компілятор перевіряє, що метод перевизначає успадкований або реалізує інтерфейс.
    @Override public double value(double x) {
        // Double.isFinite відкидає NaN та ±Infinity; ! заперечує перевірку.
        if (!Double.isFinite(x) || Math.abs(x) < a) throw new IllegalArgumentException("x поза областю визначення Hyperbola.");
        double result = b * Math.sqrt((Math.abs(x) / a - 1) * (Math.abs(x) / a + 1));
        if (!Double.isFinite(result)) throw new ArithmeticException("Переповнення обчислення функції.");
        return result;
    }
    @Override public void print(double x) { System.out.println(this + "; x=" + x + "; y=" + value(x)); }
    @Override public String toString() { return "Hyperbola(a=" + a + ", b=" + b + ")"; }
    // instanceof Тип змінна — перевірка типу й отримання типізованого посилання; null дає false.
    // compare повертає знак порядку: від’ємне / 0 / додатне; його використовує сортування.
    @Override public boolean equals(Object other) { return other instanceof Hyperbola f && Double.compare(a, f.a) == 0 && Double.compare(b, f.b) == 0; }
    @Override public int compareTo(Hyperbola other) {
        int first = Double.compare(a, other.a);
        // умова ? a : b — вибір значення: a, якщо true, інакше b.
        return first != 0 ? first : Double.compare(b, other.b);
    }
}
