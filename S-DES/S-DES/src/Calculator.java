import java.util.Arrays;

public class Calculator {
    int[] input;
    int[] key;
    int[] k1,k2;//子密钥
    int[] output;
    Integer model;
    public Calculator(int[] input,int[] key,Integer model){
        this.model=model;
        this.input=new int[input.length];
        for(int i=0;i<input.length;i++)
            this.input[i]=input[i];
        this.key=new int[10];
        for(int i=0;i<10;i++)
            this.key[i]=key[i];
        output=new int[input.length];
        k1=new int[8];
        k2=new int[8];
    }
    //加密Bit计算
    public int[]  encryptBit(){
        int[] initialPremutationOutput=initialPremutation(input);//初始置换
        keysGenerator();//生成密钥
        //fk1函数
        int[] R=Arrays.copyOfRange(initialPremutationOutput,4,8);
        int[] FOutput=F(R,k1);//轮函数
        //异或并左右对换
/*        System.out.println(" ");
        System.out.println("fk1函数的输出结果");*/
        int[] L=Arrays.copyOfRange(initialPremutationOutput,0,4);
        int[] fkOutput=new int[8];
        for(int i=0;i<4;i++){
            if(L[i]==FOutput[i])
                fkOutput[i]=0;
            else
                fkOutput[i]=1;
            /*System.out.println(fkOutput[i]);*/
        }
        for(int i=0;i<4;i++){
            fkOutput[4+i]=R[i];
            /*System.out.println(fkOutput[4+i]);*/
        }
        //交换
        int[] swapOutput=new int[8];
        for(int i=0;i<4;i++){
            swapOutput[i]=fkOutput[4+i];
            swapOutput[4+i]=fkOutput[i];
        }
        //fk2函数
        R=Arrays.copyOfRange(swapOutput,4,8);
        FOutput=F(R,k2);//轮函数
        /*System.out.println(" ");
        System.out.println("fk2函数的输出结果");*/
        L=Arrays.copyOfRange(swapOutput,0,4);
        for(int i=0;i<4;i++){
            if(L[i]==FOutput[i])
                fkOutput[i]=0;
            else
                fkOutput[i]=1;
           /* System.out.println(fkOutput[i]);*/
        }
        for(int i=0;i<4;i++){
            fkOutput[4+i]=R[i];
            /*System.out.println(fkOutput[4+i]);*/
        }
        output=finalPremutation(fkOutput);
        return output;
    }
    //解密Bit算法
    public int[]  decryptBit(){
        int[] initialPremutationOutput=initialPremutation(input);//初始置换
        keysGenerator();//生成密钥
        //fk2函数
        int[] R=Arrays.copyOfRange(initialPremutationOutput,4,8);
        int[] FOutput=F(R,k2);//轮函数
        //异或并左右对换
        /*System.out.println(" ");
        System.out.println("fk2函数的输出结果");*/
        int[] L=Arrays.copyOfRange(initialPremutationOutput,0,4);
        int[] fkOutput=new int[8];
        for(int i=0;i<4;i++){
            if(L[i]==FOutput[i])
                fkOutput[i]=0;
            else
                fkOutput[i]=1;
          /*  System.out.println(fkOutput[i]);*/
        }
        for(int i=0;i<4;i++){
            fkOutput[4+i]=R[i];
            /*System.out.println(fkOutput[4+i]);*/
        }
        //交换
        int[] swapOutput=new int[8];
        for(int i=0;i<4;i++){
            swapOutput[i]=fkOutput[4+i];
            swapOutput[4+i]=fkOutput[i];
        }
        //fk1函数
        R=Arrays.copyOfRange(swapOutput,4,8);
        FOutput=F(R,k1);//轮函数
        /*System.out.println(" ");
        System.out.println("fk1函数的输出结果");*/
        L=Arrays.copyOfRange(swapOutput,0,4);
        for(int i=0;i<4;i++){
            if(L[i]==FOutput[i])
                fkOutput[i]=0;
            else
                fkOutput[i]=1;
           /* System.out.println(fkOutput[i]);*/
        }
        for(int i=0;i<4;i++){
            fkOutput[4+i]=R[i];
           /* System.out.println(fkOutput[4+i]);*/
        }
        output=finalPremutation(fkOutput);
        return output;
    }
    //加密ASCII计算
    public String  encryptASCII(){
        int count=input.length/8;
        String outputStr=new String("");
        for(int j=0;j<count;j++) {
            int[] block=Arrays.copyOfRange(input,j*8,j*8+8);
            int[] initialPremutationOutput = initialPremutation(block);//初始置换
            keysGenerator();//生成密钥
            //fk1函数
            int[] R = Arrays.copyOfRange(initialPremutationOutput, 4, 8);
            int[] FOutput = F(R, k1);//轮函数
            //异或并左右对换
            /*System.out.println(" ");
            System.out.println("fk1函数的输出结果");*/
            int[] L = Arrays.copyOfRange(initialPremutationOutput, 0, 4);
            int[] fkOutput = new int[8];
            for (int i = 0; i < 4; i++) {
                if (L[i] == FOutput[i])
                    fkOutput[i] = 0;
                else
                    fkOutput[i] = 1;
                /*System.out.println(fkOutput[i]);*/
            }
            for (int i = 0; i < 4; i++) {
                fkOutput[4 + i] = R[i];
               /* System.out.println(fkOutput[4 + i]);*/
            }
            //交换
            int[] swapOutput = new int[8];
            for (int i = 0; i < 4; i++) {
                swapOutput[i] = fkOutput[4 + i];
                swapOutput[4 + i] = fkOutput[i];
            }
            //fk2函数
            R = Arrays.copyOfRange(swapOutput, 4, 8);
            FOutput = F(R, k2);//轮函数
            /*System.out.println(" ");
            System.out.println("fk2函数的输出结果");*/
            L = Arrays.copyOfRange(swapOutput, 0, 4);
            for (int i = 0; i < 4; i++) {
                if (L[i] == FOutput[i])
                    fkOutput[i] = 0;
                else
                    fkOutput[i] = 1;
                /*System.out.println(fkOutput[i]);*/
            }
            for (int i = 0; i < 4; i++) {
                fkOutput[4 + i] = R[i];
               /* System.out.println(fkOutput[4 + i]);*/
            }
            int[] finalPremutationOutput = finalPremutation(fkOutput);
            //转化为ASCII字符
            int letterInt=0;
            for(int k=0;k<8;k++){
                int x=finalPremutationOutput[k];
                for(int m=7-k;m>0;m--){
                    x*=2;
                }
                letterInt+=x;
            }
            char letterChar=(char)letterInt;
            String letterString=String.valueOf(letterChar);
            outputStr+=letterString;
        }
        return outputStr;
    }
    //解密ASCII计算
    public String  decryptASCII(){
        int count=input.length/8;
        String outputStr=new String("");
        for(int j=0;j<count;j++) {
            int[] block=Arrays.copyOfRange(input,j*8,j*8+8);
            int[] initialPremutationOutput = initialPremutation(block);//初始置换
            keysGenerator();//生成密钥
            //fk2函数
            int[] R = Arrays.copyOfRange(initialPremutationOutput, 4, 8);
            int[] FOutput = F(R, k2);//轮函数
            //异或并左右对换
           /* System.out.println(" ");
            System.out.println("fk2函数的输出结果");*/
            int[] L = Arrays.copyOfRange(initialPremutationOutput, 0, 4);
            int[] fkOutput = new int[8];
            for (int i = 0; i < 4; i++) {
                if (L[i] == FOutput[i])
                    fkOutput[i] = 0;
                else
                    fkOutput[i] = 1;
                /*System.out.println(fkOutput[i]);*/
            }
            for (int i = 0; i < 4; i++) {
                fkOutput[4 + i] = R[i];
                /*System.out.println(fkOutput[4 + i]);*/
            }
            //交换
            int[] swapOutput = new int[8];
            for (int i = 0; i < 4; i++) {
                swapOutput[i] = fkOutput[4 + i];
                swapOutput[4 + i] = fkOutput[i];
            }
            //fk1函数
            R = Arrays.copyOfRange(swapOutput, 4, 8);
            FOutput = F(R, k1);//轮函数
            /*System.out.println(" ");
            System.out.println("fk1函数的输出结果");*/
            L = Arrays.copyOfRange(swapOutput, 0, 4);
            for (int i = 0; i < 4; i++) {
                if (L[i] == FOutput[i])
                    fkOutput[i] = 0;
                else
                    fkOutput[i] = 1;
                /*System.out.println(fkOutput[i]);*/
            }
            for (int i = 0; i < 4; i++) {
                fkOutput[4 + i] = R[i];
                /*System.out.println(fkOutput[4 + i]);*/
            }
            int[] finalPremutationOutput = finalPremutation(fkOutput);
            //转化为ASCII字符
            int letterInt=0;
            for(int k=0;k<8;k++){
                int x=finalPremutationOutput[k];
                for(int m=7-k;m>0;m--){
                    x*=2;
                }
                letterInt+=x;
            }
            char letterChar=(char)letterInt;
            String letterString=String.valueOf(letterChar);
            outputStr+=letterString;
        }
        return outputStr;
    }
    //初始置换
    public int[] initialPremutation(int[] input){
        int[] initialPremutationOutput=new int[8];
        int[] IP={2,6,3,1,4,8,5,7};
        /*System.out.println("初始置换的结果是");*/
         for(int i=0;i<8;i++){
             initialPremutationOutput[i]=input[IP[i]-1];
             /*System.out.println(initialPremutationOutput[i]);*/
        }
        return initialPremutationOutput;
    }
    //最终置换
    public int[] finalPremutation(int[] input){
        int[] finalPremutationOutput=new int[8];
        int[] IP={4,1,3,5,7,2,8,6};
        /*System.out.println("最终置换的结果是");*/
        for(int i=0;i<8;i++){
            finalPremutationOutput[i]=input[IP[i]-1];
            /*System.out.println(finalPremutationOutput[i]);*/
        }
        return finalPremutationOutput;
    }
    //密钥生成
    public void keysGenerator(){
        int[] tmptInput1=new int[10];
        int[] P10={3,5,2,7,4,10,1,9,8,6};
        //密钥首次转置
        /*System.out.println(" ");
        System.out.println("密钥首次转置的结果");*/
        for(int i=0;i<10;i++){
            tmptInput1[i]=key[P10[i]-1];
            /*System.out.println(tmptInput1[i]);*/
        }
        //第一次左移位处理
        /*System.out.println(" ");
        System.out.println("第一次移位处理结果");*/
        int[] tmptInput2=new int[10];
        int[] PLeft= {2,3,4,5,1,7,8,9,10,6};
        for(int i=0;i<10;i++){
            tmptInput2[i]=tmptInput1[PLeft[i]-1];
            /*System.out.println(tmptInput2[i]);*/
        }
        //生成子密钥k1
        /*System.out.println(" ");
        System.out.println("k1为");*/
        int[] P8={6,3,7,4,8,5,10,9};
        for(int i=0;i<8;i++){
            k1[i]=tmptInput2[P8[i]-1];
           /* System.out.println(k1[i]);*/
        }
        //第二次左移位处理
        /*System.out.println(" ");
        System.out.println("第二次移位处理结果");*/
        int[] tmptInput3=new int[10];
        for(int i=0;i<10;i++){
            tmptInput3[i]=tmptInput2[PLeft[i]-1];
            /*System.out.println(tmptInput3[i]);*/
        }
        //生成子密钥k2
       /* System.out.println(" ");
        System.out.println("k2为");*/
        for(int i=0;i<8;i++){
            k2[i]=tmptInput3[P8[i]-1];
            /*System.out.println(k2[i]);*/
        }
    }
    //轮函数
    public int[] F(int[] input,int[] ki){
        int[] EP={4,1,2,3,2,3,4,1};
        int[] tmptInput1=new int[8];
        //扩展转置
        /*System.out.println(" ");
        System.out.println("轮函数扩展转置结果");*/
        for(int i=0;i<8;i++){
            tmptInput1[i]=input[EP[i]-1];
            /*System.out.println(tmptInput1[i]);*/
        }
        //加轮密钥
        /*System.out.println(" ");
        System.out.println("加轮密钥的结果");*/
        int[] tmptInput2=new int[8];
        for(int i=0;i<8;i++){
            if(tmptInput1[i]==ki[i])
                tmptInput2[i]=0;
            else
                tmptInput2[i]=1;
            /*System.out.println(tmptInput2[i]);*/
        }
        //替换盒
        int[] tmptInput3=new int[4];
        int[][] SBoxL={{1,0,11,10},{11,10,1,0},{0,10,1,11},{11,1,0,10}};
        int[][] SBoxR={{0,1,10,11},{10,11,1,0},{11,0,1,10},{10,1,0,11}};
        int[] tmptLeft=Arrays.copyOfRange(tmptInput2,0,4);
        int x1=tmptLeft[1]*2+tmptLeft[2];
        int y1=tmptLeft[0]*2+tmptLeft[3];
        tmptInput3[0]=SBoxL[y1][x1]/10;
        tmptInput3[1]=SBoxL[y1][x1]%10;
        int[] tmptRight=Arrays.copyOfRange(tmptInput2,4,8);
        int x2=tmptRight[1]*2+tmptRight[2];
        int y2=tmptRight[0]*2+tmptRight[3];
        tmptInput3[2]=SBoxR[y2][x2]/10;
        tmptInput3[3]=SBoxR[y2][x2]%10;
        //直接置换
        /*System.out.println(" ");
        System.out.println("轮函数输出结果");*/
        int[] FOutput=new int[4];
        int[] IS= {2,4,3,1};
        for(int i=0;i<4;i++){
            FOutput[i]=tmptInput3[IS[i]-1];
           /* System.out.println(FOutput[i]);*/
        }
        return FOutput;
    }

}
