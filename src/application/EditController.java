package application;

import java.io.File;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    @FXML
    private TextField oldPathText;
    
    @FXML
    private TextField newPathText;
    
    private PathItem pathItem;
      
    private Stage dialogStage;
    
    
    public void setPathItem(PathItem pathItem){
	this.pathItem = pathItem;
	if(pathItem.getPath().toFile().isDirectory()){
	oldPathText.setText(pathItem.getPath().getFileName().toString());
	}
	else{
	oldPathText.setText(getFileNameWithoutExt(pathItem.getPath().getFileName().toString()));
	}
    }
    
    @FXML
    private void handleOk(){
	if(newPathText != null){
	    pathItem = getPathItem();
	    String uri = pathItem.getPath().getParent().toString();
	    File changed = null;
	    if(pathItem.getPath().toFile().isDirectory()){
		 changed = new File(uri+"\\"+newPathText.getText());
	    }else{
		 String fName = pathItem.getPath().getFileName().toString();
		 String ext = getExtensionOfFile(fName);
		 changed = new File(uri+"\\"+newPathText.getText()+"."+ext);
	    }
	    pathItem.getPath().toFile().renameTo(changed);
	    pathItem.setPath(Paths.get(changed.getPath()));
	    dialogStage.close();
	}
	
    }
    public PathItem getPathItem(){
	return pathItem;
    }
    

    public Stage getDialogStage() {
	return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
	this.dialogStage = dialogStage;
    }
    private String getFileNameWithoutExt(String filename){
	int pos = filename.lastIndexOf(".");
	if(pos>0){
	    filename = filename.substring(0, pos);
	}
	return filename;
    }
    private String getExtensionOfFile(String filename){
	int pos = filename.lastIndexOf(".");
	if(pos>0){
	    filename = filename.substring(pos+1);
	}
	return filename;
    }
}
