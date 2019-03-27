package cn.coderme.cblog.dto.hotnews.zhihu;

import java.util.List;

/**
 * Created By zzitbar
 * Date:2018/11/15
 * Time:10:50
 */
public class ZhihuHotnewsDto {


    /**
     * fresh_text : 榜单已更新
     * paging : {"is_end":true,"previous":"","next":""}
     * data : [{"style_type":"1","feed_specific":{"trend":21,"score":191,"debut":false,"answer_count":136},"target":{"label_area":{"trend":21,"type":"trend","night_color":"#B7302D","normal_color":"#F1403C"},"metrics_area":{"text":"617 万热度"},"title_area":{"text":"人人网被多牛传媒并购，对其后续发展有何影响？"},"excerpt_area":{"text":"多牛旗下包含了大量娱乐、游戏类媒体，官网 slogan 为「中国最大的游戏、泛娱乐媒体集团」 新闻来源： 多牛传媒战略并购人人网社交网络业务 打造社交化智能媒体矩阵 再见，人人网；再见，陈一舟 2018年11月14日 多牛互动传媒股份有限公司（以下简称\u201c多牛传媒\u201d）宣布，已与人人公司（纽交所股票代码：RENN）达成协议，战略并购人人网（www.renren.com）相关的社交网络、人人直播及增值业务。人人网将成为多牛传媒旗下智能媒体矩阵的重要组成部分，共同服务于中国的3.5亿年轻用户。 根据双方达成的协议，多牛传媒将战略并购人人网相关的社交网络业务，其主要资产包括中国领先的社交网络平台人人网（www.renren.com），人人直播，以及相关的一揽子业务。"},"image_area":{"url":"https://pic3.zhimg.com/50/v2-4201aa2f012218df8491b41f5e5649d3_b.jpg"},"link":{"url":"https://www.zhihu.com/question/302347850"}},"card_id":"Q_302347850","attached_info":"CmAI37uAyaGigv5REAMaCDI5NDY0NTc5ILfkrd8FMBA4mQhAAEoKCgFNEgEwGAAgAFITbWVtYmVyX2Fza19xdWVzdGlvbnIJMzAyMzQ3ODUweACqAQliaWxsYm9hcmTSAQA=","type":"hot_list_feed","id":"0_1542249130.42"},{"style_type":"1","feed_specific":{"trend":0,"score":-230,"debut":false,"answer_count":287},"target":{"label_area":{"trend":0,"type":"trend","night_color":"#B7302D","normal_color":"#F1403C"},"metrics_area":{"text":"18 万热度"},"title_area":{"text":"重庆没有鸡公煲，非洲也没有非洲鸡，还有哪些名声在外，本地却没有的食物？"},"excerpt_area":{"text":"《风味人间》那个看起来很好吃的非洲鸡，非洲没有，重庆也没有鸡公煲，据说加州牛肉面也不是加州的...还有没有这种本地没有但名声在外的食物？"},"image_area":{"url":"https://pic3.zhimg.com/80/v2-784b26b6294effc9446ba25db3815f16_hd.jpg"},"link":{"url":"https://www.zhihu.com/question/301329162"}},"card_id":"Q_301329162","attached_info":"CmAI37uAyaGigv5REAMaCDI5MjM4NDM3IMPq/t4FMAo44gZAMUoKCgFNEgEwGAAgAFITbWVtYmVyX2Fza19xdWVzdGlvbnIJMzAxMzI5MTYyeACqAQliaWxsYm9hcmTSAQA=","type":"hot_list_feed","id":"49_1542249130.54"}]
     */

    private String fresh_text;
    private Paging paging;
    private List<Data> data;

    public String getFresh_text() {
        return fresh_text;
    }

    public void setFresh_text(String fresh_text) {
        this.fresh_text = fresh_text;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Paging {
        /**
         * is_end : true
         * previous :
         * next :
         */

        private boolean is_end;
        private String previous;
        private String next;

        public boolean isIs_end() {
            return is_end;
        }

        public void setIs_end(boolean is_end) {
            this.is_end = is_end;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
    }

    public static class Data {
        /**
         * style_type : 1
         * feed_specific : {"trend":21,"score":191,"debut":false,"answer_count":136}
         * target : {"label_area":{"trend":21,"type":"trend","night_color":"#B7302D","normal_color":"#F1403C"},"metrics_area":{"text":"617 万热度"},"title_area":{"text":"人人网被多牛传媒并购，对其后续发展有何影响？"},"excerpt_area":{"text":"多牛旗下包含了大量娱乐、游戏类媒体，官网 slogan 为「中国最大的游戏、泛娱乐媒体集团」 新闻来源： 多牛传媒战略并购人人网社交网络业务 打造社交化智能媒体矩阵 再见，人人网；再见，陈一舟 2018年11月14日 多牛互动传媒股份有限公司（以下简称\u201c多牛传媒\u201d）宣布，已与人人公司（纽交所股票代码：RENN）达成协议，战略并购人人网（www.renren.com）相关的社交网络、人人直播及增值业务。人人网将成为多牛传媒旗下智能媒体矩阵的重要组成部分，共同服务于中国的3.5亿年轻用户。 根据双方达成的协议，多牛传媒将战略并购人人网相关的社交网络业务，其主要资产包括中国领先的社交网络平台人人网（www.renren.com），人人直播，以及相关的一揽子业务。"},"image_area":{"url":"https://pic3.zhimg.com/50/v2-4201aa2f012218df8491b41f5e5649d3_b.jpg"},"link":{"url":"https://www.zhihu.com/question/302347850"}}
         * card_id : Q_302347850
         * attached_info : CmAI37uAyaGigv5REAMaCDI5NDY0NTc5ILfkrd8FMBA4mQhAAEoKCgFNEgEwGAAgAFITbWVtYmVyX2Fza19xdWVzdGlvbnIJMzAyMzQ3ODUweACqAQliaWxsYm9hcmTSAQA=
         * type : hot_list_feed
         * id : 0_1542249130.42
         */

        private String style_type;
        private FeedSpecific feed_specific;
        private Target target;
        private String card_id;
        private String attached_info;
        private String type;
        private String id;

        public String getStyle_type() {
            return style_type;
        }

        public void setStyle_type(String style_type) {
            this.style_type = style_type;
        }

        public FeedSpecific getFeed_specific() {
            return feed_specific;
        }

        public void setFeed_specific(FeedSpecific feed_specific) {
            this.feed_specific = feed_specific;
        }

        public Target getTarget() {
            return target;
        }

        public void setTarget(Target target) {
            this.target = target;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getAttached_info() {
            return attached_info;
        }

        public void setAttached_info(String attached_info) {
            this.attached_info = attached_info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class FeedSpecific {
            /**
             * trend : 21
             * score : 191
             * debut : false
             * answer_count : 136
             */

            private int trend;
            private int score;
            private boolean debut;
            private int answer_count;

            public int getTrend() {
                return trend;
            }

            public void setTrend(int trend) {
                this.trend = trend;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public boolean isDebut() {
                return debut;
            }

            public void setDebut(boolean debut) {
                this.debut = debut;
            }

            public int getAnswer_count() {
                return answer_count;
            }

            public void setAnswer_count(int answer_count) {
                this.answer_count = answer_count;
            }
        }

        public static class Target {
            /**
             * label_area : {"trend":21,"type":"trend","night_color":"#B7302D","normal_color":"#F1403C"}
             * metrics_area : {"text":"617 万热度"}
             * title_area : {"text":"人人网被多牛传媒并购，对其后续发展有何影响？"}
             * excerpt_area : {"text":"多牛旗下包含了大量娱乐、游戏类媒体，官网 slogan 为「中国最大的游戏、泛娱乐媒体集团」 新闻来源： 多牛传媒战略并购人人网社交网络业务 打造社交化智能媒体矩阵 再见，人人网；再见，陈一舟 2018年11月14日 多牛互动传媒股份有限公司（以下简称\u201c多牛传媒\u201d）宣布，已与人人公司（纽交所股票代码：RENN）达成协议，战略并购人人网（www.renren.com）相关的社交网络、人人直播及增值业务。人人网将成为多牛传媒旗下智能媒体矩阵的重要组成部分，共同服务于中国的3.5亿年轻用户。 根据双方达成的协议，多牛传媒将战略并购人人网相关的社交网络业务，其主要资产包括中国领先的社交网络平台人人网（www.renren.com），人人直播，以及相关的一揽子业务。"}
             * image_area : {"url":"https://pic3.zhimg.com/50/v2-4201aa2f012218df8491b41f5e5649d3_b.jpg"}
             * link : {"url":"https://www.zhihu.com/question/302347850"}
             */

            private LabelArea label_area;
            private MetricsArea metrics_area;
            private TitleArea title_area;
            private ExcerptArea excerpt_area;
            private ImageArea image_area;
            private Link link;

            public LabelArea getLabel_area() {
                return label_area;
            }

            public void setLabel_area(LabelArea label_area) {
                this.label_area = label_area;
            }

            public MetricsArea getMetrics_area() {
                return metrics_area;
            }

            public void setMetrics_area(MetricsArea metrics_area) {
                this.metrics_area = metrics_area;
            }

            public TitleArea getTitle_area() {
                return title_area;
            }

            public void setTitle_area(TitleArea title_area) {
                this.title_area = title_area;
            }

            public ExcerptArea getExcerpt_area() {
                return excerpt_area;
            }

            public void setExcerpt_area(ExcerptArea excerpt_area) {
                this.excerpt_area = excerpt_area;
            }

            public ImageArea getImage_area() {
                return image_area;
            }

            public void setImage_area(ImageArea image_area) {
                this.image_area = image_area;
            }

            public Link getLink() {
                return link;
            }

            public void setLink(Link link) {
                this.link = link;
            }

            public static class LabelArea {
                /**
                 * trend : 21
                 * type : trend
                 * night_color : #B7302D
                 * normal_color : #F1403C
                 */

                private int trend;
                private String type;
                private String night_color;
                private String normal_color;

                public int getTrend() {
                    return trend;
                }

                public void setTrend(int trend) {
                    this.trend = trend;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getNight_color() {
                    return night_color;
                }

                public void setNight_color(String night_color) {
                    this.night_color = night_color;
                }

                public String getNormal_color() {
                    return normal_color;
                }

                public void setNormal_color(String normal_color) {
                    this.normal_color = normal_color;
                }
            }

            public static class MetricsArea {
                /**
                 * text : 617 万热度
                 */

                private String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }

            public static class TitleArea {
                /**
                 * text : 人人网被多牛传媒并购，对其后续发展有何影响？
                 */

                private String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }

            public static class ExcerptArea {
                /**
                 * text : 多牛旗下包含了大量娱乐、游戏类媒体，官网 slogan 为「中国最大的游戏、泛娱乐媒体集团」 新闻来源： 多牛传媒战略并购人人网社交网络业务 打造社交化智能媒体矩阵 再见，人人网；再见，陈一舟 2018年11月14日 多牛互动传媒股份有限公司（以下简称“多牛传媒”）宣布，已与人人公司（纽交所股票代码：RENN）达成协议，战略并购人人网（www.renren.com）相关的社交网络、人人直播及增值业务。人人网将成为多牛传媒旗下智能媒体矩阵的重要组成部分，共同服务于中国的3.5亿年轻用户。 根据双方达成的协议，多牛传媒将战略并购人人网相关的社交网络业务，其主要资产包括中国领先的社交网络平台人人网（www.renren.com），人人直播，以及相关的一揽子业务。
                 */

                private String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }

            public static class ImageArea {
                /**
                 * url : https://pic3.zhimg.com/50/v2-4201aa2f012218df8491b41f5e5649d3_b.jpg
                 */

                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class Link {
                /**
                 * url : https://www.zhihu.com/question/302347850
                 */

                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}