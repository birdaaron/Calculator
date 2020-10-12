package com.cal.UI;

import com.cal.service.ExpressionService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UIController
{
    @FXML
    private TextField tfCount;
    @FXML
    private ListView<String> lvContainer,lvAnswer,lvResult;
    private ExpressionService es;
    private Stage stage;
    @FXML
    public void initialize()
    {
        es = new ExpressionService();
        initContainer();
    }
    public void setStage(Stage primaryStage)
    {
        this.stage = primaryStage;
    }
    private void initContainer()
    {
        lvContainer.setFixedCellSize(40);
        lvAnswer.setFixedCellSize(40);
        lvResult.setFixedCellSize(40);
        lvAnswer.setCellFactory(TextFieldListCell.forListView());
    }
    @FXML
    public void btnCreateClick()
    {
        List<String> answerList = new ArrayList<>();
        int count = Integer.parseInt(tfCount.getText());
        List<String> list = es.getExpressionList(count);
        lvContainer.getItems().setAll(list);
        for(int i = 0;i<count;i++)
            answerList.add("");
        lvAnswer.getItems().setAll(answerList);
    }
    @FXML
    public void btnCorrectClick()
    {
        List<String> inputList = lvAnswer.getItems();
        List<String> answerList = es.getAnswerList();
        List<String> resultList = new ArrayList<>();
        for(int i = 0;i<inputList.size();i++)
        {
            String result;
            if(inputList.get(i).equals(answerList.get(i)))
                result = "正确";
            else
                result = "错误,答案为"+answerList.get(i);
            resultList.add(result);
        }
        lvResult.getItems().setAll(resultList);
    }
    @FXML
    public void btnCreateQFileClick()
    {
        List<String> question = lvContainer.getItems();
        FileChooser fc = new FileChooser();
        fc.setTitle("保存问题文件");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fc.showSaveDialog(stage);
        try(FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);)
        {
            StringBuilder sb = new StringBuilder();
            for(String str : question)
                sb.append(str).append("\r\n");
            pw.write(sb.toString().toCharArray());
            pw.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void btnCreateAFileClick()
    {
        List<String> answer = lvAnswer.getItems();
        FileChooser fc = new FileChooser();
        fc.setTitle("保存答案文件");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fc.showSaveDialog(stage);
        try(FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);)
        {
            StringBuilder sb = new StringBuilder();
            for(String str : answer)
                sb.append(str).append("\r\n");
            pw.write(sb.toString().toCharArray());
            pw.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void btnCreateGFileClick()
    {
        int correctCount = 0;
        int wrongCount = 0;
        List<String> result = lvResult.getItems();
        StringBuilder sbCorrect = new StringBuilder();
        StringBuilder sbWrong = new StringBuilder();
        sbCorrect.append("Correct: ");
        sbWrong.append("Wrong: ");
        for(String str : result)
            if(str.contains("正确"))
                correctCount++;
            else
                wrongCount++;
        sbCorrect.append(correctCount).append("(");
        sbWrong.append(wrongCount).append("(");
        for(String str : result)
        {
            int index = result.indexOf(str)+1;
            if(str.contains("正确"))
                sbCorrect.append(index).append(",");
            else
                sbWrong.append(index).append(",");
        }
        sbCorrect.append(")").append("\r\n");
        sbWrong.append(")").append("\r\n");

        FileChooser fc = new FileChooser();
        fc.setTitle("保存结果文件");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fc.showSaveDialog(stage);
        try(FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);)
        {
            pw.write(sbCorrect.append(sbWrong).toString().toCharArray());
            pw.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
