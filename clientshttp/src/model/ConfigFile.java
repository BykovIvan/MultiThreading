package model;

import java.util.List;

public class ConfigFile {
    private Integer rCount;
    private Integer wCount;
    private List<Integer> idList;

    public ConfigFile() {
    }

    public ConfigFile(Integer rCount, Integer wCount, List<Integer> idList) {
        this.rCount = rCount;
        this.wCount = wCount;
        this.idList = idList;
    }

    public Integer getrCount() {
        return rCount;
    }

    public void setrCount(Integer rCount) {
        this.rCount = rCount;
    }

    public Integer getwCount() {
        return wCount;
    }

    public void setwCount(Integer wCount) {
        this.wCount = wCount;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rCount);
        sb.append(";");
        sb.append(wCount);
        sb.append(";");
        for (Integer integer : idList) {
            sb.append(integer);
            sb.append(",");
        }
        return sb.toString();

    }
}
