package org.meng.demo.service;

import org.meng.demo.dao.sys.MenuDao;
import org.meng.demo.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date
 */
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    public List<Menu> getPers(List<Integer> menuId) {
        List<Menu> menus = null;
        if (null != menuId && menuId.size() > 0) {
            Example example = new Example(Menu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("menuId", menuId);
            menus = new ArrayList<>();
            menus = menuDao.selectByExample(example);
        }
      return menus;
    }
}
