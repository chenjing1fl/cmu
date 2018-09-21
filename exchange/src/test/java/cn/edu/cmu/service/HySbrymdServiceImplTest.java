package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.HySbrymd;
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
public class HySbrymdServiceImplTest extends SpringIOC {

    HySbrymdService service;

    @Before
    public  void init(){
        service = ac.getBean(HySbrymdService.class);
    }
    //����
    @Test
    public void save() {
        HySbrymd hySbrymd = new HySbrymd(CmuStringUtil.UUID(), "01","01","01","01","01","01",null,null);
        boolean success = service.insert(hySbrymd);
        System.out.println("HySbrymd:"+hySbrymd);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "88459d2616e241e99c3a07188d2ab597";
        HySbrymd hySbrymd = service.queryById(keyId);
        System.out.println(hySbrymd);
        Assert.assertTrue(hySbrymd!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        HySbrymd hySbrymdParam = new HySbrymd();
        hySbrymdParam.setXm("01");
        List<HySbrymd> hySbrymds = service.list(hySbrymdParam);
        for (HySbrymd hySbrymd : hySbrymds ) {
            System.out.println(hySbrymd);
        }
        Assert.assertTrue(hySbrymds.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "88459d2616e241e99c3a07188d2ab597";
        HySbrymd hySbrymd = service.queryById(keyId);
        System.out.println("����ǰ:"+hySbrymd);
        hySbrymd.setXm("02");
        boolean success = service.updateById(hySbrymd);
        System.out.println("success:"+success);
        HySbrymd newHySbrymd = service.queryById(keyId);
        System.out.println("���º�:"+newHySbrymd);
        Assert.assertTrue(newHySbrymd.getXm().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        HySbrymd hySbrymd = new HySbrymd(keyId,"01","01","01","01","01","01",null,null);
        boolean success = service.insert(hySbrymd);
        System.out.println("����ɹ���"+success);
        HySbrymd queryHySbrymd = service.queryById(keyId);
        System.out.println("������ѯ"+queryHySbrymd);
        queryHySbrymd.setValid("0");
        queryHySbrymd.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryHySbrymd);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}