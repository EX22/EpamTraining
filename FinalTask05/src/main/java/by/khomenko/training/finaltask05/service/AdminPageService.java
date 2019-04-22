package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.BlackListDao;
import by.khomenko.training.finaltask05.dao.mysql.BlackListDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.BlackList;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPageService {

    public static final int BLACKLIST_PAGE_SIZE = 10;

    public Map<String, Object> load(int page) {

        Map<String, Object> map = new HashMap<>();
        List<BlackList> blacklist = new ArrayList<>();

        BlackListDao blackListDao = new BlackListDaoImpl();
        try {
            blackListDao.setConnection( ConnectionPool.getInstance().getConnection());
            blacklist = blackListDao.readAll(page, BLACKLIST_PAGE_SIZE);
            int c = blackListDao.count();
            int pageCount = c/BLACKLIST_PAGE_SIZE + 1;
            map.put("pageCount", pageCount);
        } catch (PersistentException e) {
            e.printStackTrace();
            //TODO Add logger
        }

        map.put("filesize", 45);
        map.put("extension", "jpeg,png,bmp");
        map.put("blacklist", blacklist);


        return map;
    }

    public void addUserToBlacklist(String login){
        if (login == null){
            return;
        }
        BlackListDao blackListDao = new BlackListDaoImpl();
        try {
            blackListDao.setConnection( ConnectionPool.getInstance().getConnection());
            blackListDao.create(new BlackList(login));

        } catch (PersistentException e) {
            e.printStackTrace();
            //TODO Add logger
        }
    }
}
