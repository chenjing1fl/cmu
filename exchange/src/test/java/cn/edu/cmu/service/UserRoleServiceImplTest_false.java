package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.UserRole;
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
public class UserRoleServiceImplTest_false extends SpringIOC {

    UserRoleService service;

    @Before
    public  void init(){
        service = ac.getBean(UserRoleService.class);
    }
    //����
    @Test
    public void save() {
        UserRole userRole = new UserRole("3d331604574846cb9032569a3759fe3e","7cd757d6508e4f31a2a2318d06a68c5a",null,null);
        boolean success = service.insert(userRole);
        System.out.println("UserRole:"+userRole);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "e6a186552a5a4707a3d80d98e2d5f50b";
        UserRole userRole = service.queryById(keyId);
        System.out.println(userRole);
        Assert.assertTrue(userRole!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        UserRole userRoleParam = new UserRole();
        userRoleParam.setRoleId("0");
        List<UserRole> userRoles = service.list(userRoleParam);
        for (UserRole userRole : userRoles ) {
            System.out.println(userRole);
        }
        Assert.assertTrue(userRoles.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "e6a186552a5a4707a3d80d98e2d5f50b";
        UserRole userRole = service.queryById(keyId);
        System.out.println("����ǰ:"+userRole);
        userRole.setRoleId("02");
        boolean success = service.updateById(userRole);
        System.out.println("success:"+success);
        UserRole newUserRole = service.queryById(keyId);
        System.out.println("���º�:"+newUserRole);
        Assert.assertTrue(newUserRole.getRoleId().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        UserRole userRole = new UserRole("d538b9ce55a04737acd4ac3bef6903ed","7cd757d6508e4f31a2a2318d06a68c5a",null,null);
        boolean success = service.insert(userRole);
        System.out.println("����ɹ���"+success);
        UserRole queryUserRole = service.queryById("d538b9ce55a04737acd4ac3bef6903ed");
        System.out.println("������ѯ"+queryUserRole);
        queryUserRole.setValid("0");
        queryUserRole.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryUserRole);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}