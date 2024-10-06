# S-DES程序开发手册
## 一.简介
本开发手册提供了一个基于S-DES（Simplified Data Encryption Standard）算法的加解密程序的详细接口文档，用于对简单、规模小的数据进行加密、解密与破解操作。

## 二.接口文档
1.encryption(int[] plainTxt, int[] K, int round)

描述：使用S-DES算法加密输入的明文。  
参数：  
plainTxt：int[]类型，要加密的明文。  
k：int[]类型，加密使用的密钥。  
round：int类型，加密的次数。  
返回值：int[]类型，加密后的密文数组。  

2.decryption(int[] cipherTxt, int[] K, int round)

描述：使用S-DES算法解密输入的密文。  
参数：  
cipherTxt：int[]类型，要加密的明文。  
k：int[]类型，加密使用的密钥。  
round：int类型，加密的次数。  
返回值：int[]类型，解密后的明文数组。  

3.Test1  
描述：Test1类是一个图形用户界面，用于二进制加密和解密文本。  
方法：  
actionPerformed(ActionEvent e)：实现ActionListener接口，处理加密和解密按钮的点击事件。  
属性：
txtPlaintext 明文字段文本框  
txtKey 密钥文本框  
txtCiphertext 密文文本框  
btnEncrypt 加密按钮  
btnDecrypt 解密按钮 

4.Test3  
描述：Test3类是一个图形用户界面，用于ASCLL码加密和解密文本。
方法：  
actionPerformed(ActionEvent e)：实现ActionListener接口，处理加密和解密按钮的点击事件
属性：
txtPlaintext 明文字段文本框  
txtKey 密钥文本框  
txtCiphertext 密文文本框  
btnEncrypt 加密按钮  
btnDecrypt 解密按钮 

5.Test4.main(String[] args)
描述：Test4类是一个图形用户界面，用于暴力测试破解密钥。
参数：args数组，命令行参数。
返回值：无返回值。
输入：从命令行读取要破解的明密文对数量。
输出：输出找到的秘钥和破解时间（如果找到了秘钥）。

## 三.程序过程详解
1.初始置换（IP）: 使用初始置换表（IP）对输入的8位明文进行置换，得到置换后的8位序列。

2.密钥生成: 使用P10对输入的10位密钥进行置换，然后对得到的结果分别进行1位和2位的左移，生成两个子密钥k1和k2。

3.拓展置换（EP-Box）: 对右半部分8位密文进行拓展置换，扩展为12位。
子密钥加轮: 将拓展置换后的12位结果与k1进行异或运算，得到12位结果。

4.S盒置换: 将上一步结果分为两组，分别进行S盒置换。

5.压缩置换: 对S盒置换后的结果进行压缩置换，得到4位结果。

6.直接置换（SP-Box）: 对压缩置换后的4位结果进行直接置换。

7.最终置换（IP^-1）: 将上述结果与初始置换后的4位结果进行异或运算，得到8位加密结果。

3.2解密过程详解

1.初始置换（IP^-1）: 使用逆初始置换表（IP^-1）对输入的8位密文进行置换，得到置换后的8位序列。

2.密钥生成: 使用P10对输入的10位密钥进行置换，然后对得到的结果分别进行1位和2位的左移，生成两个子密钥k1和k2。

3.拓展置换（EP-Box）: 对右半部分8位密文进行拓展置换，扩展为12位。
子密钥加轮: 将拓展置换后的12位结果与k2进行异或运算，得到12位结果。

4.S盒置换: 将上一步结果分为两组，分别进行S盒置换。

5.压缩置换: 对S盒置换后的结果进行压缩置换，得到4位结果。

6.直接置换（SP-Box）: 对压缩置换后的4位结果进行直接置换。

7.最终置换（IP）: 将上述结果与初始置换后的4位密文进行异或运算，得到8位解密结果。

3.3破解过程详解

破解过程是通过暴力破解的方式，尝试所有可能的密钥，直到找到正确的密钥，从而解密密文。具体步骤如下：

1.输入明文和密文。
2.对于每个可能的密钥，使用SDES解密算法对密文进行解密。
3.将解密后的明文与输入的明文进行比较，如果相同，则找到了正确的密钥。
4.输出找到的密钥。

由于SDES算法的密钥长度较短，只有10位，因此可以通过暴力破解的方式尝试所有可能的密钥。但是，随着计算机运算能力的提高，暴力破解的难度也在增加，因此需要使用更加安全的加密算法来保护数据的安全性。

## 四.注意事项：
1.该代码使用的是SDES算法，密钥长度为10位，明文和密文长度为8位。  
2.在破解过程中，程序会尝试1024个可能的密钥，因此破解时间可能会很长。  
3.程序中使用了Scanner类来读取用户输入，因此需要确保输入的格式正确。  
4.程序中使用了ArrayList类来存储明文和密文对，因此需要确保输入的数量正确。  
5.程序中使用了SDES类来实现加密和解密，因此需要确保该类的实现正确。  
6.程序中使用了long类型来存储时间，因此需要确保时间的单位正确。  
7.程序中使用了System.nanoTime()方法来获取时间，因此需要确保系统时间的准确性。  

## 五.结束语
本用户指南提供了有关S-DES相关算法程序的使用手册。需要注意的是，如果想要加密敏感性的文件数据，请谨慎使用该算法。如果您有更多的加密需求，请联系专业人士而非使用该算法进行加密。
