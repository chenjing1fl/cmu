package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.RoleMenu;
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
public class RoleMenuServiceImplTest_false extends SpringIOC {

    RoleMenuService service;

    @Before
    public  void init(){
        service = ac.getBean(RoleMenuService.class);
    }
    //����
    @Test
    public void save() {
        RoleMenu roleMenu = new RoleMenu("7cd757d6508e4f31a2a2318d06a68c5a","de05ce34c9a343eaa5e08244d1e93425",null,null);
        boolean success = service.insert(roleMenu);
        System.out.println("RoleMenu:"+roleMenu);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "7cd757d6508e4f31a2a2318d06a68c5a";
        RoleMenu roleMenu = service.queryById(keyId);
        System.out.println(roleMenu);
        Assert.assertTrue(roleMenu!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        RoleMenu roleMenuParam = new RoleMenu();
        roleMenuParam.setMenuId("0");
        List<RoleMenu> roleMenus = service.list(roleMenuParam);
        for (RoleMenu roleMenu : roleMenus ) {
            System.out.println(roleMenu);
        }
        Assert.assertTrue(roleMenus.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "7cd757d6508e4f31a2a2318d06a68c5a";
        RoleMenu roleMenu = service.queryById(keyId);
        System.out.println("����ǰ:"+roleMenu);
        roleMenu.setMenuId("de05ce34c9a343eaa5e08244d1e93425");
        boolean success = service.updateById(roleMenu);
        System.out.println("success:"+success);
        RoleMenu newRoleMenu = service.queryById(keyId);
        System.out.println("���º�:"+newRoleMenu);
        Assert.assertTrue(newRoleMenu.getMenuId().contains("de05ce34c9a343eaa5e08244d1e93425"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        //String keyId = CmuStringUtil.UUID();
        RoleMenu roleMenu = new RoleMenu("116f3c9b61a048c690cdb5f6ad5012b1","de05ce34c9a343eaa5e08244d1e93425",null,null);
        boolean success = service.insert(roleMenu);
        System.out.println("����ɹ���"+success);
        RoleMenu queryRoleMenu = service.queryById("116f3c9b61a048c690cdb5f6ad5012b1");
        System.out.println("������ѯ"+queryRoleMenu);
        queryRoleMenu.setValid("0");
        queryRoleMenu.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryRoleMenu);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}