/* Justin Pullen
 * Customers 2 - Nested If/else, Compound 9-24
 * This program is for taking in user data pertaining to credit cards. The user enters their account number, beginning
 * monthly balance, new monthly charges, total already paid, and their credit limit. After we have that information
 * we first calculate how to determine owed balance, how much over limit is, and then how to determine avail credit and
 * store it for use. After, we print out users account number and their owed balance. If what they owe is over their
 * limit, we print a message letting user know and by how much, if owed is lower than limit, let them know avail credit.
 * Finally, if it isn't over or under, then it must be equal, so let user know they are at their limit and show a warning.
*/

import javax.swing.*;
import java.awt.*;

public class Customers2 {
    private JFrame frame; // JFrame for the main window
    private JTextField[] textFields; // Array to store text fields for user input
    private JTextArea resultArea; // Text area for displaying the result

    public Customers2() {
        initialize(); // Initialize the GUI components
    }

    private void initialize() {
        frame = new JFrame("Credit Card Calculator"); // Create the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout for layout
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout control
        gbc.insets = new Insets(4, 4, 4, 4); // Padding between components
        int rowIndex = 0; // Current row index for grid layout

        textFields = new JTextField[5]; // Initialize an array to store text fields

        // Labels for user input fields
        String[] labels = {"Account Number:", "Beginning Balance:", "New Charges:", "Total Paid:", "Credit Limit:"};

        // Create and add labels and text fields for user input
        for (int i = 0; i < labels.length; i++) {
            textFields[i] = addLabelAndTextField(labels[i], rowIndex++); // Add label and text field
        }

        // Create and add a "Calculate" button with an ActionListener
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateResult()); // Define the action when the button is clicked
        gbc.gridx = 1; // Set grid X position
        gbc.gridy = rowIndex++; // Increase row index
        frame.add(calculateButton, gbc); // Add the button to the frame

        // Create and add a text area for displaying the result (with scroll pane)
        resultArea = new JTextArea(5, 30); // Rows and columns for text area
        resultArea.setEditable(false); // Make it non-editable
        gbc.gridx = 0; // Reset grid X position
        gbc.gridy = rowIndex; // Set the grid row
        gbc.gridwidth = 2; // Span across two columns
        frame.add(new JScrollPane(resultArea), gbc); // Add the scrollable text area to the frame

        frame.pack(); // Pack the components for optimal size
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true); // Make the frame visible
    }

    // Method to add a label and text field to the frame
    private JTextField addLabelAndTextField(String label, int row) {
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout control
        gbc.insets = new Insets(4, 4, 4, 4); // Padding between components
        gbc.anchor = GridBagConstraints.EAST; // Align components to the right
        gbc.gridx = 0; // Label in column 0
        gbc.gridy = row; // Specified row for the label

        frame.add(new JLabel(label), gbc); // Create and add a label

        JTextField textField = new JTextField(10); // Create a text field with columns
        gbc.gridx = 1; // Text field in column 1
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal expansion
        frame.add(textField, gbc); // Add the text field

        return textField; // Return the created text field
    }

    // Method to calculate and display the result
    private void calculateResult() {
        try {
            // Parse input values from text fields
            int accNum = Integer.parseInt(textFields[0].getText());
            double beginBal = Double.parseDouble(textFields[1].getText());
            double totalNew = Double.parseDouble(textFields[2].getText());
            double totalPaid = Double.parseDouble(textFields[3].getText());
            double creditLimit = Double.parseDouble(textFields[4].getText());

            // Calculate various values
            double owedBal = beginBal + totalNew - totalPaid;
            double overLimitBy = owedBal - creditLimit;
            double availCredit = creditLimit - owedBal;

            // Prepare the result message
            StringBuilder result = new StringBuilder();
            result.append("Account Number: ").append(accNum).append("\n");
            result.append(String.format("Ending Balance: $%.2f \n", owedBal));

            if (owedBal > creditLimit) {
                result.append(String.format("Credit Limit Exceeded by $%.2f", overLimitBy));
            } else if (owedBal < creditLimit) {
                result.append(String.format("Available Credit: $%.2f", availCredit));
            } else {
                result.append("Warning: You are at your credit limit.");
            }

            resultArea.setText(result.toString()); // Display the result in the text area
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter correct values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Customers2(); // Create an instance of the Customers2 class
    }
}
