public class BurgerShop
{
    private Burger[] menu;

    private double balance;

    private String orderHistory;

    public BurgerShop()
    {
        menu = new Burger[4];

        menu[0] = new Burger("Classic Burger", 6.50, 620);

        menu[1] = new Burger("Cheeseburger", 7.25, 710);

        menu[2] = new Burger("Bacon Burger", 8.50, 790);

        menu[3] = new Burger("Veggie Burger", 7.00, 540);

        balance = 0.0;

        orderHistory = "";
    }

    public String[] getBurgerNames()
    {
        String[] names = new String[menu.length];

        for (int i = 0; i < menu.length; i++)
        {
            names[i] = menu[i].getName();
        }

        return names;
    }

    public String getMenuText()
    {
        String result = "----- Burger Menu -----\n";

        for (Burger burger : menu)
        {
            result += burger.toString() + "\n";
        }

        return result;
    }

    public String addFunds(double amount)
    {
        if (amount <= 0)
        {
            return "The amount must be greater than $0.00.";
        }

        balance += amount;

        return "Funds added: $" + String.format("%.2f", amount) + "\nCurrent balance: $" + String.format("%.2f", balance);
    }

    public String placeOrder(String burgerName, int quantity)
    {
        Burger selectedBurger = findBurger(burgerName);

        if (selectedBurger == null)
        {
            return "Burger not found.";
        }

        if (quantity < 1)
        {
            return "Quantity must be at least 1.";
        }

        double total = selectedBurger.getPrice() * quantity;

        if (total > balance)
        {
            return "Insufficient funds.\nOrder total: $" + String.format("%.2f", total) + "\nCurrent balance: $" + String.format("%.2f", balance);
        }

        balance -= total;

        String order = quantity + " x " + selectedBurger.getName() + " - $" + String.format("%.2f", total);

        if (!orderHistory.isEmpty())
        {
            orderHistory += "\n";
        }

        orderHistory += order;

        return "Order successful!\n" + order + "\nRemaining balance: $" + String.format("%.2f", balance);
    }

    public String getBalanceText()
    {
        return "Current balance: $" + String.format("%.2f", balance);
    }

    public String getOrderHistory()
    {
        if (orderHistory.isEmpty())
        {
            return "No orders have been placed yet.";
        }

        return "----- Order History -----\n" + orderHistory;
    }

    private Burger findBurger(String burgerName)
    {
        for (Burger burger : menu)
        {
            if (burger.getName().equals(burgerName))
            {
                return burger;
            }
        }

        return null;
    }
}