// extends — успадкування класу; final, якщо вказано, забороняє подальше успадкування.
// implements — клас реалізує методи зазначеного інтерфейсу.
public final class Ellipse extends Function implements Comparable<Ellipse> {
    // super(...) — виклик конструктора батьківського класу.
    public Ellipse(double a, double b) { super(a, b); } // Передаємо перевірку півосей базовому конструктору.
    // @Override — компілятор перевіряє, що метод перевизначає успадкований або реалізує інтерфейс.
    @Override public double value(double x) { // Для однозначності обираємо верхню гілку y >= 0 рівняння x^2/a^2 + y^2/b^2 = 1.
        // Double.isFinite відкидає NaN та ±Infinity; ! заперечує перевірку.
        // throw передає помилку в catch; new створює об’єкт винятку з повідомленням.
        // && — «і», || — «або»; праву умову перевіряють лише за потреби.
        if (!Double.isFinite(x) || Math.abs(x) > a) throw new IllegalArgumentException("x поза областю визначення Ellipse."); // Не допускаємо від'ємного підкореневого виразу.
        double result = b * Math.sqrt(Math.max(0, 1 - (x / a) * (x / a)));
        if (!Double.isFinite(result)) throw new ArithmeticException("Переповнення обчислення функції."); // Повідомляємо про вихід за можливості double.
        return result;
    }
    @Override public void print(double x) { System.out.println(this + "; x=" + x + "; y=" + value(x)); } // Друкуємо параметри, аргумент і результат.
    @Override public String toString() { return "Ellipse(a=" + a + ", b=" + b + ")"; } // Повертаємо опис саме цього типу кривої.
    // instanceof Тип змінна — перевірка типу й отримання типізованого посилання; null дає false.
    // compare повертає знак порядку: від’ємне / 0 / додатне; його використовує сортування.
    @Override public boolean equals(Object other) { return other instanceof Ellipse f && Double.compare(a, f.a) == 0 && Double.compare(b, f.b) == 0; } // Рівність вимагає однакового типу та двох параметрів.
    @Override public int compareTo(Ellipse other) { // Природний порядок спочатку за a, потім за b.
        int first = Double.compare(a, other.a);
        // умова ? a : b — вибір значення: a, якщо true, інакше b.
        return first != 0 ? first : Double.compare(b, other.b);
    }
}
