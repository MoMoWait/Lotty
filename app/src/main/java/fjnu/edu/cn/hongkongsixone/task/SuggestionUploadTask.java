package fjnu.edu.cn.beijingquickthree.task;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fjnu.edu.cn.beijingquickthree.data.ConstData;
import momo.cn.edu.fjnu.androidutils.utils.PackageUtils;

/**
 * Created by Administrator on 2017\9\14 0014.
 * 意见反馈
 */

public class SuggestionUploadTask extends AsyncTask<String, Integer, Integer> {
    public interface Callback{
        void onResult(int status);
    }

    private RegisterUserTask.Callback mCallback;

    public SuggestionUploadTask(RegisterUserTask.Callback callback){
        mCallback = callback;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        String suggestionInfo = strings[0];
        String phoneInfo = strings[1];
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            return ConstData.TaskResult.FAILED;
        }
        String url = "jdbc:mysql://120.24.18.183:3306/Lotty";
        try {
            Connection connection = DriverManager.getConnection(url, ConstData.DataBaseData.USER_NAME, ConstData.DataBaseData.PASSWORD);
            //搜索用户表
            PreparedStatement insertStatement = connection.prepareStatement("insert into Suggestion(info, phone, packageName) values(?, ?, ?)");
            insertStatement.setString(1, suggestionInfo);
            insertStatement.setString(2, phoneInfo);
            insertStatement.setString(3, PackageUtils.getPackageName());
            int res = insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
            if(res == 1)
                return ConstData.TaskResult.SUCC;
        } catch (SQLException e) {
            return ConstData.TaskResult.FAILED;
        }
        return ConstData.TaskResult.FAILED;
    }

    @Override
    protected void onPostExecute(Integer result) {
        mCallback.onResult(result);
    }
}
