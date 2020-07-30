package com.framework.testcases;

import com.framework.core.commonUtils.BaseSetup;
import com.framework.core.commonUtils.RequestSpecificationBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends BaseSetup {




    @BeforeClass
    public void setUpTest() {
        urlsetup();

        RequestSpecificationBuilder.pathparams.clear();
        RequestSpecificationBuilder.queryparams.clear();

    }

    @BeforeMethod
     public void clearing()
    {

    }

    @AfterClass
    public void tearDown() {

    }

}
