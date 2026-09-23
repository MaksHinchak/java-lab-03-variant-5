// extends — успадкування класу; final, якщо вказано, забороняє подальше успадкування.
// implements — клас реалізує методи зазначеного інтерфейсу.
public final class Hyperbola extends Function implements Comparable<Hyperbola> {
    // super(...) — виклик конструктора батьківського класу.
    public Hyperbola(double a, double b) { super(a, b); } // Передаємо перевірку півосей базовому конструктору.
    // @Override — компілятор перевіряє, що метод перевизначає успадкований або реалізує інтерфейс.
    @Override public double value(double x) { // Для однозначності обираємо верхню гілку y >= 0 рівняння x^2/a^2 - y^2/b^2 = 1.
        // Double.isFinite відкидає NaN та ±Infinity; ! заперечує перевірку.
        // throw передає помилку в catch; new створює об’єкт винятку з повідомленням.
        // && — «і», || — «або»; праву умову перевіряють лише за потреби.
        if (!Double.isFinite(x) || Math.abs(x) < a) throw new IllegalArgumentException("x поза областю визначення Hyperbola."); // Не допускаємо від'ємного підкореневого виразу.
        double result = b * Math.sqrt((Math.abs(x) / a - 1) * (Math.abs(x) / a + 1));
        if (!Double.isFinite(result)) throw new ArithmeticException("Переповнення обчислення функції."); // Повідомляємо про вихід за можливості double.
        return result;
    }
    @Override public void print(double x) { System.out.println(this + "; x=" + x + "; y=" + value(x)); } // Друкуємо параметри, аргумент і результат.
    @Override public String toString() { return "Hyperbola(a=" + a + ", b=" + b + ")"; } // Повертаємо опис саме цього типу кривої.
    // instanceof Тип змінна — перевірка типу й отримання типізованого посилання; null дає false.
    // compare повертає знак порядку: від’ємне / 0 / додатне; його використовує сортування.
    @Override public boolean equals(Object other) { return other instanceof Hyperbola f && Double.compare(a, f.a) == 0 && Double.compare(b, f.b) == 0; } // Рівність вимагає однакового типу та двох параметрів.
    @Override public int compareTo(Hyperbola other) { // Природний порядок спочатку за a, потім за b.
        int first = Double.compare(a, other.a);
        // умова ? a : b — вибір значення: a, якщо true, інакше b.
        return first != 0 ? first : Double.compare(b, other.b);
    }
}
