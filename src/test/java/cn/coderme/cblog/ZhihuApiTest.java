package cn.coderme.cblog;

import cn.coderme.cblog.service.hotnews.crawl.ZhihuHotnewsService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By zzitbar
 * Date:2018/11/15
 * Time:10:08
 */
public class ZhihuApiTest {

    @Test
    public void login() throws Exception {
        Map header = new HashMap();
        header.put("Host", "api.zhihu.com");
        header.put("Cookie", "tgw_l7_route=27a99ac9a31c20b25b182fd9e44378b8; _xsrf=B78D14r6WWFihTpy3rMeeEODmVVydS54; q_c1=1c0e75e476484a08a3dd5b381f16e73f|1542245341000|1542245341000; capsion_ticket=\"2|1:0|10:1542247519|14:capsion_ticket|44:YjdmNmU1ZmFmMmI5NGU5ZmIwNjRkZjMxZmE4N2ZmMzc=|5c3ab67b9cca9345281ae9b044c1f539efdd9b5bd06c6092e951b0a32607a78c\"; zst_82=1.0AKAicO1ahQ4LAAAASwUAADEuMDvU7FsAAAAA3SF2G0itLMpF0aWW8UePDidIrio=; l_n_c=1; cap_id=\"MjUwMmY5ODdmYWJlNGVjNzlhYWQwZTM2ZTQ1ZjJjYzk=|1542247482|c53bcb4e1887190ca6606baa54c2b864c19ab98c\"; r_cap_id=\"MDA5OWZmMjZjZTc5NGNkMmJhNTllZjI1NDM1NTIwYzE=|1542247482|54b5726f99bfb5086fcc94b69836ad3308e86a89\"; l_cap_id=\"MzM0NWFmODExZWE2NGVkMDkyZTFhMmI4YTU0M2ExMmU=|1542247482|69ce75b05ec42a921c4f63186acdfb1e7c9d0d8b\"; n_c=1; z_c0=gt2.0AAAAAA0slqcOhVLFo6KwAAAAAAtNVQJgAgAcIDxvqOr1JLTO3VZY7WsVrlkUVg==; _zap=469bd420-2826-482a-b31c-0323089632f7");
        header.put("User-Agent", "osee2unifiedRelease/4.28.0 (iPhone; iOS 12.0; Scale/2.00)");
        header.put("X-APP-VersionCode", "1138");
        header.put("X-ZST-82", "1.0AKAicO1ahQ4LAAAASwUAADEuMDvU7FsAAAAA3SF2G0itLMpF0aWW8UePDidIrio=");
        header.put("X-Ab-Param", "se_shopsearch=0;top_feedre_cpt=101;top_rank=9;top_v_album=1;top_an=0;top_vd_rt_int=0;top_tag_isolation=0;pin_efs=orig;se_consulting_switch=off;top_followtop=1;top_gr_auto_model=0;top_nuc=0;se_ingress=on;top_hweb=0;top_vdio_rew=0;zr_ans_rec=gbrank;se_cq=0;top_recall_tb_short=61;top_sjre=0;top_uit=0;top_feedtopiccard=0;top_recall_tb_follow=71;top_tagore_topic=0;tp_write_pin_guide=3;top_billread=1;top_recommend_topic_card=0;top_wonderful=1;top_yhgc=0;top_dtmt=0;se_gi=1;ls_is_use_zrec=0;ls_new_video=1;top_billab=1;top_bill=0;top_billupdate1=3;top_fqa=0;top_is_gr=0;se_entity=on;top_alt=0;top_recall_tb_long=51;se_correct_ab=1;se_wiki_box=1;top_billvideo=0;top_root_few_topic=0;tp_discussion_feed_card_type=0;top_card=-1;top_tmt=0;top_user_gift=0;top_cc_at=1;top_gif=0;top_nucc=0;top_recall_core_interest=81;top_tagore=1;top_topic_feedre=21;se_filter=0;top_videos_priority=-1;top_ad_slot=1;top_nszt=0;top_root_ac=1;se_billboard=3;top_gr_model=0;top_ebook=0;se_major_onebox=major;top_retagg=0;top_ab_validate=4;top_video_score=1;se_daxuechuisou=new;se_relevant_query=new;top_adpar=0;top_quality=0;top_recall=1;top_root_web=0;tp_discussion_feed_type_android=0;se_majorob_style=1;top_tffrt=0;top_billboard_count=1;top_retag=0;top_local=1;top_30=0;top_tuner_refactor=-1;se_tf=1;top_fqai=0;top_recall_follow_user=91;se_product_rank_list=0;top_nad=1;top_slot_ad_pos=1;top_yc=0;se_ltr=0;top_feedre_itemcf=31;se_cm=1;se_merger=1;top_ac_merge=0;top_manual_tag=1;top_memberfree=1;top_spec_promo=1;tp_favsku=a;top_feedre_rtt=41;top_video_fix_position=0;top_video_rew=0;top_distinction=3;se_rescore=1;tp_sft=a;top_promo=1;top_root_mg=1;se_consulting_price=n;top_free_content=-1;top_new_feed=1;top_universalebook=1;top_billpic=0;top_mlt_model=0;top_new_user_gift=0;pin_ef=orig;top_test_4_liguangyi=1;top_feedre=1;top_hca=0;se_auto_syn=0;top_raf=n;top_multi_model=0;top_ntr=1;top_tr=0;se_new_market_search=on;se_refactored_search_index=0;top_newfollow=0;top_pfq=5;ls_new_score=0;top_nid=0;top_lowup=1;top_newfollowans=0;top_recall_deep_user=1;top_root=1;top_vd_op=0;top_f_r_nb=1;top_hqt=9;se_minor_onebox=d;top_no_weighing=1;top_vd_gender=0;se_gemini_service=knowledge;top_follow_reason=0;top_mt=0;top_sj=2;top_nmt=0;se_dl=1;se_dt=1;top_login_card=1;top_recall_tb=1;tp_ios_topic_write_pin_guide=1;top_vds_alb_pos=0;top_gr_topic_reweight=0;top_roundtable=1");
        header.put("x-app-za", "OS=iOS&Release=12.0&Model=iPhone8,1&VersionName=4.28.0&VersionCode=1138&Width=750&Height=1334&DeviceType=Phone&Brand=Apple&OperatorType=46003");
        header.put("X-APP-VERSION", "4.28.0");
        header.put("Authorization", "Bearer gt2.0AAAAAA0slqcOhVLFo6KwAAAAAAtNVQJgAgAcIDxvqOr1JLTO3VZY7WsVrlkUVg==");
        header.put("X-UDID", "ALCio8VShQ5LBcRh-JUzElJpOqooAFyZlLc=");
        header.put("X-APP-Build", "release");
        header.put("X-SUGER", "SURGVj0yRkZEQTMzRi02MkE0LTRCQjktODQyMi1CQkQwQjAxMTlFNDU7SURGQT02QjA1NjFFNi0yODIzLTQ0RUMtQTZGOS1CMzBCOUM1NDA1MTM7Q09PUkRfTEFUPTM0LjgyNTA4MjIxMjk3OTQ7Q09PUkRfTE5HPTExMy41NTQ1MTcxNDUwNDk2O0NPT1JEX1RJTUVTVEFNUD0xNTQyMjQ3NDgzO1VESUQ9QUxDaW84VlNoUTVMQmNSaC1KVXpFbEpwT3Fvb0FGeVpsTGM9");
        header.put("X-API-Version", "3.0.92");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-Hans-CN;q=1, en-CN;q=0.9");
        header.put("Accept-Encoding", "gzip");
        header.put("Connection", "keep-alive");
        Connection connection = Jsoup.connect("https://api.zhihu.com/sign_in?client_id=5774b305d2ae4469a2c9258956ea49&grant_type=password&password=ztf714593351&signature=70c36cfd370b3c1dc251a4795181c85540802d9b&source=com.zhihu.ios&timestamp=1542247520&username=%2B8618638157232")
                .headers(header)
                .followRedirects(false)
                .timeout(60000);
        Connection.Response res = connection.ignoreContentType(true).method(Connection.Method.POST).execute();// 执行请求
        System.out.println(res.headers());
        System.out.println(res.cookies());
        System.out.println(res.statusCode());
        String body = res.body();
        System.out.println(ZhihuHotnewsService.unicodeToString(body));
    }
}