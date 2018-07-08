package br.com.uniritter.tasima.arqweb.marsroverkata.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoadFile {

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the file path: ");
        String s = reader.nextLine();
        reader.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(s)));

        StringBuffer stringBuffer = new StringBuffer();
        String line = null;

        while((line =bufferedReader. readLine())!=null){
            stringBuffer.append(line).append("\n");
        }
        char x = stringBuffer.charAt(0);
        char y = stringBuffer.charAt(1);
        StringBuffer commands = stringBuffer.delete(0,2);
    }
}