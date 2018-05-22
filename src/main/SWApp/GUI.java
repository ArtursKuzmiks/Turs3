package SWApp;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame {

    private JTextField jTextField = new JTextField();
    private JScrollPane jScrollPane;
    private JLabel label2= new JLabel("History:");
    private DefaultListModel<String> listMode = new DefaultListModel<>();


    GUI() {

        final Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        final String title = "Company income App";
        final int location = 200;
        final int width = 500;
        final int height = 500;

        JButton predict = new JButton("Predict");
        JLabel label1 = new JLabel("Desired income: ");
        JList<String> list = new JList<>();

        list.setBorder(border);
        list.setModel(listMode);
        jScrollPane = new JScrollPane(list);

        predict.setFont(new Font("Tahoma", Font.BOLD, 16));
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label2.setFont(new Font("Tahoma",Font.BOLD,16));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(label1))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField)
                                .addComponent(predict))
                        .addComponent(label2)
                        .addComponent(jScrollPane))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jTextField)
                        .addComponent(predict))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jScrollPane))

        );

        layout.linkSize(SwingConstants.VERTICAL,predict,jTextField);

        label2.setVisible(false);
        jScrollPane.setVisible(false);

        pack();

        setBounds(location, location, width, height);
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        predict.addActionListener(new ActionPredict());
        jTextField.addActionListener(new ActionPredict());


    }

    private class ActionPredict implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Logic logic = new Logic();
            try{
                int balance = Integer.parseInt(jTextField.getText());
                logic.predict(listMode,balance);
                label2.setVisible(true);
                jScrollPane.setVisible(true);


            }catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(null,"Input error",
                        "Error",JOptionPane.ERROR_MESSAGE);
                jTextField.setText("");

            }
        }
    }


}
