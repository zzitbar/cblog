package cn.coderme.cblog.service.hotnews.crawl;

import cn.coderme.cblog.BusException;
import cn.coderme.cblog.Constants;
import cn.coderme.cblog.dto.bing.BingCnDto;
import cn.coderme.cblog.dto.hotnews.zhihu.ZhihuHotnewsDto;
import cn.coderme.cblog.entity.hotnews.Hotnews;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By zzitbar
 * Date:2018/11/14
 * Time:15:24
 */
@Service
public class ZhihuHotnewsService extends HotnewsCrawlService {

    private static final String NEWS_CATEGORY = "zhihu";

    private Map header;

    @PostConstruct
    public void initMethod() {
        this.addCategoryList();
        header = new HashMap();
        header.put("Host", "api.zhihu.com");
        header.put("Cookie", "tgw_l7_route=bc9380c810e0cf40598c1a7b1459f027; _xsrf=B78D14r6WWFihTpy3rMeeEODmVVydS54; q_c1=1c0e75e476484a08a3dd5b381f16e73f|1542245341000|1542245341000; capsion_ticket=\"2|1:0|10:1542245414|14:capsion_ticket|44:NzYyYjIxMDQ1NjNiNGYzZDhiOTI2ZDI1MzUwOTdmODU=|0bb1b7c960c4d3931fbffaad7d3ea76283bce183c5335af6ada2d799054f6204\"; z_c0=\"2|1:0|10:1542245414|4:z_c0|92:Mi4xOW9rSEFBQUFBQUFBc0tLanhWS0ZEZ3NBQUFCZ0FsVk5KbGtVWEFEODNzamVtODYwcFRhYm5GbTVRM3M5aWk3Q1Jn|63ea530b9df1c04be20ce3305fd9dc1ee38372f5cb59d9102147c8fbfa605469\"; q_c0=2|1:0|10:1542245414|4:q_c0|92:Mi4xOW9rSEFBQUFBQUFBc0tLanhWS0ZEZ3NBQUFCZ0FsVk5KbGtVWEFEODNzamVtODYwcFRhYm5GbTVRM3M5aWk3Q1Jn|f613cb5cb955025be3892f33811afc1ea83525c2f24ca3416d4dec6685696166; zst_82=1.0AMCivZVVhQ4LAAAASwUAADEuMMPO7FsAAAAAI6MXoe2FsH2QioRtg-eOqdScJvk=");
        header.put("User-Agent", "osee2unifiedRelease/4.28.0 (iPhone; iOS 12.0; Scale/2.00)");
        header.put("X-APP-VersionCode", "1138");
        header.put("X-ZST-82", "1.0AMCivZVVhQ4LAAAASwUAADEuMMPO7FsAAAAAI6MXoe2FsH2QioRtg-eOqdScJvk=");
        header.put("X-Ab-Param", "se_majorob_style=0;top_feedre_itemcf=31;top_multi_model=0;top_promo=1;top_follow_reason=0;top_manual_tag=1;top_roundtable=1;top_yc=0;top_vd_op=0;top_vd_gender=0;top_uit=0;top_ab_validate=0;top_v_album=1;top_new_feed=1;top_ac_merge=0;top_gr_model=0;top_pfq=5;top_newfollowans=0;top_mlt_model=0;top_recall_tb_short=61;top_distinction=2;top_followtop=1;top_nad=1;top_dtmt=0;top_video_fix_position=0;pin_efs=orig;top_test_4_liguangyi=1;top_quality=0;top_recall_core_interest=81;top_wonderful=1;top_cc_at=1;top_no_weighing=1;top_retag=0;top_tagore_topic=0;top_ebook=0;top_slot_ad_pos=1;top_tuner_refactor=-1;se_dt=1;top_recall_tb_long=51;top_spec_promo=1;top_vds_alb_pos=0;top_sjre=0;se_shopsearch=0;top_card=-1;se_refactored_search_index=0;top_local=1;se_consulting_switch=off;top_raf=n;top_tag_isolation=0;top_universalebook=1;se_cq=0;top_ntr=1;se_tf=1;top_alt=0;top_billvideo=0;top_recall_deep_user=1;top_feedre_rtt=41;top_memberfree=1;top_nucc=3;se_entity=on;top_f_r_nb=1;top_mt=0;top_new_user_gift=0;top_nmt=0;top_tffrt=0;tp_sft=a;se_filter=0;top_ad_slot=1;top_yhgc=0;pin_ef=orig;se_ingress=on;top_gr_topic_reweight=0;top_recall_tb=1;top_video_score=1;top_billupdate1=3;tp_favsku=a;se_relevant_query=new;top_nuc=0;se_cm=1;top_root_web=0;se_auto_syn=0;top_billboard_count=1;top_billpic=0;top_lowup=1;top_nszt=0;top_recall=1;se_ltr=0;top_retagg=0;tp_write_pin_guide=3;se_merger=1;top_billread=1;top_feedre_cpt=101;ls_new_video=0;top_root_few_topic=0;zr_ans_rec=gbrank;se_major_onebox=major;tp_discussion_feed_type_android=0;top_an=0;top_gif=0;ls_new_score=0;top_fqai=0;top_tr=0;se_billboard=3;top_recall_tb_follow=71;tp_ios_topic_write_pin_guide=1;top_tagore=1;top_video_rew=0;se_consulting_price=n;top_feedtopiccard=0;top_videos_priority=-1;se_gi=0;top_adpar=0;top_hqt=9;top_nid=0;se_daxuechuisou=new;se_correct_ab=1;top_is_gr=0;tp_discussion_feed_card_type=0;se_dl=1;top_recall_follow_user=91;top_root_mg=1;top_feedre=1;top_sj=2;ls_is_use_zrec=0;top_root_ac=1;top_topic_feedre=21;top_free_content=-1;top_hca=0;top_tmt=0;top_vd_rt_int=0;se_gemini_service=content;top_30=0;top_gr_auto_model=0;top_root=1;top_vdio_rew=0;se_minor_onebox=d;top_billab=1;top_hweb=0;top_recommend_topic_card=0;top_newfollow=0;se_product_rank_list=0;top_bill=0;top_login_card=1;top_rank=2;se_rescore=1;se_new_market_search=off;se_wiki_box=1;top_fqa=0;top_user_gift=0");
        header.put("x-app-za", "OS=iOS&Release=12.0&Model=iPhone8,1&VersionName=4.28.0&VersionCode=1138&Width=750&Height=1334&DeviceType=Phone&Brand=Apple&OperatorType=46003");
        header.put("X-APP-VERSION", "4.28.0");
        header.put("Authorization", "Bearer 2.19okHAAAAAAAAsKKjxVKFDgsAAABgAlVNX2EUXABsuWnqyD2sWktt7BKGlpL3iloUNg");
//        header.put("X-UDID", "ALCio8VShQ5LBcRh-JUzElJpOqooAFyZlLc=");
//        header.put("X-APP-Build", "release");
//        header.put("X-SUGER", "SURGVj0yRkZEQTMzRi02MkE0LTRCQjktODQyMi1CQkQwQjAxMTlFNDU7SURGQT02QjA1NjFFNi0yODIzLTQ0RUMtQTZGOS1CMzBCOUM1NDA1MTM7Q09PUkRfTEFUPTM0LjgyNTExMzY4NjkwMTkxO0NPT1JEX0xORz0xMTMuNTU0NDY5NTcwMDQ5NTtDT09SRF9USU1FU1RBTVA9MTU0MjI0NjAxMjtVRElEPUFMQ2lvOFZTaFE1TEJjUmgtSlV6RWxKcE9xb29BRnlabExjPQ==");
        header.put("X-API-Version", "3.0.94");

        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-Hans-CN;q=1, en-CN;q=0.9");
        header.put("Accept-Encoding", "gzip");
        header.put("Connection", "keep-alive");
    }

    @Override
    public List<Hotnews> crawlNews() throws Exception {
        List<Hotnews> hotnewsList = new ArrayList<>();
        Connection connection = Jsoup.connect("https://api.zhihu.com/topstory/hot-list?limit=10&reverse_order=0")
                .headers(header)
                .followRedirects(false)
                .timeout(60000);
        Connection.Response res = connection.ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求
        if (res.statusCode() == HttpStatus.OK.value()) {
            String body = res.body();
            Gson gson = new Gson();
            ZhihuHotnewsDto dto = gson.fromJson(unicodeToString(body), ZhihuHotnewsDto.class);
            if (null != dto && null != dto.getData() && dto.getData().size() > 0) {
                Integer i = 1;
                for (ZhihuHotnewsDto.Data data : dto.getData()) {
                    Hotnews hotnews = new Hotnews();
                    hotnews.setNewsOrder(i);
                    hotnews.setNewsRank(i.toString());
                    ZhihuHotnewsDto.Data.Target target = data.getTarget();
                    hotnews.setNewsTitle(target.getTitle_area().getText());
                    hotnews.setNewsUrl(target.getLink().getUrl());
                    hotnews.setNewsAbstract(target.getExcerpt_area().getText());
                    hotnews.setNewsPoint(target.getMetrics_area().getText());
                    hotnews.setNewsCategory(NEWS_CATEGORY);
                    hotnews.setNewsDate(LocalDate.now());
                    hotnews.setCreateTime(LocalDateTime.now());
                    hotnews.setUpdateTime(LocalDateTime.now());
                    hotnews.setOriginData(gson.toJson(data));
                    hotnewsList.add(hotnews);
                    i++;
                }
            }
        } else {
            throw new BusException("请求知乎热榜接口出错: " + res.statusCode() + ", error message: " + res.statusMessage());
        }
        return hotnewsList;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(new ZhihuHotnewsService().crawlNews());
        Map header = new HashMap();
        header.put("Host", "api.zhihu.com");
        header.put("Cookie", "tgw_l7_route=bc9380c810e0cf40598c1a7b1459f027; _xsrf=B78D14r6WWFihTpy3rMeeEODmVVydS54; q_c1=1c0e75e476484a08a3dd5b381f16e73f|1542245341000|1542245341000; capsion_ticket=\"2|1:0|10:1542245414|14:capsion_ticket|44:NzYyYjIxMDQ1NjNiNGYzZDhiOTI2ZDI1MzUwOTdmODU=|0bb1b7c960c4d3931fbffaad7d3ea76283bce183c5335af6ada2d799054f6204\"; z_c0=\"2|1:0|10:1542245414|4:z_c0|92:Mi4xOW9rSEFBQUFBQUFBc0tLanhWS0ZEZ3NBQUFCZ0FsVk5KbGtVWEFEODNzamVtODYwcFRhYm5GbTVRM3M5aWk3Q1Jn|63ea530b9df1c04be20ce3305fd9dc1ee38372f5cb59d9102147c8fbfa605469\"; q_c0=2|1:0|10:1542245414|4:q_c0|92:Mi4xOW9rSEFBQUFBQUFBc0tLanhWS0ZEZ3NBQUFCZ0FsVk5KbGtVWEFEODNzamVtODYwcFRhYm5GbTVRM3M5aWk3Q1Jn|f613cb5cb955025be3892f33811afc1ea83525c2f24ca3416d4dec6685696166; zst_82=1.0AMCivZVVhQ4LAAAASwUAADEuMMPO7FsAAAAAI6MXoe2FsH2QioRtg-eOqdScJvk=");
        header.put("User-Agent", "osee2unifiedRelease/4.28.0 (iPhone; iOS 12.0; Scale/2.00)");
        header.put("X-APP-VersionCode", "1138");
        header.put("X-ZST-82", "1.0AMCivZVVhQ4LAAAASwUAADEuMMPO7FsAAAAAI6MXoe2FsH2QioRtg-eOqdScJvk=");
        header.put("X-Ab-Param", "se_majorob_style=0;top_feedre_itemcf=31;top_multi_model=0;top_promo=1;top_follow_reason=0;top_manual_tag=1;top_roundtable=1;top_yc=0;top_vd_op=0;top_vd_gender=0;top_uit=0;top_ab_validate=0;top_v_album=1;top_new_feed=1;top_ac_merge=0;top_gr_model=0;top_pfq=5;top_newfollowans=0;top_mlt_model=0;top_recall_tb_short=61;top_distinction=2;top_followtop=1;top_nad=1;top_dtmt=0;top_video_fix_position=0;pin_efs=orig;top_test_4_liguangyi=1;top_quality=0;top_recall_core_interest=81;top_wonderful=1;top_cc_at=1;top_no_weighing=1;top_retag=0;top_tagore_topic=0;top_ebook=0;top_slot_ad_pos=1;top_tuner_refactor=-1;se_dt=1;top_recall_tb_long=51;top_spec_promo=1;top_vds_alb_pos=0;top_sjre=0;se_shopsearch=0;top_card=-1;se_refactored_search_index=0;top_local=1;se_consulting_switch=off;top_raf=n;top_tag_isolation=0;top_universalebook=1;se_cq=0;top_ntr=1;se_tf=1;top_alt=0;top_billvideo=0;top_recall_deep_user=1;top_feedre_rtt=41;top_memberfree=1;top_nucc=3;se_entity=on;top_f_r_nb=1;top_mt=0;top_new_user_gift=0;top_nmt=0;top_tffrt=0;tp_sft=a;se_filter=0;top_ad_slot=1;top_yhgc=0;pin_ef=orig;se_ingress=on;top_gr_topic_reweight=0;top_recall_tb=1;top_video_score=1;top_billupdate1=3;tp_favsku=a;se_relevant_query=new;top_nuc=0;se_cm=1;top_root_web=0;se_auto_syn=0;top_billboard_count=1;top_billpic=0;top_lowup=1;top_nszt=0;top_recall=1;se_ltr=0;top_retagg=0;tp_write_pin_guide=3;se_merger=1;top_billread=1;top_feedre_cpt=101;ls_new_video=0;top_root_few_topic=0;zr_ans_rec=gbrank;se_major_onebox=major;tp_discussion_feed_type_android=0;top_an=0;top_gif=0;ls_new_score=0;top_fqai=0;top_tr=0;se_billboard=3;top_recall_tb_follow=71;tp_ios_topic_write_pin_guide=1;top_tagore=1;top_video_rew=0;se_consulting_price=n;top_feedtopiccard=0;top_videos_priority=-1;se_gi=0;top_adpar=0;top_hqt=9;top_nid=0;se_daxuechuisou=new;se_correct_ab=1;top_is_gr=0;tp_discussion_feed_card_type=0;se_dl=1;top_recall_follow_user=91;top_root_mg=1;top_feedre=1;top_sj=2;ls_is_use_zrec=0;top_root_ac=1;top_topic_feedre=21;top_free_content=-1;top_hca=0;top_tmt=0;top_vd_rt_int=0;se_gemini_service=content;top_30=0;top_gr_auto_model=0;top_root=1;top_vdio_rew=0;se_minor_onebox=d;top_billab=1;top_hweb=0;top_recommend_topic_card=0;top_newfollow=0;se_product_rank_list=0;top_bill=0;top_login_card=1;top_rank=2;se_rescore=1;se_new_market_search=off;se_wiki_box=1;top_fqa=0;top_user_gift=0");
        header.put("x-app-za", "OS=iOS&Release=12.0&Model=iPhone8,1&VersionName=4.28.0&VersionCode=1138&Width=750&Height=1334&DeviceType=Phone&Brand=Apple&OperatorType=46003");
        header.put("X-APP-VERSION", "4.28.0");
        header.put("Authorization", "Bearer 2.19okHAAAAAAAAsKKjxVKFDgsAAABgAlVNX2EUXABsuWnqyD2sWktt7BKGlpL3iloUNg");
//        header.put("X-UDID", "ALCio8VShQ5LBcRh-JUzElJpOqooAFyZlLc=");
//        header.put("X-APP-Build", "release");
//        header.put("X-SUGER", "SURGVj0yRkZEQTMzRi02MkE0LTRCQjktODQyMi1CQkQwQjAxMTlFNDU7SURGQT02QjA1NjFFNi0yODIzLTQ0RUMtQTZGOS1CMzBCOUM1NDA1MTM7Q09PUkRfTEFUPTM0LjgyNTExMzY4NjkwMTkxO0NPT1JEX0xORz0xMTMuNTU0NDY5NTcwMDQ5NTtDT09SRF9USU1FU1RBTVA9MTU0MjI0NjAxMjtVRElEPUFMQ2lvOFZTaFE1TEJjUmgtSlV6RWxKcE9xb29BRnlabExjPQ==");
        header.put("X-API-Version", "3.0.94");

        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-Hans-CN;q=1, en-CN;q=0.9");
        header.put("Accept-Encoding", "gzip");
        header.put("Connection", "keep-alive");
        Connection connection = Jsoup.connect("https://api.zhihu.com/topstory/hot-list?limit=10&reverse_order=0")
                .headers(header)
                .followRedirects(false)
                .timeout(60000);
        Connection.Response res = connection.ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求
        System.out.println(res.headers());
        System.out.println(res.cookies());
        System.out.println(res.statusCode());
        String body = res.body();
        System.out.println(unicodeToString(body));
        Map map = new Gson().fromJson(body, Map.class);
        System.out.println(map);
    }


    public Map getHeader() {
        return header;
    }

    public void setHeader(Map header) {
        this.header = header;
    }

    @Override
    public void addCategoryList() {
        Map map = new HashMap();
        map.put("id", Constants.CATEGORY_ID.ZHIHU.getValue());
        map.put("name", "知乎");
        map.put("type", NEWS_CATEGORY);
        map.put("icon", "");
        map.put("visible", true);
        map.put("contentTitle", "知乎热榜");
        CATEGORY_MAP.put(NEWS_CATEGORY, map);
    }
}