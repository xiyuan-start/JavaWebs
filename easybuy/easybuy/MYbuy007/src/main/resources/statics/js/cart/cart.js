
/**
 * 商品详情页添加到购物车
 */
function addCart(){
    var entityId=$("input[name='entityId']").val();
    var quantity=$("input[name='quantity']").val();
    //添加到购物车
    addCartByParam(entityId,quantity);
}
/**
 * 商品列表页添加到购物车
 * @param entityId
 * @param quantity
 */
function addCartByParam(entityId,quantity){
    //添加到购物车
    $.ajax({
        url: contextPath + "/Cart",
        method: "post",
        data: {
            action: "add",
            entityId: entityId,
            quantity: quantity
        },
        success: function (jsonStr) {
            var result=eval("("+jsonStr+")");

             //状态判断
            console.log(result.status);
             if (result.status == 1) {
            	showMessage("操作成功");
            	 refreshCart();
            	// window.location.reload();
             }else if (result.status==2)
             {
                 showMessage("发生异常");


             }else if(result.status==3)
             {
                 showMessage("请先进行登录");
                 window.location.href=contextPath+"/Login?action=toLogin"

             }

             else{
            	 showMessage(result.message);
             }
        }
    } )
}
/**
 * 刷新购物车 searchBar DIV
 */
function refreshCart(){
    $.ajax({
        url: contextPath + "/Cart",
        method: "post",
        data: {
            action: "refreshCart"
        },
        success: function (jsonStr) {
            $("#searchBar").html(jsonStr);
             window.location.reload();
        }
    })
}
/**
 * 结算 加载购物车列表
 */
function settlement1(){
    $.ajax({
        url: contextPath + "/Cart",
        method: "post",
        data: {
            action: "settlement1"
        },
        success: function (jsonStr) {
        	//refreshCart();
            $("#settlement").html(jsonStr);
        }
    });
}
/**
 * 结算 形成预备订单
 */
function settlement2(){
    $.ajax({
        url: contextPath + "/Cart",
        method: "post",
        data: {
            action: "settlement2"
        },
        success: function (jsonStr) {
            $("#settlement").html(jsonStr);
        }
    });
}
/**
 * 结算 订单支付提醒
 */
function settlement3(){
    //判断地址
    var addressId=$("input[name='selectAddress']:checked").val();
    var newAddress=$("input[name='address']").val();
    var newRemark=$("input[name='remark']").val();
    if(addressId=="" || addressId==null){
        showMessage("请选择收货地址");
        return;
    }else if(addressId=="-1"){
        if(newAddress=="" || newAddress==null){
            showMessage("请填写新的收货地址");
            return;
        }else if(newAddress.length<=2 || newAddress.length>=50){
            showMessage("收货地址为2-50个字符");
            return;
        }
    }
    $.ajax({
        url: contextPath + "/Cart",
        method: "post",
        data: {
            action: "settlement3",
            addressId:addressId,
            newAddress:newAddress,
            newRemark:newRemark
        },
        success: function (jsonStr) {
        	if(jsonStr.substr(0,1)=="{"){
        		var result = eval("(" + jsonStr + ")");
        		showMessage(result.message);
        	}else{
        		$("#settlement").html(jsonStr);
        	}
        }
    });
}
/**
 * 商品详情页的 数量加
 * @param obj
 * @param entityId
 */
function addQuantity(obj,entityId,stock){
	var quantity=Number(getPCount(jq(obj)))+1;
	if(stock<quantity){
		showMessage("商品数量不足");
		return;
	}
    modifyCart(entityId,quantity,jq(obj));
}
/**
 * 减去 数量减
 * @param obj
 * @param entityId
 */
function subQuantity(obj,entityId){
    var quantity=getPCount(jq(obj))-1;
    if(quantity==0) quantity=1;
    modifyCart(entityId,quantity,jq(obj));
}
/**
 * 修改购物车列表
 * @param entityId
 * @param quantity
 */
function modifyCart(entityId,quantity,obj){
	$.ajax({
        url: contextPath + "/Cart",
        method: "post",
        data: {
            action: "modCart",
            entityId:entityId,
            quantity:quantity
        },
        success: function (jsonStr) {
        	var result = eval("(" + jsonStr + ")");

            //状态判断
            if (result.status == 1) {
                refreshCart();
                obj.parent().find(".car_ipt").val(quantity);
                settlement1();
            }else{
           	 	showMessage(result.message);
            }
        }
    });
}
/**
 * 清空购物车
 */
function clearCart(){
    $.ajax({
        url: contextPath + "/Cart",
        method: "post",
        data: {
            action: "clearCart"
        },
        success: function (jsonStr) {
            $("#settlement").html(jsonStr);
            showMessage("操作成功");
        }
    });
}
/**
 * 删除购物车
 * @param entityId
 */
function removeCart(entityId){
	$.ajax({
        url: contextPath + "/Cart",
        method: "post",
        data: {
            action: "modCart",
            entityId:entityId,
            quantity:0
        },
        success: function (jsonStr) {
        	var result = eval("(" + jsonStr + ")");
            //状态判断
            if (result.status == 1) {
            	settlement1();
            }else{
           	 	showMessage(result.message);
            }
        }
    });
}
/**
 * 喜欢的列表
 */
function favoriteList(){
    $.ajax({
        url: contextPath + "/Favorite",
        method: "post",
        data: {
            action: "toFavoriteList"
        },
        success: function (jsonStr) {
            $("#favoriteList").html(jsonStr);
        }
    });
}
/**
 * 添加到收藏列表
 * @param productId
 */
function addFavorite(productId){
    $.ajax({
        url: contextPath + "/Favorite",
        method: "post",
        data: {
            action: "addFavorite",
            id:productId
        },
        success: function (jsonStr) {

        }
    });
}