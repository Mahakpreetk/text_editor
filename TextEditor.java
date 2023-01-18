import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file;
    JMenu edit;
    JMenuItem newFile,saveFile,openFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){
        //initialise frame
        frame=new JFrame();

        //initialise menu bar
        menuBar=new JMenuBar();

        //initialise text area
        textArea =new JTextArea();

        //initialse menus(file and edit)
        file= new JMenu("File");
        edit=new JMenu("Edit");

        //Initialize menu items
        newFile=new JMenuItem("New Window");
        saveFile=new JMenuItem("Save File");
        openFile =new JMenuItem("Open File");

        //edit menu items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //Add ACTION LISTENER  to file menu items
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);

        //Add Action Listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding MENU items to FILE and EDIT menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        //set Menu bar to our frame
        frame.setJMenuBar(menuBar);

        //add Text Area to frame
        frame.add(textArea);
        //set bounds of frame
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);//set frame to be visible
    }
    public static void main(String args[]){
        TextEditor texteditor=new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       //if cut menu item is clicked
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        //if copy menu item is clicked
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        //if paste menu item is clicked
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        //if select all menu item is clicked
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        //if close menu item is clicked
        if(actionEvent.getSource()==close){
             System.exit(0);
        }
        //if new file menu item is clicked
        if(actionEvent.getSource()==newFile){
            TextEditor newWindow=new TextEditor();
        }
        //if open file menu item is clicked
        if(actionEvent.getSource()==openFile){
            //initialise the file chooser
            JFileChooser fileChooser=new JFileChooser("C:");

            //getting chosen option in file chooser
            int chooseOption=fileChooser.showOpenDialog(null); //it will give us simple file picker

            //if chosen option is approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting selected file from file chooser
                File file=fileChooser.getSelectedFile();
                //setting path of selected file
                String filePath=file.getPath();
                try{
                    //to read file and to read text written inside that
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                    //initialise two strings= intermediate for current file and output for complete content of file
                    String intermediate=" ",output=" ";
                    //read line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set text area with content of file
                    textArea.setText(output);
                }
                catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //initialize file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            //get chosen option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);
            //if chosen option is approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a new file with directory's path
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }
}