package Project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class fileCopy {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/yyq/Downloads/read.txt");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            int len = (int) randomAccessFile.length();
            byte[]a = new byte[len];
            for (int i = 0; i < a.length; i++) {
                a[i] = randomAccessFile.readByte();
            }
            System.out.println("读取到的文件内容：");
            randomAccessFile.close();
			String str = new String(a,"utf-8");
			System.out.println(str);
            RandomAccessFile writeAccessFile = new RandomAccessFile("/Users/yyq/Downloads/writer.txt", "rw");
            for (int i = 0; i < a.length; i++) {
                writeAccessFile.writeByte(a[i]);
            }


            System.out.println("数据成功复制到另一文件中。");
            System.out.print("请输入要追加的内容:");
            System.out.println("---------------------");

            String input = new Scanner(System.in).nextLine();
            byte b[] = input.getBytes("utf-8");  //把输入的内容转编码格式到byte数组里面，切记，数组设置编码只能是byte类型数组
            for (int i = 0; i < b.length; i++) {
                writeAccessFile.writeByte(b[i]);
            }

//			writeAccessFile.writeUTF(input);
            writeAccessFile.close();
            System.out.println("追加成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
