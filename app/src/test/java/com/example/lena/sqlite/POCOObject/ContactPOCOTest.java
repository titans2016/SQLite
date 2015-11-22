package com.example.lena.sqlite.POCOObject;

import com.example.lena.sqlite.ContactPOCO;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Lena on 21.11.2015.
 */
public class ContactPOCOTest extends TestCase {
    public void testGetName() throws Exception {
        ContactPOCO contactPOCO=new ContactPOCO();
        String name=contactPOCO.GetName();
        Assert.assertEquals(null,name);
    }

    public void testGetEmail() throws Exception {
        ContactPOCO contactPOCO=new ContactPOCO();
        String email=contactPOCO.GetEmail();
        Assert.assertEquals(null,email);
    }

    public void testSetName() throws Exception {
        ContactPOCO contactPOCO=new ContactPOCO();
        String newName="testUser";
        contactPOCO.SetName(newName);
        String actualName=contactPOCO.GetName();
        Assert.assertEquals(newName,actualName);
    }

    public void testSetEmail() throws Exception {
        ContactPOCO contactPOCO=new ContactPOCO();
        String newEmail ="test.email@mail.ru";
        contactPOCO.SetEmail(newEmail);
        String actualEmail=contactPOCO.GetEmail();
        Assert.assertEquals(newEmail,actualEmail);
    }
}