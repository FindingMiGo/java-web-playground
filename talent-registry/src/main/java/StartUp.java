import java.util.List;

import dao.JaniesDao;
import exception.JaniesException;
import model.Janies;

public class StartUp {

    public static void main(String[] args) {
        try {
            // JaniesDaoのインスタンスを作成
            JaniesDao janiesDao = new JaniesDao();

            // findAllJaniesメソッドを呼び出してJaniesのリストを取得
            List<Janies> janiesList = janiesDao.findAllJanies();

            // 取得したJaniesのリストを処理する例
            for (Janies janies : janiesList) {
                System.out.println(janies.toString());
            }

            // JaniesDaoの後片付け（リソースの解放など）
//            janiesDao.close(); // closeメソッドは必要に応じて実装してください

        } catch (JaniesException e) {
            // 例外処理
            e.printStackTrace();
        }
    }
}
