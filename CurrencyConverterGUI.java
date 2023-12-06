import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CurrencyConverterGUI extends JFrame {

    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountTextField;
    private JTextField resultTextField;
    private JButton convertButton;

    // Example conversion rates
    private final double usdToEuroRate = 0.85;
    private final double usdToInrRate = 73.62;
    private final double usdToJpyRate = 114.43;

    public CurrencyConverterGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Currency Converter");

        fromCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "INR", "JPY"});
        toCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "INR", "JPY"});
        amountTextField = new JTextField();
        resultTextField = new JTextField();
        resultTextField.setEditable(false);
        convertButton = new JButton("Convert");

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(fromCurrencyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(amountTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(convertButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(toCurrencyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(resultTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(new JLabel("From Currency"))
                                                .addGap(88, 88, 88)
                                                .addComponent(new JLabel("To Currency")))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(new JLabel("Amount"))
                                                .addGap(106, 106, 106)
                                                .addComponent(new JLabel("Result"))))
                                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(new JLabel("From Currency"))
                                        .addComponent(new JLabel("To Currency")))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(fromCurrencyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toCurrencyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(new JLabel("Amount"))
                                        .addComponent(new JLabel("Result")))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(amountTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resultTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(convertButton))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }

    private void convertButtonActionPerformed(ActionEvent evt) {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
            String toCurrency = (String) toCurrencyComboBox.getSelectedItem();
            double result;

            if ("USD".equals(fromCurrency) && "EUR".equals(toCurrency)) {
                result = amount * usdToEuroRate;
            } else if ("USD".equals(fromCurrency) && "INR".equals(toCurrency)) {
                result = amount * usdToInrRate;
            } else if ("USD".equals(fromCurrency) && "JPY".equals(toCurrency)) {
                result = amount * usdToJpyRate;
            } else if ("EUR".equals(fromCurrency) && "USD".equals(toCurrency)) {
                result = amount / usdToEuroRate;
            } else if ("INR".equals(fromCurrency) && "USD".equals(toCurrency)) {
                result = amount / usdToInrRate;
            } else if ("JPY".equals(fromCurrency) && "USD".equals(toCurrency)) {
                result = amount / usdToJpyRate;
            } else {
                result = amount;  // Default to no conversion
            }

            resultTextField.setText(String.format("%.2f", result));
        } catch (NumberFormatException e) {
            resultTextField.setText("Invalid input");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverterGUI().setVisible(true);
            }
        });
    }
}