package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by user on 2019/6/3.
 */

public class TeamTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;
    public LimitlessTask limitlessTask;
    public SingleTask singleTask;
    public TeamTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
        limitlessTask = new LimitlessTask(ypFairy);
        singleTask = new SingleTask(ypFairy);
    }
    private int taskType = 1;
    private int ranksNum = 1;
    private String ranksName = "";
    private String taskName = "";

    TaskContent.Slide slideActivity;
    TaskContent.Slide slideTask;
    TaskContent.Time timeRanks;
    TaskContent.Time timeXue;

    private boolean yao1 = false;
    private boolean yao2 = false;
    private boolean yao3 = false;
    private boolean jzHan = false;

    abstract class TeamTaskContent extends TaskContent {

        public TeamTaskContent(AtFairyImpl mFairy) throws Exception {
            super(mFairy);
        }

        @Override
        void create() throws Exception {
            slideActivity = new Slide(499, 235, 543, 430);
            slideTask = new Slide(132, 275, 167, 394);
            timeRanks = new Time();
            timeXue = new Time();
            if (AtFairyConfig.getOption("yao1").equals("1")) {
                yao1 = true;
            }
            if (AtFairyConfig.getOption("yao2").equals("1")) {
                yao2 = true;
            }
            if (AtFairyConfig.getOption("yao3").equals("1")) {
                yao3 = true;
            }
            if (AtFairyConfig.getOption("han").equals("1")) {
                jzHan = true;
            }
        }

        @Override
        void init() throws Exception {
            gamePublicFuntion.init();
            gamePublicFuntion.ranksSetUp(1);
            gamePublicFuntion.battleEnd();
            setTaskName(1);
        }

        @Override
        void inOperation() throws Exception {
            result = mFairy.findPic("findWay.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.95f);
            if (l > 30) {
                err = 0;
            }

            result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
            mFairy.onTap(0.8f, result, "????????????", 1000);

            result = mFairy.findPic(782,394,1001,570,"shiyong.png");
            mFairy.onTap(0.8f, result, "??????", 1000);

            result = mFairy.findPic(563,602,680,693,"dian.png");
            mFairy.onTap(0.8f, result,954,585,972,599, "?????????????????????", 1000);

            result = mFairy.findPic(573, 71, 822, 230, "err4.png");
            if (result.sim > 0.85f) {
                mFairy.onTap(261, 107, 269, 121, "??????err4", 2000);
                mFairy.onTap(261, 107, 269, 121, "??????err4", 1000);
            }
        }

        @Override
        void content_01() throws Exception {
            if(timeCount(13, 0)){
                if(activityJudgeCount(2)){
                    setTaskEnd();
                }
                return;
            }

            result = mFairy.findPic("activity.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "??????", 1000);
                oneJudgeCount = 0;
            }
            result = mFairy.findPic(new String[]{"UIactivity.png", "UIactivity1.png"});
            if (result.sim > 0.8f) {
                oneJudgeCount = 0;
                switch (taskType) {
                    case 1:
                        result = mFairy.findPic("activities.png");
                        mFairy.onTap(0.9f, result, "????????????", 1500);

                        break;
                    case 2:
                        result = mFairy.findPic("timeLimit.png");
                        mFairy.onTap(0.9f, result, "????????????", 1500);
                        break;
                }

                result = mFairy.findPic(270,548,1145,649,"huoyue.png");
                mFairy.onTap(0.92f,result,result.x+25,result.y+25,result.x+35,result.y+35,"????????????",1000);

                FindResult result1 = mFairy.findPic(117, 172, 842, 547, taskName);
                if (result1.sim > 0.82f) {
                    if (taskName.equals("ylsl.png")) {
                        result = mFairy.findPic2(result1.x + 50, result1.y + 25,
                                result1.x + 100, result1.y + 60, "ylslEnd.png");
                        LtLog.e("" + result.sim);
                        if (result.sim > 0.95f) {
                            LtLog.e(mFairy.getLineInfo("??????????????????,End!"));
                            setTaskEnd();
                            return;
                        }
                    }


                    if (taskName.equals("dqg.png")) {
                        result = mFairy.findPic(result1.x + 133, result1.y + 22,
                                result1.x + 259, result1.y + 110, "pp.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "??????", 500);
                            if (gamePublicFuntion.unableFindWay()) {
                                gamePublicFuntion.err();
                            } else {
                                Thread.sleep(3000);
                                setTaskName(2);
                            }
                            activityJudgeCount=0;
                            gamePublicFuntion.whileContent();
                            return;
                        }

                    } else {

                        result = mFairy.findPic(result1.x + 133, result1.y + 22,
                                result1.x + 259, result1.y + 110, "pp.png");
                        if (result.sim > 0.8f) {
                            gamePublicFuntion.close();
                            setTaskName(2);
                            return;
                        }
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "complete.png");
                    if (result.sim > 0.85f) {
                        LtLog.e("???????????????");
                        setTaskEnd();
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "kai.png");
                    if (result.sim > 0.85f) {
                        LtLog.e("???????????????");
                        setTaskEnd();
                    }
                }

                slideActivity.slideRange(new int[]{4, 6, 8, 10,11}, 2);
            } else {
                if (oneJudgeCount(3)) {
                    setTaskName(0);
                    return;
                }
            }
        }

        @Override
        void content_02() throws Exception {
            timeCount(15, 0);
            result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
            mFairy.onTap(0.95f, result, "??????", 1500);

            result = mFairy.findPic("UIinvitation.png");
            if (result.sim > 0.88f) {

                if (yao1) {
                    mFairy.onTap(484, 209, 485, 210, "??????", 500);
                    mFairy.onTap(484, 209, 485, 210, "??????", 1000);
                    if (gamePublicFuntion.yaoqing()) {
                        return;
                    }
                }

                if (yao2) {
                    mFairy.onTap(627, 214, 628, 215, "??????", 500);
                    mFairy.onTap(627, 214, 628, 215, "??????", 1000);
                    if (gamePublicFuntion.yaoqing()) {
                        return;
                    }
                }

                if (yao3) {
                    mFairy.onTap(764, 218, 765, 219, "??????", 500);
                    mFairy.onTap(764, 218, 765, 219, "??????", 1000);
                    if (gamePublicFuntion.yaoqing()) {
                        return;
                    }
                }

                mFairy.onTap(787, 124, 812, 144, "????????????????????????", 1000);
            }

            result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
            if (result.sim > 0.85f) {
                err = 0;
                result = mFairy.findPic("create.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("rank5.png");
                mFairy.onTap(0.9f, result, "??????", 1000);

                FindResult result1 = mFairy.findPic(942, 591, 1119, 685, "dd1.png");
                FindResult result2 = mFairy.findPic("tz1.png");
                if (result2.sim > 0.85f) {
                    twoJudgeCount = 0;
                }
                if (result1.sim > 0.85f && result2.sim < 0.85f) {

                    result = mFairy.findPic(268, 480, 1101, 531, "lx.png");
                    mFairy.onTap(0.82f, result,result.x+1,result.y-200,result.x+2,result.y-150, "??????", 2000);

                    gamePublicFuntion.pleaseLeaveRanks();

                    if (ranksName.equals("tf0.png")) {
                        if (gamePublicFuntion.limitNumber(2) == false) {
                            return;
                        }
                    }
                    result = mFairy.findPic(ranksName);
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 0;

                        if (ranksNum <= gamePublicFuntion.ranksGetNum()) {
                            gamePublicFuntion.close();
                            setTaskName(3);
                            gamePublicFuntion.ranksSetUp(1);
                            return;
                        }

                        FindResult result3 = mFairy.findPic("Yelling.png");
                        if (result3.sim > 0.85f) {
                            if (jzHan) {
                                result = mFairy.findPic("han1.png");
                                mFairy.onTap(0.8f, result, "???", 1000);
                            } else {
                                result = mFairy.findPic("han.png");
                                mFairy.onTap(0.8f, result, "", 1000);
                            }

                            mFairy.onTap(0.85f, result3, "??????", 2000);

                            if (yao1 || yao2 || yao3) {
                                mFairy.onTap(854, 638, 855, 639, "????????????", 1000);
                            }
                        }
                    } else {
                        if (oneJudgeCount(2)) {
                            result = mFairy.findPic("target.png");
                            mFairy.onTap(0.85f, result, "????????????", 1000);
                        }
                    }

                } else {
                    if (twoJudgeCount(3)) {
                        result = mFairy.findPic("td.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, "????????????", 1500);
                            mFairy.onTap(853, 506, 855, 510, "??????", 1000);
                        }
                    }
                }
            }
        }

        @Override
        void content_03() throws Exception {
            if(timeCount(13, 0)){
                if(activityJudgeCount(2)){
                    setTaskEnd();
                }
                return;
            }

            result = mFairy.findPic("activity.png");
            if (result.sim > 0.8f) {
                err=0;
                mFairy.onTap(0.8f, result, "??????", 1000);
                oneJudgeCount = 0;
            }
            result = mFairy.findPic(new String[]{"UIactivity.png", "UIactivity1.png"});
            if (result.sim > 0.8f) {
                switch (taskType) {
                    case 1:
                        result = mFairy.findPic("activities.png");
                        mFairy.onTap(0.9f, result, "????????????", 1500);
                        break;
                    case 2:
                        result = mFairy.findPic("timeLimit.png");
                        mFairy.onTap(0.9f, result, "????????????", 1500);
                        break;
                }

                result = mFairy.findPic(270,548,1145,649,"huoyue.png");
                mFairy.onTap(0.92f,result,result.x+25,result.y+25,result.x+35,result.y+35,"????????????",1000);

                FindResult result1 = mFairy.findPic(117, 172, 842, 547, taskName);
                if (result1.sim > 0.82f) {
                    if (taskName.equals("ylsl.png")) {
                        result = mFairy.findPic2(result1.x + 50, result1.y + 25,
                                result1.x + 100, result1.y + 60, "ylslEnd.png");
                        LtLog.e("" + result.sim);
                        if (result.sim > 0.95f) {
                            LtLog.e(mFairy.getLineInfo("??????????????????,End!"));
                            setTaskEnd();
                            return;
                        }
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "pp.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "??????", 500);
                        if (gamePublicFuntion.unableFindWay()) {
                            gamePublicFuntion.err();
                        } else {
                            Thread.sleep(1000);
                            setTaskName(4);
                        }
                        activityJudgeCount=0;
                        return;
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "complete.png");
                    if (result.sim > 0.85f) {
                        LtLog.e("???????????????");
                        setTaskEnd();
                    }

                    result = mFairy.findPic(result1.x + 133, result1.y + 22,
                            result1.x + 259, result1.y + 110, "kai.png");
                    if (result.sim > 0.85f) {
                        LtLog.e("???????????????");
                        setTaskEnd();
                    }
                }

                slideActivity.slideRange(new int[]{4, 6, 8, 10,11}, 2);
            } else {
                if (oneJudgeCount(3)) {
                    setTaskName(0);
                    return;
                }
            }
        }

    }

    public void ylsl() throws Exception {
        new TeamTaskContent(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "????????????";
                taskName = "ylsl.png";
                ranksName = "ylsl0.png";
                ranksNum = 2;
                taskType = 1;
            }
            @Override
            void inOperation() throws Exception {
                super.inOperation();

                gamePublicFuntion.qx();

                result = mFairy.findPic(1034, 277, 1192, 585, "ylsl1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "????????????????????????", 1000);
                } else {
                    gamePublicFuntion.chat();
                }

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(1);
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    setTaskName(4);
                    return;
                }
            }

            @Override
            void content_02() throws Exception {
                super.content_02();
                gamePublicFuntion.selectionTask("team.png", "ylslRanks.png");
            }

            @Override
            void content_04() throws Exception {
                timeCount(20, 0);
                Thread.sleep(1000);

                gamePublicFuntion.task();

                result = mFairy.findPic("tackChat.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("ylsl3.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("pickup.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    twoJudgeCount = 0;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    gamePublicFuntion.battle(0);
                    err = 0;
                    if (gamePublicFuntion.boss()) {
                        twoJudgeCount = 0;
                    } else {
                        if (twoJudgeCount(3)) {
                            result = mFairy.findPic("task1.png");
                            mFairy.onTap(0.85f, result, 55, 210, 126, 219, "??????????????????", 3000);
                        }
                    }
                    return;
                }else{
                    result = mFairy.findPic(829, 637, 926, 712, "main.png");
                    if (result.sim > 0.8f) {

                        result = mFairy.findPic(137, 143, 287, 412, new String[]{
                                "ylslEnd1.png", "ylslEnd2.png", "ylslEnd3.png", "ylslEnd4.png", "ylslEnd5.png"});
                        if (result.sim > 0.92f) {
                            LtLog.e(mFairy.getLineInfo("????????????10???????????????,End!"));
                            mFairy.ranSwipe(157, 460, 214, 587, 2, 1000, (long)1000);
                            mFairy.ranSwipe(157, 460, 214, 587, 2, 1000, (long)1000);
                            setTaskName(3);
                            gamePublicFuntion.close();
                            return;
                        }

                        if (gamePublicFuntion.mainRanksGetNum() >= 2) {
                            oneJudgeCount = 0;
                        } else {
                            if (oneJudgeCount(8)) {
                                setTaskName(0);
                                return;
                            }
                        }

                        if (err > 3) {
                            result = mFairy.findPic(45, 144, 281, 422, "ylsl2.png");
                            if (result.sim > 0.85f) {
                                err = 0;
                                mFairy.onTap(0.85f, result, "??????", 1000);
                                return;
                            }
                        }

                        slideTask.slideRange(new int[]{6, 8}, 2, 0);

                    }
                }
            }
        };
    }//????????????

    public void mjpt() throws Exception {
        new TeamTaskContent(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "????????????-??????";
                taskName = "mjhb.png";
                ranksName = "mjpt.png";
                ranksNum = 4;
                taskType = 1;
            }
            @Override
            void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("mjhb1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "????????????", 1000);
                } else {
                    gamePublicFuntion.qx();
                }
                gamePublicFuntion.chat();

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(1);
                }
            }

            @Override
            void content_02() throws Exception {
                super.content_02();
                gamePublicFuntion.selectionTask("team.png", "ptmjRanks.png");
            }

            @Override
            void content_04() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                gamePublicFuntion.task();

                result = mFairy.findPic("UImjhb.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 181, 175, 268, 194, "??????", 1000);
                    mFairy.onTap(1036, 633, 1091, 648, "??????", 1000);
                }

                result = mFairy.findPic("mjhb1.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);


                result = mFairy.findPic("mjhbEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "??????????????????!", 1000);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("ylsl3.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    twoJudgeCount = 0;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    gamePublicFuntion.battle(0);
                    err = 0;
                    if (gamePublicFuntion.boss()) {
                        twoJudgeCount = 0;
                    } else {
                        if (twoJudgeCount(3)) {
                            result = mFairy.findPic("task1.png");
                            mFairy.onTap(0.85f, result, 55, 210, 126, 219, "??????????????????", 3000);
                        }
                    }
                    return;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {

                    if (gamePublicFuntion.mainRanksGetNum() >= 2) {
                        oneJudgeCount = 0;
                    } else {
                        if (oneJudgeCount(8)) {
                            setTaskName(0);
                            return;
                        }
                    }
                }
            }
        };
    }//????????????-??????

    public void mjkn() throws Exception {
        new TeamTaskContent(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "????????????-??????";
                taskName = "mjhb.png";
                ranksName = "mjkn.png";
                ranksNum = 5;
                taskType = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                result = mFairy.findPic("mjhb1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "????????????", 1000);
                } else {
                    gamePublicFuntion.qx();
                }
                gamePublicFuntion.chat();

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(1);
                }
            }

            @Override
            void content_02() throws Exception {
                super.content_02();
                gamePublicFuntion.selectionTask("team.png", "knmjRanks.png");
            }

            @Override
            void content_04() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                gamePublicFuntion.task();

                result = mFairy.findPic("UImjhb.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, 182, 261, 264, 285, "??????", 1500);
                    mFairy.onTap(1036, 633, 1091, 648, "??????", 1000);
                }

                result = mFairy.findPic("mjhbEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "??????????????????!", 1000);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("ylsl3.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    twoJudgeCount = 0;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    gamePublicFuntion.battle(0);
                    err = 0;
                    if (gamePublicFuntion.boss()) {
                        twoJudgeCount = 0;
                    } else {
                        if (twoJudgeCount(3)) {
                            result = mFairy.findPic("task1.png");
                            mFairy.onTap(0.85f, result, 55, 210, 126, 219, "??????????????????", 3000);
                        }
                    }
                    return;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {

                    if (gamePublicFuntion.mainRanksGetNum() >= 2) {
                        oneJudgeCount = 0;
                    } else {
                        if (oneJudgeCount(8)) {
                            setTaskName(0);
                            return;
                        }
                    }
                }
            }
        };
    }//????????????-??????

    private int dqg = 0;
    public void dqg(int em) throws Exception {
        new TeamTaskContent(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "?????????"+em;
                taskName = "dqg.png";
                ranksName = "dqg0.png";
                ranksNum = 3;
                dqg = 0;
                taskType = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();

                result = mFairy.findPic("mjhb1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "????????????", 1000);
                } else {
                    gamePublicFuntion.qx();
                }

                gamePublicFuntion.chat();

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(1);
                }

                result = mFairy.findPic(317, 460, 436, 547, "dqgEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "????????????????????????", 1000);
                    setTaskName(2);
                    dqg = 1;
                    gamePublicFuntion.init();
                    return;
                }

            }

            @Override
            void content_02() throws Exception {
                super.content_02();
                gamePublicFuntion.selectionTask("team.png", "dqgRanks.png");
            }

            @Override
            void content_04() throws Exception {
                timeCount(20, 0);
                Thread.sleep(1000);

                result = mFairy.findPic("dqg2.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(278, 420, 280, 422, "???", 1500);
                    mFairy.onTap(1152, 69, 1155, 70, "????????????", 2000);
                    gamePublicFuntion.whileContent();
                    gamePublicFuntion.battle(0);
                }else{
                    gamePublicFuntion.close();
                }


                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.8f) {
                    oneJudgeCount=0;
                    setTaskName(5);
                    return;
                }else{
                    result = mFairy.findPic(1131, 1, 1277, 28, "dqg1.png");
                    if (result.sim > 0.8f) {
                        LtLog.e(mFairy.getLineInfo("???????????????"));

                        if(em==1){
                            gamePublicFuntion.battle(0);
                            Thread.sleep(5000);
                            return;
                        }

                        result = mFairy.findPic("dqg3.png");
                        if (result.sim > 0.8f) {
                            gamePublicFuntion.battle(0);
                            Thread.sleep(3000);
                        } else {
                            if (oneJudgeCount(2)) {
                                if (dqg == 0) {
                                    LtLog.e(mFairy.getLineInfo("????????????"));
                                    mFairy.onTap(1215, 67, 1218, 68, "????????????", 2000);
                                    return;
                                }
                                gamePublicFuntion.battle(0);
                            }
                        }
                    }
                }
            }

            @Override
            void content_05() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                gamePublicFuntion.task();

                result = mFairy.findPic(new String[]{"dqg4.png", "dqg5.png", "dqg6.png"});
                mFairy.onTap(0.8f, result, "??????", 1000);

                result = mFairy.findPic("mjhbEnd.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "?????????????????????!", 5000);
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    twoJudgeCount = 0;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    gamePublicFuntion.battle(0);
                    err = 0;
                    if (gamePublicFuntion.boss()) {
                        twoJudgeCount = 0;
                    } else {
                        if (twoJudgeCount(3)) {
                            result = mFairy.findPic("task1.png");
                            mFairy.onTap(0.85f, result, 55, 210, 126, 219, "??????????????????", 3000);
                        }
                    }
                    return;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {
                    if (gamePublicFuntion.mainRanksGetNum() >= 2) {
                        oneJudgeCount = 0;
                    } else {
                        if (oneJudgeCount(8)) {
                            setTaskName(0);
                            return;
                        }
                    }
                }
            }
        };
    }//?????????


    public void tftz() throws Exception {
        new TeamTaskContent(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "??????-????????????";
                taskName = "tf.png";
                ranksName = "tf0.png";
                ranksNum = 2;
                taskType = 1;
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();


                result = mFairy.findPic("mjhb1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "????????????", 1000);
                } else {
                    gamePublicFuntion.qx();
                }
                gamePublicFuntion.chat();

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(1);
                }
            }

            @Override
            void content_01() throws Exception {
                setTaskName(2);
            }

            @Override
            void content_02() throws Exception {
                super.content_02();
                gamePublicFuntion.selectionTask("team.png", "tfRanks1.png");
            }

            @Override
            void content_04() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                gamePublicFuntion.task();

                result = mFairy.findPic("tf7.png");
                mFairy.onTap(0.9f, result, "????????????", 1000);

                result = mFairy.findPic(322, 278, 964, 392, "ok2.png");
                mFairy.onTap(0.85f, result, 858, 501, 860, 505, "??????", 1000);

                result = mFairy.findPic("UItf.png");
                if (result.sim > 0.85f) {
                    err = 0;
                    result = mFairy.findPic("tf1.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "????????????", 2000);
                        mFairy.onTap(642, 487, 645, 490, "??????", 1500);
                        mFairy.onTap(858, 502, 860, 505, "????????????", 1500);
                        mFairy.onTap(1147, 648, 1150, 650, "?????????????????????", 1000);
                    }

                    result = mFairy.findPic("tf2.png");
                    if (result.sim > 0.85f) {
                        mFairy.onTap(0.85f, result, "??????", 2000);

                        result = mFairy.findPic(363, 270, 770, 397, "ta1.png");
                        if (result.sim > 0.85f) {
                            mFairy.onTap(0.85f, result, 395, 497, 400, 500, "??????", 1000);
                            setTaskName(0);
                            return;
                        }

                        result = mFairy.findPic("tf4.png");
                        if (result.sim > 0.85f) {
                            setTaskName(0);
                            return;
                        }
                        result = mFairy.findPic("tfEnd.png");
                        if (result.sim > 0.92f) {
                            LtLog.e(mFairy.getLineInfo("??????-??????????????????,End!"));
                            setTaskEnd();
                            return;
                        }

                        for (int i = 0; i < 3; i++) {
                            result = mFairy.findPic(643, 372, 1120, 661, "ok4.png");
                            if (result.sim > 0.85f) {
                                i = 0;
                                mFairy.onTap(0.85f, result, "??????", 1000);
                            }
                        }
                    }
                }

                result = mFairy.findPic("tf5.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(417, 500, 420, 502, "??????-??????????????????,End!", 1000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    twoJudgeCount = 0;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    gamePublicFuntion.battle(0);
                    err = 0;
                    if (gamePublicFuntion.boss()) {
                        twoJudgeCount = 0;
                    } else {
                        if (twoJudgeCount(3)) {
                            result = mFairy.findPic("task1.png");
                            mFairy.onTap(0.85f, result, 55, 210, 126, 219, "??????????????????", 3000);
                        }
                    }

                    return;
                }
            }
        };
    }//??????-????????????

    public void ybc() throws Exception {
        new TeamTaskContent(mFairy) {
            @Override
            void create() throws Exception {
                super.create();
                TaskMain.TASKNAME = "????????????100???";
                taskName = "ylsl.png";
                ranksName = "ylsl0.png";
                ranksNum = 3;
                taskType = 1;
            }

            @Override
            void init() throws Exception {
                super.init();
                setTaskName(2);
            }

            @Override
            void content_03() throws Exception {
                timeCount(10, 0);

                result = mFairy.findPic("activity.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "??????", 1000);
                    oneJudgeCount = 0;
                }
                result = mFairy.findPic(new String[]{"UIactivity.png", "UIactivity1.png"});
                if (result.sim > 0.8f) {
                    oneJudgeCount = 0;
                    result = mFairy.findPic("activities.png");
                    mFairy.onTap(0.9f, result, "????????????", 1500);

                    FindResult result1 = mFairy.findPic(117, 172, 842, 547, taskName);
                    if (result1.sim > 0.88f) {

                        result = mFairy.findPic(result1.x + 133, result1.y + 22,
                                result1.x + 259, result1.y + 110, "pp.png");
                        if (result.sim > 0.8f) {
                            mFairy.onTap(0.8f, result, "??????", 500);
                            if (gamePublicFuntion.unableFindWay()) {
                                gamePublicFuntion.err();
                            } else {
                                Thread.sleep(1000);
                                setTaskName(4);
                            }
                            activityJudgeCount=0;
                            return;
                        }
                    }
                    slideActivity.slideRange(new int[]{2, 4, 6, 8}, 2);
                } else

                {
                    if (oneJudgeCount(3)) {
                        setTaskName(0);
                        return;
                    }
                }
            }

            @Override
            void inOperation() throws Exception {
                super.inOperation();
                gamePublicFuntion.qx();

                result = mFairy.findPic(1034, 277, 1192, 585, "ylsl1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "??????????????????", 1000);
                } else {
                    gamePublicFuntion.chat();
                }

                if (timeRanks.timeJudge(60000)) {
                    gamePublicFuntion.ranksSetUp(1);
                }
            }

            @Override
            void content_02() throws Exception {
                super.content_02();
                gamePublicFuntion.selectionTask("team.png", "ylslRanks.png");
            }

            @Override
            void content_04() throws Exception {
                timeCount(10, 0);
                Thread.sleep(1000);

                gamePublicFuntion.task();

                result = mFairy.findPic("tackChat.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("ylsl3.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("pickup.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    twoJudgeCount = 0;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    gamePublicFuntion.battle(0);
                    err = 0;
                    if (gamePublicFuntion.boss()) {
                        twoJudgeCount = 0;
                    } else {
                        if (twoJudgeCount(3)) {
                            result = mFairy.findPic("task1.png");
                            mFairy.onTap(0.85f, result, 55, 210, 126, 219, "??????????????????", 3000);
                        }
                    }
                    return;
                }

                result = mFairy.findPic(829, 637, 926, 712, "main.png");
                if (result.sim > 0.8f) {

                    result = mFairy.findPic(137, 143, 287, 412, "ylsl99.png");
                    if (result.sim > 0.92f) {
                        LtLog.e(mFairy.getLineInfo("????????????100???????????????,End!"));
                        mFairy.ranSwipe(157, 460, 214, 587, 2, 1000, (long)1000);
                        setTaskEnd();
                        return;
                    }

                    if (gamePublicFuntion.mainRanksGetNum() >= 2) {
                        oneJudgeCount = 0;
                    } else {
                        if (oneJudgeCount(8)) {
                            setTaskName(0);
                            return;
                        }
                    }

                    if (err > 3) {
                        result = mFairy.findPic(45, 144, 281, 422, "ylsl2.png");
                        if (result.sim > 0.85f) {
                            err = 0;
                            mFairy.onTap(0.85f, result, "??????", 1000);
                            return;
                        }
                    }

                    slideTask.slideRange(new int[]{6, 8}, 2, 0);

                }
            }
        };
    }//????????????100???

    /***??????*/
    TaskContent.Time lingTime;
    TaskContent.Time judgeRanksTime;
    private boolean lingBools = false;
    private int fudao = 1;
    public void gen() throws Exception {
        new TaskContent(mFairy) {
            @Override
            void create() throws Exception {
                TaskMain.TASKNAME = "??????ing";
                lingTime = new Time();
                timeRanks = new Time();
                judgeRanksTime = new Time();
                if (!AtFairyConfig.getOption("ls").equals("")) {
                    lingBools = true;
                    switch (AtFairyConfig.getOption("ls")) {
                        case "1":
                            lingTime.setTime(System.currentTimeMillis() - 1800000);
                            break;
                        case "2":
                            lingTime.setTime(System.currentTimeMillis() + 1800000);
                            break;
                        case "3":
                            lingTime.setTime(System.currentTimeMillis() + 5400000);
                            break;
                        case "4":
                            lingTime.setTime(System.currentTimeMillis() + 9000000);
                            break;
                    }
                }
            }

            @Override
            void init() throws Exception {
                gamePublicFuntion.close();
                gamePublicFuntion.qx();

                result = mFairy.findPic("yhEnd.png");
                mFairy.onTap(0.9f, result, "????????????", 1000);

                result = mFairy.findPic("msxsEnd1.png");
                mFairy.onTap(0.85f, result, 1148, 594, 1150, 600, "????????????!", 1000);

                result = mFairy.findPic("blsEnd.png");
                mFairy.onTap(0.9f, result, 626, 686, 628, 688, "???????????????init1", 1000);

                result = mFairy.findPic("blsEnd1.png");
                mFairy.onTap(0.9f, result, 1162, 605, 1165, 607, "???????????????init2", 1000);

                result = mFairy.findPic("dsyjEnd.png");
                mFairy.onTap(0.85f, result, 1166, 610, 1168, 612, "????????????init", 1000);

                result = mFairy.findPic("fdzEnd.png");
                mFairy.onTap(0.85f, result, 1167, 677, 1168, 680, "???????????????init", 1000);

                result = mFairy.findPic("gb.png");
                mFairy.onTap(0.9f, result, 1148, 588, 1150, 590, "?????????????????????", 1000);

                result = mFairy.findPic("myqqEnd.png");
                mFairy.onTap(0.85f, result, "????????????init1", 1000);

                result = mFairy.findPic("jzlyEnd.png");
                mFairy.onTap(0.85f, result, 1171, 597, 1173, 600, "????????????init1", 1000);

                result = mFairy.findPic("jzlyEnd1.png");
                mFairy.onTap(0.85f, result, 1164, 606, 1165, 608, "????????????init2", 1000);

                result = mFairy.findPic("jzfzEnd.png");
                mFairy.onTap(0.85f, result, 1171, 597, 1175, 600, "????????????init", 1000);

                result = mFairy.findPic("jzfzEnd.png");
                mFairy.onTap(0.85f, result, 1171, 597, 1175, 600, "????????????init", 1000);

                result = mFairy.findPic(new String[]{
                        "sszEnd.png", "sszEnd1.png"});
                mFairy.onTap(0.85f, result, 1172, 691, 1175, 700, "???????????????init", 1000);

                result = mFairy.findPic("dqcEnd.png");
                mFairy.onTap(0.85f, result, 1158, 617, 1160, 620, "???????????????init", 1000);

                result = mFairy.findPic(489, 601, 789, 716, new String[]{"jx.png", "jx1.png"});
                mFairy.onTap(0.82f, result, 1148, 588, 1150, 590, "??????", 1000);

                result = mFairy.findPic("chatBox.png");
                mFairy.onTap(0.9f, result, "err?????????", 1000);

                setTaskName(1);
            }

            void inOperation() throws Exception {

                result = mFairy.findPic("findWay.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err = 0;
                }

                long l = mFairy.getColorNum(86, 661, 237, 668, "170,187,255", 0.98f);
                if (l > 50) {
                    err = 0;
                }

                result = mFairy.findPic(300, 404, 509, 579, "resurrection.png");
                mFairy.onTap(0.8f, result, "????????????", 1000);

                result = mFairy.findPic(525, 451, 765, 563, "bls2.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic(782,394,1001,570,"shiyong.png");
                mFairy.onTap(0.8f, result, "??????", 1000);

                result = mFairy.findPic(563,602,680,693,"dian.png");
                mFairy.onTap(0.8f, result,954,585,972,599, "?????????????????????", 1000);

                result = mFairy.findPic(573, 71, 822, 230, "err4.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(261, 107, 269, 121, "??????err4", 2000);
                    mFairy.onTap(261, 107, 269, 121, "??????err4", 1000);
                }

                if (timeRanks.timeJudge(5000)) {
                    gamePublicFuntion.ranksSetUp(2);
                }
            }

            @Override
            void content_01() throws Exception {
                timeCount(15, 0);

                if (lingBools) {
                    if (lingTime.timeJudge(1800000)) {
                        limitlessTask.lingshuang();
                        gamePublicFuntion.close();
                    }
                }

                if (mFairy.dateHour() == 23) {
                    if (oneSecond()) {
                        singleTask.signIn();
                        gamePublicFuntion.close();
                    }
                }

                if (judgeRanksTime.timeJudge(200000)) {
                    if (judgeRanks()) {
                        LtLog.e(mFairy.getLineInfo("???????????????????????????,end!"));
                        LtLog.e(mFairy.getLineInfo("???????????????????????????,end!"));
                        LtLog.e(mFairy.getLineInfo("???????????????????????????,end!"));
                        setTaskEnd();
                        return;
                    }
                }

                content_02();

                gamePublicFuntion.battle(0);
                gamePublicFuntion.chat();

                result = mFairy.findPic("main.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                if (gamePublicFuntion.boss()) {
                    err = 0;
                }

                result = mFairy.findPic(1199, 98, 1279, 334, "leave.png");
                if (result.sim > 0.85f) {
                    err=0;
                }

                result = mFairy.findPic("ok1.png");
                mFairy.onTap(0.85f, result, "????????????", 1000);

                result = mFairy.findPic("song2.png");
                mFairy.onTap(0.85f, result, "??????", 1000);

                result = mFairy.findPic(622, 322, 1057, 617, "gd1.png");
                if (result.sim > 0.85f) {
                    mFairy.onTap(0.85f, result, "??????", 1000);
                } else {
                    gamePublicFuntion.qx();
                }

                result = mFairy.findPic("mjhbEnd.png");
                mFairy.onTap(0.85f, result, "??????????????????!", 1000);

                result = mFairy.findPic("tf5.png");
                mFairy.onTap(0.85f, result, 417, 500, 420, 502, "??????-??????????????????,End!", 1000);
            }

            @Override
            void content_02() throws Exception {
                result = mFairy.findPic(933,0,1274,45,"gen1.png");
                if(result.sim>0.85f){
                    LtLog.e(mFairy.getLineInfo("????????????"));
                    err=0;
                    mFairy.onTap(741, 28, 742, 29, "???", 5000);
                    gamePublicFuntion.battle(1);
                    Thread.sleep(3000);
                }

                result = mFairy.findPic(933,0,1274,45,"gen2.png");
                if(result.sim>0.85f){
                    LtLog.e(mFairy.getLineInfo("???????????????"));
                    err=0;
                    result = mFairy.findPic(525, 451, 765, 563, "bls2.png");
                    mFairy.onTap(0.85f, result, "????????????", 1000);

                    result = mFairy.findPic(989, 13, 1095, 118, "bls1.png");
                    mFairy.onTap(0.85f, result, "????????????", 1000);
                }

                result = mFairy.findPic(933,0,1274,45,"gen3.png");
                if(result.sim>0.85f){
                    LtLog.e(mFairy.getLineInfo("????????????"));
                    err=0;
                    mFairy.onTap(1063, 46, 1065, 50, "????????????", 1000);
                    gamePublicFuntion.battle(0);
                    Thread.sleep(5000);
                }

                result = mFairy.findPic(933,0,1274,45,"gen4.png");
                if(result.sim>0.85f){
                    LtLog.e(mFairy.getLineInfo("???????????????"));
                    err=0;
                    result = mFairy.findPic(560, 470, 731, 537, "fdz1.png");
                    if (result.sim > 0.85f) {
                        oneJudgeCount = 5;
                        mFairy.onTap(0.85f, result, "????????????", 1000);
                    }
                    if (oneJudgeCount(2)) {
                        mFairy.onTap(1229, 46, 1253, 86, "????????????", 3000);
                    }

                    result = mFairy.findPic(901, 566, 1021, 707, "wei.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(520, 363, 550, 393, "??????????????????", 1000);
                        mFairy.onTap(1143, 60, 1160, 83, "??????", 3000);
                    }

                }


                result = mFairy.findPic(933,0,1274,45,"gen5.png");
                if(result.sim>0.85f){
                    LtLog.e(mFairy.getLineInfo("???????????????"));
                    err=0;
                    result = mFairy.findPic(560, 470, 731, 537, "fdz1.png");
                    if (result.sim > 0.85f) {
                        twoJudgeCount = 5;
                        mFairy.onTap(0.85f, result, "????????????", 1000);
                    }

                    result = mFairy.findPic("red.png");
                    if (result.sim < 0.85f) {
                        result = mFairy.findPic("fdz2.png");
                        mFairy.onTap(0.85f, result, "????????????", 3500);
                    }

                    if (twoJudgeCount(3)) {
                        mFairy.onTap(1229, 46, 1253, 86, "????????????", 3000);
                    }

                    result = mFairy.findPic(901, 566, 1021, 707, "wei.png");
                    if (result.sim > 0.8f) {
                        switch (fudao) {
                            case 1:
                                mFairy.onTap(335, 443, 353, 457, "???????????????", 1000);
                                fudao = 1;
                                break;
                            case 2:
                                mFairy.onTap(647, 310, 666, 334, "???????????????", 1000);
                                fudao = 2;
                                break;
                        }
                        mFairy.onTap(1141, 61, 1161, 79, "??????", 3000);
                    }
                }
            }
        };
    }//??????

    public boolean judgeRanks() throws Exception {
        gamePublicFuntion.close();
        int num = 0;
        for (int i = 0; i < 10; i++) {

            result = mFairy.findPic(new String[]{"ranks.png", "ranks1.png"});
            mFairy.onTap(0.95f, result, "??????", 1500);

            result = mFairy.findPic(new String[]{"UIranks.png", "UIranks1.png"});
            if (result.sim > 0.85f) {
                result = mFairy.findPic("rank5.png");
                mFairy.onTap(0.9f, result, "??????", 1000);

                result = mFairy.findPic("create.png");
                if (result.sim > 0.85f) {
                    num++;
                }

                result = mFairy.findPic(942, 591, 1119, 685, "dd1.png");
                if (result.sim > 0.85f) {
                    num++;
                }
            }

            if (num > 2) {
                return true;
            }
        }
        gamePublicFuntion.close();
        return false;
    }//
}