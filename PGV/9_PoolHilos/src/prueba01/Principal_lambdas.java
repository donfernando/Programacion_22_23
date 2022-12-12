package prueba01;


import java.util.concurrent.*;
import java.util.logging.Logger;

public class Principal_lambdas {
	public static void main(String[] args) {
		 
		int poolSize = 2;
		int maxPoolSize = 2;
		long keepAliveTime = 10;
		final ArrayBlockingQueue<Runnable> queue = 
		        new ArrayBlockingQueue<Runnable>(5);
		ThreadPoolExecutor threadPool = 
		        new ThreadPoolExecutor(poolSize, maxPoolSize,
		              keepAliveTime, TimeUnit.SECONDS, queue);
		                   
		Runnable[] tasks = {          // y le asignamos tareas
				() -> {	try {
			            	System.out.println("Ana entra... " );
			                Thread.sleep(1000);
			                System.out.println("Ana se va... " );			                    
			            } catch (InterruptedException ie){   }
					},
			    () -> {	try {
			            	System.out.println("Abraham entra... " );
			                Thread.sleep(1000);
			                System.out.println("Abraham se va... " );			                    
			    		} catch (InterruptedException ie){   }
			        },
			    () -> {	try {
			            	System.out.println("Rosa entra... " );
			                Thread.sleep(1000);
			                System.out.println("Rosa se va... " );			                    
			    		} catch (InterruptedException ie){   }
				}
		};

		//Poner a ejecutar dos tareas y una que quedará en cola:
		for(int i=0; i<3; i++){
		    threadPool.execute(tasks[i]);
		    System.out.println("Tareas:" + queue.size());
		}  
		 
		//Encolar otra tarea más que declaramos aquí mismo:  
		threadPool.execute(
				() -> {
		            for (int i = 0; i < 5; i++) {
		                try {
		                    System.out.println(" i=" + i);
		                    Thread.sleep(1000);
		                } catch (InterruptedException ie){ }
		            }
				});    
		
		//Ejecuta las tareas que queden pero ya no acepta nuevas:
		threadPool.shutdown();
		
		try {
			threadPool.execute(
					() -> {
						System.out.println(" ... no lleeeego !!!");
					});
		} catch (RejectedExecutionException e) {
			Logger.getGlobal().warning("Ya no se aceptan tareas");
		}    
	    System.out.println("y tareas:" + queue.size());
		 
	}
}
