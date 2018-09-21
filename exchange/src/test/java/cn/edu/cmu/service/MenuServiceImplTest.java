package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.Menu;
import cn.edu.cmu.domain.User;
import cn.edu.cmu.framework.utils.CmuStringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;


/**
 * Service��Ĳ�����
 *
 * ����ɾ�Ĳ�
 */
public class MenuServiceImplTest extends SpringIOC {

    MenuService service;

    @Before
    public  void init(){
        service = ac.getBean(MenuService.class);
    }
    //����
    @Test
    public void save() {
        Menu menu = new Menu(CmuStringUtil.UUID(),"01","01","01","01","01",null,null);
        boolean success = service.insert(menu);
        System.out.println("Menu:"+menu);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "de05ce34c9a343eaa5e08244d1e93425";
        Menu menu = service.queryById(keyId);
        System.out.println(menu);
        Assert.assertTrue(menu!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        Menu menuParam = new Menu();
        menuParam.setMenuName("0");
        List<Menu> menus = service.list(menuParam);
        for (Menu menu : menus ) {
            System.out.println(menu);
        }
        Assert.assertTrue(menus.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "de05ce34c9a343eaa5e08244d1e93425";
        Menu menu = service.queryById(keyId);
        System.out.println("����ǰ:"+menu);
        menu.setMenuName("02");
        boolean success = service.updateById(menu);
        System.out.println("success:"+success);
        Menu newMenu = service.queryById(keyId);
        System.out.println("���º�:"+newMenu);
        Assert.assertTrue(newMenu.getMenuName().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        Menu menu = new Menu(keyId,"01","01","01","01","01",null,null);
        boolean success = service.insert(menu);
        System.out.println("����ɹ���"+success);
        Menu queryMenu = service.queryById(keyId);
        System.out.println("������ѯ"+queryMenu);
        queryMenu.setValid("0");
        queryMenu.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryMenu);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}