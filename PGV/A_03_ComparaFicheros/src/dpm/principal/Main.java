package dpm.principal;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import dpm.concurrencia.CuentaLetras;

public class Main {
	public static void main(String[] args) {
		Instant inicio = Instant.now(); 
		try {
			System.out.println();
			int r = comparaLetras(new File(args[0]), new File(args[1]));
			Duration tiempo = Duration.between(Instant.now(),inicio);
			if (r < 0)
				System.out.printf("El fichero %s tiene menos letras que el %s.\n", args[0], args[1]);
			else if (r > 0)
				System.out.printf("El fichero %s tiene menos letras que el %s.\n", args[1], args[0]);
			else
				System.out.printf("Los ficheros %s y %s tienen el mismo número de letras.\n", args[0], args[1]);
			System.out.printf("La ejecución tardó %s",tiempo);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static Integer comparaLetras(File f1, File f2) throws InterruptedException, ExecutionException {
		CuentaLetras c1 = new CuentaLetras(f1);
		CuentaLetras c2 = new CuentaLetras(f2);
		ExecutorService exec = Executors.newFixedThreadPool(2);
		List<Future<Integer>> responses = Arrays.asList(exec.submit(c1), exec.submit(c2));
		return responses.get(0).get() - responses.get(1).get();
	}
}
