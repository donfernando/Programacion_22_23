package idiomas;

import java.io.IOException;
import java.util.Properties;

public class NProperties extends Properties{

    private static final long serialVersionUID = 1L;

	public NProperties(String idioma){
        if(idioma.equals("ES")){//español
            getProperties("_ES.properties");
        }else if(idioma.equals("EN")){//Ingles
            getProperties("_EN.properties");
        }else{//sino por default idioma español
            getProperties("ES");
        }
    }

    /* se leen las propiedades */
     private void getProperties(String idioma) {
        try {                
            this.load( getClass().getResourceAsStream(idioma) );
        } catch (IOException ex) {            
        }
   }

}