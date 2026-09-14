public final class Ellipse extends Function implements Comparable<Ellipse> { // Конкретна крива та природний порядок для колекцій.
    public Ellipse(double a, double b) { super(a, b); } // Передаємо перевірку півосей базовому конструктору.
    @Override public double value(double x) { // Для однозначності обираємо верхню гілку y >= 0 рівняння x^2/a^2 + y^2/b^2 = 1.
        if (!Double.isFinite(x) || Math.abs(x) > a) throw new IllegalArgumentException("x поза областю визначення Ellipse."); // Не допускаємо від'ємного підкореневого виразу.
        double result = b * Math.sqrt(Math.max(0, 1 - (x / a) * (x / a))); // Виражаємо додатне y через задане x.
        if (!Double.isFinite(result)) throw new ArithmeticException("Переповнення обчислення функції."); // Повідомляємо про вихід за можливості double.
        return result; // Повертаємо значення верхньої гілки.
    }
    @Override public void print(double x) { System.out.println(this + "; x=" + x + "; y=" + value(x)); } // Друкуємо параметри, аргумент і результат.
    @Override public String toString() { return "Ellipse(a=" + a + ", b=" + b + ")"; } // Повертаємо опис саме цього типу кривої.
    @Override public boolean equals(Object other) { return other instanceof Ellipse f && Double.compare(a, f.a) == 0 && Double.compare(b, f.b) == 0; } // Рівність вимагає однакового типу та двох параметрів.
    @Override public int compareTo(Ellipse other) { // Природний порядок спочатку за a, потім за b.
        int first = Double.compare(a, other.a); // Порівнюємо головну ознаку сортування.
        return first != 0 ? first : Double.compare(b, other.b); // При однаковому a використовуємо b.
    }
}
