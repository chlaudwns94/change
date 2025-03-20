package com.korit.mcdonaldkiosk.mapper;

import com.korit.mcdonaldkiosk.entity.Menu;
import com.korit.mcdonaldkiosk.entity.MenuPrice;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> selectAllMenus();

    MenuPrice find1ByMenuPriceId(int menuPriceId);

}