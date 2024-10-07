import java.util.Arrays;
import java.util.Scanner;

public class Cracker {
    public void crack(){
        Scanner scanner = new Scanner(System.in);
        //获取明文
        System.out.println("请输入明文：");
        String plaintextStr=scanner.nextLine();
        int[] plaintext=new int[8];
        for(int i=0;i<8;i++) {
            plaintext[i] = Integer.parseInt(plaintextStr.substring(i, i + 1));
        }
        //获取密文
        System.out.println("请输入密文：");
        String ciphertextStr=scanner.nextLine();
        int[] ciphertext=new int[8];
        for(int i=0;i<8;i++) {
            ciphertext[i] = Integer.parseInt(ciphertextStr.substring(i, i + 1));
        }
        //破解
        int flag=0;//记录是否找到密钥
        int[] key=new int[10];
        long millis1 = System.currentTimeMillis();
        for(int i=0;i<1024;i++){
            int number=i;
            for(int j=0;j<10;j++){
                key[9-j]=number%2;
                number=number/2;
            }
            Calculator calculator=new Calculator(plaintext,key,0);
            int[] tmptCiphertext=calculator.encryptBit();
            if(Arrays.equals(ciphertext,tmptCiphertext)){
                long millis2 = System.currentTimeMillis();
                long time=millis2-millis1;
                flag=1;
                String keyStr=new String("");
                for(int j=0;j<10;j++){
                    keyStr+=String.valueOf(key[j]);
                }
                System.out.println("找到密钥："+keyStr+"，耗时："+time+"毫秒");
            }
        }
        if(flag==0)
            System.out.println("没有找到密钥");
    }
    //封闭测试
    public void closedTest(){
        int count1=0;//记录有多少对只存在一个密钥
        int count2=0;//记录有多少对存在密钥
        for(int i=0;i<256;i++){
            //生成明文
            int number1=i;
            int[] plaintext=new int[8];
            for(int j=0;j<8;j++){
                plaintext[7-j]=number1%2;
                number1=number1/2;
            }
            for(int j=0;j<256;j++){
                //生成密文
                int number2=j;
                int[] ciphertext=new int[8];
                for(int k=0;k<8;k++){
                    ciphertext[7-k]=number2%2;
                    number2=number2/2;
                }
                int flag=0;//记录是否找到密钥
                //生成密钥
                int[] key=new int[10];
                for(int k=0;k<1024;k++){
                    int number3=k;
                    for(int m=0;m<10;m++){
                        key[9-m]=number3%2;
                        number3=number3/2;
                    }
                    Calculator calculator=new Calculator(plaintext,key,0);
                    int[] tmptCiphertext=calculator.encryptBit();
                    if(Arrays.equals(ciphertext,tmptCiphertext))
                        flag++;
                }
                if(flag==1)
                    count1++;
                if(flag>0)
                    count2++;
            }
        }
        System.out.println("存在密钥的明密文对数"+count2);
        System.out.println("仅有一个密钥的密文对数"+count1);

    }
}
