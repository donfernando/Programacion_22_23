package pipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OrdenarNumeros {

	public static void main(String[] args) {
		int num = 1;
		ArrayList<Integer> numeros = new ArrayList<>();
		Scanner leer = new Scanner(System.in);
		while (leer.hasNextInt()) {
			num = leer.nextInt();
			numeros.add(num);
		}
		leer.close();
		Collections.sort(numeros);
		System.out.println(numeros);
	}
}