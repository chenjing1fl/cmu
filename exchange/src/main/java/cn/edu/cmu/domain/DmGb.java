package cn.edu.cmu.domain;

public class DmGb {
    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     *
     * @mbggenerated 2018-09-26
     */
    public DmGb(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     *
     * @mbggenerated 2018-09-26
     */
    public DmGb() {
        super();
    }

    /**
     * 代码
     * @return code 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 代码
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     *
     * @mbggenerated 2018-09-26
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}