package cn.coderme.cblog.dto.qiniu;

/**
 * Created By zhangtengfei
 * Date:2018/4/26
 * Time:17:45
 */
public class FetchRetDto {
    /**
     * 抓取后保存到空间文件名
     */
    public String key;
    /**
     * 抓取后保存到空间的文件hash值
     */
    public String hash;
    /**
     * 抓取后保存到空间的文件的mimeType
     */
    public String mimeType;
    /**
     * 抓取后保存到空间的文件的大小，单位：字节
     */
    public long fsize;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getFsize() {
        return fsize;
    }

    public void setFsize(long fsize) {
        this.fsize = fsize;
    }
}