package org.poc.greekbang.rpc.datatransfer.javaserialization;

import lombok.Data;

import java.io.Serializable;

@Data
public class Foo implements Serializable {

    private String name;

}
