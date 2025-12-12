package CalculadoraPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculadoraSwing extends JFrame {

    private Calculadora calculadora = new Calculadora();

    private JTextField display;
    private String operador = "";
    private double n1 = 0;

    public CalculadoraSwing() {

        setTitle("Calculadora");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        Color fundo = new Color(30, 30, 30);
        Color botao = new Color(50, 50, 50);
        Color hover = new Color(80, 80, 80);
        Color texto = new Color(230, 230, 230);

        getContentPane().setBackground(fundo);

        // Display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Consolas", Font.BOLD, 36));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setBackground(new Color(20, 20, 20));
        display.setForeground(texto);
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(display, BorderLayout.NORTH);

        // Container dos botões
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(fundo);

        JPanel botoesPanel = new JPanel(new GridLayout(5, 4, 12, 12));
        botoesPanel.setPreferredSize(new Dimension(300, 330));
        botoesPanel.setBackground(fundo);

        // Botões da calculadora
        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "X"
        };

        for (String t : botoes) {

            JButton btn = new JButton(t);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.setFocusPainted(false);
            btn.setForeground(texto);
            btn.setBackground(botao);
            btn.setBorder(BorderFactory.createLineBorder(new Color(40, 40, 40), 2));

            // Hover
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(hover);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(botao);
                }
            });

            botoesPanel.add(btn);

            btn.addActionListener(e -> onClick(t));
        }

        container.add(botoesPanel);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(container, BorderLayout.CENTER);

        setVisible(true);
    }


    // LÓGICA DA CALCULADORA
   

    private void onClick(String texto) {

        // Dígitos
        if (texto.matches("[0-9]")) {
            display.setText(display.getText() + texto);
            return;
        }

        // Decimal
        if (texto.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
            return;
        }

        // Operadores
        if (texto.matches("[+\\-*/]")) {
            if (display.getText().isEmpty()) return;

            operador = texto;
            n1 = Double.parseDouble(display.getText());
            display.setText("");
            return;
        }

        // Igual (=)
        if (texto.equals("=")) {
            if (display.getText().isEmpty()) return;

            double n2 = Double.parseDouble(display.getText());

            try {
                double resultado = 0;
                switch (operador) {
                    case "+":
                        resultado = calculadora.somar(n1, n2);
                        break;
                    case "-":
                        resultado = calculadora.subtrair(n1, n2);
                        break;
                    case "*":
                        resultado = calculadora.multiplicar(n1, n2);
                        break;
                    case "/":
                        resultado = calculadora.dividir(n1, n2);
                        break;
                    default:
                        resultado = 0;
                }

                display.setText(String.valueOf(resultado));

            } catch (ArithmeticException e) {
                display.setText("Erro");
            }

            return;
        }

        // Clear (C)
        if (texto.equals("C")) {
            display.setText("");
            calculadora.reset();
            operador = "";
            n1 = 0;
            return;
        }

        // Backspace (X)
        if (texto.equals("X")) {
            String txt = display.getText();
            if (!txt.isEmpty()) {
                display.setText(txt.substring(0, txt.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new CalculadoraSwing();
    }
}
