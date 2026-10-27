// Van Quach 1002196157 //

import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class BurgerShopFE_GUI
{
    private static JFrame frame;

    private static JPanel orderPanel;

    private static JPanel actionsPanel;

    private static JPanel displayPanel;

    private static JLabel fundsLabel;

    private static JLabel burgerLabel;

    private static JLabel quantityLabel;

    private static JTextField fundsField;

    private static JTextField quantityField;

    private static JComboBox<String> burgerComboBox;

    private static JButton addFundsButton;

    private static JButton placeOrderButton;

    private static JButton viewMenuButton;

    private static JButton checkBalanceButton;

    private static JButton orderHistoryButton;

    private static JButton clearFieldsButton;

    private static JSlider textSizeSlider;

    private static JLabel textSizeLabel;

    private static JRadioButton plainRadioButton;

    private static JRadioButton boldRadioButton;

    private static ButtonGroup textStyleGroup;

    private static JCheckBox confirmationCheckBox;

    private static JTextArea outputArea;

    private static JScrollPane outputScrollPane;

    private static JLabel statusLabel;

    private static BurgerShop burgerShop;

    private static String[] burgerNames;

    private static String fundsText;

    private static double fundsAmount;

    private static String burgerName;

    private static int burgerQuantity;

    private static String burgerQuantityText;

    public static void main(String[] args)
    {
        createFrame();

        createMenuBar();

        createOrderPanel();

        createActionsPanel();

        createDisplayPanel();

        createTextSizeSlider();

        createTextSizeLabel();

        createFundsComponents();

        createBurgerComponents();

        createQuantityComponents();

        createAddFundsButton();

        addFunds();

        createPlaceOrderButton();

        placeOrder();

        createViewMenuButton();

        viewMenu();

        createCheckBalanceButton();

        checkBalance();

        createOrderHistoryButton();

        orderHistory();

        createClearFieldsButton();

        clearFields();

        createOutputArea();

        createStatusLabel();

        createTextStyleButtons();

        createConfirmationCheckBox();

        updateDisplayFont();

        frame.setVisible(true);
    }

    // Creates the main window for the burger shop. //

    public static void createFrame()
    {
        frame = new JFrame();

        frame.setLayout(null);

        frame.setTitle("Burger Shop");

        frame.setSize(850, 520);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Creates the File and Help menus for managing the application. //

    public static void createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);

        JMenuItem clearFieldsMenuItem = new JMenuItem("Clear Fields");

        fileMenu.add(clearFieldsMenuItem);

        clearFieldsMenuItem.addActionListener(e -> resetFields());

        JMenuItem exitMenuItem = new JMenuItem("Exit");

        fileMenu.add(exitMenuItem);

        exitMenuItem.addActionListener(e -> frame.dispose());

        JMenu helpMenu = new JMenu("Help");

        menuBar.add(helpMenu);

        JMenuItem aboutMenuItem = new JMenuItem("About");

        helpMenu.add(aboutMenuItem);

        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Burger Shop GUI\nCreated by: Van Quach\nHomework 06", "About", JOptionPane.INFORMATION_MESSAGE));
    }

    public static void createOrderPanel()
    {
        orderPanel = new JPanel();

        orderPanel.setLayout(null);

        orderPanel.setBorder(new TitledBorder("Order Information"));

        orderPanel.setBounds(10,10,420,150);

        frame.add(orderPanel);
    }

    // Creates the label and text field for adding funds. //

    public static void createFundsComponents()
    {
        fundsLabel = new JLabel();

        fundsLabel.setText("Funds Amount:");

        fundsLabel.setBounds(20,25,150,30);

        orderPanel.add(fundsLabel);

        fundsField = new JTextField();

        fundsField.setBounds(170,25,240,32);

        orderPanel.add(fundsField);
    }

    // Creates the burger selection using the burger names from BurgerShop. //

    public static void createBurgerComponents()
    {
        burgerLabel = new JLabel();

        burgerLabel.setText("Burger:");

        burgerLabel.setBounds(20,65,150,30);

        orderPanel.add(burgerLabel);

        burgerShop = new BurgerShop();

        burgerNames = burgerShop.getBurgerNames();

        burgerComboBox = new JComboBox<String>(burgerNames);

        burgerComboBox.setBounds(170,65,240,32);

        orderPanel.add(burgerComboBox);
    }

    public static void createQuantityComponents()
    {
        quantityLabel = new JLabel();

        quantityLabel.setText("Quantity:");

        quantityLabel.setBounds(20,105,150,30);

        orderPanel.add(quantityLabel);

        quantityField = new JTextField();

        quantityField.setBounds(170,105,240,32);

        orderPanel.add(quantityField);
    }

    // Organizes the burger shop operation buttons into one panel. //

    public static void createActionsPanel()
    {
        actionsPanel = new JPanel();

        actionsPanel.setLayout(null);

        actionsPanel.setBorder(new TitledBorder("Actions"));

        actionsPanel.setBounds(440,10,390,195);

        frame.add(actionsPanel);
    }

    public static void createAddFundsButton()
    {
        addFundsButton = new JButton();

        addFundsButton.setText("Add Funds");

        addFundsButton.setBounds(20, 25, 150, 45);

        actionsPanel.add(addFundsButton);
    }

    // Reads the entered amount and adds the funds to the account. //

    public static void addFunds()
    {
        addFundsButton.addActionListener(e ->
        {
            fundsText = fundsField.getText();

            fundsAmount = Double.parseDouble(fundsText);

            String message = burgerShop.addFunds(fundsAmount);

            outputArea.setText(message);

            statusLabel.setText("Status: Funds operation completed");

            if (confirmationCheckBox.isSelected())
            {
                JOptionPane.showMessageDialog(frame, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void createPlaceOrderButton()
    {
        placeOrderButton = new JButton();

        placeOrderButton.setText("Place Order");

        placeOrderButton.setBounds(190, 25, 150, 45);

        actionsPanel.add(placeOrderButton);
    }

    // Gets the selected burger and quantity and places the order. //

    public static void placeOrder()
    {
        placeOrderButton.addActionListener(e ->
        {
            burgerName = burgerComboBox.getSelectedItem().toString();

            burgerQuantityText = quantityField.getText();

            burgerQuantity = Integer.parseInt(burgerQuantityText);

            String message = burgerShop.placeOrder(burgerName, burgerQuantity);

            outputArea.setText(message);

            statusLabel.setText("Status: Order processed");

            if (confirmationCheckBox.isSelected())
            {
                JOptionPane.showMessageDialog(frame, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void createViewMenuButton()
    {
        viewMenuButton = new JButton();

        viewMenuButton.setText("View Menu");

        viewMenuButton.setBounds(20, 80, 150, 45);

        actionsPanel.add(viewMenuButton);
    }

    // Displays the burger menu in the output area. //

    public static void viewMenu()
    {
        viewMenuButton.addActionListener(e ->
        {
            outputArea.setText(burgerShop.getMenuText());

            statusLabel.setText("Status: Menu displayed");
        });
    }

    public static void createCheckBalanceButton()
    {
        checkBalanceButton = new JButton();

        checkBalanceButton.setText("Check Balance");

        checkBalanceButton.setBounds(190,80,150,45);

        actionsPanel.add(checkBalanceButton);
    }

    public static void checkBalance()
    {
        checkBalanceButton.addActionListener(e ->
        {
            outputArea.setText(burgerShop.getBalanceText());

            statusLabel.setText("Status: Balance displayed");
        });
    }

    public static void createOrderHistoryButton()
    {
        orderHistoryButton = new JButton();

        orderHistoryButton.setText("Order History");

        orderHistoryButton.setBounds(20, 135, 150, 45);

        actionsPanel.add(orderHistoryButton);
    }

    public static void orderHistory()
    {
        orderHistoryButton.addActionListener(e ->
        {
            outputArea.setText(burgerShop.getOrderHistory());

            statusLabel.setText("Status: Order history displayed");
        });
    }

    public static void createClearFieldsButton()
    {
        clearFieldsButton = new JButton();

        clearFieldsButton.setText("Clear Fields");

        clearFieldsButton.setBounds(190,135,150,45);

        actionsPanel.add(clearFieldsButton);
    }

    public static void clearFields()
    {
        clearFieldsButton.addActionListener(e -> resetFields());
    }

    // Clears user inputs without changing the account balance or order history. //

    public static void resetFields()
    {
        fundsField.setText("");

        quantityField.setText("");

        burgerComboBox.setSelectedIndex(0);

        statusLabel.setText("Status: Fields cleared");
    }

    public static void createDisplayPanel()
    {
        displayPanel = new JPanel();

        displayPanel.setLayout(null);

        displayPanel.setBorder(new TitledBorder("Display Settings"));

        displayPanel.setBounds(10,215,820,110);

        frame.add(displayPanel);
    }

    public static void createTextSizeSlider()
    {
        textSizeSlider = new JSlider();

        textSizeSlider.setMinimum(10);

        textSizeSlider.setMaximum(24);

        textSizeSlider.setValue(14);

        textSizeSlider.setMajorTickSpacing(2);

        textSizeSlider.setPaintTicks(true);

        textSizeSlider.setPaintLabels(true);

        textSizeSlider.setBounds(20,25,350,55);

        displayPanel.add(textSizeSlider);

        textSizeSlider.addChangeListener(e -> updateDisplayFont());
    }

    public static void createTextSizeLabel()
    {
        textSizeLabel = new JLabel();

        textSizeLabel.setText("Text Size: 14");

        textSizeLabel.setBounds(380,35,120,30);

        displayPanel.add(textSizeLabel);
    }

    public static void createTextStyleButtons()
    {
        textStyleGroup = new ButtonGroup();

        plainRadioButton = new JRadioButton();

        plainRadioButton.setText("Plain");

        plainRadioButton.setSelected(true);

        plainRadioButton.setBounds(520,25,80,30);

        displayPanel.add(plainRadioButton);

        textStyleGroup.add(plainRadioButton);

        plainRadioButton.addActionListener(e -> updateDisplayFont());

        boldRadioButton = new JRadioButton();

        boldRadioButton.setText("Bold");

        boldRadioButton.setSelected(false);

        boldRadioButton.setBounds(610,25,80,30);

        displayPanel.add(boldRadioButton);

        textStyleGroup.add(boldRadioButton);

        boldRadioButton.addActionListener(e -> updateDisplayFont());
    }

    public static void createConfirmationCheckBox()
    {
        confirmationCheckBox = new JCheckBox("Show confirmation messages");

        confirmationCheckBox.setSelected(true);

        confirmationCheckBox.setBounds(520, 65, 200, 30);

        displayPanel.add(confirmationCheckBox);
    }

    // Updates the output text size and style based on the user's selections. //

    public static void updateDisplayFont()
    {
        int fontSize;

        int fontStyle;

        fontSize = textSizeSlider.getValue();

        fontStyle = Font.PLAIN;

        if (boldRadioButton.isSelected())
        {
            fontStyle = Font.BOLD;
        }
        else
        {
            fontStyle = Font.PLAIN;
        }

        outputArea.setFont(new Font("SansSerif", fontStyle, fontSize));

        textSizeLabel.setText("Text Size: " + fontSize);
    }

    // Creates a scrollable text area to display burger shop information. //

    public static void createOutputArea()
    {
        outputArea = new JTextArea();

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);

        outputScrollPane = new JScrollPane(outputArea);

        outputScrollPane.setBounds(20, 335, 790, 100);

        frame.add(outputScrollPane);
    }

    public static void createStatusLabel()
    {
        statusLabel = new JLabel("Status: Ready");

        statusLabel.setBounds(20, 445, 790, 25);

        frame.add(statusLabel);
    }
}