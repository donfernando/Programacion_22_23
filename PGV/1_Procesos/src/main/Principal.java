package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {
	public static void main(String[] args) {
		Runtime builder = Runtime.getRuntime();
		try {
			Process out = builder.exec("ls -l");
			BufferedReader bf = new BufferedReader(new InputStreamReader(out.getInputStream()));
			String linea;
			while ((linea = bf.readLine()) != null) {
				System.out.println(linea.toUpperCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
