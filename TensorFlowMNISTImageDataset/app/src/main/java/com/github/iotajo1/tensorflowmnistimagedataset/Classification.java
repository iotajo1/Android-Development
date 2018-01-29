package com.github.iotajo1.tensorflowmnistimagedataset;

/**
 * Created by JO_ on 7/10/2017.
 */

public class Classification {

    private float conf;
    private String label;

    public Classification(float conf, String label) {
        update(conf, label);
    }

    public Classification() {
        this.conf = (float)-1.0;
        this.label = null;
    }

    public void update(float conf, String label) {
        this.conf = conf;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public float getConf() {
        return conf;
    }

}
