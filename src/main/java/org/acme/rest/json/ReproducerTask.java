package org.acme.rest.json;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.transaction.Transactional;
import java.util.Map;

@Dependent
public class ReproducerTask implements Runnable {

    private String data;
    private Map<Integer, String> myMapping;
    private boolean flag;
    private char myChar;
    private ReproduceEnum reproduceEnum;

    @Override
    @Transactional
    @ActivateRequestContext
    public void run() {
        ///do stuff
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMyMapping(Map<Integer, String> myMapping) {
        this.myMapping = myMapping;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setMyChar(char myChar) {
        this.myChar = myChar;
    }

    public void setReproduceEnum(ReproduceEnum reproduceEnum) {
        this.reproduceEnum = reproduceEnum;
    }
}
