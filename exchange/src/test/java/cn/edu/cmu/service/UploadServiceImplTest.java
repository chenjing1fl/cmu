package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.Upload;
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
public class UploadServiceImplTest extends SpringIOC {

    UploadService service;

    @Before
    public  void init(){
        service = ac.getBean(UploadService.class);
    }
    //����
    @Test
    public void save() {
        Upload upload = new Upload(CmuStringUtil.UUID(),"01", new Date(), "01", "01", "01",null,null);
        boolean success = service.insert(upload);
        System.out.println("Upload:"+upload);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "485600be9cb34b20b0340a376756f064";
        Upload upload = service.queryById(keyId);
        System.out.println(upload);
        Assert.assertTrue(upload!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        Upload uploadParam = new Upload();
        uploadParam.setUploadPath("0");
        List<Upload> uploads = service.list(uploadParam);
        for (Upload upload : uploads ) {
            System.out.println(upload);
        }
        Assert.assertTrue(uploads.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "485600be9cb34b20b0340a376756f064";
        Upload upload = service.queryById(keyId);
        System.out.println("����ǰ:"+upload);
        upload.setUploadPath("02");
        boolean success = service.updateById(upload);
        System.out.println("success:"+success);
        Upload newUpload = service.queryById(keyId);
        System.out.println("���º�:"+newUpload);
        Assert.assertTrue(newUpload.getUploadPath().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        Upload upload = new Upload(keyId,"01", new Date(), "01", "01", "01",null,null);
        boolean success = service.insert(upload);
        System.out.println("����ɹ���"+success);
        Upload queryUpload = service.queryById(keyId);
        System.out.println("������ѯ"+queryUpload);
        queryUpload.setValid("0");
        queryUpload.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryUpload);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}