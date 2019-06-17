<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
    <c:if test="${recentProducts!=null}">
        <div class="l_history">
        <div class="fav_t">我的收藏</div>
        <c:forEach items="${recentProducts}" var="temp">
        <ul>
            <li>
                <div class="img">
                    <a href="${ctx}/Product?action=queryProductDeatil&id=${temp.id}">
                        <img src="http://47.94.101.75/images/${temp.fileName}" width="185" height="162"/>
                    </a>
                </div>
                <div class="name">
                    <a href="${ctx}/Product?action=queryProductDeatil&id=${temp.id}">${temp.name}</a>
                </div>
                <div class="price">
                    <font>￥<span>${temp.price}</span></font> &nbsp;
                </div>
            </li>
        </ul>
    </c:forEach>
    </c:if>
</div>