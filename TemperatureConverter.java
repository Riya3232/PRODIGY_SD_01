import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame implements ActionListener {

    // GUI Components
    private JTextField inputField;
    private JComboBox<String> unitBox;
    private JTextArea resultArea;

    // Constructor for GUI
    public TemperatureConverter() {
        setTitle("Temperature Conversion Program");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter Temperature: ");
        inputField = new JTextField(10);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitBox = new JComboBox<>(units);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);

        add(label);
        add(inputField);
        add(unitBox);
        add(convertButton);
        add(new JScrollPane(resultArea));
    }

    // Conversion Logic for GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double value = Double.parseDouble(inputField.getText());
            String unit = (String) unitBox.getSelectedItem();

            if (unit.equals("Celsius")) {
                double f = (value * 9 / 5) + 32;
                double k = value + 273.15;
                resultArea.setText(value + "°C = " + f + "°F\n" + value + "°C = " + k + "K");
            } else if (unit.equals("Fahrenheit")) {
                double c = (value - 32) * 5 / 9;
                double k = c + 273.15;
                resultArea.setText(value + "°F = " + c + "°C\n" + value + "°F = " + k + "K");
            } else if (unit.equals("Kelvin")) {
                double c = value - 273.15;
                double f = (c * 9 / 5) + 32;
                resultArea.setText(value + "K = " + c + "°C\n" + value + "K = " + f + "°F");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input! Please enter a number.");
        }
    }

    // Console-based version
    public static void runConsoleVersion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Temperature Conversion Program (Console Mode) ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Convert from Celsius");
            System.out.println("2. Convert from Fahrenheit");
            System.out.println("3. Convert from Kelvin");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            if (choice == 4) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            System.out.print("Enter temperature value: ");
            double value = scanner.nextDouble();

            switch (choice) {
                case 1: // Celsius
                    double f1 = (value * 9 / 5) + 32;
                    double k1 = value + 273.15;
                    System.out.printf("%.2f°C = %.2f°F, %.2fK%n", value, f1, k1);
                    break;

                case 2: // Fahrenheit
                    double c2 = (value - 32) * 5 / 9;
                    double k2 = c2 + 273.15;
                    System.out.printf("%.2f°F = %.2f°C, %.2fK%n", value, c2, k2);
                    break;

                case 3: // Kelvin
                    double c3 = value - 273.15;
                    double f3 = (c3 * 9 / 5) + 32;
                    System.out.printf("%.2fK = %.2f°C, %.2f°F%n", value, c3, f3);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    // Main method to choose between Console and GUI
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Temperature Conversion Program ===");
        System.out.println("Choose mode:");
        System.out.println("1. Console Mode");
        System.out.println("2. GUI Mode");
        System.out.print("Enter choice: ");
        int mode = scanner.nextInt();

        if (mode == 1) {
            runConsoleVersion();
        } else if (mode == 2) {
            SwingUtilities.invokeLater(() -> {
                new TemperatureConverter().setVisible(true);
            });
        } else {
            System.out.println("Invalid choice! Exiting program.");
        }

        scanner.close();
    }
}
