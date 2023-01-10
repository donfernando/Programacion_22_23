import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SumaCallable {
	private static final Instant INICIO = Instant.now();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		List<Callable<Integer>> tareas = Arrays.asList(() -> {
			log("Inicio de la tarea");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 8;
		}, () -> {
			log("Inicio de la tarea");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 2;
		}, () -> {
			log("Inicio de la tarea");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 5;
		});

		List<Future<Integer>> responses = new ArrayList<>();

//		for (Callable<Integer> t : tareas) {
//			responses.add(executor.submit(t));
//		}
		executor.invokeAll(tareas);
		executor.shutdown();

		int total = 0;
		for (Future<Integer> f : responses) {
			total += f.get();
		}
		log("Este es el resultado es " + total);

	}

	private static void log(Object mensaje) {
		Logger.getGlobal().info(String.format("%s miliSeg. [%s] %s", Duration.between(INICIO, Instant.now()).getNano()/1000000.0,
				Thread.currentThread().getName(), mensaje.toString()));
	}
}