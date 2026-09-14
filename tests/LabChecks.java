public class LabChecks { // Автономні перевірки без зовнішніх бібліотек тестування.
    private static int count = 0; // Лічильник успішно перевірених умов.
    private static void check(boolean value) { // Допоміжний метод перетворює хибну умову на провал тесту.
        if (!value) throw new AssertionError("Перевірка " + (count + 1) + " не пройшла."); // Зупиняємо тест із ненульовим кодом процесу.
        count++; // Рахуємо лише успішні перевірки.
    }
    private static void near(double actual, double expected) { // Порівнюємо double з допуском на округлення.
        check(Math.abs(actual - expected) <= 1e-9 * Math.max(1, Math.abs(expected))); // Допуск враховує масштаб очікуваного числа.
    }
    private interface Action { void run() throws Exception; } // Лямбда тесту може породжувати і перевірювані винятки.
    private static void expect(Class<? extends Throwable> type, Action action) { // Перевіряємо, що помилкові дані дають саме потрібний вид помилки.
        try { action.run(); } // Виконуємо потенційно помилкову операцію.
        catch (Throwable error) { check(type.isInstance(error)); return; } // Неправильний тип винятку теж провалює тест.
        throw new AssertionError("Очікували " + type.getSimpleName()); // Відсутність потрібного винятку є помилкою реалізації.
    }
    public static void main(String[] args) throws Exception { // Метод запускає усі перевірки цієї лабораторної.
        Function ellipse = new Ellipse(5,3); // Еліпс із півосями п'ять і три.
        near(ellipse.value(0), 3); near(ellipse.value(5), 0); // Перевіряємо центр верхньої дуги та край області визначення.
        expect(IllegalArgumentException.class, () -> ellipse.value(6)); // Аргумент поза еліпсом має бути відхилений.
        Function hyperbola = new Hyperbola(3,2); // Гіпербола для перевірки іншої формули.
        near(hyperbola.value(3), 0); near(hyperbola.value(-5), 8.0/3); // Перевіряємо вершину та від'ємний аргумент.
        expect(IllegalArgumentException.class, () -> hyperbola.value(0)); // Центр не належить області визначення цієї гілки.
        check(ellipse.equals(new Ellipse(5,3)) && !ellipse.equals(new Hyperbola(5,3))); // Однакові параметри різних кривих не означають рівність.
        check(ellipse.hashCode() == new Ellipse(5,3).hashCode()); // Контракт хешування рівних об'єктів.
        near(new EllipseViaInterface(5,3).value(4), 1.8); // Самостійна реалізація інтерфейсу дає правильний результат.
        near(new HyperbolaViaInterface(3,2).value(5), 8.0/3); // Перевіряємо інший реалізатор інтерфейсу.
        check(new Factory("A","B",2).toString().contains("працівників: 2")); // Похідний опис має містити власне поле.
        expect(IllegalArgumentException.class, () -> new InsuranceCompany("A","B",-1)); // Перевіряємо інваріант спеціального поля.
        System.out.println("OK: " + count + " перевірок"); // Видимий підсумок після успішного виконання.
    }
}
