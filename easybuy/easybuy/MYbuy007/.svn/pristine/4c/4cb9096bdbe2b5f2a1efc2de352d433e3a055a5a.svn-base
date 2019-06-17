<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
  var contextPath = "${ctx}";
</script>
<div class="soubg">
  <div class="sou">
    <!--Begin 所在收货地区 Begin-->
    	<span class="s_city_b">
        	<span class="fl">送货至：</span>
            <span class="s_city">
            	<span>四川</span>
                <div class="s_city_bg">
                  <div class="s_city_t"></div>
                  <div class="s_city_c">
                    <h2>请选择所在的收货地区</h2>
                    <table border="0" class="c_tab" style="width:235px; margin-top:10px;" cellspacing="0" cellpadding="0">
                      <tr>
                        <th>A</th>
                        <td class="c_h"><span>安徽</span><span>澳门</span></td>
                      </tr>
                      <tr>
                        <th>B</th>
                        <td class="c_h"><span>北京</span></td>
                      </tr>
                      <tr>
                        <th>C</th>
                        <td class="c_h"><span>重庆</span></td>
                      </tr>
                      <tr>
                        <th>F</th>
                        <td class="c_h"><span>福建</span></td>
                      </tr>
                      <tr>
                        <th>G</th>
                        <td class="c_h"><span>广东</span><span>广西</span><span>贵州</span><span>甘肃</span></td>
                      </tr>
                      <tr>
                        <th>H</th>
                        <td class="c_h"><span>河北</span><span>河南</span><span>黑龙江</span><span>海南</span><span>湖北</span><span>湖南</span></td>
                      </tr>
                      <tr>
                        <th>J</th>
                        <td class="c_h"><span>江苏</span><span>吉林</span><span>江西</span></td>
                      </tr>
                      <tr>
                        <th>L</th>
                        <td class="c_h"><span>辽宁</span></td>
                      </tr>
                      <tr>
                        <th>N</th>
                        <td class="c_h"><span>内蒙古</span><span>宁夏</span></td>
                      </tr>
                      <tr>
                        <th>Q</th>
                        <td class="c_h"><span>青海</span></td>
                      </tr>
                      <tr>
                        <th>S</th>
                        <td class="c_h"><span>上海</span><span>山东</span><span>山西</span><span class="c_check">四川</span><span>陕西</span></td>
                      </tr>
                      <tr>
                        <th>T</th>
                        <td class="c_h"><span>台湾</span><span>天津</span></td>
                      </tr>
                      <tr>
                        <th>X</th>
                        <td class="c_h"><span>西藏</span><span>香港</span><span>新疆</span></td>
                      </tr>
                      <tr>
                        <th>Y</th>
                        <td class="c_h"><span>云南</span></td>
                      </tr>
                      <tr>
                        <th>Z</th>
                        <td class="c_h"><span>浙江</span></td>
                      </tr>
                    </table>
                  </div>
                </div>
            </span>
        </span>
        <!--End 所在收货地区 End-->
        <span class="fr">
          <c:if test="${sessionScope.loginUser==null}">
            <span class="fl">你好，请<a href="${ctx}/Login?action=toLogin"  style="color:#ff4e00;">登录</a>&nbsp;<a href="${ctx}/Register?action=toRegister" style="color:#ff4e00;">免费注册</a>&nbsp;&nbsp;</span>
          </c:if>
          <c:if test="${sessionScope.loginUser!=null}">
            <span class="fl"><a href="${ctx}/Admin/user?action=index">${sessionScope.loginUser.userName}</a>&nbsp;|&nbsp;<a href="${ctx}/Admin/order?action=index&userId=${sessionScope.loginUser.id}">我的订单</a>&nbsp;</span>
          </c:if>
           <c:if test="${sessionScope.loginUser!=null && sessionScope.loginUser.type==1}">
            <span class="fl">|&nbsp;<a href="${ctx}/Admin/product?action=index&userId=${sessionScope.loginUser.id}">后台管理</a>&nbsp;</span>
          </c:if>
           <c:if test="${sessionScope.loginUser!=null}">
             <span class="fl">|&nbsp;<a href="${ctx}/Login?action=loginOut" >注销</a></span>
          </c:if>
        </span>
  </div>
</div>
<div id="fade1" class="black_overlay"></div>
<div id="MyDiv1" class="white_content">
  <div class="white_d">
    <div class="notice_t">
      <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv_1('MyDiv1','fade1')"><img src="${ctx}/statics/images/close.gif" /></span>
    </div>
    <div class="notice_c">
      <table border="0" align="center" cellspacing="0" cellpadding="0">
        <tr valign="top">
          <td width="40"><img src="${ctx}/statics/images/suc.png"></td>
          <td>
            <span style="color:#3e3e3e; font-size:18px; font-weight:bold;" id="showMessage">操作成功</span><br />
          </td>
        </tr>
      </table>
    </div>
  </div>
</div>
<link type="text/css" rel="stylesheet" href="${ctx}/statics/css/style.css"/>
<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/jquery.bxslider_e88acd1b.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/iban.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/fban.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/f_ban.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/mban.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/bban.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/hban.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/tban.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll_1.js"></script>

<script type="text/javascript" src="${ctx}/statics/js/register/register.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/statics/css/ShopShow.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/statics/css/MagicZoom.css" />
<script type="text/javascript" src="${ctx}/statics/js/common/MagicZoom.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/num.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/p_tab.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/shade.js"></script>

