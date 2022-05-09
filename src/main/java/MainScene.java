import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class MainScene extends JPanel {

    private JTextField textFieldToSearchIP;
    private final ImageIcon BACK_ROUND=new ImageIcon("C:\\Users\\Shilo\\Documents\\GitHub\\WebScrappingProject\\target\\photos\\IP PROJECT.jpg.png");
    private  JLabel labelBackRound;
    private JLabel title;
    private JButton searchButton;
    private final Color SPECIFIC_ORANGE =new Color(200,130,0);
    public MainScene(int x, int y, int width, int height){
        //Settings of panel

        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setBackground(null);
        this.setDoubleBuffered(true);


        //Title Of Page
        title = new JLabel("IP Countries");

        title.setFont(new Font("arial", Font.BOLD,80));
        title.setBounds(Window.WINDOW_WIDTH/4+Window.WINDOW_WIDTH/15,Window.WINDOW_HEIGHT/4, 600,100);
        title.setForeground(SPECIFIC_ORANGE);
        title.setVisible(true);
        this.add(title);

        //Text Field to enter IP
        textFieldToSearchIP = createJTextField();
        this.add(textFieldToSearchIP);

        //Button to Search
        searchButton = new JButton("Search");
        searchButton.setBounds((title.getX()+title.getWidth()/3)-10, textFieldToSearchIP.getY() + (textFieldToSearchIP.getHeight()*2), 100,50);
        searchButton.setVisible(true);
        this.add(searchButton);

        labelBackRound=new JLabel(BACK_ROUND);
        labelBackRound.setSize(width,height);
        labelBackRound.setBounds(x,y,BACK_ROUND.getIconWidth(),BACK_ROUND.getIconHeight());
        this.add(labelBackRound);

        //Click button
        searchButton.addActionListener((event) -> {
            JFrame frame = new JFrame();
            if(textFieldToSearchIP.getText().length() == 0) {
                if(JOptionPane.showConfirmDialog(frame,"This field cannot be empty!","Error",JOptionPane.CLOSED_OPTION) == JOptionPane.CLOSED_OPTION);
            }else {
                if(isValidIP(textFieldToSearchIP.getText()));
                JOptionPane.showConfirmDialog(frame,"the IP you type is not valid!","Wrong IP",JOptionPane.CLOSED_OPTION);
            }
        });

        Thread thread = new Thread(() -> {
            try {
                ScrappingIP scrappingIP = new ScrappingIP();
                Thread.sleep(5);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        thread.start();

        this.setVisible(true);
    }

    public JTextField createJTextField(){
        JTextField textField = new JTextField();
        textField.setBounds(title.getX()+title.getWidth()/10,title.getY()+title.getHeight()*3/2,350,50);
        textField.setFont(new Font("arial",Font.BOLD,24));
        textField.setForeground(Color.lightGray);
        textField.setBackground(Color.BLACK);
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
