
function refreshCode()
{
    var t = (new Date()).getTime();
    var code = "generateCaptcha?k=" + t;
    $("#img").prop("src", code);

}
//登录的方法
function login(){
    var loginName=$("#loginName").val();
    var password=$("#password").val();
    var captcha=$("#captcha").val();
    $.ajax({
        url:contextPath+"/Login",
        method:"post",
        data:{captcha:captcha,loginName:loginName,password:password,action:"login"},
        success:function(jsonStr){
           var result=eval("("+jsonStr+")");


            if(result.status==1)
            {
                //进入主页
                console.log("登录成功");
                window.location.href="/Home?action=index";

            }
             else if (result.status==2)
            {
                $("#errorMsg").text("账号有误");
                refreshCode();

            }

            else if(result.status==3)
            {
                //验证码错误
                $("#errorMsg").text("验证码错误");

                refreshCode();

            }
            else if(result.status==4)
            {
                //验证码错误
                $("#errorMsg").text("输入存在违法字符串");

                   refreshCode();

            }else if(result==5)
            {
                showMessage(result.message);
                window.location.href="/";
            }

            else{
                showMessage(result.message)
            }



        }
    })
}