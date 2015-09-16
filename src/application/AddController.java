package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddController {
    @FXML
    private Button folderChosen;
    
    @FXML
    private Button fileChosen;
    
    @FXML
    private TextField fileText;
    
    @FXML
    private TextField dirInputText;
    
    @FXML
    private TextField folderText;
    
    @FXML private Button okButton1;
    
    @FXML private Button cancelButton1;
    
    @FXML private Button okButton2;
    
    @FXML private Button cancelButton2;
    
    @FXML private ChoiceBox<String> choiceBox;
    
    @FXML private ChoiceBox<String> resNameChoice;
    
    @FXML private ChoiceBox<String> resTypeChoice;
    
    @FXML private ChoiceBox<String> dataTypeChoice;
          
    @FXML private TextArea desText;
    
    private Stage dialogStage;

    public Stage getDialogStage() {
	return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
	this.dialogStage = dialogStage;
    }
    
    
    private List<File> selectedFiles;
    private File selectedDir;
    @FXML
    private void fileHandle(ActionEvent action){
	FileChooser fileChooser = new FileChooser();
        selectedFiles =fileChooser.showOpenMultipleDialog(getDialogStage());
        if(selectedFiles != null){
	fileText.setText(selectedFiles.toString());
        }
    }
    
    @FXML
    private void folderHandle(ActionEvent action){
	DirectoryChooser dirChooser = new DirectoryChooser();
	selectedDir = dirChooser.showDialog(getDialogStage());
	if(selectedDir!= null){
	folderText.setText(selectedDir.getPath().toString());
	}
    }
    
    @FXML
    private void okHandle2(ActionEvent action){
	String uri = System.getProperty("user.dir")+"\\Data";
	PathItem pathItem = new PathItem(null);
	String selectedType = resTypeChoice.getSelectionModel().getSelectedItem();
	String selectedName = resNameChoice.getSelectionModel().getSelectedItem();
	String selectedData = dataTypeChoice.getSelectionModel().getSelectedItem();
//	System.out.println(selectedType+  "  " + selectedName+ "   " + selectedData);
	if(selectedType!= null && selectedName!=null && selectedData!= null){
	    if(selectedDir != null || selectedFiles!= null){
		if(selectedDir != null){
		    pathItem.setPath(Paths.get(uri+"\\"+selectedType+"\\"+selectedName+"\\"+selectedData+"\\"+selectedDir.toPath().getFileName().toString()));
		    try {
			Files.move(selectedDir.toPath(), pathItem.getPath(),StandardCopyOption.REPLACE_EXISTING);
		    } catch (IOException e) {
			
			e.printStackTrace();
		    }
		}
		if(selectedFiles != null){
		    for(File f : selectedFiles){
		    pathItem.setPath(Paths.get(uri+"\\"+selectedType+"\\"+selectedName+"\\"+selectedData+"\\"+f.toPath().getFileName().toString()));
		    try {
			 Files.move(f.toPath(), pathItem.getPath(), StandardCopyOption.REPLACE_EXISTING);
		    } catch (IOException e) {
			   
			 e.printStackTrace();
			}
		    }
		}
	    }else{
		System.out.println("vui lòng chọn file hoặc folder");
	    }
	}else{
	    System.out.println("Vui lòng chọn đủ");
	}
	getDialogStage().close();
    }
    
    @FXML
    private void cancelHandle2(ActionEvent action){
	getDialogStage().close();
    }
    
    @FXML
    private void cancelHandle1(ActionEvent action){
	getDialogStage().close();
    }
    
    @FXML
    private void okHandle1(ActionEvent action){
	String selectedType = choiceBox.getSelectionModel().getSelectedItem();
	PathItem pathItem = new PathItem(null);
	if(dirInputText!=null){
	if(selectedType.equals(GTK)){
	    String uri = System.getProperty("user.dir")+"\\Data";
	    pathItem.setPath(Paths.get(uri+"\\"+GTK));
	    File f = new File(uri+"\\"+GTK+"\\"+dirInputText.getText());
	    f.mkdir();
	    File f1 = new File(uri+"\\"+GTK+"\\"+dirInputText.getText()+"\\3D");
	    f1.mkdir();
	    File f2 = new File(uri+"\\"+GTK+"\\"+dirInputText.getText()+"\\Image");
	    f2.mkdir();
	    File f3 = new File(uri+"\\"+GTK+"\\"+dirInputText.getText()+"\\Properties");
	    f3.mkdir();
	    File f4 = new File(uri+"\\"+GTK+"\\"+dirInputText.getText()+"\\Video");
	    f4.mkdir();
	}
	if(selectedType.equals(HHTA)){
	    String uri = System.getProperty("user.dir")+"\\Data";
	    pathItem.setPath(Paths.get(uri+"\\"+GTK));
	    File f = new File(uri+"\\"+HHTA+"\\"+dirInputText.getText());
	    f.mkdir();
	    File f1 = new File(uri+"\\"+HHTA+"\\"+dirInputText.getText()+"\\Image");
	    f1.mkdir();
	    File f2 = new File(uri+"\\"+HHTA+"\\"+dirInputText.getText()+"\\Properties");
	    f2.mkdir();
	    File f3 = new File(uri+"\\"+HHTA+"\\"+dirInputText.getText()+"\\Voice");
	    f3.mkdir();
	}
	if(selectedType.equals(KGDT)){
	    String uri = System.getProperty("user.dir")+"\\Data";
	    pathItem.setPath(Paths.get(uri+"\\"+GTK));
	    File f = new File(uri+"\\"+KGDT+"\\"+dirInputText.getText());
	    f.mkdir();
	    File f1 = new File(uri+"\\"+KGDT+"\\"+dirInputText.getText()+"\\3D");
	    f1.mkdir();
	    File f2 = new File(uri+"\\"+KGDT+"\\"+dirInputText.getText()+"\\Image");
	    f2.mkdir();
	    File f3 = new File(uri+"\\"+KGDT+"\\"+dirInputText.getText()+"\\Properties");
	    f3.mkdir();
	    File f4 = new File(uri+"\\"+KGDT+"\\"+dirInputText.getText()+"\\Video");
	    f4.mkdir();
	    File f5 = new File(uri+"\\"+KGDT+"\\"+dirInputText.getText()+"\\Voice");
	    f5.mkdir();
	}
	if(selectedType.equals(VB)){
	    String uri = System.getProperty("user.dir")+"\\Data";
	    pathItem.setPath(Paths.get(uri+"\\"+GTK));
	    File f = new File(uri+"\\"+VB+"\\"+dirInputText.getText());
	    f.mkdir();
	    File f1 = new File(uri+"\\"+VB+"\\"+dirInputText.getText()+"\\Properties");
	    f1.mkdir();
	    File f2 = new File(uri+"\\"+VB+"\\"+dirInputText.getText()+"\\Text");
	    f2.mkdir();
	}
	}else{
	    System.out.println("Vui lòng nhập tên");
	}
	getDialogStage().close();
	
    }
    private final String GTK = "Gốc thể khối";
    private final String HHTA = "Hội họa, Tranh ảnh";
    private final String KGDT = "Không gian di tích";
    private final String VB = "Văn bản";
    private ObservableList<String> listchoiceBox = FXCollections.observableArrayList(GTK,HHTA,KGDT,VB);
    public void initialize() {
	String uri = System.getProperty("user.dir")+"\\Data";
	choiceBox.setItems(listchoiceBox);
	choiceBox.getSelectionModel().selectFirst();
	resTypeChoice.setItems(listchoiceBox);
	resTypeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

	    @Override
	    public void changed(ObservableValue<? extends String> arg0,
		    String arg1, String arg2) {
		// TODO Auto-generated method stub
		dataTypeChoice.getSelectionModel().clearSelection();
		String selectedType = resTypeChoice.getSelectionModel().getSelectedItem();
		PathItem pathItem = new PathItem(null);
		if(selectedType!= null){		    
			pathItem.setPath(Paths.get(uri+"\\"+selectedType));
			File f = pathItem.getPath().toFile();
		        ObservableList<String> listchildChoiceBox = FXCollections.observableArrayList(f.list());
//		        System.out.println(listchildChoiceBox.toString());
			resNameChoice.setItems(listchildChoiceBox);		  
		}
	    }
	    
	});
	resNameChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

	    @Override
	    public void changed(ObservableValue<? extends String> arg0,
		    String arg1, String arg2) {
		String selectedType = resTypeChoice.getSelectionModel().getSelectedItem();
		String selectedName = resNameChoice.getSelectionModel().getSelectedItem();
		PathItem pathItem = new PathItem(null);
		if(selectedName != null){
		    pathItem.setPath(Paths.get(uri+"\\"+selectedType+"\\"+selectedName));
		    File f = pathItem.getPath().toFile();
		    ObservableList<String> listchildChoiceBox = FXCollections.observableArrayList(f.list());
//		    System.out.println(listchildChoiceBox.toString());
		    dataTypeChoice.setItems(listchildChoiceBox);
		}
	    }
	    
	});
	
    }
}
