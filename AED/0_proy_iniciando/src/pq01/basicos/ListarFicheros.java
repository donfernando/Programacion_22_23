package pq01.basicos;

import java.io.File;

public class ListarFicheros {

	public static void main(String[] args) {
		File[] listado;
//		if (args.length == 0) {
//			System.out.println("Formato: progr... argumentp");
//			}
//		else {
			File f = new File("tururu");
//			File f = new File(args[0]);
//			File f = new File("recursos"+File.separator+"coordenadas.dat");
//			File f = new File(".");
			System.out.println(f.getAbsolutePath());
			System.out.printf("%s existe\n", f.exists() ? "Sí" : "No");
			if (f.isDirectory()) {
				listado = f.listFiles();
				for (File file : listado) {
					if (file.isDirectory())
						System.out.println("* " + file);
					else
						System.out.println(file);
				}
			}
//		}
	}
}