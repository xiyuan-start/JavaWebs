<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";

    function Queryproduct() {
        var QueryString=$("input[name='queryString']").val();
        console.log(QueryString);
        var Userid=<%=session.getAttribute("Userid")%>
        if(Userid==null)
        {
            var Userid=0;
        }
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.overrideMimeType("text/html;charset=gb2312");
        var url = "http://192.168.137.100/log.gif?UserId="+Userid+"&action=Queryproduct&remark="+"QueryString:"+QueryString;
        xmlhttp.open("GET", url, true); //第三个参数是同步异步,主线程只能异步
        xmlhttp.send();
    }
</script>
<div class="top">
    <div class="logo">
        <a href="${ctx}/Home?action=index"><img src="${ctx}/statics/images/logo.png"></a>
    </div>
    <div class="search">
        <form action="${ctx}/Queryproduct" method="post">
            <input type="text" value="${queryString}" name="queryString" class="s_ipt">
            <input type="submit" value="搜索" class="s_btn" onclick="Queryproduct()">
        </form>
        <!--推荐最热商品-->
    </div>
    <div class="i_car">
        <div class="car_t">
            购物车 [
            <span>
                <c:if test="${sessionScope.cart!=null && sessionScope.cart.items.size()>0}">
                    ${sessionScope.cart.items.size()}
                </c:if>
                <c:if test="${sessionScope.cart==null || sessionScope.cart.items.size()<=0}">
                    空
                </c:if>
            </span>]
        </div>
        <div class="car_bg">
            <!--Begin 购物车未登录 Begin-->
            <c:if test="${sessionScope.loginUser==null}">
                <div class="un_login">还未登录！<a href="${ctx}/Login?action=toLogin" style="color:#ff4e00;">马上登录</a></div>
            </c:if>
            <c:if test="${sessionScope.loginUser!=null}">
                <div class="un_login">我的购物车</div>
            </c:if>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
                <c:forEach items="${sessionScope.cart.items}" var="temp">
                    <li>
                        <div class="img"><a href="${ctx}/Product?action=queryProductDeatil&id=${temp.product.id}"><img src="http://47.94.101.75/images/${temp.product.fileName}" width="58" height="58" /></a></div>
                        <div class="name"><a href="${ctx}/Product?action=queryProductDeatil&id=${temp.product.id}">${temp.product.name}</a></div>
                        <div class="price"><font color="#ff4e00">￥${temp.product.price}</font> X${temp.quantity}</div>
                    </li>
                </c:forEach>
            </ul>
            <div class="price_sum">共计&nbsp;<font color="#ff4e00">￥</font><span>${sessionScope.cart.sum}</span></div>
            <c:if test="${sessionScope.loginUser==null}">
                <div class="price_a"><a href="${ctx}/Login?action=toLogin">去登录</a></div>
            </c:if>
            <c:if test="${sessionScope.loginUser!=null}">
                <div class="price_a"><a href="${ctx}/Cart?action=toSettlement">去结算</a></div>
            </c:if>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>
