package cn.coderme.cblog.dto.bing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * Created By zhangtengfei
 * Date:2018/4/25
 * Time:14:58
 */
public class BingPictureInfoDto {

    /**
     * 1 : {"title":"想与你一同站在城市夜空中","info":"下一站幸福","image":"http://s3.cn.bing.net/th?id=OJ.1CkJTlB3n1ysOA&pid=MSNJVFeeds","story":"人们都说，当我们仰望摩天轮的时候，就是在仰望幸福，幸福有多高，摩天轮就有多高。当总是渴望幸福但又迟迟感觉不到幸福的时候，不如试着坐上摩天轮，等待它慢慢升高，直到最顶端，俯视，一切都那么渺小。关于摩天轮的传说有很多，我却最喜欢这一个。世界虽大，但幸福很简单。","search":"/Search?q=ä¸­ç\u008e¯æ\u0091©å¤©è½®&amp;mkt=zh-cn&amp;FORM=BNLH\" h=\"ID=SERP,5021.2"}
     * 2 : {"title":"望尽现世繁华","info":"纸醉金迷夜不归","image":"http://s.cn.bing.net/th?id=OJ.UuemwLeP4z7lGw&pid=MSNJVFeeds","story":"华灯初上，你可以从中环一路走到太平山顶，领略\u201c东方明珠\u201d的全貌；也可以在香江旁边，看水中的灯火辉煌；还可以去庙街，在满街霓虹灯牌中做一回老香港；或者去铜锣湾，涌入血拼大潮。香港的夜，有纸醉金迷，也有亲切温情。","search":"/Search?q=ä¸\u008då¤\u009cå\u009f\u008eï¼\u008cé¦\u0099æ¸¯&amp;mkt=zh-cn&amp;FORM=BNLH\" h=\"ID=SERP,5022.2"}
     * 0 : {"title":"一起仰望新年的星空","info":"开开心心过新年","info2":"看港剧 品人生","image":"http://s2.cn.bing.net/th?id=OJ.Uf8GrmH1VCytyw&pid=MSNJVFeeds","story":"灯光将维多利亚港映射得五彩斑斓，若是能在天空中许下新年愿望，一定会实现吧。2014年12月正式开放的香港中环摩天轮，是香港地标式建筑，60米的高度不但可以俯视维港全景，而且也是欣赏跨年烟花的最佳地点。","location":"中国，香港特别行政区","search":"/dreammap/?mkt=zh-cn&amp;lat=22.28532&amp;lon=114.1611993&amp;ct=é¦\u0099æ¸¯ç\u0089¹å\u0088«è¡\u008cæ\u0094¿å\u008cº&amp;cy=ä¸­å\u009b½&amp;sib=1&amp;FORM=BNLH\" h=\"ID=SERP,5004.1"}
     * date : 20170101
     */

    @SerializedName("1")
    @JsonProperty("1")
    private _$1Bean _$1;

    @SerializedName("2")
    @JsonProperty("2")
    private _$2Bean _$2;

    @SerializedName("0")
    @JsonProperty("0")
    private _$0Bean _$0;

    private String date;

    public _$1Bean get_$1() {
        return _$1;
    }

    public void set_$1(_$1Bean _$1) {
        this._$1 = _$1;
    }

    public _$2Bean get_$2() {
        return _$2;
    }

    public void set_$2(_$2Bean _$2) {
        this._$2 = _$2;
    }

    public _$0Bean get_$0() {
        return _$0;
    }

    public void set_$0(_$0Bean _$0) {
        this._$0 = _$0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class _$1Bean {
        /**
         * title : 想与你一同站在城市夜空中
         * info : 下一站幸福
         * image : http://s3.cn.bing.net/th?id=OJ.1CkJTlB3n1ysOA&pid=MSNJVFeeds
         * story : 人们都说，当我们仰望摩天轮的时候，就是在仰望幸福，幸福有多高，摩天轮就有多高。当总是渴望幸福但又迟迟感觉不到幸福的时候，不如试着坐上摩天轮，等待它慢慢升高，直到最顶端，俯视，一切都那么渺小。关于摩天轮的传说有很多，我却最喜欢这一个。世界虽大，但幸福很简单。
         * search : /Search?q=ä¸­ç¯æ©å¤©è½®&amp;mkt=zh-cn&amp;FORM=BNLH" h="ID=SERP,5021.2
         */

        private String title;
        private String info;
        private String image;
        private String story;
        private String search;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
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
    }

    public static class _$2Bean {
        /**
         * title : 望尽现世繁华
         * info : 纸醉金迷夜不归
         * image : http://s.cn.bing.net/th?id=OJ.UuemwLeP4z7lGw&pid=MSNJVFeeds
         * story : 华灯初上，你可以从中环一路走到太平山顶，领略“东方明珠”的全貌；也可以在香江旁边，看水中的灯火辉煌；还可以去庙街，在满街霓虹灯牌中做一回老香港；或者去铜锣湾，涌入血拼大潮。香港的夜，有纸醉金迷，也有亲切温情。
         * search : /Search?q=ä¸å¤åï¼é¦æ¸¯&amp;mkt=zh-cn&amp;FORM=BNLH" h="ID=SERP,5022.2
         */

        private String title;
        private String info;
        private String image;
        private String story;
        private String search;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
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
    }

    public static class _$0Bean {
        /**
         * title : 一起仰望新年的星空
         * info : 开开心心过新年
         * info2 : 看港剧 品人生
         * image : http://s2.cn.bing.net/th?id=OJ.Uf8GrmH1VCytyw&pid=MSNJVFeeds
         * story : 灯光将维多利亚港映射得五彩斑斓，若是能在天空中许下新年愿望，一定会实现吧。2014年12月正式开放的香港中环摩天轮，是香港地标式建筑，60米的高度不但可以俯视维港全景，而且也是欣赏跨年烟花的最佳地点。
         * location : 中国，香港特别行政区
         * search : /dreammap/?mkt=zh-cn&amp;lat=22.28532&amp;lon=114.1611993&amp;ct=é¦æ¸¯ç¹å«è¡æ¿åº&amp;cy=ä¸­å½&amp;sib=1&amp;FORM=BNLH" h="ID=SERP,5004.1
         */

        private String title;
        private String info;
        private String info2;
        private String image;
        private String story;
        private String location;
        private String search;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getInfo2() {
            return info2;
        }

        public void setInfo2(String info2) {
            this.info2 = info2;
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }
    }
}