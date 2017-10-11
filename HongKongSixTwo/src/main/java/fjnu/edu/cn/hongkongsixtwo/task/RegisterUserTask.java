package fjnu.edu.cn.hongkongsixtwo.task;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fjnu.edu.cn.hongkongsixtwo.data.ConstData;
import momo.cn.edu.fjnu.androidutils.utils.PackageUtils;

/**
 * Created by Administrator on 2017\9\14 0014.
 * 用户注册异步块
 */

public class RegisterUserTask extends AsyncTask<String, Integer, Integer> {

    public interface Callback{
        void onResult(int status);
    }

    private Callback mCallback;

    public RegisterUserTask(){

    }

    public RegisterUserTask(Callback callback){
        mCallback = callback;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        String userName = strings[0];
        String passwd = strings[1];
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            return ConstData.TaskResult.FAILED;
        }
        String url = "jdbc:mysql://120.24.18.183:3306/Lotty";
        try {
            Connection connection = DriverManager.getConnection(url, ConstData.DataBaseData.USER_NAME, ConstData.DataBaseData.PASSWORD);
            //搜索用户表
            PreparedStatement selectPrepareStatement = connection.prepareStatement("select * from User where userName=? and packageName=?");
            selectPrepareStatement.setString(1, userName);
            selectPrepareStatement.setString(2, PackageUtils.getPackageName());
            ResultSet selectSet = selectPrepareStatement.executeQuery();
            if(selectSet.first()){
                selectSet.close();
                selectPrepareStatement.close();
                connection.close();
                return ConstData.TaskResult.FAILED;
            }else{
                selectSet.close();
                selectPrepareStatement.close();
                PreparedStatement insertPrepareStatement =
                        connection.prepareStatement("insert into User(userName, password, packageName) values(?, ?, ?)");
                insertPrepareStatement.setString(1, userName);
                insertPrepareStatement.setString(2, passwd);
                insertPrepareStatement.setString(3, PackageUtils.getPackageName());
                int res = insertPrepareStatement.executeUpdate();
                insertPrepareStatement.close();
                connection.close();
                if(res == 1)
                    return ConstData.TaskResult.SUCC;
            }
        } catch (SQLException e) {
            return ConstData.TaskResult.FAILED;
        }
        return ConstData.TaskResult.FAILED;
    }

    @Override
    protected void onPostExecute(Integer result) {
        mCallback.onResult(result);
    }

    public int register(String userName, String passwd){
        return doInBackground(userName, passwd);
    }
}
