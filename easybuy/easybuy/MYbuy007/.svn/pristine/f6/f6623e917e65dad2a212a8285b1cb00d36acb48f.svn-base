<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<div class="nav">
    <div class="nav_t">全部商品分类</div>
    <div class="leftNav none" style="display: none;">
        <ul id="leftMenu">
            <c:forEach items="${productCategoryVoList}" var="temp">
                <li>
                    <div class="fj">
                        <span class="n_img"><span></span>
                            <img src="${ctx}/statics/images/${temp.productCategory.iconClass}"/></span>
                        <span class="fl">${temp.productCategory.name}</span>
                    </div>
                    <div class="zj">
                        <div class="zj_l">
                            <c:forEach items="${temp.productCategoryVoList}" var="vo">
                                <div class="zj_l_c">
                                    <h2>
                                        <a href="${ctx}/Product?action=queryProductList&category=${vo.productCategory.id}&level=2">${vo.productCategory.name}</a>
                                    </h2>
                                    <c:forEach items="${vo.productCategoryVoList}" var="vo2">
                                        <a href="${ctx}/Product?action=queryProductList&category=${vo2.productCategory.id}&level=3">${vo2.productCategory.name}</a>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<ul class="menu_r">
    <li><a href="${ctx}/Home?action=index">首页</a></li>
    <c:forEach items="${productCategoryVoList}" var="temp">
        <li><a href="${ctx}/Product?action=queryProductList&level=1&category=${temp.productCategory.id}">${temp.productCategory.name}</a></li>
    </c:forEach>
</ul>
<div class="m_ad">中秋送好礼！</div>