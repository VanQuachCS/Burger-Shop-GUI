public class Burger
{
    private String name;

    private double price;

    private int calories;

    public Burger(String name, double price, int calories)
    {
        this.name = name;

        this.price = price;

        this.calories = calories;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public int getCalories()
    {
        return calories;
    }

    @Override

    public String toString()
    {
        return name + " - $" + String.format("%.2f", price) + " - " + calories + " calories";
    }
}