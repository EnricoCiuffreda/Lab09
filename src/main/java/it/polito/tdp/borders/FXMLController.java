
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    @FXML
    private ComboBox<Country> boxCountries;
    @FXML
    private Button btnpercorso;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	String stringa=txtAnno.getText();
    	int anno;
    	try {
    	anno=Integer.parseInt(stringa);
    	}  catch(Throwable t){
    		txtResult.appendText("Errore input");
    		return;
    	}
    	if(anno<1816 || anno>2016) {
    		txtResult.appendText("inserisci numero tra 1816 e 2016");
    		return;
    	}
    	model.creaGrafo(anno);
    	for(Country c:model.getPaesi()) {
    		txtResult.appendText(c.toString()+"   "+c.getCollegamentifino()+"\n");
    	}
    	for(Set<Country> s: model.staticonnessi2()) {
    		txtResult.appendText("un sotto-grafo di "+s.size()+"\n");
    		//txtResult.appendText(s.toString()+"\n");
    	}
    	boxCountries.getItems().addAll(this.model.getPaesi());

    }
    
    @FXML
    void CercaPercorso(ActionEvent event) {
    	txtResult.clear();
    	Country paese=boxCountries.getValue();
    	List<Country> percorso=model.trovaPercorso2(paese);
    	txtResult.appendText("dal Paese selezionato("+paese+") si possono raggiungere "+percorso.size()+" Paesi:\n"+percorso.toString());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxCountries != null : "fx:id=\"boxCountries\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnpercorso != null : "fx:id=\"btnpercorso\" was not injected: check your FXML file 'Scene.fxml'.";



    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
