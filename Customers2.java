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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customers2 {
    private JFrame frame;
    private JTextField accNumField, beginBalField, totalNewField, totalPaidField, creditLimitField;
    private JTextArea resultArea;

    public Customers2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Credit Card Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Setting common constraints for labels and fields
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.EAST;
        int rowIndex = 0;

        accNumField = addLabelAndTextField("Account Number:", rowIndex++, 10);
        beginBalField = addLabelAndTextField("Beginning Balance:", rowIndex++, 10);
        totalNewField = addLabelAndTextField("New Charges:", rowIndex++, 10);
        totalPaidField = addLabelAndTextField("Total Paid:", rowIndex++, 10);
        creditLimitField = addLabelAndTextField("Credit Limit:", rowIndex++, 10);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = rowIndex++;
        frame.add(calculateButton, gbc);

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = rowIndex;
        gbc.gridwidth = 2;
        frame.add(new JScrollPane(resultArea), gbc);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private JTextField addLabelAndTextField(String label, int row, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = row;
        frame.add(new JLabel(label), gbc);

        JTextField textField = new JTextField(width);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(textField, gbc);

        return textField;
    }

    private void calculateResult() {
        try {
            int accNum = Integer.parseInt(accNumField.getText());
            double beginBal = Double.parseDouble(beginBalField.getText());
            double totalNew = Double.parseDouble(totalNewField.getText());
            double totalPaid = Double.parseDouble(totalPaidField.getText());
            double creditLimit = Double.parseDouble(creditLimitField.getText());

            double owedBal = beginBal + totalNew - totalPaid;
            double overLimitBy = owedBal - creditLimit;
            double availCredit = creditLimit - owedBal;

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

            resultArea.setText(result.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter correct values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        new Customers2();
    }
}
