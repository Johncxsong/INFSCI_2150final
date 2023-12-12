# IS2150/TEL2810 Introduction to Security  

Name: John Song  
Date: 12/12/2023  
github: [CODE](https://github.com/Johncxsong/INFSCI_2150final.git)  


## How to run  
1. git clone the whole project.  
2. Use Intellj IDEA to open it.  
3. Open src folder to run as below.  


## 1. Message Digest  
#### folder: src--> Main.java  
using `SHA-256` & `MD5` to encrypt message and output  

## 2.A Authentication  

#### folder: src--> Authentication_2A is main class to run. 
this contains two sub classes--> `ProtectedClient.java` & `ProtectedServer.java` 


## 2.B Signature  

#### folder: src--> Signature_2B is main class to run.  
contains two sub classes--> `ElGamalAlice` & `ElGamalBob`  
[Reference for formula](https://medium.com/@shayanmakwana10/elgamal-digital-signature-scheme-860cfb177388)  


## 2.C Encryption  

#### folder: src--> Encryption_2C_generatingDES & Encryption_2C  
contains two sub classes --> `CipherClient` & `CipherServer`  

since server side and client side execute at one computer, I generated DES key first to read file to get keys.  
If you are trying to generate in the processing of sending message, one computer could not process client and server at same time so DES key may dismatch.  

#### Operation:  1. execute Encryption_2C_generatingDES --> 2. Encryption_2C   


## 2.D Public-Key System  
#### folder: src --> Public_Key_RSA_2D   
contains three classes --> `Public_key_2D` & `Sender_2D` & `Receiver_2D`  
using RSA schema to generate 1. Confidentility 2. Integrity.  
In the program, you could type `1` for confidenlity.   type `2` for Integerity.  



## 2.E   X.509 Certificates  

#### folder: src --> Certificates509_2E   

use command line to genereate keys. 
```
keytool -genkey -keyalg RSA -alias John -keystore keystore.jks  
keytool -export -alias John -storepass 123456 -file server.cer -keystore keystore.jks 
```

contains two sub classes --> `Client_2E` & `Server_2E`  


## Questions:  

### 1. What are the limitations of using self-signed certificates?  

- Lack of trust by client and browsers   
Browsers and clients do not inherently trust self-signed certificates.  
- No Validation of Identity  
self-signed do not provide third-party validation.  
- Man-in-the-Middle attacks  
since the third-party is hard to validate, middle-man could attack.  


### 2. What are self-signed certificates useful for?    
- Development and Testing Environment  
this allow developing and test to reduce the cost. Self-signed provides a covenient and cost-free to implement SSL.  

- Personal Project  
For individual project or small project, it could use self-signed.  


- Cost  
if people think about cost, self-signed is cheaper than CA-signed certificate.









