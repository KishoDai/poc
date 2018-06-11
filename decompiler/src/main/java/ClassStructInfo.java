import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClassStructInfo {

    private String magic;
    private int minorVersion;
    private int majorVersion;
    private ClassConstantPoolInfo constantPoolInfo;

    private ClassAccessFlags accessFlags;

    private int thisClassIndex;
    private int superClassIndex;

    private ClassInterfacesInfo interfacesInfo;

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public ClassConstantPoolInfo getConstantPoolInfo() {
        return constantPoolInfo;
    }

    public void setConstantPoolInfo(ClassConstantPoolInfo constantPoolInfo) {
        this.constantPoolInfo = constantPoolInfo;
    }

    public ClassAccessFlags getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(ClassAccessFlags accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getThisClassIndex() {
        return thisClassIndex;
    }

    public void setThisClassIndex(int thisClassIndex) {
        this.thisClassIndex = thisClassIndex;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }

    public void setSuperClassIndex(int superClassIndex) {
        this.superClassIndex = superClassIndex;
    }

    public ClassInterfacesInfo getInterfacesInfo() {
        return interfacesInfo;
    }

    public void setInterfacesInfo(ClassInterfacesInfo interfacesInfo) {
        this.interfacesInfo = interfacesInfo;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
