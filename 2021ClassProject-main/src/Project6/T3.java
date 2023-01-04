package Project6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class T3 {

    public static File src = new File("/Users/yyq/Downloads/source.txt");
    public static File dest = new File("/Users/yyq/Downloads/encrypt.txt");
    public static LinkedList<Byte> codebook = new LinkedList();
    public static LinkedList<Byte> alphabet = new LinkedList();

    public static void main(String[] args) throws IOException {


        codebook.add((byte) 't');
        codebook.add((byte)'o');
        codebook.add((byte)'i');
        codebook.add((byte) 'a');
        codebook.add((byte) 'n');
        codebook.add((byte)'d');
        codebook.add((byte)'e');
        codebook.add((byte)'g');
        codebook.add((byte)'h');
        codebook.add((byte)'z');
        codebook.add((byte)'b');
        codebook.add((byte)'k');
        codebook.add((byte)'f');
        codebook.add((byte)'j');
        codebook.add((byte)'m');
        codebook.add((byte)'c');
        codebook.add((byte)'l');
        codebook.add((byte)'p');
        codebook.add((byte)'y');
        codebook.add((byte)'v');
        codebook.add((byte)'x');
        codebook.add((byte)'q');
        codebook.add((byte)'r');
        codebook.add((byte)'w');
        codebook.add((byte)'u');
        codebook.add((byte)'s');

        char c = 'a';
        //System.out.println((int)c);
        System.out.println("编码密码表：");
        for(int i =0;i<26;i++){
            alphabet.add((byte)c);
            c++;
        }

        for(byte i:alphabet){
            System.out.print((char) i);
        }
        System.out.println();
        for(byte i:codebook){
            System.out.print((char) i);
        }
        System.out.println();
        System.out.println("---------------------------");
        System.out.print("解码后文件：");


        encryptFile(src);
        System.out.println();
        decryptFile(dest);




    }

    private static void encryptFile(File src) {
        try {
            long len = src.length();
            byte[] bytes = new byte[(int) len];
            try (FileInputStream in = new FileInputStream(src)) {
                int readLength = in.read(bytes);
                if ((long) readLength < len) {
                    throw new IOException("文件读取失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            int initpos = 0;
            for (int i = 0; i < bytes.length; i++) {
                //bytes[i] = (byte) (bytes[i] + 1);
                //System.out.println(bytes[i]);
                if(bytes[i]<97){
                    initpos = bytes[i]-65;
                    bytes[i] = (byte) (codebook.get(initpos)-32);
                }else {
                    initpos = bytes[i]-97;
                    bytes[i] = codebook.get(initpos);
                }


            }


            try (FileOutputStream out = new FileOutputStream(dest)) {
                out.write(bytes);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptFile(File encryptFile){
        try {
            long len = encryptFile.length();
            byte[] bytes = new byte[(int) len];
            try (FileInputStream in = new FileInputStream(encryptFile)) {
                int readLength = in.read(bytes);
                if ((long) readLength < len) {
                    throw new IOException("文件读取失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            for (int i = 0; i < bytes.length; i++) {
                if(bytes[i]<97){
                    for(int j=0;j<codebook.size();j++){
                        if(bytes[i]==(codebook.get(j)-32)){
                            bytes[i] = (byte) (alphabet.get(j)-32);
                            break;
                        }
                    }
                }else {
                    for(int j=0;j<codebook.size();j++){
                        if(bytes[i]==codebook.get(j)){
                            bytes[i] = alphabet.get(j);
                            break;
                        }
                    }
                }

            }

            for(int i=0;i<bytes.length;i++){
                System.out.print((char) bytes[i]);
            }
            System.out.println();



        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
