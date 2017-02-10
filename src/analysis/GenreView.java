package analysis;

import java.util.ArrayList;

import javax.swing.JFrame;

import file.MusicFile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
 
public class GenreView{
	
	private static TableView<PieChart.Data> tableView;
	private static ArrayList<String> a;
	private static ObservableList<PieChart.Data> dataList;
   
	public void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame("Music Analysis");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
    }

    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.ALICEBLUE);


        Platform.setImplicitExit(false);
        tableView = new TableView<>();
        a = new ArrayList<String>();
        dataList =FXCollections.observableArrayList();
        
        tableView.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
            @Override
                    public TableCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };
  
        TableColumn columnMonth = new TableColumn("Genre");
        columnMonth.setCellValueFactory(
                new PropertyValueFactory<PieChart.Data,String>("name"));
  
        TableColumn columnValue = new TableColumn("Value");
        columnValue.setCellValueFactory(
                new PropertyValueFactory<PieChart.Data,Double>("pieValue"));
        
        //--- Add for Editable Cell of Value field, in Double
        columnValue.setCellFactory(cellFactory);
        columnValue.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PieChart.Data, Double>>() {
                    @Override public void handle(TableColumn.CellEditEvent<PieChart.Data, Double> t) {
                        ((PieChart.Data)t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setPieValue(t.getNewValue());
                    }
                });
        //---
        
        //--- Prepare PieChart
        final PieChart pieChart = new PieChart(dataList);
        pieChart.setTitle("Genre Analysis");
        
        //---
        
        tableView.setItems(dataList);
        tableView.getColumns().addAll(columnMonth, columnValue);
        
        //---
        
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(tableView, pieChart);
  
        root.getChildren().add(hBox);
        
        for(int i=0; i<MusicFile.filenamelist.size(); i++){
			a.add(MusicFile.genrelist.get(i));
		}
        
        String genre,year;
        for(int i=0; i<a.size(); i++){
           int count = 0;
           genre = a.get(i);
           for(int j=0; j<a.size(); j++){
              if(a.get(j).equals(genre)){
                 count++;
                 a.remove(j);
                 j=0;
                 i=0;
              }
              if(j==(a.size()-1))
                 dataList.add(new PieChart.Data(genre,count));
           }
        }

        return (scene);
    }
    
    class EditingCell extends TableCell<PieChart.Data, Double> {
    	 
        private TextField textField;
        
        public EditingCell() {}
        
        @Override
        public void startEdit() {
            super.startEdit();
            
            if (textField == null) {
                createTextField();
            }
            
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }
        
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            
            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        
        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }
  
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(Double.parseDouble(textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }
        
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
  
}