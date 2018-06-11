package model;

public class ClassInterfacesInfo {
    private int interfacesCount;
    private ClassInterfaceInfo[] interfaceInfos;

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public ClassInterfaceInfo[] getInterfaceInfos() {
        return interfaceInfos;
    }

    public void setInterfaceInfos(ClassInterfaceInfo[] interfaceInfos) {
        this.interfaceInfos = interfaceInfos;
    }
}
