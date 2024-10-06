import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Window extends JFrame{
    Box boxHOne,boxHTwo,boxHThree,boxHFour,boxHFive,boxHSix;//行型盒式容器
    Box boxVOne;//列型盒式容器
    JLabel tipLabel;//提示标签
    JLabel inputLabel,outputLabel;//输入输出标签
    JTextField inputTextField,outputTextField;//输入输出框
    JLabel keyLabel;//密钥标签
    JTextField keyTextField;//密钥输入框
    JButton runButton;//运行按钮
    JRadioButton encryptionRadioButton,decryptionRadioButton;//加密/解密单选按钮
    RadioButtonListener radioButtonListener;//单选按钮监听器
    Integer model;//模式
    ButtonGroup group;//按钮组
    RunListener runListener;//运行按钮监听器
    CloseListener closeListener;//关闭监听器
    public Window(){
        super("S-DES加/解密系统");//设置标题
        setLayout(new java.awt.FlowLayout());
        setResizable(false);//不可改变窗口大小
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setWindow();//设置窗口大小、位置，使其置于屏幕中央
        addWindowListener(closeListener);
        init();//初始化并布局页面
        setVisible(true);//设置窗口可见
    }
    public void init(){
        tipLabel=new JLabel("提示：请输入8bit二进制数据或ASCII码字符串以及10bit密钥");//初始化提示
        tipLabel.setMaximumSize(new Dimension(300,30));
        tipLabel.setFont(new Font("宋体", Font.BOLD,10));
        inputLabel=new JLabel("输入：");//初始化输入标签
        inputLabel.setPreferredSize(new Dimension(70,30));
        inputLabel.setFont(new Font("宋体", Font.BOLD,20));
        keyLabel=new JLabel("密钥：");//初始化输入标签
        keyLabel.setPreferredSize(new Dimension(70,30));
        keyLabel.setFont(new Font("宋体", Font.BOLD,20));
        outputLabel=new JLabel("输出：");//初始化输出标签
        outputLabel.setPreferredSize(new Dimension(70,30));
        outputLabel.setFont(new Font("宋体", Font.BOLD,20));
        inputTextField=new JTextField(20);//初始化初始化输入文本框
        inputTextField.setMaximumSize(new Dimension(170,30));
        keyTextField=new JTextField(20);//初始化初始化密钥文本框
        keyTextField.setMaximumSize(new Dimension(170,30));
        outputTextField=new JTextField(20);//初始化初始化输出文本框
        outputTextField.setMaximumSize(new Dimension(170,30));
        outputTextField.setEditable(false);
        runListener=new RunListener(this);
        runButton=new JButton("运行");//初始化运行按钮
        runButton.setMaximumSize(new Dimension(70,30));
        runButton.setPreferredSize(new Dimension(70,30));
        runButton.setFont(new Font("宋体", Font.BOLD,15));
        runButton.addActionListener(runListener);
        //初始化单选按钮
        model=0;//0为加密，1为解密
        encryptionRadioButton=new JRadioButton("加密");
        decryptionRadioButton=new JRadioButton("解密");
        group=new ButtonGroup();
        group.add(encryptionRadioButton);
        group.add(decryptionRadioButton);
        radioButtonListener=new RadioButtonListener(this,encryptionRadioButton,decryptionRadioButton);
        encryptionRadioButton.addItemListener(radioButtonListener);
        decryptionRadioButton.addItemListener(radioButtonListener);
        //初始化盒式布局
        boxHOne=Box.createHorizontalBox();
        boxHTwo=Box.createHorizontalBox();
        boxHThree=Box.createHorizontalBox();
        boxHFour=Box.createHorizontalBox();
        boxHFive=Box.createHorizontalBox();
        boxHSix=Box.createHorizontalBox();
        boxVOne=Box.createVerticalBox();
        boxHFive.add(tipLabel);
        boxHOne.add(inputLabel);
        boxHOne.add(inputTextField);
        boxHSix.add(keyLabel);
        boxHSix.add(keyTextField);
        boxHTwo.add(outputLabel);
        boxHTwo.add(outputTextField);
        boxHThree.add(encryptionRadioButton);
        boxHThree.add(decryptionRadioButton);
        boxHFour.add(runButton);
        boxVOne.add(Box.createVerticalStrut(30));
        boxVOne.add(boxHFive);
        boxVOne.add(Box.createVerticalStrut(10));
        boxVOne.add(boxHOne);
        boxVOne.add(Box.createVerticalStrut(10));
        boxVOne.add(boxHSix);
        boxVOne.add(Box.createVerticalStrut(10));
        boxVOne.add(boxHTwo);
        boxVOne.add(Box.createVerticalStrut(15));
        boxVOne.add(boxHThree);
        boxVOne.add(Box.createVerticalStrut(10));
        boxVOne.add(boxHFour);
        add(boxVOne);
    }
    //设置窗口
    private void setWindow(){
        setSize(512,320);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
    //关闭时退出
    class CloseListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            super.windowClosing(e);
            System.exit(0);
        }
    }
    //单选按钮监听器
    class RadioButtonListener implements ItemListener{
        Window window;
        JRadioButton encryptionRadioButton,decryptionRadioButton;
        public RadioButtonListener(Window wd,JRadioButton er,JRadioButton dr){
            window=wd;
            encryptionRadioButton=er;
            decryptionRadioButton=dr;
        }
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource()==encryptionRadioButton)
                window.model=0;
            else
                window.model=1;
        }
    }
    //运行监听器
    class RunListener implements ActionListener{
        int flag;//判断输入是8bit二进制数据还是ASCII字符串，0是二进制，1是字符串
        Window window;
        String inputStr,keyStr;
        int[] input;
        int[] key;
        Calculator calculator;//计算器
        public RunListener(Window window){
            this.window=window;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            inputStr=inputTextField.getText();
            keyStr=keyTextField.getText();
            flag=0;
            //检测输入内容
            for(int i=0;i<inputStr.length();i++){
                if(inputStr.charAt(i)!='0'&&inputStr.charAt(i)!='1'){
                    flag=1;//若不是0或1，则为ASCII字符串
                }
            }
            System.out.println("输入类型是");
            System.out.println(flag);
            System.out.println(" ");
            //将inputStr转化为int数组
            System.out.println("输入为");
            if(flag==0){
                input=new int[inputStr.length()];
                for(int i=0;i<inputStr.length();i++){
                    input[i]=Integer.parseInt(inputStr.substring(i,i+1));
                    System.out.println(input[i]);
                }
            }else{
                input=new int[inputStr.length()*8];
                for(int i=0;i<inputStr.length();i++){
                    String tempStr=Integer.toBinaryString(inputStr.charAt(i));
                    int zeroCount=8-tempStr.length();
                    for(int j=0;j<zeroCount;j++) {
                        input[i * 8+j] = 0;
                        System.out.println(input[i*8]);
                    }
                    for(int j=0;j<8-zeroCount;j++){
                        input[i*8+j+zeroCount]=Integer.parseInt(tempStr.substring(j,j+1));
                        System.out.println(input[i*8+j+zeroCount]);
                    }
                }
            }
            //将密钥转化为整形数组
            System.out.println(" ");
            System.out.println("密钥是");
            key=new int[10];
            for(int i=0;i<keyStr.length();i++){
                key[i]=Integer.parseInt(keyStr.substring(i,i+1));
                System.out.println(key[i]);
            }
            //初始化计算器
            calculator=new Calculator(input,key,model);
            //检测输入长度，并进行运算
            if(input.length%8!=0||keyStr.length()!=10){
                JOptionPane.showMessageDialog(boxVOne, "您输入的格式有误！", "警告！", JOptionPane.WARNING_MESSAGE);
            }else if(window.model==0&&flag==0){
                System.out.println("运行8bit加密算法");
                int[] output=calculator.encryptBit();
                String outputStr=new String("");
                for(int i=0;i<output.length;i++)
                    outputStr+=String.valueOf(output[i]);
                outputTextField.setText(outputStr);
            }else if(window.model==1&&flag==0){
                System.out.println("运行8bit解密算法");
                int[] output=calculator.decryptBit();
                String outputStr=new String("");
                for(int i=0;i<output.length;i++)
                    outputStr+=String.valueOf(output[i]);
                outputTextField.setText(outputStr);
            }else if(window.model==0&&flag==1){
                System.out.println("运行ASCII码字符串加密算法");
                outputTextField.setText(calculator.encryptASCII());
            }else if(window.model==1&&flag==1){
                System.out.println("运行ASCII码字符串解密算法");
                outputTextField.setText(calculator.decryptASCII());
            }

        }
    }
}
