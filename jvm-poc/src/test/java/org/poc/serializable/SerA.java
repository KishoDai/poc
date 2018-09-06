package org.poc.serializable;

import java.io.Serializable;

/**
 * @author daiji
 */
public class SerA implements Serializable {

//    private static final long serialVersionUID = 12131313123L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
