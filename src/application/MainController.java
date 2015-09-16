package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController{
    @FXML
    private TreeView<PathItem> beanTreeView;
    
    @FXML
    private ListView<PathItem> listFolderView;
    
    @FXML
    private Button editButton;
    
    @FXML
    private Button delButton;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button addButton;
    
    @FXML
    private void handleNew(ActionEvent action){
	showAddDialog();
	PathItem pathItem = new PathItem(rootPath);
	beanTreeView.setRoot(createNode(pathItem));
	listFolderView.setItems(null);
    }
    
    @FXML
    private void editAction(ActionEvent action) throws IOException{
	TreeItem<PathItem> treeSelected = beanTreeView.getSelectionModel().getSelectedItem();
	PathItem editselected = listFolderView.getSelectionModel().getSelectedItem();
	if(editselected != null){
	    showEditDialog(editselected);
	}
	listFolderView.getSelectionModel().clearSelection();
	treeSelected.setExpanded(false);
	treeSelected.setExpanded(true);
	listFolderView.setItems(null);
	listFolderView.setItems(listitem);
	
    }
    @FXML
    private void backAction(ActionEvent action){
	if(listTreeItem.size()>1){
	listTreeItem.remove(listTreeItem.size()-1);
	TreeItem<PathItem> backItem = listTreeItem.get(listTreeItem.size()-1);
	listTreeItem.remove(listTreeItem.size()-1);
//	System.out.println(listTreeItem.toString());
	beanTreeView.getSelectionModel().select(backItem);
	}
    }
    private static ObservableList<PathItem> listitem;
    private static ObservableList<TreeItem<PathItem>> listTreeItem = FXCollections.observableArrayList();
    private Path rootPath;
    public void initialize() {
	String uri = System.getProperty("user.dir")+"\\Data";
	rootPath = Paths.get(uri);
	PathItem pathItem = new PathItem(rootPath);
	beanTreeView.setRoot(createNode(pathItem));
	beanTreeView.getRoot().setExpanded(true);
	beanTreeView.setShowRoot(false);
	beanTreeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	beanTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<PathItem>>(){

	    @Override
	    public void changed(
		    ObservableValue<? extends TreeItem<PathItem>> arg0,
		    TreeItem<PathItem> arg1, TreeItem<PathItem> arg2) {
		TreeItem<PathItem> selectedItem = arg2;
		listTreeItem.add(selectedItem);
		listitem = FXCollections.observableArrayList();
		if(selectedItem != null){
		ObservableList<TreeItem<PathItem>> listChild = selectedItem.getChildren();
		for(TreeItem<PathItem> child: listChild){
		    listitem.add(child.getValue());
		}
		listFolderView.setItems(listitem);
		}
	    }
	    
	});
	listFolderView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	listFolderView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PathItem>(){

	    @Override
	    public void changed(
		    ObservableValue<? extends PathItem> arg0,
		    PathItem arg1, PathItem arg2) {
		delButton.focusedProperty().addListener(new ChangeListener<Boolean>(){

		    @Override
		    public void changed(
			    ObservableValue<? extends Boolean> arg0,
			    Boolean arg1, Boolean arg2) {
			PathItem selected = listFolderView.getSelectionModel().getSelectedItem();
 			if(selected != null){
			listFolderView.getItems().remove(selected);
			recursiveDelete(selected.getPath().toFile()); 
			}
			listFolderView.getSelectionModel().clearSelection();
			listitem = listFolderView.getItems();
			listFolderView.setItems(null);
			listFolderView.setItems(listitem);
			beanTreeView.setRoot(createNode(pathItem));
		    }
		    
		});		
	    }
	    
	});
    }

    private TreeItem<PathItem> createNode(PathItem pathItem){
	return FolderTreeItem.createNode(pathItem);
    }
    
    private void showEditDialog(PathItem pathItem){
	try{
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("EditPath.fxml"));
	StackPane page = (StackPane) loader.load();
	
	Stage dialogStage = new Stage();
	dialogStage.setTitle("Chỉnh sửa");
	dialogStage.initModality(Modality.APPLICATION_MODAL);
	Scene scene = new Scene(page);
	dialogStage.setScene(scene);
	
	EditController controller = loader.getController();
	controller.setPathItem(pathItem);
	controller.setDialogStage(dialogStage);
	dialogStage.showAndWait();
	}catch(IOException e){
	    e.printStackTrace();
	}
    }
    private void showAddDialog(){
	try {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("AddPath.fxml"));
	HBox page =(HBox) loader.load();
	
	Stage dialogStage = new Stage();
	dialogStage.setTitle("Thêm mới");
	dialogStage.initModality(Modality.APPLICATION_MODAL);
	Scene scene = new Scene(page);
	dialogStage.setScene(scene);
	
	AddController controller = loader.getController();
	controller.setDialogStage(dialogStage);
	dialogStage.showAndWait();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    private void recursiveDelete(File file){
	if(file.isDirectory()){
	    for(File f : file.listFiles()){
		recursiveDelete(f);
	    }
	}
	file.delete();
    }
}
