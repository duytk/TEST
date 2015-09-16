package application;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class FolderTreeItem extends TreeItem<PathItem> {
    private boolean isLeaf = false;
    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeft = true;
    
    private FolderTreeItem(PathItem pathItem){
	super(pathItem);
    }
    public static TreeItem<PathItem> createNode(PathItem pathItem){
	return new FolderTreeItem(pathItem);
    }
    
    @Override
    public ObservableList<TreeItem<PathItem>> getChildren(){
	if(isFirstTimeChildren){
	    isFirstTimeChildren = false;
	    super.getChildren().setAll(buildChildren(this));
	}
	return super.getChildren();
    }
    
    @Override
    public boolean isLeaf(){
	if(isFirstTimeLeft){
	    isFirstTimeLeft = false;
	    Path path = getValue().getPath();
	    isLeaf = !Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS);
	}
	return isLeaf;
    }
    
    private ObservableList<TreeItem<PathItem>> buildChildren(TreeItem<PathItem> item) {
	Path path = item.getValue().getPath();
	if(path != null && Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)){
	    ObservableList<TreeItem<PathItem>> children = FXCollections.observableArrayList();
	    try(DirectoryStream<Path> dirs = Files.newDirectoryStream(path)){
		for(Path dir : dirs){
		    PathItem pathItem = new PathItem(dir);
		    children.add(createNode(pathItem));
		}
	    }catch(IOException ex){
		Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
	    }
	    return children;
	}
	return FXCollections.emptyObservableList();
    }
}
