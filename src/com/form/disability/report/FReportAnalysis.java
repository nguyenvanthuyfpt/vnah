package com.form.disability.report;

import com.form.FBeans;
import com.form.FSeed;

public class FReportAnalysis extends FSeed {
    private int id;
    private int kyBC = getMonth(getCurrentSqlDate()) > 6 ? 2 : 1;
    private int namBC = getYear(getCurrentSqlDate());
    private int id_tinh;
    private int level;
    private int quanlyca = 0;
    private String func;
    private String nameArea;
    private FBeans store = new FBeans();

    private String total;
    private String totalFemale;
    private String totalUnder6;
    private String totalUnder16;
    private String totalDacam;
    private String totalDihoc;
    private String totalCoviec;

    private String paramvalue_1;
    private String paramvalue_2;
    private String paramvalue_3;
    private String paramvalue_4;
    private String paramvalue_5;
    private String paramvalue_6;
    private String paramvalue_7;
    private String paramvalue_8;
    private String paramvalue_9;
    private String paramvalue_10;
    private String paramvalue_11;
    private String paramvalue_12;
    private String paramvalue_13;
    private String paramvalue_14;
    private String paramvalue_15;
    private String paramvalue_16;
    private String paramvalue_17;
    private String paramvalue_18;
    private String paramvalue_19;
    private String paramvalue_20;
    private String paramvalue_21;
    private String paramvalue_22;
    private String paramvalue_23;
    private String paramvalue_24;
    private String paramvalue_25;
    private String paramvalue_26;
    private String paramvalue_27;
    private String paramvalue_28;
    private String paramvalue_29;
    private String paramvalue_30;
    private String paramvalue_31;
    private String paramvalue_32;
    private String paramvalue_33;
    private String paramvalue_34;
    private String paramvalue_35;
    private String paramvalue_36;
    private String paramvalue_37;
    private String paramvalue_38;
    private String paramvalue_39;
    private String paramvalue_40;
    private String paramvalue_41;
    private String paramvalue_42;
    private String paramvalue_43;
    private String paramvalue_44;
    private String paramvalue_45;
    private String paramvalue_46;
    private String paramvalue_47;
    private String paramvalue_48;
    private String paramvalue_49;
    private String paramvalue_50;
    private String paramvalue_51;
    private String paramvalue_52;
    private String paramvalue_53;
    private String paramvalue_54;
    private String paramvalue_55;
    private String paramvalue_56;
    private String paramvalue_57;
    private String paramvalue_58;
    private String paramvalue_59;
    private String paramvalue_60;
    private String paramvalue_61;
    private String paramvalue_62;
    private String paramvalue_63;
    private String paramvalue_64;
    private String paramvalue_65;
    private String paramvalue_66;
    private String paramvalue_67;
    private String paramvalue_68;
    private String paramvalue_69;
    private String paramvalue_70;
    private String paramvalue_71;
    private String paramvalue_72;
    private String paramvalue_73;
    private String paramvalue_74;
    private String paramvalue_75;
    private String paramvalue_76;
    private String paramvalue_77;
    private String paramvalue_78;
    private String paramvalue_79;
    private String paramvalue_80;
    private String paramvalue_81;
    private String paramvalue_82;
    private String paramvalue_83;
    private String paramvalue_84;
    private String paramvalue_85;
    private String paramvalue_86;
    private String paramvalue_87;
    private String paramvalue_88;
    private String paramvalue_89;
    private String paramvalue_90;
    private String paramvalue_91;
    private String paramvalue_92;
    private String paramvalue_93;
    private String paramvalue_94;
    private String paramvalue_95;
    private String paramvalue_96;
    private String paramvalue_97;
    private String paramvalue_98;
    private String paramvalue_99;
    private String paramvalue_100;
    private String paramvalue_101;
    private String paramvalue_102;
    private String paramvalue_103;

    public void reset() {
        this.nameArea = "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId_tinh(int id_tinh) {
        this.id_tinh = id_tinh;
    }

    public int getId_tinh() {
        return id_tinh;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setKyBC(int kyBC) {
        this.kyBC = kyBC;
    }

    public int getKyBC() {
        return kyBC;
    }

    public void setNamBC(int namBC) {
        this.namBC = namBC;
    }

    public int getNamBC() {
        return namBC;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotalFemale(String totalFemale) {
        this.totalFemale = totalFemale;
    }

    public String getTotalFemale() {
        return totalFemale;
    }

    public void setTotalUnder6(String totalUnder6) {
        this.totalUnder6 = totalUnder6;
    }

    public String getTotalUnder6() {
        return totalUnder6;
    }

    public void setTotalUnder16(String totalUnder16) {
        this.totalUnder16 = totalUnder16;
    }

    public String getTotalUnder16() {
        return totalUnder16;
    }

    public void setTotalDacam(String totalDacam) {
        this.totalDacam = totalDacam;
    }

    public String getTotalDacam() {
        return totalDacam;
    }

    public void setTotalDihoc(String totalDihoc) {
        this.totalDihoc = totalDihoc;
    }

    public String getTotalDihoc() {
        return totalDihoc;
    }

    public void setTotalCoviec(String totalCoviec) {
        this.totalCoviec = totalCoviec;
    }

    public String getTotalCoviec() {
        return totalCoviec;
    }

    public void setParamvalue_1(String paramvalue_1) {
        this.paramvalue_1 = paramvalue_1;
    }

    public String getParamvalue_1() {
        return paramvalue_1;
    }

    public void setParamvalue_2(String paramvalue_2) {
        this.paramvalue_2 = paramvalue_2;
    }

    public String getParamvalue_2() {
        return paramvalue_2;
    }

    public void setParamvalue_3(String paramvalue_3) {
        this.paramvalue_3 = paramvalue_3;
    }

    public String getParamvalue_3() {
        return paramvalue_3;
    }

    public void setParamvalue_4(String paramvalue_4) {
        this.paramvalue_4 = paramvalue_4;
    }

    public String getParamvalue_4() {
        return paramvalue_4;
    }

    public void setParamvalue_5(String paramvalue_5) {
        this.paramvalue_5 = paramvalue_5;
    }

    public String getParamvalue_5() {
        return paramvalue_5;
    }

    public void setParamvalue_6(String paramvalue_6) {
        this.paramvalue_6 = paramvalue_6;
    }

    public String getParamvalue_6() {
        return paramvalue_6;
    }

    public void setParamvalue_7(String paramvalue_7) {
        this.paramvalue_7 = paramvalue_7;
    }

    public String getParamvalue_7() {
        return paramvalue_7;
    }

    public void setParamvalue_8(String paramvalue_8) {
        this.paramvalue_8 = paramvalue_8;
    }

    public String getParamvalue_8() {
        return paramvalue_8;
    }

    public void setParamvalue_9(String paramvalue_9) {
        this.paramvalue_9 = paramvalue_9;
    }

    public String getParamvalue_9() {
        return paramvalue_9;
    }

    public void setParamvalue_10(String paramvalue_10) {
        this.paramvalue_10 = paramvalue_10;
    }

    public String getParamvalue_10() {
        return paramvalue_10;
    }

    public void setParamvalue_11(String paramvalue_11) {
        this.paramvalue_11 = paramvalue_11;
    }

    public String getParamvalue_11() {
        return paramvalue_11;
    }

    public void setParamvalue_12(String paramvalue_12) {
        this.paramvalue_12 = paramvalue_12;
    }

    public String getParamvalue_12() {
        return paramvalue_12;
    }

    public void setParamvalue_13(String paramvalue_13) {
        this.paramvalue_13 = paramvalue_13;
    }

    public String getParamvalue_13() {
        return paramvalue_13;
    }

    public void setParamvalue_14(String paramvalue_14) {
        this.paramvalue_14 = paramvalue_14;
    }

    public String getParamvalue_14() {
        return paramvalue_14;
    }

    public void setParamvalue_15(String paramvalue_15) {
        this.paramvalue_15 = paramvalue_15;
    }

    public String getParamvalue_15() {
        return paramvalue_15;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setParamvalue_16(String paramvalue_16) {
        this.paramvalue_16 = paramvalue_16;
    }

    public String getParamvalue_16() {
        return paramvalue_16;
    }

    public void setParamvalue_17(String paramvalue_17) {
        this.paramvalue_17 = paramvalue_17;
    }

    public String getParamvalue_17() {
        return paramvalue_17;
    }

    public void setParamvalue_18(String paramvalue_18) {
        this.paramvalue_18 = paramvalue_18;
    }

    public String getParamvalue_18() {
        return paramvalue_18;
    }

    public void setParamvalue_19(String paramvalue_19) {
        this.paramvalue_19 = paramvalue_19;
    }

    public String getParamvalue_19() {
        return paramvalue_19;
    }

    public void setParamvalue_20(String paramvalue_20) {
        this.paramvalue_20 = paramvalue_20;
    }

    public String getParamvalue_20() {
        return paramvalue_20;
    }

    public void setParamvalue_21(String paramvalue_21) {
        this.paramvalue_21 = paramvalue_21;
    }

    public String getParamvalue_21() {
        return paramvalue_21;
    }

    public void setParamvalue_22(String paramvalue_22) {
        this.paramvalue_22 = paramvalue_22;
    }

    public String getParamvalue_22() {
        return paramvalue_22;
    }

    public void setParamvalue_23(String paramvalue_23) {
        this.paramvalue_23 = paramvalue_23;
    }

    public String getParamvalue_23() {
        return paramvalue_23;
    }

    public void setParamvalue_24(String paramvalue_24) {
        this.paramvalue_24 = paramvalue_24;
    }

    public String getParamvalue_24() {
        return paramvalue_24;
    }

    public void setParamvalue_25(String paramvalue_25) {
        this.paramvalue_25 = paramvalue_25;
    }

    public String getParamvalue_25() {
        return paramvalue_25;
    }

    public void setParamvalue_26(String paramvalue_26) {
        this.paramvalue_26 = paramvalue_26;
    }

    public String getParamvalue_26() {
        return paramvalue_26;
    }

    public void setParamvalue_27(String paramvalue_27) {
        this.paramvalue_27 = paramvalue_27;
    }

    public String getParamvalue_27() {
        return paramvalue_27;
    }

    public void setParamvalue_28(String paramvalue_28) {
        this.paramvalue_28 = paramvalue_28;
    }

    public String getParamvalue_28() {
        return paramvalue_28;
    }

    public void setParamvalue_29(String paramvalue_29) {
        this.paramvalue_29 = paramvalue_29;
    }

    public String getParamvalue_29() {
        return paramvalue_29;
    }

    public void setParamvalue_30(String paramvalue_30) {
        this.paramvalue_30 = paramvalue_30;
    }

    public String getParamvalue_30() {
        return paramvalue_30;
    }

    public void setParamvalue_31(String paramvalue_31) {
        this.paramvalue_31 = paramvalue_31;
    }

    public String getParamvalue_31() {
        return paramvalue_31;
    }

    public void setParamvalue_32(String paramvalue_32) {
        this.paramvalue_32 = paramvalue_32;
    }

    public String getParamvalue_32() {
        return paramvalue_32;
    }

    public void setParamvalue_33(String paramvalue_33) {
        this.paramvalue_33 = paramvalue_33;
    }

    public String getParamvalue_33() {
        return paramvalue_33;
    }

    public void setParamvalue_34(String paramvalue_34) {
        this.paramvalue_34 = paramvalue_34;
    }

    public String getParamvalue_34() {
        return paramvalue_34;
    }

    public void setParamvalue_35(String paramvalue_35) {
        this.paramvalue_35 = paramvalue_35;
    }

    public String getParamvalue_35() {
        return paramvalue_35;
    }

    public void setParamvalue_36(String paramvalue_36) {
        this.paramvalue_36 = paramvalue_36;
    }

    public String getParamvalue_36() {
        return paramvalue_36;
    }

    public void setParamvalue_37(String paramvalue_37) {
        this.paramvalue_37 = paramvalue_37;
    }

    public String getParamvalue_37() {
        return paramvalue_37;
    }

    public void setParamvalue_38(String paramvalue_38) {
        this.paramvalue_38 = paramvalue_38;
    }

    public String getParamvalue_38() {
        return paramvalue_38;
    }

    public void setParamvalue_39(String paramvalue_39) {
        this.paramvalue_39 = paramvalue_39;
    }

    public String getParamvalue_39() {
        return paramvalue_39;
    }

    public void setParamvalue_40(String paramvalue_40) {
        this.paramvalue_40 = paramvalue_40;
    }

    public String getParamvalue_40() {
        return paramvalue_40;
    }

    public void setParamvalue_41(String paramvalue_41) {
        this.paramvalue_41 = paramvalue_41;
    }

    public String getParamvalue_41() {
        return paramvalue_41;
    }

    public void setParamvalue_42(String paramvalue_42) {
        this.paramvalue_42 = paramvalue_42;
    }

    public String getParamvalue_42() {
        return paramvalue_42;
    }

    public void setParamvalue_43(String paramvalue_43) {
        this.paramvalue_43 = paramvalue_43;
    }

    public String getParamvalue_43() {
        return paramvalue_43;
    }

    public void setParamvalue_44(String paramvalue_44) {
        this.paramvalue_44 = paramvalue_44;
    }

    public String getParamvalue_44() {
        return paramvalue_44;
    }

    public void setParamvalue_45(String paramvalue_45) {
        this.paramvalue_45 = paramvalue_45;
    }

    public String getParamvalue_45() {
        return paramvalue_45;
    }

    public void setParamvalue_46(String paramvalue_46) {
        this.paramvalue_46 = paramvalue_46;
    }

    public String getParamvalue_46() {
        return paramvalue_46;
    }

    public void setParamvalue_47(String paramvalue_47) {
        this.paramvalue_47 = paramvalue_47;
    }

    public String getParamvalue_47() {
        return paramvalue_47;
    }

    public void setParamvalue_48(String paramvalue_48) {
        this.paramvalue_48 = paramvalue_48;
    }

    public String getParamvalue_48() {
        return paramvalue_48;
    }

    public void setParamvalue_49(String paramvalue_49) {
        this.paramvalue_49 = paramvalue_49;
    }

    public String getParamvalue_49() {
        return paramvalue_49;
    }

    public void setParamvalue_50(String paramvalue_50) {
        this.paramvalue_50 = paramvalue_50;
    }

    public String getParamvalue_50() {
        return paramvalue_50;
    }

    public void setParamvalue_51(String paramvalue_51) {
        this.paramvalue_51 = paramvalue_51;
    }

    public String getParamvalue_51() {
        return paramvalue_51;
    }

    public void setParamvalue_52(String paramvalue_52) {
        this.paramvalue_52 = paramvalue_52;
    }

    public String getParamvalue_52() {
        return paramvalue_52;
    }

    public void setParamvalue_53(String paramvalue_53) {
        this.paramvalue_53 = paramvalue_53;
    }

    public String getParamvalue_53() {
        return paramvalue_53;
    }

    public void setParamvalue_54(String paramvalue_54) {
        this.paramvalue_54 = paramvalue_54;
    }

    public String getParamvalue_54() {
        return paramvalue_54;
    }

    public void setParamvalue_55(String paramvalue_55) {
        this.paramvalue_55 = paramvalue_55;
    }

    public String getParamvalue_55() {
        return paramvalue_55;
    }

    public void setParamvalue_56(String paramvalue_56) {
        this.paramvalue_56 = paramvalue_56;
    }

    public String getParamvalue_56() {
        return paramvalue_56;
    }

    public void setParamvalue_57(String paramvalue_57) {
        this.paramvalue_57 = paramvalue_57;
    }

    public String getParamvalue_57() {
        return paramvalue_57;
    }

    public void setParamvalue_58(String paramvalue_58) {
        this.paramvalue_58 = paramvalue_58;
    }

    public String getParamvalue_58() {
        return paramvalue_58;
    }

    public void setParamvalue_59(String paramvalue_59) {
        this.paramvalue_59 = paramvalue_59;
    }

    public String getParamvalue_59() {
        return paramvalue_59;
    }

    public void setParamvalue_60(String paramvalue_60) {
        this.paramvalue_60 = paramvalue_60;
    }

    public String getParamvalue_60() {
        return paramvalue_60;
    }

    public void setParamvalue_61(String paramvalue_61) {
        this.paramvalue_61 = paramvalue_61;
    }

    public String getParamvalue_61() {
        return paramvalue_61;
    }

    public void setParamvalue_62(String paramvalue_62) {
        this.paramvalue_62 = paramvalue_62;
    }

    public String getParamvalue_62() {
        return paramvalue_62;
    }

    public void setParamvalue_63(String paramvalue_63) {
        this.paramvalue_63 = paramvalue_63;
    }

    public String getParamvalue_63() {
        return paramvalue_63;
    }

    public void setParamvalue_64(String paramvalue_64) {
        this.paramvalue_64 = paramvalue_64;
    }

    public String getParamvalue_64() {
        return paramvalue_64;
    }

    public void setParamvalue_65(String paramvalue_65) {
        this.paramvalue_65 = paramvalue_65;
    }

    public String getParamvalue_65() {
        return paramvalue_65;
    }

    public void setParamvalue_66(String paramvalue_66) {
        this.paramvalue_66 = paramvalue_66;
    }

    public String getParamvalue_66() {
        return paramvalue_66;
    }

    public void setParamvalue_67(String paramvalue_67) {
        this.paramvalue_67 = paramvalue_67;
    }

    public String getParamvalue_67() {
        return paramvalue_67;
    }

    public void setParamvalue_68(String paramvalue_68) {
        this.paramvalue_68 = paramvalue_68;
    }

    public String getParamvalue_68() {
        return paramvalue_68;
    }

    public void setParamvalue_69(String paramvalue_69) {
        this.paramvalue_69 = paramvalue_69;
    }

    public String getParamvalue_69() {
        return paramvalue_69;
    }

    public void setParamvalue_70(String paramvalue_70) {
        this.paramvalue_70 = paramvalue_70;
    }

    public String getParamvalue_70() {
        return paramvalue_70;
    }

    public void setParamvalue_71(String paramvalue_71) {
        this.paramvalue_71 = paramvalue_71;
    }

    public String getParamvalue_71() {
        return paramvalue_71;
    }

    public void setParamvalue_72(String paramvalue_72) {
        this.paramvalue_72 = paramvalue_72;
    }

    public String getParamvalue_72() {
        return paramvalue_72;
    }

    public void setParamvalue_73(String paramvalue_73) {
        this.paramvalue_73 = paramvalue_73;
    }

    public String getParamvalue_73() {
        return paramvalue_73;
    }

    public void setParamvalue_74(String paramvalue_74) {
        this.paramvalue_74 = paramvalue_74;
    }

    public String getParamvalue_74() {
        return paramvalue_74;
    }

    public void setParamvalue_75(String paramvalue_75) {
        this.paramvalue_75 = paramvalue_75;
    }

    public String getParamvalue_75() {
        return paramvalue_75;
    }

    public void setParamvalue_76(String paramvalue_76) {
        this.paramvalue_76 = paramvalue_76;
    }

    public String getParamvalue_76() {
        return paramvalue_76;
    }

    public void setParamvalue_77(String paramvalue_77) {
        this.paramvalue_77 = paramvalue_77;
    }

    public String getParamvalue_77() {
        return paramvalue_77;
    }

    public void setParamvalue_78(String paramvalue_78) {
        this.paramvalue_78 = paramvalue_78;
    }

    public String getParamvalue_78() {
        return paramvalue_78;
    }

    public void setParamvalue_79(String paramvalue_79) {
        this.paramvalue_79 = paramvalue_79;
    }

    public String getParamvalue_79() {
        return paramvalue_79;
    }

    public void setParamvalue_80(String paramvalue_80) {
        this.paramvalue_80 = paramvalue_80;
    }

    public String getParamvalue_80() {
        return paramvalue_80;
    }

    public void setParamvalue_81(String paramvalue_81) {
        this.paramvalue_81 = paramvalue_81;
    }

    public String getParamvalue_81() {
        return paramvalue_81;
    }

    public void setParamvalue_82(String paramvalue_82) {
        this.paramvalue_82 = paramvalue_82;
    }

    public String getParamvalue_82() {
        return paramvalue_82;
    }

    public void setParamvalue_83(String paramvalue_83) {
        this.paramvalue_83 = paramvalue_83;
    }

    public String getParamvalue_83() {
        return paramvalue_83;
    }

    public void setParamvalue_84(String paramvalue_84) {
        this.paramvalue_84 = paramvalue_84;
    }

    public String getParamvalue_84() {
        return paramvalue_84;
    }

    public void setParamvalue_85(String paramvalue_85) {
        this.paramvalue_85 = paramvalue_85;
    }

    public String getParamvalue_85() {
        return paramvalue_85;
    }

    public void setParamvalue_86(String paramvalue_86) {
        this.paramvalue_86 = paramvalue_86;
    }

    public String getParamvalue_86() {
        return paramvalue_86;
    }

    public void setParamvalue_87(String paramvalue_87) {
        this.paramvalue_87 = paramvalue_87;
    }

    public String getParamvalue_87() {
        return paramvalue_87;
    }

    public void setParamvalue_88(String paramvalue_88) {
        this.paramvalue_88 = paramvalue_88;
    }

    public String getParamvalue_88() {
        return paramvalue_88;
    }

    public void setParamvalue_89(String paramvalue_89) {
        this.paramvalue_89 = paramvalue_89;
    }

    public String getParamvalue_89() {
        return paramvalue_89;
    }

    public void setParamvalue_90(String paramvalue_90) {
        this.paramvalue_90 = paramvalue_90;
    }

    public String getParamvalue_90() {
        return paramvalue_90;
    }

    public void setParamvalue_91(String paramvalue_91) {
        this.paramvalue_91 = paramvalue_91;
    }

    public String getParamvalue_91() {
        return paramvalue_91;
    }

    public void setParamvalue_92(String paramvalue_92) {
        this.paramvalue_92 = paramvalue_92;
    }

    public String getParamvalue_92() {
        return paramvalue_92;
    }

    public void setParamvalue_93(String paramvalue_93) {
        this.paramvalue_93 = paramvalue_93;
    }

    public String getParamvalue_93() {
        return paramvalue_93;
    }

    public void setParamvalue_94(String paramvalue_94) {
        this.paramvalue_94 = paramvalue_94;
    }

    public String getParamvalue_94() {
        return paramvalue_94;
    }

    public void setParamvalue_95(String paramvalue_95) {
        this.paramvalue_95 = paramvalue_95;
    }

    public String getParamvalue_95() {
        return paramvalue_95;
    }

    public void setParamvalue_96(String paramvalue_96) {
        this.paramvalue_96 = paramvalue_96;
    }

    public String getParamvalue_96() {
        return paramvalue_96;
    }

    public void setParamvalue_97(String paramvalue_97) {
        this.paramvalue_97 = paramvalue_97;
    }

    public String getParamvalue_97() {
        return paramvalue_97;
    }

    public void setParamvalue_98(String paramvalue_98) {
        this.paramvalue_98 = paramvalue_98;
    }

    public String getParamvalue_98() {
        return paramvalue_98;
    }

    public void setParamvalue_99(String paramvalue_99) {
        this.paramvalue_99 = paramvalue_99;
    }

    public String getParamvalue_99() {
        return paramvalue_99;
    }

    public void setParamvalue_100(String paramvalue_100) {
        this.paramvalue_100 = paramvalue_100;
    }

    public String getParamvalue_100() {
        return paramvalue_100;
    }

    public void setParamvalue_101(String paramvalue_101) {
        this.paramvalue_101 = paramvalue_101;
    }

    public String getParamvalue_101() {
        return paramvalue_101;
    }

    public void setParamvalue_102(String paramvalue_102) {
        this.paramvalue_102 = paramvalue_102;
    }

    public String getParamvalue_102() {
        return paramvalue_102;
    }

    public void setParamvalue_103(String paramvalue_103) {
        this.paramvalue_103 = paramvalue_103;
    }

    public String getParamvalue_103() {
        return paramvalue_103;
    }

    public void setQuanlyca(int quanlyca) {
        this.quanlyca = quanlyca;
    }

    public int getQuanlyca() {
        return quanlyca;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getFunc() {
        return func;
    }
}
