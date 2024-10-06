# S-DES程序用户指南
## 一.简介
本开发手册提供了一个基于S-DES（Simplified Data Encryption Standard）算法的加解密程序的用户说明文档，意在能够满足用户对于二进制加解密和ASCLL码字符串加解密等基础需要。以及按照用户需求在已知明密文的前提下暴力破解出密钥等主要功能。

## 二.功能描述

本程序提供以下四个主要功能:

1.对二进制编码加解密或者字符文本进行加解密：

（1）加密：使用S-DES算法对输入的明文进行加密。

（2）解密：使用S-DES算法对输入的密文进行解密。

2.使用S-DES算法对输入的明密文对进行暴力破解：

（1）输入多对明密文并得到相应的密钥与破解时间（可能并没有相应的密钥）。

（2）输入一对明密文并得到一个或者多个密钥。

## 三.使用方法

3.1加解密操作

1.运行Main即可展示加加密UI界面，Main为如下。
public class Main {
    public static void main(String[] args) {
        Cracker cracker=new Cracker();
        Window window=new Window();
    }
}

2.通过提示输入符合要求的二进制数明文，密钥。

3.点击加密（或解密）按钮获取加密（或解密）后的密文（或明文）。
     
4.加解密UI通过ASCLL码进行同样的操作

3.2破解密钥操作

1.运行Main文件。
public class Main {
    public static void main(String[] args) {
        Cracker cracker=new Cracker();
        cracker.crack();
        Window window=new Window();
    }
}

2.在终端输入你想要破解的明密文对数量以及相应的明密文对。

3.得到破解的密钥、破解时间。

tip：输入多对明密文对可能没有相应的密钥。

3.3封闭测试

1.运行Main文件，修改为如下。
public class Main {
    public static void main(String[] args) {
        Cracker cracker=new Cracker();
        cracker.closedTest();
        Window window=new Window();
    }
}

2.在终端输入对应的明密文对。

3.可能得到多个破解的密钥。

## 四.加解密参数
密钥：S-DES算法使用一个10位二进制密钥。请确保输入正确的密钥以保证加密解密的一致性。

二进制文本：二进制文本需要保证输入的文本符合二进制格式。

字符文本：字符文本需要保证输入的文本符合ASCⅡ码规定。

## 五.安全性
S-DES算法作为一个简单的加密算法，注定不适用于现在的安全通信。由于其密钥空间相对较小，容易受到暴力破解等威胁。可以注意到，此应用程序中也给出了暴力破解的相关代码。因此，SDES代码并不适用于保护安全数据。此外，当信息输入不符合标准，如bit长度不正确时，系统会显示输入信息有误并进行报错。
