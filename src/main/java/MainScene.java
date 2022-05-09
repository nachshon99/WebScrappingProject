import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class MainScene extends JPanel {

    private JTextField textFieldToSearchIP;

    public MainScene(int x, int y, int width, int height){
        //Settings of panel
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);


        //Title Of Page
        JLabel title = new JLabel("IP Countries");
        title.setFont(new Font("arial", Font.BOLD,50));
        title.setBounds(Window.WINDOW_WIDTH/3,Window.WINDOW_HEIGHT/4, 400,50);
        title.setVisible(true);
        this.add(title);

        //למחוק -------------
        searchCountry();
        //-------------


        //Text Field to enter IP
        textFieldToSearchIP = createJTextField();
        this.add(textFieldToSearchIP);

        //Button to Search
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(Window.WINDOW_WIDTH/2 - 35, textFieldToSearchIP.getY() + (textFieldToSearchIP.getHeight()*2), 100,50);
        searchButton.setVisible(true);
        this.add(searchButton);

        //Click button
        searchButton.addActionListener((event) -> {
            JFrame frame = new JFrame();
            if(textFieldToSearchIP.getText().length() == 0) {
                if(JOptionPane.showConfirmDialog(frame,"IP cannot be null","Error",JOptionPane.CLOSED_OPTION) == JOptionPane.CLOSED_OPTION);
            }else {
                if(isValidIP(textFieldToSearchIP.getText())){
                    searchCountry();
                }
            }
        });



        this.setVisible(true);
    }

    public void searchCountry(){
        Thread thread = new Thread(() -> {
            try {
                ScrappingIP scrappingIP = new ScrappingIP();
                Thread.sleep(5);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public JTextField createJTextField(){
        JTextField textField = new JTextField();
        textField.setBounds(290,300,250,50);
        textField.setFont(new Font("arial",Font.BOLD,24));
        textField.setForeground(Color.BLUE);
        textField.setBackground(Color.lightGray);
        textField.setToolTipText("Enter IP");
        textField.setVisible(true);
        return textField;
    }

    public boolean isValidIP(String str){
        boolean isValid = false;
        int countDots = 0;
        int countNumbers = 0;
        String saveNumber = "";
        Queue<Character> numbers = new LinkedList<>();
        String ip;
        if(str.length() >= 4 && str.length() <= 15){
            ip = str;
            for (int i = 0; i < ip.length();i++){
                numbers.add(ip.charAt(i));
                if(ip.charAt(i) == '.'){
                    countDots++;
                }
            }
            numbers.add('.');
            if(countDots == 3){
                do {
                    if(Character.isDigit(numbers.peek())){
                        countNumbers++;
                        saveNumber += numbers.peek();
                    }else if(numbers.peek() == '.') {
                        if(countNumbers >= 1 && countNumbers <= 3){
                            if(isValidNumber(saveNumber) && (Integer.parseInt(saveNumber) >= 0 && Integer.parseInt(saveNumber) <= 255)){
                                isValid = true;
                                saveNumber = "";
                                countNumbers = 0;
                            }else {
                                isValid = false;
                                break;
                            }
                        }else {
                            isValid = false;
                            break;
                        }
                    }
                    else {
                        isValid = false;
                        break;
                    }
                    numbers.poll();
                }while (!numbers.isEmpty());
            }
        }
        return isValid;
    }

    public boolean isValidNumber(String number){
        boolean isValid = false;
        int countZero = 0;
        for (int i = 0; i < number.length();i++){
            char currentChar = number.charAt(i);
            if (Character.isDigit(currentChar)){
                if(currentChar == '0'){
                    countZero++;
                    if(countZero > 1){
                        isValid = false;
                        break;
                    }
                }
                isValid = true;
            }else {
                isValid = false;
            }
        }
        return isValid;
    }
}
