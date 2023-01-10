package dpm.concurrencia;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class CuentaLetras implements Callable<Integer> {

	private File f;

	public CuentaLetras(File f) {
		this.f = f;
	}

	@Override
	public Integer call() {
		int iCh;
		char ch;
		int letras = 0;
		try(FileReader in = new FileReader(f, Charset.forName("UTF8"))) {
			while ((iCh = in.read()) != -1) {
				if (Character.isLetter((char) iCh))
					letras++;
			}
		} catch (IOException e) {
			Logger.getGlobal().severe("Error leyendo fichero "+f);
		}
		Logger.getGlobal().info("Total letras "+f+": "+letras);
		return letras;
	}

}
