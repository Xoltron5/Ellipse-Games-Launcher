import java.io.IOException;
import javafx.stage.Stage;

public abstract class Page {

    final private String FXMLFILEPATH = null;
    final private String CSSFILEPATH = null;
    final private String CSS = null;
    
    public abstract void initializeScene(Stage primaryStage) throws IOException;

    public String getFXMLFILEPATH() {
        return FXMLFILEPATH;
    }

    public String getCSSFILEPATH() {
        return CSSFILEPATH;
    }
    
    public String getCSS() {
        return CSS;
    }
}
