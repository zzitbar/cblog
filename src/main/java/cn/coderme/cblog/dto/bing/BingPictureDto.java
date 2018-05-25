package cn.coderme.cblog.dto.bing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created By zhangtengfei
 * Date:2018/4/25
 * Time:11:40
 */
public class BingPictureDto {

    private static final Integer SUCCESS = 200;

    /**
     * code : 200
     * title : 勇敢而凶悍
     * attribute : 美国，风穴国家公园
     * image : https://www.bing.com/az/hprichbg/rb/WindCaveBison_ZH-CN9135908894_1920x1080.jpg
     * story : 在风穴国家公园的草原之下，有一些你意想不到的东西，这里有着地球上最长的洞穴之一。该洞穴包括140英里的探索通道，使其成为世界上第六大洞穴，也是第一个建在国家公园地下的洞穴。而地面上的东西同样特别，这里的草原上有着勇敢而凶悍的美洲野牛，如果你也喜欢它们，那么和我一起走进它们的世界吧！
     * search : http://www.bing.com/search?q=é£ç©´å½å®¶å¬å­&form=hpcapt&mkt=zh-cn
     * API Host : https://api.lylares.com/
     * provider : © Newman Mark/ SuperStock
     * Continent : 北美洲
     * Country : 美国
     * City : 风穴国家公园
     * Longitude : -103.477013
     * Latitude : 43.556117
     * date : 20180425
     */

    private Integer code;
    private String title;
    private String attribute;
    private String image;
    private String story;
    private String search;
    @SerializedName("API Host")
    @JsonProperty("API Host")
    private String apiHost;
    private String provider;

    @SerializedName("Continent")
    @JsonProperty("Continent")
    private String continent;

    @SerializedName("Country")
    @JsonProperty("Country")
    private String country;

    @SerializedName("City")
    @JsonProperty("City")
    private String city;

    @SerializedName("Longitude")
    @JsonProperty("Longitude")
    private String longitude;

    @SerializedName("Latitude")
    @JsonProperty("Latitude")
    private String latitude;

    private String date;

    private List<List<String>> location;//旧接口

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<List<String>> getLocation() {
        return location;
    }

    public void setLocation(List<List<String>> location) {
        this.location = location;
    }
}